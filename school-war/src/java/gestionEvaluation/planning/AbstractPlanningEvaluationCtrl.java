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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.DualListModel;
import session.AnneeFacadeLocal;
import session.ClasseElementevaluationFacadeLocal;
import session.ClasseFacadeLocal;
import session.PlanningEvaluationFacadeLocal;
import session.SequenceanneeFacadeLocal;
import session.TraceurFacadeLocal;

/**
 *
 * @author gervais
 */
public class AbstractPlanningEvaluationCtrl {

    @EJB
    protected PlanningEvaluationFacadeLocal planningEvaluationFacadeLocal;
    protected PlanningEvaluation planningEvaluation;
    protected List<PlanningEvaluation> planningEvaluations = new ArrayList<>();
    protected List<PlanningEvaluation> planningEvaluations1 = new ArrayList<>();

    @EJB
    protected ClasseElementevaluationFacadeLocal classeElementevaluationFacadeLocal;
    protected DualListModel<ClasseElementevaluation> dualListModel = new DualListModel<>();

    @EJB
    protected SequenceanneeFacadeLocal sequenceanneeFacadeLocal;
    protected Sequenceannee sequenceannee;
    protected List<Sequenceannee> sequenceannees = new ArrayList<>();
    
    @EJB
    protected ClasseFacadeLocal classeFacadeLocal;
    protected Classe classe;
    protected List<Classe>classes = new ArrayList<>();
    
    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    
    
    protected boolean  detail = true;
    
    @EJB
    protected TraceurFacadeLocal traceurFacadeLocal;

    public DualListModel<ClasseElementevaluation> getDualListModel() {
        return dualListModel;
    }

    public void setDualListModel(DualListModel<ClasseElementevaluation> dualListModel) {
        this.dualListModel = dualListModel;
    }

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }
    
    

    public PlanningEvaluation getPlanningEvaluation() {
        return planningEvaluation;
    }

    public void setPlanningEvaluation(PlanningEvaluation planningEvaluation) {
        this.planningEvaluation = planningEvaluation;
    }

    public List<PlanningEvaluation> getPlanningEvaluations() {
        return planningEvaluations;
    }

    public void setPlanningEvaluations(List<PlanningEvaluation> planningEvaluations) {
        this.planningEvaluations = planningEvaluations;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public Sequenceannee getSequenceannee() {
        return sequenceannee;
    }

    public void setSequenceannee(Sequenceannee sequenceannee) {
        this.sequenceannee = sequenceannee;
    }

    public List<Sequenceannee> getSequenceannees() {
        return sequenceannees;
    }

    public void setSequenceannees(List<Sequenceannee> sequenceannees) {
        this.sequenceannees = sequenceannees;
    }

    public List<PlanningEvaluation> getPlanningEvaluations1() {
        return planningEvaluations1;
    }

    public void setPlanningEvaluations1(List<PlanningEvaluation> planningEvaluations1) {
        this.planningEvaluations1 = planningEvaluations1;
    }
    
    
    

}
