/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionInscription.Inscription;

import entities.Annee;
import entities.Catanneeprix;
import entities.Classecategorie;
import entities.Compte;
import entities.Eleveanneeclasse;
import entities.Operation;
import entities.PensionCumulee;
import entities.Pension;
import entities.PensionSave;
import entities.Tranche;
import entities.Typecompte;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@SessionScoped
public class InscriptionCtrl extends AbstractInscriptionCtrl implements InterfaceInterfaceCtrl, Serializable {

    @PostConstruct
    private void initPension() {
        pension = new Pension();
        selectedPension = new Pension();
        tranche = new Tranche();
        categorie = new Classecategorie();
        catAnneePrix = new Catanneeprix();
        eleveanneeclasse = new Eleveanneeclasse();
        pensionSave = new PensionSave();
        annee = SessionMBean.getYear();
        pension = new Pension();
        this.loadEleves();
        this.calculMontantApayer();

        System.err.println(Utilitaires.codeInscription(SessionMBean.getSchool(), SessionMBean.getYear()));
    }

    public void prepareCreate() {
        tranches.clear();
        pensionApayer.clear();
        eleveanneeclasse = new Eleveanneeclasse();
        operation = new Operation();
        compte = new Compte();
        typecompte = new Typecompte();
        this.calculMontantApayer();
    }

