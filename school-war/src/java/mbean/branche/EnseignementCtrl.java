/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.branche;

import entities.Enseignement;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.SessionMBean;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "enseignementCtrl")
@ViewScoped
public class EnseignementCtrl extends AbstractEnseignementCtrl implements EnseignementInterfaceCtrl, Serializable {

    @PostConstruct
    private void initEnseignement() {
        selectedEnseignement = new Enseignement();
        enseignement = new Enseignement();
    }

    @Override
    public void enregistrerEnseignement() {
        if (enseignement != null) {
            Enseignement result = enseignementFacadeLocal.findByNom(SessionMBean.getSchool().getId() , enseignement.getNom());
            if (result == null) {
                enseignement.setEtablissement(SessionMBean.getSchool());
                enseignementFacadeLocal.create(enseignement);
                initEnseignement();
                JsfUtil.addSuccessMessage("operation réussie !");
            } else {
                JsfUtil.addErrorMessage("une branche portant ce nom existe deja !");
            }
        } else {
            JsfUtil.addErrorMessage("echec !");
        }
    }

    @Override
    public void modifier() {
        if (selectedEnseignement != null) {
            enseignementFacadeLocal.edit(selectedEnseignement);
            initEnseignement();
            JsfUtil.addSuccessMessage("la branche a été mise à jour !");
        } else {
            JsfUtil.addErrorMessage("echec !");
        }
    }

    @Override
    public void supprimer() {
        if (selectedEnseignement == null || selectedEnseignement.getIdbranche() == null) {
            JsfUtil.addSuccessMessage("Veuillez selectionner un enseignement !");
        } else {
            if (selectedEnseignement.getClasseList().isEmpty()) {
                enseignementFacadeLocal.remove(selectedEnseignement);
                initEnseignement();
                JsfUtil.addSuccessMessage("opération réussie");
            } else {
                JsfUtil.addErrorMessage("impossible de supprimer, cette branche contient des données");
                initEnseignement();
            }
        }
    }

    @Override
    public void imprimerEnseignementPdf() {

    }

    @Override
    public void imprimerEnseignementHtml() {
        System.out.println("Impression html types compte");
    }

}
