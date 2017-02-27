/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.elementevaluation;

import entities.ElementEvaluation;
import entities.Matiere;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.ElementEvaluationFacadeLocal;
import session.MatiereFacadeLocal;
import session.TraceurFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractElementEvaluationCtrl {

    @EJB
    protected ElementEvaluationFacadeLocal elementEvaluationFacadeLocal;
    protected ElementEvaluation elementEvaluation;
    protected List<ElementEvaluation> elementEvaluations = new ArrayList<>();
    protected ElementEvaluation selected;

    @EJB
    protected MatiereFacadeLocal matiereFacade;
    protected Matiere matiere = new Matiere();
    protected List<Matiere> listMatiere;
    protected Matiere selectedMatiere = new Matiere();

    protected List<Matiere> matieres = new ArrayList<>();

    protected TraceurFacadeLocal traceurFacadeLocal;

    public AbstractElementEvaluationCtrl() {

    }

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public boolean isDetail() {
        return detail;
    }

    public boolean isModifier() {
        return modifier;
    }

    public boolean isSupprimer() {
        return supprimer;
    }

    public boolean isImprimer() {
        imprimer = matiereFacade.findAll().isEmpty();
        return imprimer;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public List<Matiere> getListMatiere() {
        listMatiere = matiereFacade.findAll();
        return listMatiere;
    }

    public void setListMatiere(List<Matiere> listMatiere) {
        this.listMatiere = listMatiere;
    }

    public Matiere getSelectedMatiere() {

        return selectedMatiere;
    }

    public void setSelectedMatiere(Matiere selectedMatiere) {
        this.selectedMatiere = selectedMatiere;
        modifier = supprimer = detail = selectedMatiere == null;
    }

    public List<Matiere> getMatieres() {
        matieres = matiereFacade.findAll();
        return matieres;
    }

    public ElementEvaluation getElementEvaluation() {
        return elementEvaluation;
    }

    public void setElementEvaluation(ElementEvaluation elementEvaluation) {
        this.elementEvaluation = elementEvaluation;
    }

    public void setMatieres(List<Matiere> matieres) {
        this.matieres = matieres;
    }

    public List<ElementEvaluation> getElementEvaluations() {
        elementEvaluations = elementEvaluationFacadeLocal.findAllRange();
        return elementEvaluations;
    }

    public void setElementEvaluations(List<ElementEvaluation> elementEvaluations) {
        this.elementEvaluations = elementEvaluations;
    }

    public ElementEvaluation getSelected() {
        return selected;
    }

    public void setSelected(ElementEvaluation selected) {
        detail = supprimer = modifier = selected == null;
        this.selected = selected;
    }
    

}
