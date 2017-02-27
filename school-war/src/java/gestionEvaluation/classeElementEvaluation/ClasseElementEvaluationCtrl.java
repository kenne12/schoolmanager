/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvaluation.classeElementEvaluation;

import com.sun.xml.xsom.impl.scd.Iterators;
import entities.Classe;
import entities.ClasseElementevaluation;
import entities.Classematiere;
import entities.ElementEvaluation;
import entities.Etablissement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;
import utils.JsfUtil;
import utils.UtilitaireSession;
import utils.Utilitaires;

/**
 *
 * @author gervais
 */
@ManagedBean
@ViewScoped
public class ClasseElementEvaluationCtrl extends AbstractClasseElementEvaluation implements ClasseElementEvaluationInterface {

    /**
     * Creates a new instance of ClasseElementEvaluationCtrl
     */
    public ClasseElementEvaluationCtrl() {
        
    }
    
    @PostConstruct
    private void init() {
        classeElementevaluation = new ClasseElementevaluation();
        etablissement = new Etablissement();
        classe = new Classe();
        etablissements = Utilitaires.findSchoolByPersonnel(UtilitaireSession.getInstance().getuser());
    }
    
    public void activeButton() {
        detail = false;
    }
    
    public void deactiveButton() {
        detail = true;
    }
    
    public void prepareEdit() {
        
    }
    
    public void prepareCreate() {
        classeElementevaluation = new ClasseElementevaluation();
        dualElements = new DualListModel<>();
        classeElementevaluationTemp.clear();
        classe = new Classe();
    }
    
    public void updateClasse() {
        try {
            if (etablissement != null) {
                categories = classecategorieFacadeLocal.get(etablissement.getId(), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void upadteMatiere() {
        try {
            classe = classeFacadeLocal.find(classe.getIdclasse());
            dualElements.getSource().clear();
            dualElements.getTarget().clear();
            matieresTest.clear();
            List<Classematiere> results = classematiereFacadeLocal.get(classe.getIdclasse());
            if (!results.isEmpty()) {
                List<ClasseElementevaluation> elementTemp = classe.getClasseElementevaluationList();
                if (!elementTemp.isEmpty()) {
                    System.err.println("la classe a les elements");
                    List<ElementEvaluation> temp = new ArrayList<>();
                    List<ElementEvaluation> temp2 = new ArrayList<>();
                    
                    for (Classematiere c : results) {
                        if (!c.getIdmatiere().getElementEvaluationList().isEmpty()) {
                            temp2.addAll(c.getIdmatiere().getElementEvaluationList());
                        }
                    }
                    
                    for (ClasseElementevaluation c : elementTemp) {
                        temp.add(c.getElementevaluation());
                    }
                    
                    for (ElementEvaluation e : temp2) {
                        if (!temp.contains(e)) {
                            dualElements.getSource().add(e);
                        }
                    }
                    
                } else {
                    System.err.println("la classe n a pas les elements");
                    List<ElementEvaluation> temp2 = new ArrayList<>();
                    for (Classematiere c : results) {
                        if (!c.getIdmatiere().getElementEvaluationList().isEmpty()) {
                            temp2.addAll(c.getIdmatiere().getElementEvaluationList());
                        }
                    }
                    dualElements.getSource().addAll(temp2);
                }
            } else {
                JsfUtil.addErrorMessage("Aucune matiere n'est definie pour cette classe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void transfertEtape() {
        try {
            if (!dualElements.getTarget().isEmpty()) {
                classe = classeFacadeLocal.find(classe.getIdclasse());
                for (ElementEvaluation e : dualElements.getTarget()) {
                    classeElementevaluation = new ClasseElementevaluation();
                    classeElementevaluation.setElementevaluation(e);
                    classeElementevaluation.setCoefficient(e.getCoeficient());
                    classeElementevaluation.setClasse(classe);
                    if (classeElementevaluationTemp.isEmpty()) {
                        classeElementevaluationTemp.add(classeElementevaluation);
                    } else {
                        classeElementevaluationTemp.add(classeElementevaluation);
                        
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void save() {
        try {
            if (classe != null) {
                if (!classeElementevaluationTemp.isEmpty()) {
                    for (ClasseElementevaluation c : classeElementevaluationTemp) {
                        c.setId(classeElementevaluationFacadeLocal.nextVal());
                        classeElementevaluationFacadeLocal.create(c);
                    }
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addSuccessMessage("La liste de destination est vide");
                }
            } else {
                JsfUtil.addErrorMessage("Veuillez selectionner une classe");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void edit() {
        try {
            if (classeElementevaluation != null) {
                classeElementevaluationFacadeLocal.edit(classeElementevaluation);
                JsfUtil.addSuccessMessage("Unité d'évaluation mise à avec succès");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne sélectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void delete() {
        try {
            if (classeElementevaluation != null) {
                classeElementevaluationFacadeLocal.remove(classeElementevaluation);
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne sélectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void print() {
        
    }
    
}