    @Override
    public void save() {
        try {
            if (!pensionApayer.isEmpty()) {

                // ce booleen permet de verifier si des montants sont saisie dans le tableau
                boolean drapau = false;

                //somme des montant a payer 
                Integer montant = 0;

                for (Pension t1 : pensionApayer) {
                    if (t1.getMontant() != 0 && t1.getMontant() != null) {
                        montant += t1.getMontant();
                        drapau = true;
                    }
                }

                if (drapau) {

                    //code de l'operation
                    String code = Utilitaires.codeInscription(SessionMBean.getSchool(), SessionMBean.getYear());
                    code += "-" + eleveanneeclasse.getIdannee().getCodefin().toString();

                    Long nextPayement = pensionSaveFacadeLocal.nextVal(eleveanneeclasse.getIdannee());
                    if (nextPayement < 10) {
                        code += "-000000" + nextPayement.toString();
                    } else if (nextPayement >= 10 && nextPayement < 100) {
                        code += "-00000" + nextPayement.toString();
                    } else if (nextPayement >= 100 && nextPayement < 1000) {
                        code += "-0000" + nextPayement.toString();
                    } else if (nextPayement >= 1000 && nextPayement < 10000) {
                        code += "-000" + nextPayement.toString();
                    } else if (nextPayement >= 10000 && nextPayement < 100000) {
                        code += "-00" + nextPayement.toString();
                    } else if (nextPayement >= 100000 && nextPayement < 1000000) {
                        code += "-0" + nextPayement.toString();
                    } else {
                        code += "-" + nextPayement.toString();
                    }

                    // on enregistre l'operation à effectuer
                    operation.setIdoperation(operationFacadeLocal.nextVal());
                    operation.setCredit(montant.doubleValue());
                    operation.setDebit(0D);
                    operation.setIdetablissement(SessionMBean.getSchool());
                    operation.setIdtypeoperation(typeoperationFacadeLocal.find(1));
                    operation.setIdcompte(compte);
                    operation.setLibelle("Paiement des frais de Scolarité : Elève -> " + eleveanneeclasse.getEleve().getNom() + " " + eleveanneeclasse.getEleve().getPrenom() + " [" + eleveanneeclasse.getEleve().getMatricule() + "]");
                    operation.setDateoperation(new Date());
                    operation.setHeure(new Date());
                    operation.setVersement(true);
                    operation.setIdannee(SessionMBean.getYear());
                    operationFacadeLocal.create(operation);

                    // on met à jour le compte comptable
                    compte.setCredit(compte.getCredit() + montant);
                    compteFacadeLocals.edit(compte);

                    // on met a jour le grand compte
                    compte.getIdtypecompte().setCredit(compte.getIdtypecompte().getCredit() + montant);
                    typecompteFacadeLocal.edit(compte.getIdtypecompte());

                    // cette operation sert a guise de reconstituer le réçu 
                    pensionSave.setIdpensionsave(pensionSaveFacadeLocal.nextVal());
                    pensionSave.setCode(code);
                    pensionSave.setDatepayement(new Date());
                    pensionSave.setEleve(eleveanneeclasse.getEleve());
                    pensionSave.setAnnee(eleveanneeclasse.getIdannee());
                    pensionSave.setIdoperation(operation);
                    pensionSave.setMontant(montant);
                    pensionSaveFacadeLocal.create(pensionSave);

                    // a ce niveau on commence à enregistrer les operations par tranche
                    pension.setEleve(eleveanneeclasse.getEleve());
                    pension.setIdannee(eleveanneeclasse.getIdannee());

                    for (Pension t : pensionApayer) {
                        if (t.getMontant() != 0 && t.getMontant() != null) {

                            t.setIdpension(pensionFacadeLocal.nextVal());
                            t.setEtat(false);
                            t.setPensionsave(pensionSave);
                            if (t.getMontant() > t.getReste()) {
                                t.setMontant(t.getReste());
                            }
                            t.setMontantPaye(t.getMontantPaye() + t.getMontant());
                            t.setReste(t.getIdtranche().getPrix() - t.getMontantPaye());
                            if (t.getReste() <= 0) {
                                t.setReste(0);
                                t.setEtat(true);
                            }
                            pensionFacadeLocal.create(t);
                        }
                    }

                    // ici on met à jour la pension cumulée payée par l'eleve
                    PensionCumulee pensionCumulee = pensionCumuleeFacadeLocal.findByEleve(eleveanneeclasse.getEleve().getIdeleve(), eleveanneeclasse.getIdannee().getIdannee());
                    if (pensionCumulee != null) {

                        int reste = calculMontantApayer();
                        if (reste != 0) {
                            pensionCumulee.setReste(reste - (montant + pensionCumulee.getMontantCumule()));
                        }
                        pensionCumulee.setMontantCumule(pensionCumulee.getMontantCumule() + montant);

                        pensionCumuleeFacadeLocal.edit(pensionCumulee);
                    } else {
                        pensionCumulee = new PensionCumulee();
                        pensionCumulee.setId(pensionCumuleeFacadeLocal.nextVal());
                        pensionCumulee.setAnnee(eleveanneeclasse.getIdannee());
                        pensionCumulee.setEleve(eleveanneeclasse.getEleve());
                        int reste = calculMontantApayer();
                        if (reste != 0) {
                            pensionCumulee.setReste(reste - montant);
                        }
                        pensionCumulee.setMontantCumule(montant);

                        pensionCumuleeFacadeLocal.create(pensionCumulee);
                    }

                    System.err.println(Utilitaires.codeInscription(SessionMBean.getSchool(), SessionMBean.getYear()));
                    Utilitaires.saveOperation(traceurFacadeLocal, "Paiement des frais de Scolarité : Elève -> " + eleveanneeclasse.getEleve().getNom() + " " + eleveanneeclasse.getEleve().getPrenom() + " [" + eleveanneeclasse.getEleve().getMatricule() + "] ; Montant : " + montant, SessionMBean.getUserAccount(), annee);

                    JsfUtil.addSuccessMessage("Transaction réussie");

                } else {
                    JsfUtil.addErrorMessage("Aucun montant saisi");
                }
            } else {
                JsfUtil.addErrorMessage("Le tableau est vide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit() {

    }

    @Override
    public void delete() {
        try {
            if (pensionSave != null) {
                pensionSave.getPensionList();

                for (Pension p : pensionFacadeLocal.findByPensionsave(pensionSave)) {
                    pensionFacadeLocal.remove(p);
                }

                PensionCumulee p = pensionCumuleeFacadeLocal.findByEleve(pensionSave.getEleve().getIdeleve(), annee.getIdannee());
                if (p != null) {
                    p.setMontantCumule(p.getMontantCumule() - pensionSave.getMontant());
                    //p.setReste(p.getReste() - pensionSave.getMontant());
                    pensionCumuleeFacadeLocal.edit(p);
                }

                pensionSave.getIdoperation().getIdcompte().setCredit(pensionSave.getIdoperation().getIdcompte().getCredit() - pensionSave.getMontant());
                compteFacadeLocals.edit(pensionSave.getIdoperation().getIdcompte());

                pensionSave.getIdoperation().getIdcompte().getIdtypecompte().setCredit(pensionSave.getIdoperation().getIdcompte().getIdtypecompte().getCredit() - pensionSave.getMontant());
                typecompteFacadeLocal.edit(pensionSave.getIdoperation().getIdcompte().getIdtypecompte());

                pensionSaveFacadeLocal.remove(pensionSave);
                operationFacadeLocal.remove(pensionSave.getIdoperation());

                Utilitaires.saveOperation(traceurFacadeLocal, "Annulation de l'operation ->" + pensionSave.getIdoperation().getLibelle() + " ; Montant : " + pensionSave.getMontant(), SessionMBean.getUserAccount(), annee);
                JsfUtil.addSuccessMessage("Transaction annulée avec succès");

            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void printPdf() {

    }

    @Override
    public void printHtml() {

    }

    public void eleveOnchange() {

    }

    // methode qui affiche les eleve en fonction de la classe selectionnée
    public void handleEleveChange() {
        if (classe.getIdclasse() != null) {
            Annee an = anneeFacade.findByEtatSingle(true);
            List<Eleveanneeclasse> eleveClasses = eleveAnneeFacade.findByAnneeClasse(an.getIdannee(), classe.getIdclasse());
            if (!eleveClasses.isEmpty()) {
                if (!getEleveActifs().isEmpty()) {
                    eleveActifs.clear();
                }
                for (int i = 0; i < eleveClasses.size(); i++) {
                    eleveActifs.add(eleveClasses.get(i).getEleve());
                }
            } else {
                eleveActifs.clear();
            }
        } else {
            eleveActifs.clear();
        }
    }

    public void updateAll() {
        pensionInsolvables.clear();
        pensionApayer.clear();
        try {
            if (eleveanneeclasse.getId() != null) {
                eleveanneeclasse = eleveAnneeFacade.find(eleveanneeclasse.getId());
                Classecategorie classecategorie = classeCategorieFacade.getClasseCategorieByIdClasse(eleveanneeclasse.getIdclasse().getIdclasse());
                tranches = trancheFacade.getByAnneeCategorie(eleveanneeclasse.getIdannee().getIdannee(), classecategorie.getIdcategorie().getIdcategorie(), true);
                //tranches = trancheFacade.getPensionByAnneeCategorie(eleveanneeclasse.getIdannee().getIdannee(), classecategorie.getIdcategorie().getIdcategorie(), true);
                if (!tranches.isEmpty()) {
                    PensionCumulee pensionCumulee = pensionCumuleeFacadeLocal.findByEleve(eleveanneeclasse.getEleve().getIdeleve(), eleveanneeclasse.getIdannee().getIdannee());
                    if (pensionCumulee != null) {
                        if (pensionCumulee.getMontantCumule() >= calculMontantApayer()) {
                            JsfUtil.addSuccessMessage("L'eleve s'est deja acquité de ses frais exigible et par consequent est solvable");
                        } else {
                            for (Tranche t : tranches) {

                                if (pensionFacadeLocal.getPensionByEleveTranche(eleveanneeclasse.getEleve().getIdeleve(), t.getIdtranche()).isEmpty()) {
                                    pension = new Pension();
                                    pension.setIdtranche(t);
                                    pension.setDatepayement(new Date());
                                    pension.setIdannee(eleveanneeclasse.getIdannee());
                                    pension.setEleve(eleveanneeclasse.getEleve());
                                    pension.setMontantPaye(0);
                                    pension.setMontant(0);
                                    pension.setReste(t.getPrix());
                                    pensionApayer.add(pension);
                                } else {
                                    int somme = 0;
                                    for (Pension t1 : pensionFacadeLocal.getPensionByEleveTranche(eleveanneeclasse.getEleve().getIdeleve(), t.getIdtranche())) {
                                        somme += t1.getMontant();
                                    }
                                    if (somme < t.getPrix()) {
                                        //ici il est a moitié solvable
                                        pension = new Pension();
                                        pension.setIdtranche(t);
                                        pension.setDatepayement(new Date());
                                        pension.setMontant(0);
                                        pension.setIdannee(eleveanneeclasse.getIdannee());
                                        pension.setEleve(eleveanneeclasse.getEleve());
                                        pension.setMontantPaye(somme);
                                        pension.setReste(t.getPrix() - somme);
                                        pensionApayer.add(pension);
                                    }
                                }
                            }
                        }
                    } else {
                        for (Tranche t : tranches) {
                            pension = new Pension();
                            pension.setIdtranche(t);
                            pension.setDatepayement(new Date());
                            pension.setIdannee(eleveanneeclasse.getIdannee());
                            pension.setEleve(eleveanneeclasse.getEleve());
                            pension.setMontantPaye(0);
                            pension.setMontant(0);
                            pension.setReste(t.getPrix());
                            pensionApayer.add(pension);
                        }
                    }
                } else {
                    JsfUtil.addErrorMessage("Le parametrage des frais exigible n'est pas encore fait");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.calculMontantApayer();
    }

    public void loadEleves() {
        try {
            if (SessionMBean.getYear() != null) {
                eleveanneeclasses = eleveAnneeFacade.get(SessionMBean.getYear().getIdannee(), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int calculMontantApayer() {
        if (tranches.isEmpty()) {
            return 0;
        } else {
            int montant = 0;
            for (Tranche t : tranches) {
                montant += t.getPrix();
            }
            return montant;
        }
    }

    public void updateCompte() {
        try {
            if (typecompte.getIdtypecompte() != null) {
                typecompte = typecompteFacadeLocal.find(typecompte.getIdtypecompte());
                comptes = compteFacadeLocals.find(typecompte);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initCompte() {
        try {
            compte = compteFacadeLocals.find(compte.getIdcompte());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
