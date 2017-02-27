/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvaluation.procesVerbalNoteAnnuel;

import entities.Annee;
import entities.Classe;
import entities.ClasseElementevaluation;
import entities.Classecategorie;
import entities.Eleveanneeclasse;
import entities.Evaluation;
import entities.PlanningEvaluation;
import entities.Sequenceannee;
import entities.Trimesteannee;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.AnneeFacadeLocal;
import session.ClasseElementevaluationFacadeLocal;
import session.ClasseFacadeLocal;
import session.ClassecategorieFacadeLocal;
import session.EleveanneeclasseFacadeLocal;
import session.EvaluationFacadeLocal;
import session.PlanningEvaluationFacadeLocal;
import session.SequenceanneeFacadeLocal;
import session.TrimesteanneeFacadeLocal;
import utils.SessionMBean;

/**
 *
 * @author kenne
 */
public class AbstractProcesVeralNoteAnnuelCtrl {

    @EJB
    protected EvaluationFacadeLocal evaluationFacadeLocal;
    protected Evaluation evaluation;
    protected List<Evaluation> evaluations = new ArrayList<>();

    @EJB
    protected PlanningEvaluationFacadeLocal planningEvaluationFacadeLocal;
    protected PlanningEvaluation planningEvaluation;
    protected List<PlanningEvaluation> planningEvaluations = new ArrayList<>();

    @EJB
    protected SequenceanneeFacadeLocal sequenceanneeFacadeLocal;
    protected Sequenceannee sequenceannee;
    protected List<Sequenceannee> sequenceannees = new ArrayList<>();

    @EJB
    protected TrimesteanneeFacadeLocal trimesteanneeFacadeLocal;
    protected Trimesteannee trimesteannee;
    protected List<Trimesteannee> trimesteannees = new ArrayList<>();

    @EJB
    protected EleveanneeclasseFacadeLocal eleveanneeclasseFacadeLocal;
    protected Eleveanneeclasse eleveanneeclasse;
    protected List<Eleveanneeclasse> eleveanneeclasses = new ArrayList<>();

    @EJB
    protected ClasseFacadeLocal classeFacadeLocal;
    protected Classe classe;
    protected List<Classe> classes = new ArrayList<>();

    @EJB
    protected ClassecategorieFacadeLocal classeCategorieFacade;
    protected Classecategorie categorie;
    protected List<Classecategorie> categories = new ArrayList<>();

    @EJB
    protected ClasseElementevaluationFacadeLocal classeElementevaluationFacadeLocal;
    protected ClasseElementevaluation classeElementevaluation;
    protected List<ClasseElementevaluation> classeElementevaluations = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacade;
    protected Annee annee;

    protected String fichier = "";

    protected boolean imprimer = true;

    public boolean isImprimer() {
        imprimer = eleveanneeclasses.isEmpty() && sequenceannees.isEmpty() && trimesteannees.isEmpty();
        return imprimer;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
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

    public Eleveanneeclasse getEleveanneeclasse() {
        return eleveanneeclasse;
    }

    public void setEleveanneeclasse(Eleveanneeclasse eleveanneeclasse) {
        this.eleveanneeclasse = eleveanneeclasse;
    }

    public List<Eleveanneeclasse> getEleveanneeclasses() {
        return eleveanneeclasses;
    }

    public void setEleveanneeclasses(List<Eleveanneeclasse> eleveanneeclasses) {
        this.eleveanneeclasses = eleveanneeclasses;
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

    public Classecategorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Classecategorie categorie) {
        this.categorie = categorie;
    }

    public List<Classecategorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Classecategorie> categories) {
        this.categories = categories;
    }

    public Annee getAnnee() {
        if (SessionMBean.getUserAccount() != null) {
            List<Annee> annees = anneeFacade.findByEtat(SessionMBean.getUserAccount().getEtablissement().getId(), true);
            if (!annees.isEmpty()) {
                annee = annees.get(annees.size() - 1);
            }
        }
        return annee;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public Trimesteannee getTrimesteannee() {
        return trimesteannee;
    }

    public void setTrimesteannee(Trimesteannee trimesteannee) {
        this.trimesteannee = trimesteannee;
    }

    public List<Trimesteannee> getTrimesteannees() {
        return trimesteannees;
    }

    public void setTrimesteannees(List<Trimesteannee> trimesteannees) {
        this.trimesteannees = trimesteannees;
    }

    public ClasseElementevaluation getClasseElementevaluation() {
        return classeElementevaluation;
    }

    public void setClasseElementevaluation(ClasseElementevaluation classeElementevaluation) {
        this.classeElementevaluation = classeElementevaluation;
    }

    public List<ClasseElementevaluation> getClasseElementevaluations() {
        return classeElementevaluations;
    }

    public void setClasseElementevaluations(List<ClasseElementevaluation> classeElementevaluations) {
        this.classeElementevaluations = classeElementevaluations;
    }

}
