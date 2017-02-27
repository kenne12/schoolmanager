/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comptabilite.recette;

import entities.Compte;
import entities.Operation;
import entities.Recette;
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
public class RecetteCtrl extends AbstractRecetteCtrl implements RecetteInterfaceCtrl, Serializable {

    /**
     * Creates a new instance of EtablissementCtrl
     */
    public RecetteCtrl() {

    }

    @PostConstruct
    private void init() {
        typecompte = new Typecompte();
        compte = new Compte();
        try {
            comptes = compteFacadeLocal.find(SessionMBean.getSchool());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareCreate() {
        mode = "Create";
        recette = new Recette();
        operation = new Operation();
        soldeCrediteur = 0.0;
        this.init();
        showFilter = false;
    }

    public void prepareEdit() {
        mode = "Edit";
        showFilter = true;
        try {
            if (recette != null) {
                if (recette.getIdoperation().getIdcompte() != null) {
                    compte = recette.getIdoperation().getIdcompte();
                    typecompte = compte.getIdtypecompte();
                    soldeCrediteur = compte.getCredit() - compte.getDebit();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCompteCredit() {
        try {
            if (typecompte.getIdtypecompte() != null) {
                typecompte = typecompteFacadeLocal.find(typecompte.getIdtypecompte());
                comptes = compteFacadeLocal.find(typecompte);
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
                soldeCrediteur = compte.getCredit() - compte.getDebit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveRecette() {
        try {

            if ("Create".equals(mode)) {

                //code de l'operation
                String code = Utilitaires.codeInscription(SessionMBean.getSchool(), SessionMBean.getYear());
                code += "-" + annee.getCodefin().toString()+"-R";

                Long nextPayement = recetteFacadeLocal.nextVal(SessionMBean.getYear());
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

                //on enregistre l'operation debiteur
                operation.setIdoperation(operationFacadeLocal.nextVal());
                operation.setIdcompte(compte);
                operation.setIdetablissement(SessionMBean.getSchool());
                operation.setIdannee(SessionMBean.getYear());
                operation.setLibelle(recette.getLibelle());
                operation.setDateoperation(new Date());
                operation.setHeure(new Date());
                operation.setDebit(0.0);
                operation.setIdtypeoperation(typeoperationFacadeLocal.find(3));
                operation.setCredit(recette.getMontant());
                operation.setVersement(true);
                operationFacadeLocal.create(operation);

                // on met à jour le compte comptable
                compte.setDebit(compte.getDebit() - recette.getMontant());
                compteFacadeLocal.edit(compte);

                // on met a jour le grand compte
                compte.getIdtypecompte().setDebit(compte.getIdtypecompte().getDebit() - recette.getMontant());
                typecompteFacadeLocal.edit(compte.getIdtypecompte());

                //on enregistre la dépense
                recette.setIdrecette(recetteFacadeLocal.nextVal());
                recette.setIdannee(SessionMBean.getYear());
                recette.setIdetablissement(SessionMBean.getSchool());
                recette.setCode(code);
                recette.setIdoperation(operation);
                recette.setDaterecette(new Date());
                recetteFacadeLocal.create(recette);

                Utilitaires.saveOperation(traceurFacadeLocal, "Enregistrement du versement en caisse  :-> " + recette.getLibelle() + " ; " + recette.getMontant(), SessionMBean.getUserAccount(), SessionMBean.getYear());
                recette = new Recette();
                compte = new Compte();
                JsfUtil.addSuccessMessage("Transaction réussie");

                return;

            } else {
                if (recette != null) {

                    Recette recette1 = recetteFacadeLocal.find(recette.getIdrecette());

                    if (recette1.getMontant() != recette.getMontant()) {

                        //on met è jour le compte avec l ancien montant
                        compte = recette1.getIdoperation().getIdcompte();
                        compte.setCredit(compte.getCredit() - recette1.getMontant());
                        compteFacadeLocal.edit(compte);

                        // on met a jour la grande classe avec l ancien montant
                        compte.getIdtypecompte().setCredit(compte.getIdtypecompte().getCredit() - recette1.getMontant());
                        typecompteFacadeLocal.edit(compte.getIdtypecompte());

                        // on met à jour la recette
                        recetteFacadeLocal.edit(recette);

                        // on met à jour la transaction crediteur
                        operation = recette.getIdoperation();
                        operation.setCredit(recette.getMontant());
                        operation.setLibelle(recette.getLibelle());
                        operationFacadeLocal.edit(operation);

                        //on met à jour le compte de l'operation debiteur
                        compte = compteFacadeLocal.find(compte.getIdcompte());
                        compte.setCredit(compte.getCredit() + recette.getMontant());
                        compteFacadeLocal.edit(compte);

                        compte.getIdtypecompte().setCredit(compte.getIdtypecompte().getCredit() + recette.getMontant());
                        typecompteFacadeLocal.edit(compte.getIdtypecompte());

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
            if (recette != null) {

                recetteFacadeLocal.remove(recette);

                Compte compte = recette.getIdoperation().getIdcompte();
                compte.setDebit(compte.getDebit() + recette.getMontant());
                compteFacadeLocal.edit(compte);

                compte.getIdtypecompte().setDebit(compte.getIdtypecompte().getDebit() + recette.getMontant());
                typecompteFacadeLocal.edit(compte.getIdtypecompte());

                // Operation operation = operationFacadeLocal.find(recette.getOperation2());
                compte = operation.getIdcompte();
                compte.setCredit(compte.getCredit() - recette.getMontant());
                compteFacadeLocal.edit(compte);

                compte.getIdtypecompte().setCredit(compte.getIdtypecompte().getCredit() - recette.getMontant());
                typecompteFacadeLocal.edit(compte.getIdtypecompte());

                operationFacadeLocal.remove(operation);
                operationFacadeLocal.remove(recette.getIdoperation());

                Utilitaires.saveOperation(traceurFacadeLocal, "Annulation de la dépense  :-> " + recette.getLibelle() + " ; " + recette.getMontant(), SessionMBean.getUserAccount(), annee);
                JsfUtil.addSuccessMessage("Opération reussie");

            } else {
                JsfUtil.addErrorMessage("Echec de l'operation : aucun élément selectionné");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
