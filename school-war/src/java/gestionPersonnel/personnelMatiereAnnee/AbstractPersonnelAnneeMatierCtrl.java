/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPersonnel.personnelMatiereAnnee;

import entities.Annee;
import entities.Classe;
import entities.Matiere;
import entities.Personnel;
import entities.Personnelmatiereclasseannee;
import entities.Traceur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;
import org.primefaces.model.DualListModel;
import session.AnneeFacadeLocal;
import session.ClasseFacadeLocal;
import session.ClassematiereFacadeLocal;
import session.MatiereFacadeLocal;
import session.PersonnelFacadeLocal;
import session.PersonnelmatiereclasseanneeFacadeLocal;
import session.TraceurFacadeLocal;

/**
 *
 * @author kenne gervais
 */
public class AbstractPersonnelAnneeMatierCtrl {

    @EJB
    protected PersonnelmatiereclasseanneeFacadeLocal personnelMatiereClasseFacade;

    protected Personnelmatiereclasseannee personnelMatiereClasse;

    protected List<Personnelmatiereclasseannee> PersonnelMatiereClasses;

    protected Personnelmatiereclasseannee selectedPersonnelMatiereClasse;

    protected ClassematiereFacadeLocal classeMatiereFacade;

    @EJB
    protected AnneeFacadeLocal anneeFacade;
    protected Annee annee;
    protected List<Annee> annees;

    @EJB
    protected ClasseFacadeLocal classeFacade;
    protected Classe classe;
    protected List<Classe> classes;

    @EJB
    protected MatiereFacadeLocal matiereFacade;
    protected Matiere matiere;
    protected List<Matiere> matieres;
    protected List<Matiere> matiereSource = new ArrayList<>();
    protected List<Matiere> matiereTarget = new ArrayList<>();
    protected DualListModel<Matiere> dualMatieres = new DualListModel<>();

    @EJB
    protected TraceurFacadeLocal traceurFacade;
    protected Traceur traceur = new Traceur();

    @EJB
    protected PersonnelFacadeLocal personnelFacadeLocal;
    protected List<Personnel> personnels = new ArrayList<>();
    protected Personnel personnel;

    protected String fileName;
    protected StringBuffer personnelTableHtml = new StringBuffer("pas encore implementé");

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Personnel> getPersonnels() {
        personnels = personnelFacadeLocal.findByEtat(true);
        return personnels;
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
        imprimer = personnelMatiereClasseFacade.findAll().isEmpty();
        return imprimer;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public StringBuffer getPersonnelsTableHtml() {
        return personnelTableHtml;
    }

    public Personnelmatiereclasseannee getPersonnelMatiereClasse() {
        return personnelMatiereClasse;
    }

    public void setPersonnelMatiereClasse(Personnelmatiereclasseannee personnelMatiereClasse) {
        this.personnelMatiereClasse = personnelMatiereClasse;
    }

    public Personnelmatiereclasseannee getSelectedPersonnelMatiereClasse() {
        return selectedPersonnelMatiereClasse;
    }

    public void setSelectedPersonnelMatiereClasse(Personnelmatiereclasseannee selectedPersonnelMatiereClasse) {
        this.selectedPersonnelMatiereClasse = selectedPersonnelMatiereClasse;
        modifier = detail = supprimer = selectedPersonnelMatiereClasse == null;
    }

    public List<Personnelmatiereclasseannee> getPersonnelMatiereClasses() {
        PersonnelMatiereClasses = personnelMatiereClasseFacade.findAll();
        return PersonnelMatiereClasses;
    }

    public void setPersonnelMatiereClasses(List<Personnelmatiereclasseannee> PersonnelMatiereClasses) {
        this.PersonnelMatiereClasses = PersonnelMatiereClasses;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Annee> getAnnees() {
        annees = anneeFacade.findByEtat(true);
        return annees;
    }

    public void setAnnees(List<Annee> Annees) {
        this.annees = Annees;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<Classe> getClasses() {
        //classes = classeFacade.findAll();
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public List<Matiere> getMatieres() {
        //matieres.addAll(matiereFacade.findAll());
        return matieres;
    }

    public void setMatieres(List<Matiere> matieres) {
        this.matieres = matieres;
    }

    public List<Matiere> getMatiereTarget() {
        return matiereTarget;
    }

    public void setMatiereTarget(List<Matiere> matiereTarget) {
        this.matiereTarget = matiereTarget;
    }

    public DualListModel<Matiere> getDualMatieres() {
        return dualMatieres;
    }

    public void setDualMatieres(DualListModel<Matiere> dualMatieres) {
        this.dualMatieres = dualMatieres;
    }

    public List<Matiere> getMatiereSource() {
        return matiereSource;
    }

    public void setMatiereSogurce(List<Matiere> matiereSource) {
        this.matiereSource = matiereSource;
    }

    public Traceur getTraceur() {
        return traceur;
    }

    public void setTraceur(Traceur traceur) {
        this.traceur = traceur;
    }

}
