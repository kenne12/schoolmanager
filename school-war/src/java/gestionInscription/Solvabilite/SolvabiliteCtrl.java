/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionInscription.Solvabilite;

import entities.Annee;
import entities.Catanneeprix;
import entities.Classe;
import entities.Classecategorie;
import entities.Eleve;
import entities.Eleveanneeclasse;
import entities.PensionCumulee;
import entities.Pension;
import entities.Tranche;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.PrintUtils;
import utils.SessionMBean;
import utils.Solvabilite;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class SolvabiliteCtrl extends AbstractSolvabiliteCtrl implements SolvabiliteInterfaceCtrl, Serializable {

    @PostConstruct
    private void initPension() {
        pension = new Pension();
        annee = new Annee();
        tranche = new Tranche();
        eleve = new Eleve();
        categorie = new Classecategorie();
        catAnneePrix = new Catanneeprix();
        loadCategorie();
    }

    public void changeMode() {

    }

    @Override
    public void globallementSolvable() {

        List<Eleve> eleves = eleveFacade.findByEtat(true);
        Annee an = anneeFacade.findByEtatSingle(true);

        //on parcourt le tableau des eleve actifs;
        for (int i = 0; i < eleves.size(); i++) {
            eleve = new Eleve();
            eleve = eleves.get(i);

            System.err.println("id" + eleve.getIdeleve());

            //on recupere la classe de l'eleve a l'année active
            Eleveanneeclasse eleveAnneeClasse = eleveAnneeFacade.getEleveAnneeClaseByAnneClasse(eleve.getIdeleve(), an.getIdannee());

            if (eleveAnneeClasse != null) {
                System.err.println("l'eleve" + eleve.getNom() + "a pour classe id" + eleveAnneeClasse.getIdclasse().getIdclasse());
                //System.err.println("l'eleve a une classe"+eleves.get(i).getMatricule());
                Classecategorie classeCategorie;
                classeCategorie = classeCategorieFacade.getClasseCategorieByIdClasse(eleveAnneeClasse.getIdclasse().getIdclasse());
                if (classeCategorie != null) {
                    System.err.println("la classe" + eleveAnneeClasse.getIdclasse().getNom() + "a pour categorie" + classeCategorie.getIdcategorie().getNom());
                    Catanneeprix categorieAnneePrix;
                    categorieAnneePrix = catAnneePrixFacade.typeTrangeGetCatAnnee(an.getIdannee(), classeCategorie.getIdcategorie().getIdcategorie(), true);
                    if (categorieAnneePrix != null) {
                        int montantByCategorie = categorieAnneePrix.getPrix();
                        System.err.println("la categorie" + classeCategorie.getIdcategorie().getNom() + "a pour montant" + montantByCategorie);

                        List<Pension> resultPensions;
                        resultPensions = pensionFacade.getPensionByAnneeEleve(an.getIdannee(), eleve.getIdeleve());
                        if (resultPensions != null) {
                            int montantParEleve = 0;
                            for (int c = 0; c < resultPensions.size(); c++) {
                                montantParEleve += resultPensions.get(c).getMontant();
                            }
                            System.err.println("l'eleve " + eleve.getNom() + " a payé " + montantParEleve + "/n/n");
                            System.err.println("lélève " + eleve.getNom() + " a payé" + resultPensions.size());
                            if (montantParEleve < montantByCategorie) {
                                eleveInslvables.add(eleves.get(i));
                                System.err.println("id eleve " + eleves.get(i).getIdeleve());
                                System.err.println("id anne" + an.getIdannee());
                                System.err.println("eleve annee classe id classe" + eleveAnneeClasse.getIdclasse().getIdclasse());
                                System.err.println("classe categorie" + eleveAnneeClasse.getIdclasse().getIdclasse());
                                System.err.println("matricule" + eleves.get(i).getMatricule());
                                System.err.println("montant " + montantByCategorie);
                                System.err.println("mari t'es pas ici");
                                System.err.println("montant de yankam" + montantParEleve);
                                System.err.println("année" + an.getCode());
                                System.err.println("classe categorie 2 " + classeCategorie.getIdcategorie().getIdcategorie());
                                System.err.println("classe categorie id classe " + classeCategorie.getIdclasse().getIdclasse());
                            } else {
                                eleveSolvables.add(eleves.get(i));
                            }
                        } else {
                            eleveInslvables.add(eleves.get(i));
                            System.err.println("l'eleve " + eleve.getNom() + " n a rien payé");
                        }
                    } else {
                        System.err.println("la categorie " + classeCategorie.getIdcategorie().getNom() + " n a pas de prix");
                        //a ce niveau la classe de l'eleve n a pas de prix au cour de l'année
                        //une variable qui porte les categorie non cuplée au prix pour une année
                    }
                } else {
                    System.err.println("la classe de l'eleve" + eleves.get(i).getMatricule() + "n a pas de categorie");
                }
            } else {
                elevesWithoutClass.add(eleves.get(i));
                System.err.println("l eleve n a pas de classe " + eleve.getNom());
            }
        }
    }

    @Override
    public void modifier() {

    }

    @Override
    public void supprimer() {

    }

    @Override
    public void imprimerInsolvablePdf() {
        try {
            if (!solvabilites.isEmpty()) {
                if (showTranche) {
                    fichier_insolvable = "liste_des_insolvables" + "_" + tranche.getNom() + "_" + categorie.getIdclasse().getNom() + ".pdf";
                    PrintUtils.printInsolventStudent(this.getAnnee(), solvabilites, categorie.getIdclasse(), tranche);
                } else {
                    fichier_insolvable = "liste_des_insolvables_" + categorie.getIdclasse().getNom() + ".pdf";
                    PrintUtils.printInsolventStudent(this.getAnnee(), solvabilites, categorie.getIdclasse(), catAnneePrix);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void imprimerSolvablePdf() {
        try {
            if (!solvabilites1.isEmpty()) {
                if (showTranche) {
                    fichier_solvable = "liste_des_solvables" + "_" + tranche.getNom() + "_" + categorie.getIdclasse().getNom() + ".pdf";
                    PrintUtils.printSolventStudent(this.getAnnee(), solvabilites1, categorie.getIdclasse(), tranche);
                    System.err.println("" + fichier_solvable);
                } else {
                    fichier_solvable = "liste_des_solvables_" + categorie.getIdclasse().getNom() + ".pdf";
                    PrintUtils.printSolventStudent(this.getAnnee(), solvabilites1, categorie.getIdclasse(), catAnneePrix);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void imprimerPensionHtml() {

    }

    public void eleveOnchange() {
        JsfUtil.addSuccessMessage("cool");
    }

    public void handleClasseChange() {
        try {
            if (annee != null) {
                if (categorie.getId() != null) {
                    categorie = classeCategorieFacade.find(categorie.getId());
                    tranches = trancheFacade.getByAnneeCategorie(this.getAnnee().getIdannee(), categorie.getIdcategorie().getIdcategorie(), true);
                    //tranches = trancheFacade.getTypePensionByAnneeCategorie(this.getAnnee().getIdannee(), categorie.getIdcategorie().getIdcategorie(), true);
                } else {
                    tranches.clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchInsolvable() {
        solvabilites.clear();
        solvabilites1.clear();
        try {
            if (annee != null) {
                if (showTranche) {
                    // ici on cherche par tanche
                    tranche = trancheFacade.find(tranche.getIdtranche());
                    if (categorie.getId() != null) {
                        if (tranche.getIdtranche() != null) {
                            categorie = classeCategorieFacade.find(categorie.getId());
                            tranche = trancheFacade.find(tranche.getIdtranche());
                            List<Eleveanneeclasse> eleveAnnee = eleveAnneeFacade.findByAnneeClasse(this.getAnnee().getIdannee(), categorie.getIdclasse().getIdclasse());
                            if (!eleveAnnee.isEmpty()) {

                                catAnneePrix = catAnneePrixFacade.typeTrangeGetCatAnnee(this.getAnnee().getIdannee(), categorie.getIdcategorie().getIdcategorie(), true);
                                if (catAnneePrix != null) {

                                    int montantByEleve = 0;
                                    for (Eleveanneeclasse e : eleveAnnee) {

                                        PensionCumulee pc = pensionCumuleeFacadeLocal.findByEleve(e.getEleve().getIdeleve(), this.getAnnee().getIdannee());

                                        pensions = pensionFacade.getPensionByAnneeEleve(this.getAnnee().getIdannee(), e.getEleve().getIdeleve());

                                        if (!pensions.isEmpty()) {
                                            for (Pension t : pensions) {
                                                montantByEleve += t.getMontantPaye();
                                            }

                                            if (montantByEleve >= catAnneePrix.getPrix()) {
                                                //elève sovable
                                                solvabilites1.add(new Solvabilite(e.getEleve(), tranche.getPrix(), 0));
                                            } else {
                                                // a moitié solvbale
                                                List<Pension> pensionByTypePension = pensionFacade.getPensionByEleveTranche(e.getEleve().getIdeleve(), tranche.getIdtranche());
                                                if (!pensionByTypePension.isEmpty()) {
                                                    int montantEleveByPension = 0;
                                                    for (Pension t1 : pensionByTypePension) {
                                                        montantEleveByPension += t1.getMontant();
                                                    }
                                                    if (montantEleveByPension >= tranche.getPrix()) {
                                                        // eleve solvalbe pour la pension
                                                        solvabilites1.add(new Solvabilite(e.getEleve(), montantEleveByPension, 0));
                                                    } else {
                                                        // eleve a moitié solvalbele pour la pension
                                                        if (new Date().after(tranche.getDatefin())) {
                                                            solvabilites.add(new Solvabilite(e.getEleve(), montantEleveByPension, tranche.getPrix() - montantEleveByPension));
                                                        } else {
                                                            solvabilites1.add(new Solvabilite(e.getEleve(), montantEleveByPension, tranche.getPrix() - montantEleveByPension));
                                                        }
                                                    }
                                                } else {
                                                    //eleve insolvale pour la pension
                                                    if (new Date().after(tranche.getDatefin())) {
                                                        solvabilites.add(new Solvabilite(e.getEleve(), 0, tranche.getPrix()));
                                                    } else {
                                                        solvabilites1.add(new Solvabilite(e.getEleve(), 0, tranche.getPrix()));
                                                    }
                                                }
                                            }
                                        } else {
                                            //eleve insolvable
                                            if (new Date().after(tranche.getDatefin())) {
                                                solvabilites.add(new Solvabilite(e.getEleve(), 0, tranche.getPrix()));
                                            } else {
                                                solvabilites1.add(new Solvabilite(e.getEleve(), 0, tranche.getPrix()));
                                            }
                                        }
                                    }
                                } else {
                                    JsfUtil.addErrorMessage("Le payement pour cette catégorie n'est pas encore planifié");
                                }
                            } else {
                                JsfUtil.addErrorMessage("Aucun élève dans la classe selectionnée");
                            }
                        }
                    }
                } else {
                    // ici on cherche glaballement
                    categorie = classeCategorieFacade.find(categorie.getId());
                    List<Eleveanneeclasse> eleveAnnee = eleveAnneeFacade.findByAnneeClasse(this.getAnnee().getIdannee(), categorie.getIdclasse().getIdclasse());

                    if (!eleveAnnee.isEmpty()) {

                        catAnneePrix = catAnneePrixFacade.typeTrangeGetCatAnnee(this.getAnnee().getIdannee(), categorie.getIdcategorie().getIdcategorie(), true);
                        if (catAnneePrix != null) {

                            for (Eleveanneeclasse e : eleveAnnee) {
                                pensions = pensionFacade.getPensionByAnneeEleve(this.getAnnee().getIdannee(), e.getEleve().getIdeleve());
                                int montantByEleve = 0;
                                if (!pensions.isEmpty()) {
                                    for (Pension t : pensions) {
                                        montantByEleve += t.getMontantPaye();
                                    }
                                    if (montantByEleve >= catAnneePrix.getPrix()) {
                                        //eleve solovable
                                        solvabilites1.add(new Solvabilite(e.getEleve(), montantByEleve, 0));
                                    } else {
                                        solvabilites.add(new Solvabilite(e.getEleve(), montantByEleve, catAnneePrix.getPrix() - montantByEleve));
                                    }
                                } else {
                                    solvabilites.add(new Solvabilite(e.getEleve(), 0, catAnneePrix.getPrix()));
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadCategorie() {
        try {
            if (SessionMBean.getUserAccount() != null) {
                List<Classe> classeTemp = classeFacadeLocal.findByEtaBlissement(SessionMBean.getUserAccount().getEtablissement().getId());
                if (!classeTemp.isEmpty()) {

                    for (Classe c : classeTemp) {
                        Classecategorie categorieTemp = classeCategorieFacade.getClasseCategorieByIdClasse(c.getIdclasse());
                        if (categorieTemp != null) {
                            System.err.println("trouvé : " + c.getNom());
                            categories.add(categorieTemp);
                        } else {
                            System.err.println("pas trouve : " + c.getNom());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void imprimerTrancheHtml() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
