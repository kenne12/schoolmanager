/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.salle;

import entities.Batiment;
import entities.Salle;
import java.io.Serializable;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import utils.JsfUtil;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@SessionScoped
public class SalleCtrl extends AbstractSalleCtrl implements SalleInterfaceCtrl, Serializable {

    /**
     * Creates a new instance of SalleCtrl
     */
    public SalleCtrl() {
        
    }

    @PostConstruct
    private void initSalle() {
        salle = new Salle();
        selectedSalle = new Salle();
        batiment = new Batiment();
    }

    @Override
    public void enregistrerSalle() {

        Salle sal = salleFacadeLocal.findSalleByCode(salle.getCode());
        if (sal != null) {
            JsfUtil.addErrorMessage("Une salle portant ce code existe déjà");
        } else {
            salle.setIdbatiment(batiment);
            salleFacadeLocal.create(salle);
            initSalle();
            JsfUtil.addSuccessMessage("La salle été enregistré");
        }
    }

    @Override
    public void modifier() {
        if (selectedSalle.getIdbatiment() == null) {
            JsfUtil.addErrorMessage("selectionner un batiment");
            initSalle();
        } else {
            salleFacadeLocal.edit(selectedSalle);
            JsfUtil.addSuccessMessage("La salle a été mise à jour");
            initSalle();
        }
    }

    @Override
    public void supprimer() {
        if (selectedSalle != null) {
            if (selectedSalle.getClassesalleList().isEmpty()) {
                salleFacadeLocal.remove(salle);
                JsfUtil.addSuccessMessage("La sallé a été supprimée !");
                initSalle();
            } else {
                JsfUtil.addErrorMessage("impossible");
            }
        } else {
            JsfUtil.addErrorMessage("impossible !");
        }
    }

    @Override
    public void imprimerSallePdf() {
        System.out.println("Impression pdf types compte");
        salles = salleFacadeLocal.findAll();
//        fileName = PdfBatiment.etatsBatiment(batiments);
    }

    @Override
    public void imprimerSalleHtml() {
        System.out.println("Impression html types compte");
    }

}
