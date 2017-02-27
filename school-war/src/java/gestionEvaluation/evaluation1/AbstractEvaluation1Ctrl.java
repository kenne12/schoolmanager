/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvaluation.evaluation1;

import entities.Annee;
import entities.Classe;
import entities.ClasseElementevaluation;
import entities.Eleveanneeclasse;
import entities.Etablissement;
import entities.Evaluation;
import entities.Matiere;
import entities.Observationnote;
import entities.Personnel;
import entities.PlanningEvaluation;
import entities.Sequenceannee;
import entities.Traceur;
import entities.Trimesteannee;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.AnneeFacadeLocal;
import session.AppreciationannuelleFacadeLocal;
import session.AppreciationtrimestrielleFacadeLocal;
import session.ClasseElementevaluationFacadeLocal;
import session.ClasseFacadeLocal;
import session.ClassematiereFacadeLocal;
import session.EleveanneeclasseFacadeLocal;
import session.EvaluationFacadeLocal;
import session.ObservationnoteFacadeLocal;
import session.PlanningEvaluationFacadeLocal;
import session.SequenceanneeFacadeLocal;
import session.TraceurFacadeLocal;
import session.TrimesteanneeFacadeLocal;
import utils.SessionMBean;

/**
 *
 * @author Gervais
 */
public class AbstractEvaluation1Ctrl {

    @EJB
    protected EvaluationFacadeLocal evaluationFacadeLocal;
    protected Evaluation evaluation;
    protected Evaluation selectedEvaluation;
    protected List<Evaluation> evaluations = new ArrayList<>();
    protected List<Evaluation> evaluations1 = new ArrayList<>();

    @EJB
    protected ObservationnoteFacadeLocal observationnoteFacadeLocal;
    protected Observationnote observationnote;
    protected List<Observationnote> observationnotes = new ArrayList<>();

    //protected String[] observations = {"Null", "Mauvais", "Faible", "Insuffisant", "Médicore", "Passable", "Asssez-bien", "Bien", "Très-bien", "Excellent", "Parfait"};
    Personnel personnel;

    @EJB
    protected AnneeFacadeLocal anneeFacade;
    protected Annee annee = SessionMBean.getYear();

    protected Etablissement etablissement = SessionMBean.getSchool();

    @EJB
    protected ClasseFacadeLocal classeFacadeLocal;
    protected Classe classe = new Classe();
    protected List<Classe> classes = new ArrayList<>();

    public AbstractEvaluation1Ctrl() {
    }

    @EJB
    protected TrimesteanneeFacadeLocal trimestreFacade;
    protected Trimesteannee trimestre;
    protected List<Trimesteannee> trimestres;

    @EJB
    protected SequenceanneeFacadeLocal sequenceFacade;
    protected Sequenceannee sequence;
    protected List<Sequenceannee> sequences;

    protected Matiere matiere;
    protected List<Matiere> matieres;

    @EJB
    protected ClasseElementevaluationFacadeLocal classeElementevaluationFacadeLocal;
    protected ClasseElementevaluation classeElementevaluation = new ClasseElementevaluation();
    protected List<ClasseElementevaluation> classeElementevaluations = new ArrayList<>();

    @EJB
    protected EleveanneeclasseFacadeLocal eleveanneeclasseFacadeLocal;
    protected List<Eleveanneeclasse> eleveanneeclasses = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;

    @EJB
    protected ClassematiereFacadeLocal classeMatiereFacade;

    @EJB
    protected PlanningEvaluationFacadeLocal planningEvaluationFacadeLocal;
    PlanningEvaluation planningEvaluation;

    @EJB
    protected AppreciationannuelleFacadeLocal appreciationannuelleFacadeLocal;

    @EJB
    protected AppreciationtrimestrielleFacadeLocal appreciationtrimestrielleFacadeLocal;

    @EJB
    protected TraceurFacadeLocal traceurFacade;
    protected Traceur traceur;

    protected boolean showCreateDlg = false;
    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;
    protected boolean valider = true;

    public List<Evaluation> getEvaluations() {
        try {
            evaluations = evaluationFacadeLocal.findByAnnee(SessionMBean.getYear().getIdannee());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return evaluations;
    }

    public Evaluation getSelectedEvaluation() {
        return selectedEvaluation;
    }

    public void setSelectedEvaluation(Evaluation selectedEvaluation) {
        this.selectedEvaluation = selectedEvaluation;
        modifier = supprimer = detail = selectedEvaluation == null;
    }

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
        imprimer = evaluationFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public boolean isValider() {
        return valider;
    }

    public void setValider(boolean valider) {
        this.valider = valider;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public Trimesteannee getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Trimesteannee trimestre) {
        this.trimestre = trimestre;
    }

    public List<Trimesteannee> getTrimestres() {
        return trimestres;
    }

    public void setTrimestres(List<Trimesteannee> trimestres) {
        this.trimestres = trimestres;
    }

    public Sequenceannee getSequence() {
        return sequence;
    }

    public void setSequence(Sequenceannee sequence) {
        this.sequence = sequence;
    }

    public List<Sequenceannee> getSequences() {
        return sequences;
    }

    public void setSequences(List<Sequenceannee> sequences) {
        this.sequences = sequences;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public List<Matiere> getMatieres() {
        return matieres;
    }

    public void setMatieres(List<Matiere> matieres) {
        this.matieres = matieres;
    }

    public Observationnote getObservationnote() {
        return observationnote;
    }

    public void setObservationnote(Observationnote observationnote) {
        this.observationnote = observationnote;
    }

    public List<Observationnote> getObservationnotes() {
        return observationnotes;
    }

    public void setObservationnotes(List<Observationnote> observationnotes) {
        this.observationnotes = observationnotes;
    }

    public Traceur getTraceur() {
        return traceur;
    }

    public void setTraceur(Traceur traceur) {
        this.traceur = traceur;
    }

    public List<Evaluation> getEvaluations1() {
        return evaluations1;
    }

    public void setEvaluations1(List<Evaluation> evaluations1) {
        this.evaluations1 = evaluations1;
    }

    public boolean isShowCreateDlg() {
        return showCreateDlg;
    }

    public void setShowCreateDlg(boolean showCreateDlg) {
        this.showCreateDlg = showCreateDlg;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
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
