/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.elementevaluation;

import entities.ElementEvaluation;
import entities.Matiere;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.UtilitaireSession;
import utils.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class ElementEvaluationCtrl extends AbstractElementEvaluationCtrl implements ElementEvaluationInterfaceCtrl, Serializable {
    
    public ElementEvaluationCtrl() {
        
    }
    
    @PostConstruct
    private void initMatiere() {
        elementEvaluation = new ElementEvaluation();
        matiere = new Matiere();
        selected = new ElementEvaluation();
    }
    
    public void prepareCreate() {
        elementEvaluation = new ElementEvaluation();
        elementEvaluation.setTrimestriel(false);
    }
    
    @Override
    public void save() {
        try {
            List<ElementEvaluation> elementEvaluationTemp = elementEvaluationFacadeLocal.findByLibelle(elementEvaluation.getMatiere().getIdmatiere(), elementEvaluation.getNom());
            if (elementEvaluationTemp.isEmpty()) {
                elementEvaluation.setIdelement(elementEvaluationFacadeLocal.nextVal());
                elementEvaluationFacadeLocal.create(elementEvaluation);
                initMatiere();
                JsfUtil.addSuccessMessage("L'unité d'évaluation a été créée avec succès");
            } else {
                JsfUtil.addErrorMessage("Une unité ayant ce nom existe déjà");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void edit() {
        try {
            ElementEvaluation temp = elementEvaluationFacadeLocal.find(selected.getIdelement());
            if (selected != null) {
                elementEvaluationFacadeLocal.edit(selected);
                initMatiere();
                JsfUtil.addSuccessMessage("L'unité  a été mise à jour !");
            } else {
                JsfUtil.addErrorMessage("Aucune selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void delete() {
        try {
            if (selected != null) {
                if (selected.getClasseElementevaluationList().isEmpty()) {
                    
                    elementEvaluationFacadeLocal.remove(selected);
                    Utilitaires.saveOperation(traceurFacadeLocal, "Suppression de l'unité d'évaluation -> " + selected.getNom() + " de la matiere -> " + selected.getMatiere().getLibelle(), UtilitaireSession.getInstance().getuser());
                    initMatiere();
                    JsfUtil.addSuccessMessage("operation réussie !");
                    
                } else {
                    JsfUtil.addErrorMessage("impossible, l'unité est utilisé par plusieurs classes");
                }
                
            } else {
                JsfUtil.addErrorMessage("la matiere est non valide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void imprimerMatierePdf() {
        
    }
    
    @Override
    public void imprimerMatiereHtml() {
        
    }
    
}
