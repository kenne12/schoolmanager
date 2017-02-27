/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEtat.imprimerBulletin;

import entities.Annee;
import entities.Classe;
import entities.Classecategorie;
import entities.Classematiere;
import entities.Eleve;
import entities.Eleveanneeclasse;
import entities.Evaluation;
import entities.Matiere;
import entities.Personnel;
import entities.Personnelmatiereclasseannee;
import entities.Sequenceannee;
import entities.Trimesteannee;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.DualListModel;
import session.AnneeFacadeLocal;
import session.ClasseFacadeLocal;
import session.ClassecategorieFacadeLocal;
import session.ClassematiereFacadeLocal;
import session.EleveFacadeLocal;
import session.EleveanneeclasseFacadeLocal;
import session.EvaluationFacadeLocal;
import session.NaturematiereFacadeLocal;
import session.PersonnelmatiereclasseanneeFacadeLocal;
import session.SequenceanneeFacadeLocal;
import session.TrimesteanneeFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractBulletinCtrl {

    @EJB
    protected EvaluationFacadeLocal evaluationFacadeLocal;
    protected List<Evaluation> evaluations = new ArrayList<>();
    protected Evaluation evaluation;
    protected Evaluation selectedEvaluation;
    List<Evaluation> notesPropres = new ArrayList<>();

    protected String[] observations = {"Null", "Mauvais", "Faible", "Insuffisant", "Médicore", "Passable", "Asssez-bien", "Bien", "Très-bien", "Excellent", "Parfait"};

    Personnel personnel;

    @EJB
    protected AnneeFacadeLocal anneeFacade;
    Annee annee;

    @EJB
    protected TrimesteanneeFacadeLocal trimestreFacade;
    protected Trimesteannee trimestre;
    protected List<Trimesteannee> trimestres;

    @EJB
    protected SequenceanneeFacadeLocal sequenceFacade;
    protected Sequenceannee sequence;
    protected List<Sequenceannee> sequences;

    protected Matiere matiere;
    protected List<Matiere> matieres = new ArrayList<>();
    List<Matiere> matiereCompses = new ArrayList<>();

    @EJB
    protected PersonnelmatiereclasseanneeFacadeLocal personnelMatiereFacade;
    protected List<Personnelmatiereclasseannee> personnelMatieres;

    @EJB
    protected EleveFacadeLocal eleveFacade;
    protected Eleve eleve;
    protected List<Eleve> eleves;

    protected List<Eleve> eleveTarget;
    protected List<Eleve> eleveSource;

    protected DualListModel<Eleve> pickList;

    @EJB
    protected ClassecategorieFacadeLocal classecategorieFacadeLocal;
    protected Classecategorie classeCategorie;
    protected List<Classecategorie> classecategories = new ArrayList<>();

    @EJB
    protected EleveanneeclasseFacadeLocal eleveAnneeClasseFacade;
    protected Eleveanneeclasse eleveAnneeClasse;
    protected List<Eleveanneeclasse> eleveAnneeClasses = new ArrayList<>();

    @EJB
    protected ClassematiereFacadeLocal classeMatiereFacade;
    protected List<Classematiere> classematieres = new ArrayList<>();

    @EJB
    protected NaturematiereFacadeLocal natureMatiereFacade;

    @EJB
    protected ClasseFacadeLocal classeFacade;
    protected Classe classe;

    //date de debut   
    protected Date semaineDebut;

    //date de fin
    protected Date semaineFin;

    @EJB
    protected ClassecategorieFacadeLocal categorieFacadeLocal;
    protected Classecategorie categorie;
    protected List<Classecategorie> categories = new ArrayList<>();

    protected StringBuffer evaluationsTableHtml = new StringBuffer("pas encore implementé");

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    //date de debut 
    public List<Evaluation> getEvaluations() {
        Annee an = anneeFacade.findByEtatSingle(true);
        evaluations = evaluationFacadeLocal.getEvaluationByPersonnel(1, an.getIdannee());
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
        //imprimer = getEvaluations().isEmpty();
        return imprimer;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public StringBuffer getEvaluationsTableHtml() {
        return evaluationsTableHtml;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public List<Eleve> getEleves() {
        eleves = eleveFacade.findByEtat(true);
        return eleves;
    }

    public void setEleves(List<Eleve> eleves) {
        this.eleves = eleves;
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
        Annee an = anneeFacade.findByEtatSingle(true);
        trimestres = trimestreFacade.getByAnneEtat(an.getIdannee(), true);
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
        Annee an = anneeFacade.findByEtatSingle(true);
        sequences = sequenceFacade.getByAnneEtat(an.getIdannee(), true);
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

    public String[] getObservations() {
        return observations;
    }

    public void setObservations(String[] observations) {
        this.observations = observations;
    }

    public List<Personnelmatiereclasseannee> getPersonnelMatieres() {
        personnelMatieres = personnelMatiereFacade.getMatiereByPersonnelAnnee(1, 3);
        return personnelMatieres;
    }

    public void setPersonnelMatieres(List<Personnelmatiereclasseannee> personnelMatieres) {
        this.personnelMatieres = personnelMatieres;
    }

    public List<Eleve> getEleveTarget() {
        return eleveTarget;
    }

    public void setEleveTarget(List<Eleve> eleveTarget) {
        this.eleveTarget = eleveTarget;
    }

    public List<Eleve> getEleveSource() {
        eleveSource.addAll(eleveFacade.findByEtat(true));
        return eleveSource;
    }

    public void setEleveSource(List<Eleve> eleveSource) {
        this.eleveSource = eleveSource;
    }

    public DualListModel<Eleve> getPickList() {
        return pickList;
    }

    public void setPickList(DualListModel<Eleve> pickList) {
        this.pickList = pickList;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Date getSemaineDebut() {
        return semaineDebut;
    }

    public void setSemaineDebut(Date semaineDebut) {
        this.semaineDebut = semaineDebut;
    }

    public Date getSemaineFin() {
        return semaineFin;
    }

    public void setSemaineFin(Date semaineFin) {
        this.semaineFin = semaineFin;
    }

    public List<Classecategorie> getCategories() {
        categories = categorieFacadeLocal.get(true);
        return categories;
    }

    public void setCategories(List<Classecategorie> categories) {
        this.categories = categories;
    }

    public List<Classecategorie> getClassecategories() {
        classecategories = classecategorieFacadeLocal.get(true);
        return classecategories;
    }

    public void setClassecategories(List<Classecategorie> classecategories) {
        this.classecategories = classecategories;
    }

    public Classecategorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Classecategorie categorie) {
        this.categorie = categorie;
    }

    public Eleveanneeclasse getEleveAnneeClasse() {
        return eleveAnneeClasse;
    }

    public void setEleveAnneeClasse(Eleveanneeclasse eleveAnneeClasse) {
        this.eleveAnneeClasse = eleveAnneeClasse;
    }

    public List<Eleveanneeclasse> getEleveAnneeClasses() {
        return eleveAnneeClasses;
    }

    public void setEleveAnneeClasses(List<Eleveanneeclasse> eleveAnneeClasses) {
        this.eleveAnneeClasses = eleveAnneeClasses;
    }

}
