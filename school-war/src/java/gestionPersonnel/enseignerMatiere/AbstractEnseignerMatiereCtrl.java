/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPersonnel.enseignerMatiere;

import entities.Annee;
import entities.Classe;
import entities.Etablissement;
import entities.Matiere;
import entities.Personnel;
import entities.Personnelmatiereclasseannee;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.DualListModel;
import session.AnneeFacadeLocal;
import session.ClasseFacadeLocal;
import session.EtablissementFacadeLocal;
import session.MatiereFacadeLocal;
import session.PersonnelFacadeLocal;
import session.PersonnelmatiereclasseanneeFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractEnseignerMatiereCtrl {

    @EJB
    protected PersonnelmatiereclasseanneeFacadeLocal ensignerMatiereFacade;
    protected Personnelmatiereclasseannee enseigneMatiere;
    protected List<Personnelmatiereclasseannee> enseigneMatieres = new ArrayList<>();
    protected Personnelmatiereclasseannee selectedEnseigneMatiere;

    @EJB
    protected EtablissementFacadeLocal etablissementFacadeLocal;
    protected Etablissement etablissement;
    protected List<Etablissement> etablissements = new ArrayList<>();

    //on injecte la session année
    @EJB
    protected AnneeFacadeLocal anneeFacade;
    protected Annee annee;
    protected List<Annee> annees = new ArrayList<>();

    //on injecte les matieres
    @EJB
    protected MatiereFacadeLocal matiereFacade;
    protected DualListModel<Matiere>dualMatiere = new DualListModel<>();

    //on injecte la sessioon classe
    @EJB
    protected ClasseFacadeLocal classeFacade;
    protected Classe classe;
    protected List<Classe> classes = new ArrayList<>();

    @EJB
    protected PersonnelFacadeLocal personnelFacadeLocal;
    protected List<Personnel> personnels = new ArrayList<>();
    protected Personnel personnel;

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Personnel> getPersonnels() {
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
        imprimer = ensignerMatiereFacade.getByanneeActive(true).isEmpty();
        return imprimer;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Personnelmatiereclasseannee getEnseigneMatiere() {
        return enseigneMatiere;
    }

    public void setEnseigneMatiere(Personnelmatiereclasseannee enseigneMatiere) {
        this.enseigneMatiere = enseigneMatiere;
    }

    public List<Personnelmatiereclasseannee> getEnseigneMatieres() {       
        return enseigneMatieres;
    }

    public void setEnseigneMatieres(List<Personnelmatiereclasseannee> enseigneMatieres) {
        this.enseigneMatieres = enseigneMatieres;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<Classe> getClasses() {
        classes = classeFacade.findAll();
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }


    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Annee> getAnnees() {
        return annees;
    }

    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
    }

    public Personnelmatiereclasseannee getSelectedEnseigneMatiere() {
        return selectedEnseigneMatiere;
    }

    public void setSelectedEnseigneMatiere(Personnelmatiereclasseannee selectedEnseigneMatiere) {
        this.selectedEnseigneMatiere = selectedEnseigneMatiere;
        detail = modifier = supprimer = selectedEnseigneMatiere == null;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public List<Etablissement> getEtablissements() {
        return etablissements;
    }

    public void setEtablissements(List<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }

    public DualListModel<Matiere> getDualMatiere() {
        return dualMatiere;
    }

    public void setDualMatiere(DualListModel<Matiere> dualMatiere) {
        this.dualMatiere = dualMatiere;
    }   

}
