/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionInscription.eleve;

import entities.Annee;
import entities.Classe;
import entities.Eleve;
import entities.Eleveanneeclasse;
import entities.Etablissement;
import entities.Parent;
import entities.Personnel;
import entities.Traceur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.UploadedFile;
import session.AnneeFacadeLocal;
import session.ClasseFacadeLocal;
import session.EleveFacadeLocal;
import session.EleveanneeclasseFacadeLocal;
import session.EtablissementFacadeLocal;
import session.ParentFacadeLocal;
import session.TraceurFacadeLocal;
import utils.Utilitaires;

/**
 *
 * @author Gervais
 */
public class AbstractEleveCtrl {

    protected String fileName;
    protected String context = Utilitaires.path;

    @EJB
    protected EleveFacadeLocal eleveFacadeLocal;

    protected List<Eleve> eleves;
    protected Eleve selectedEleve;
    protected Eleve eleve;

    @EJB
    protected EtablissementFacadeLocal EtablissementFacadeLocal;
    protected Etablissement Etablissement;
    protected List<Etablissement> etablissements = new ArrayList<>();

    @EJB
    protected TraceurFacadeLocal traceurFacade;
    protected Traceur traceur;

    protected Personnel personnel;

    protected UploadedFile photo;
    protected String imageDir = "/" + Utilitaires.repertoireImageParDefautEleve;

    @EJB
    protected EleveanneeclasseFacadeLocal eleveanneeclasseFacadeLocal;
    protected Eleveanneeclasse eleveAnneeClasse;

    @EJB
    protected ClasseFacadeLocal classeFacadeLocal;
    protected Classe classe;
    protected List<Classe> classes = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    protected Annee annee;
    protected List<Annee> annees;

    @EJB
    protected ParentFacadeLocal parentFacadeLocal;
    protected Parent parent;
    protected List<Parent> parents = new ArrayList<>();

    protected String mode = "";
    protected String inscriptionMode = "oui";

    protected Boolean show = true;
    protected Boolean showEtateleve = false;

    protected String confirmPassword;
    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Eleve> getEleves() {
        eleves = eleveFacadeLocal.findAll();
        return eleves;
    }

    public Eleve getSelectedEleve() {
        return selectedEleve;
    }

    public void setSelectedEleve(Eleve selectedEleve) {
        this.selectedEleve = selectedEleve;

        if (selectedEleve != null) {
            confirmPassword = selectedEleve.getMatricule();
        }
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
        imprimer = eleveFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        modifier = supprimer = detail = eleve == null;
        this.eleve = eleve;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public UploadedFile getPhoto() {
        return photo;
    }

    public void setPhoto(UploadedFile photo) {
        this.photo = photo;
    }

    public String getImageDir() {
        return imageDir;
    }

    public void setImageDir(String imageDir) {
        this.imageDir = imageDir;
    }

    public String getContext() {
        return context;
    }

    public Traceur getTraceur() {
        return traceur;
    }

    public void setTraceur(Traceur traceur) {
        this.traceur = traceur;
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

    public Etablissement getEtablissement() {
        return Etablissement;
    }

    public void setEtablissement(Etablissement Etablissement) {
        this.Etablissement = Etablissement;
    }

    public List<Etablissement> getEtablissements() {
        return etablissements;
    }

    public void setEtablissements(List<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(List<Parent> parents) {
        this.parents = parents;
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

    public String getInscriptionMode() {
        return inscriptionMode;
    }

    public void setInscriptionMode(String inscriptionMode) {
        this.inscriptionMode = inscriptionMode;
    }

    public Boolean getShow() {
        return show;
    }

    public void setShow(Boolean show) {
        this.show = show;
    }

    public Boolean getShowEtateleve() {
        return showEtateleve;
    }

    public void setShowEtateleve(Boolean showEtateleve) {
        this.showEtateleve = showEtateleve;
    }

}
