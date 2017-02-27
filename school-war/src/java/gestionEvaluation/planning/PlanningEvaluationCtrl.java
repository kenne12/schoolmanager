/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvaluation.planning;

import entities.Classe;
import entities.ClasseElementevaluation;
import entities.PlanningEvaluation;
import entities.Sequenceannee;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;
import utils.JsfUtil;
import utils.SessionMBean;

/**
 *
 * @author gervais
 */
@ManagedBean
@ViewScoped
public class PlanningEvaluationCtrl extends AbstractPlanningEvaluationCtrl implements PlanningEvaluationInterfaceCtrl {

    /**
     * Creates a new instance of PlanningEvaluationCtrl
     */
    public PlanningEvaluationCtrl() {
        
    }
    
    @PostConstruct
    private void init() {
        sequenceannee = new Sequenceannee();
        classe = new Classe();
        this.findPLan();
    }
    
    public void activeButton() {
        detail = false;
    }
    
    public void deactiveButton() {
        detail = true;
    }
    
    public void prepareCreate() {
        try {
            if (SessionMBean.getUserAccount() != null) {
                classe = new Classe();
                sequenceannee = new Sequenceannee();
                planningEvaluations1.clear();
                dualListModel = new DualListModel<>();
                classes = classeFacadeLocal.findByEtaBlissement(SessionMBean.getSchool().getId(), true);
                sequenceannees = sequenceanneeFacadeLocal.getByAnnee(SessionMBean.getYear().getIdannee());
                //SessionMBean.getYear().getSequenceanneeList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void prepareEdit() {
        
    }
    
    @Override
    public void save() {
        try {
            if (!planningEvaluations1.isEmpty()) {
                for (PlanningEvaluation p : planningEvaluations1) {
                    if (p.getElementEvaluation().getElementevaluation().getTrimestriel()) {
                        List<Sequenceannee> temp = p.getSequence().getTrimestre().getSequenceanneeList();
                        if (!temp.isEmpty()) {
                            for (Sequenceannee s : temp) {
                                if (planningEvaluationFacadeLocal.findByElementSequence(p.getElementEvaluation().getId(), s.getIdsequencean()) == null) {
                                    PlanningEvaluation p1 = new PlanningEvaluation();
                                    p1.setIdplanning(planningEvaluationFacadeLocal.nextVal());
                                    p1.setSequence(s);
                                    p1.setElementEvaluation(p.getElementEvaluation());
                                    planningEvaluationFacadeLocal.create(p1);
                                }
                            }
                        }
                    } else {
                        if (planningEvaluationFacadeLocal.findByElementSequence(p.getElementEvaluation().getId(), p.getSequence().getIdsequencean()) == null) {
                            p.setIdplanning(planningEvaluationFacadeLocal.nextVal());
                            planningEvaluationFacadeLocal.create(p);
                        }
                    }
                }
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Le tableau est vide");
            }
            this.findPLan();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void edit() {
        try {
            if (planningEvaluation != null) {
                planningEvaluationFacadeLocal.edit(planningEvaluation);
                JsfUtil.addErrorMessage("Opération reussie");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void delete() {
        try {
            if (planningEvaluation != null) {
                planningEvaluationFacadeLocal.remove(planningEvaluation);
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectinnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void print() {
        
    }
    
    public void transfertEtape() {
        try {
            if (!dualListModel.getTarget().isEmpty()) {
                for (ClasseElementevaluation c : dualListModel.getTarget()) {
                    PlanningEvaluation p = new PlanningEvaluation();
                    p.setElementEvaluation(c);
                    Sequenceannee s = sequenceannee;
                    p.setSequence(s);
                    planningEvaluations1.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateUnite() {
        try {
            dualListModel.getSource().clear();
            dualListModel.getTarget().clear();
            if (classe != null) {
                if (sequenceannee != null) {
                    List<ClasseElementevaluation> classeElementevaluations = classeElementevaluationFacadeLocal.findByClasse(classe.getIdclasse());
                    if (!classeElementevaluations.isEmpty()) {
                        for (ClasseElementevaluation c : classeElementevaluations) {
                            if (planningEvaluationFacadeLocal.findByElementSequence(c.getId(), sequenceannee.getIdsequencean()) == null) {
                                dualListModel.getSource().add(c);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void findPLan() {
        try {
            if (SessionMBean.getYear() != null) {
                planningEvaluations = planningEvaluationFacadeLocal.findByAnnee(SessionMBean.getYear().getIdannee());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
