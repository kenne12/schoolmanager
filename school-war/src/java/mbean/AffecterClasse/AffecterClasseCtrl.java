/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.AffecterClasse;

import entities.Classe;
import entities.Classesalle;
import entities.Salle;
import java.io.Serializable;
import java.util.Objects;
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
public class AffecterClasseCtrl extends AbstractAffecterClasse implements AffecterClasseInterfaceCtrl, Serializable {

    /*public AffecterClasseCtrl() {
        
     }*/
    @PostConstruct
    private void initAffectation() {
        classe = new Classe();
        selectedAffectation = new Classesalle();
        salle = new Salle();
        affectation = new Classesalle();
    }

    @Override
    public void enregistrerAffectation() {
        Classesalle clas;
        clas = classeSalleFacade.findByClasseSalle(classe.getIdclasse(), salle.getIdsalle());

        if (clas == null) {
            affectation.setIdclasse(classe);
            affectation.setIdsalle(salle);
            classeSalleFacade.create(affectation);
            initAffectation();
            JsfUtil.addSuccessMessage("Affectation réussie");
        } else {
            JsfUtil.addErrorMessage("L'affectation existe dejà !");
        }
    }

    @Override
    public void modifier() {
        Classesalle clas = classeSalleFacade.findByClasseSalle(classe.getIdclasse(), salle.getIdsalle());

        if (clas == null) {
            selectedAffectation.setIdclasse(classe);
            selectedAffectation.setIdsalle(salle);
            classeSalleFacade.edit(selectedAffectation);
            initAffectation();
            JsfUtil.addSuccessMessage("La classe a été mise à jour !");
        } else {
            if (Objects.equals(clas.getEtat(), selectedAffectation.getEtat())) {
                JsfUtil.addErrorMessage("cette affectation existe deja");
            } else {
                classeSalleFacade.edit(selectedAffectation);
                initAffectation();
                JsfUtil.addSuccessMessage("La classe a été mise à jour !");
            }

        }

    }

    @Override
    public void supprimer() {
        if (selectedAffectation != null) {
            classeSalleFacade.remove(selectedAffectation);
            initAffectation();
            JsfUtil.addSuccessMessage("operation réussie !");
        } else {
            JsfUtil.addErrorMessage("selectionner une classe");
        }
    }

    @Override
    public void imprimerAffectationPdf() {

    }

    @Override
    public void imprimerAffectationHtml() {

    }
}
