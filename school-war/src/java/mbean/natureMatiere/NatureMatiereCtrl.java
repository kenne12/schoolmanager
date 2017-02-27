/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.natureMatiere;

import entities.Naturematiere;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import utils.JsfUtil;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@SessionScoped
public class NatureMatiereCtrl extends AbstractNatureMatiereCtrl implements NatureMatiereInterfaceCtrl, Serializable {

    /*public NatureMatiereCtrl() {
        
     }*/
    @PostConstruct
    private void initNatureMatiere() {
        natureMatiere = new Naturematiere();
        selectedNatureMatiere = new Naturematiere();
    }

    @Override
    public void enregistrerNatureMatiere() {
        Naturematiere nat = natureMatiereFacade.findByLibelle(natureMatiere.getLibelle());
        if (nat == null) {
            natureMatiereFacade.create(natureMatiere);
            initNatureMatiere();
            JsfUtil.addSuccessMessage("La nature de matiere a été enregistrée");

        } else {
            JsfUtil.addErrorMessage("Une nature  portant ce code existe déjà");
        }
    }

    @Override
    public void modifier() {
        if (selectedNatureMatiere != null) {
            natureMatiereFacade.edit(selectedNatureMatiere);
            initNatureMatiere();
            JsfUtil.addSuccessMessage("La nature matiere a été mise à jour !");
        } else {
            JsfUtil.addErrorMessage("La nature matiere est invalide");
        }
    }

    @Override
    public void supprimer() {
        if (selectedNatureMatiere != null) {
            natureMatiereFacade.remove(selectedNatureMatiere);
            initNatureMatiere();
            JsfUtil.addSuccessMessage("Opération réussie !");
        } else {
            JsfUtil.addErrorMessage("Echec de l'opération !");
        }
    }

    @Override
    public void imprimerNatureMatierePdf() {

    }

    @Override
    public void imprimerNatureMatiereHtml() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
