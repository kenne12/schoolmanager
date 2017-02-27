/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comptabilite.typecompte;

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
public class TypecompteCtrl extends AbstractTypecompteCtrl implements TypecompteInterfaceCtrl, Serializable {

    /**
     * Creates a new instance of EtablissementCtrl
     */
    public TypecompteCtrl() {
        
    }
    
    public void init() {
        typecompte = new Typecompte();
    }
    
    public void prepareCreate() {
        mode = "Create";
        this.init();
        typecompte.setDebit(0D);
        typecompte.setCredit(0D);
    }
    
    public void prepareEdit() {
        mode = "Edit";
    }
    
    @Override
    public void saveTypecompte() {
        try {
            
            if ("Create".equals(mode)) {
                
                if (typecompteFacadeLocal.find(SessionMBean.getSchool(), typecompte.getClasse()).isEmpty()) {
                    typecompte.setIdtypecompte(typecompteFacadeLocal.nextVal());
                    typecompte.setIdetablissement(SessionMBean.getSchool());
                    typecompteFacadeLocal.create(typecompte);
                    
                    Utilitaires.saveOperation(traceurFacadeLocal, "Enregistrement : classe de compte -> " + typecompte.getClasse() + " ; " + typecompte.getLibelle(), SessionMBean.getUserAccount(), SessionMBean.getYear());
                    typecompte = new Typecompte();
                    JsfUtil.addSuccessMessage("Type de compte créé avec succès !");
                    
                    return;
                } else {
                    JsfUtil.addWarningMessage("Un porte déja ce numero");
                }
                
            } else {
                if (typecompte != null) {
                    typecompteFacadeLocal.edit(typecompte);
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
            if (typecompte != null) {
                if (typecompte.getCompteList().isEmpty()) {
                    typecompteFacadeLocal.remove(typecompte);
                    Utilitaires.saveOperation(traceurFacadeLocal, "Suppression : classe de compte -> " + typecompte.getClasse() + " ; " + typecompte.getLibelle(), SessionMBean.getUserAccount(), SessionMBean.getYear());
                    JsfUtil.addSuccessMessage("Opération reussie");
                } else {
                    JsfUtil.addWarningMessage("Ce compte comptient plusieurs sous - comptes");
                }
            } else {
                JsfUtil.addErrorMessage("Echec de l'operation : aucun élément selectionné");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
