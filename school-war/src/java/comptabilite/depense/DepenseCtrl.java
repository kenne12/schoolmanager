/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comptabilite.depense;

import entities.Compte;
import entities.Depense;
import entities.Operation;
import entities.Typecompte;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.Utilitaires;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class DepenseCtrl extends AbstractDepenseCtrl implements DepenseInterfaceCtrl, Serializable {

    /**
     * Creates a new instance of EtablissementCtrl
     */
    public DepenseCtrl() {

    }

    @PostConstruct
    private void init() {
        typecompte = new Typecompte();
        compte = new Compte();
        try {
            comptes = compteFacadeLocal.find(SessionMBean.getSchool());
            comptes2 = compteFacadeLocal.find(SessionMBean.getSchool());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareCreate() {
        mode = "Create";
        depense = new Depense();
        this.init();
        showFilter = false;
    }

    public void prepareEdit() {
        mode = "Edit";
        showFilter = true;
        try {
            if (depense != null) {
                if (depense.getIdoperation().getIdcompte() != null) {
                    compte = depense.getIdoperation().getIdcompte();
                    typecompte = compte.getIdtypecompte();
                    soldeDediteur = compte.getCredit() - compte.getDebit();
                }
                Operation operation = operationFacadeLocal.find(depense.getOperation2());
                compte1 = operation.getIdcompte();
                typecompte1 = compte1.getIdtypecompte();
                soldeCrediteur = compte1.getCredit() - compte1.getDebit();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCompteDebit() {
        try {
            if (typecompte.getIdtypecompte() != null) {
                typecompte = typecompteFacadeLocal.find(typecompte.getIdtypecompte());
                compte = new Compte();
                comptes = compteFacadeLocal.find(typecompte);
                soldeDediteur = 0.0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCompteCredit() {
        try {
            if (typecompte1.getIdtypecompte() != null) {
                typecompte1 = typecompteFacadeLocal.find(typecompte1.getIdtypecompte());
                comptes2 = compteFacadeLocal.find(typecompte1);
                compte1 = new Compte();
                soldeCrediteur = 0.0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        try {
            if (compte.getIdcompte() != null) {
                compte = compteFacadeLocal.find(compte.getIdcompte());
                soldeDediteur = compte.getCredit() - compte.getDebit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update2() {
        try {
            if (compte1.getIdcompte() != null) {
                compte1 = compteFacadeLocal.find(compte1.getIdcompte());
                soldeCrediteur = compte1.getCredit() - compte1.getDebit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveDepense() {
        try {

            if ("Create".equals(mode)) {

                // on verifie si le compte de débit est celui du crédit
                if (!compte.equals(compte1)) {

                    //on enregistre l'operation debiteur
                    operation.setIdoperation(operationFacadeLocal.nextVal());
                    operation.setIdcompte(compte);
                    operation.setIdetablissement(SessionMBean.getSchool());
                    operation.setIdannee(SessionMBean.getYear());
                    operation.setLibelle(depense.getLibelle());
                    operation.setDateoperation(new Date());
                    operation.setHeure(new Date());
                    operation.setDebit(depense.getMontant());
                    operation.setIdtypeoperation(typeoperationFacadeLocal.find(3));
                    operation.setCredit(0d);
                    operation.setVersement(false);
                    operationFacadeLocal.create(operation);

                    // on met à jour le compte comptable
                    compte.setDebit(compte.getDebit() - depense.getMontant());
                    compteFacadeLocal.edit(compte);

                    // on met a jour le grand compte
                    compte.getIdtypecompte().setDebit(compte.getIdtypecompte().getDebit() - depense.getMontant());
                    typecompteFacadeLocal.edit(compte.getIdtypecompte());

                    // on met à jour le compte comptable
                    compte1.setCredit(compte1.getDebit() + depense.getMontant());
                    compteFacadeLocal.edit(compte1);

                    // on met a jour le grand compte
                    compte1.getIdtypecompte().setCredit(compte.getIdtypecompte().getCredit() + depense.getMontant());
                    typecompteFacadeLocal.edit(compte1.getIdtypecompte());

                    // on enregistre l'operation créditeur
                    operation1.setIdoperation(operationFacadeLocal.nextVal());
                    operation1.setIdcompte(compte1);
                    operation1.setIdetablissement(SessionMBean.getSchool());
                    operation1.setIdannee(SessionMBean.getYear());
                    operation1.setLibelle(depense.getLibelle());
                    operation1.setDebit(0d);
                    operation1.setCredit(depense.getMontant());
                    operation1.setIdtypeoperation(typeoperationFacadeLocal.find(4));
                    operation1.setDateoperation(new Date());
                    operation1.setHeure(new Date());
                    operation1.setVersement(true);
                    operationFacadeLocal.create(operation1);

                    //on enregistre la dépense
                    depense.setIddepense(depenseFacadeLocal.nextVal());
                    depense.setIdannee(SessionMBean.getYear());
                    depense.setIdetablissement(SessionMBean.getSchool());
                    depense.setIdoperation(operation);
                    depense.setOperation2(operation1.getIdoperation());
                    depense.setDatedepense(new Date());
                    depenseFacadeLocal.create(depense);

                    Utilitaires.saveOperation(traceurFacadeLocal, "Enregistrement de la dépense  :-> " + depense.getLibelle() + " ; " + depense.getMontant(), SessionMBean.getUserAccount(), SessionMBean.getYear());
                    depense = new Depense();
                    compte = new Compte();
                    compte1 = new Compte();
                    JsfUtil.addSuccessMessage("Transaction réussie");

                    return;

                } else {
                    JsfUtil.addErrorMessage("Les deux comptes identiques");
                }

            } else {
                if (depense != null) {

                    Depense depense1 = depenseFacadeLocal.find(depense.getIddepense());

                    if (depense1.getMontant() != depense.getMontant()) {

                        //on met è jour le compte avec l ancien montant
                        compte = depense1.getIdoperation().getIdcompte();
                        compte.setDebit(compte1.getDebit() + depense1.getMontant());
                        compteFacadeLocal.edit(compte1);

                        // on met a jour la grande classe avec l ancien montant
                        compte.getIdtypecompte().setDebit(compte.getIdtypecompte().getDebit() + depense1.getMontant());
                        typecompteFacadeLocal.edit(compte.getIdtypecompte());

                        Operation operation = operationFacadeLocal.find(depense1.getOperation2());

                        //on met jour le compte credite avec le montant de la depense
                        compte1 = operation.getIdcompte();
                        compte1.setCredit(compte1.getCredit() - depense1.getMontant());
                        compteFacadeLocal.edit(compte1);

                        compte1.getIdtypecompte().setCredit(compte1.getIdtypecompte().getCredit() - depense1.getMontant());
                        typecompteFacadeLocal.edit(compte1.getIdtypecompte());

                        // on met à jour la depense
                        depenseFacadeLocal.edit(depense);

                        // on met a jour la transaction debiteur
                        depense.getIdoperation().setDebit(depense.getMontant());
                        depense.getIdoperation().setLibelle(depense.getLibelle());
                        operationFacadeLocal.edit(depense.getIdoperation());

                        // on met à jour la transaction crediteur
                        operation.setCredit(depense.getMontant());
                        operation.setLibelle(depense.getLibelle());
                        operationFacadeLocal.edit(operation);

                        //on met à jour le compte de l'operation debiteur
                        compte = compteFacadeLocal.find(compte.getIdcompte());
                        compte.setDebit(compte.getDebit() - depense.getMontant());
                        compteFacadeLocal.edit(compte);

                        compte.getIdtypecompte().setDebit(compte.getIdtypecompte().getDebit() - depense.getMontant());
                        typecompteFacadeLocal.edit(compte.getIdtypecompte());

                        operation = operationFacadeLocal.find(depense1.getOperation2());

                        // on met a jour le compte l'operation crediteur
                        compte1 = operation.getIdcompte();
                        compte1.setCredit(compte1.getCredit() + depense.getMontant());
                        compteFacadeLocal.edit(compte1);

                        compte1.getIdtypecompte().setCredit(compte1.getIdtypecompte().getCredit() + depense.getMontant());
                        typecompteFacadeLocal.edit(compte1.getIdtypecompte());

                    }

                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne selectionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        try {
            if (depense != null) {

                depenseFacadeLocal.remove(depense);

                Compte compte = depense.getIdoperation().getIdcompte();
                compte.setDebit(compte.getDebit() + depense.getMontant());
                compteFacadeLocal.edit(compte);

                compte.getIdtypecompte().setDebit(compte.getIdtypecompte().getDebit() + depense.getMontant());
                typecompteFacadeLocal.edit(compte.getIdtypecompte());

                Operation operation = operationFacadeLocal.find(depense.getOperation2());

                compte = operation.getIdcompte();
                compte.setCredit(compte.getCredit() - depense.getMontant());
                compteFacadeLocal.edit(compte);

                compte.getIdtypecompte().setCredit(compte.getIdtypecompte().getCredit() - depense.getMontant());
                typecompteFacadeLocal.edit(compte.getIdtypecompte());

                operationFacadeLocal.remove(operation);
                operationFacadeLocal.remove(depense.getIdoperation());

                Utilitaires.saveOperation(traceurFacadeLocal, "Annulation de la dépense  :-> " + depense.getLibelle() + " ; " + depense.getMontant(), SessionMBean.getUserAccount(), annee);
                JsfUtil.addSuccessMessage("Opération reussie");

            } else {
                JsfUtil.addErrorMessage("Echec de l'operation : aucun élément selectionné");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
