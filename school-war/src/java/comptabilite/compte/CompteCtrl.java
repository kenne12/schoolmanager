/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comptabilite.compte;

import entities.Compte;
import entities.Typecompte;
import java.io.Serializable;
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
public class CompteCtrl extends AbstractCompteCtrl implements CompteInterfaceCtrl, Serializable {

    /**
     * Creates a new instance of EtablissementCtrl
     */
    public CompteCtrl() {
        
    }
    
    public void init() {
        typecompte = new Typecompte();
        compte = new Compte();
    }
    
    public void prepareCreate() {
        mode = "Create";
        this.init();
        compte.setDebit(0D);
        compte.setCredit(0D);
    }
    
    public void prepareEdit() {
        mode = "Edit";
        try{
            if(compte!=null){
                if(compte.getIdtypecompte()!=null){
                    typecompte = compte.getIdtypecompte();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }        
    }
    
    public void update() {
        try {
            if (typecompte.getIdtypecompte() != null) {
                typecompte = typecompteFacadeLocal.find(typecompte.getIdtypecompte());
                compte.setClasse(typecompte.getClasse());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void saveCompte() {
        try {
            
            if ("Create".equals(mode)) {
                
                if (compteFacadeLocal.find(SessionMBean.getSchool(), compte.getClasse()).isEmpty()) {
                    compte.setIdcompte(compteFacadeLocal.nextVal());
                    
                    typecompte = typecompteFacadeLocal.find(typecompte.getIdtypecompte());
                    compte.setIdtypecompte(typecompte);
                    
                    compte.setIdetablissement(SessionMBean.getSchool());
                    compteFacadeLocal.create(compte);
                    
                    Utilitaires.saveOperation(traceurFacadeLocal, "Enregistrement : compte -> " + compte.getClasse() + " ; " + compte.getLibelle(), SessionMBean.getUserAccount(), SessionMBean.getYear());
                    compte = new Compte();
                    JsfUtil.addSuccessMessage("Compte créé avec succès !");
                    
                    return;
                } else {
                    JsfUtil.addWarningMessage("Un compte ayant ce numero existe déjà");
                }
                
            } else {
                if (compte != null) {
                    compte.setIdtypecompte(typecompteFacadeLocal.find(typecompte.getIdtypecompte()));
                    compteFacadeLocal.edit(compte);
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
            if (compte != null) {
                if (compte.getOperationList().isEmpty()) {
                    compteFacadeLocal.remove(compte);
                    Utilitaires.saveOperation(traceurFacadeLocal, "Suppression : du compte -> " + compte.getClasse() + " ; " + compte.getLibelle(), SessionMBean.getUserAccount(), SessionMBean.getYear());
                    JsfUtil.addSuccessMessage("Opération reussie");
                } else {
                    JsfUtil.addWarningMessage("Ce compte comptient plusieurs opérations");
                }
            } else {
                JsfUtil.addErrorMessage("Echec de l'operation : aucun élément selectionné");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
