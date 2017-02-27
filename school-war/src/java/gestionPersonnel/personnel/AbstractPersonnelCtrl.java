/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPersonnel.personnel;

import entities.Etablissement;
import entities.EtablissementPersonnel;
import entities.Fonction;
import entities.Matiere;
import entities.Personnel;
import entities.PhotoPersonnel;
import entities.Qualification;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.DualListModel;
import org.primefaces.model.UploadedFile;
import session.EtablissementFacadeLocal;
import session.EtablissementPersonnelFacadeLocal;
import session.FonctionFacadeLocal;
import session.MatiereFacadeLocal;
import session.PersonnelFacadeLocal;
import session.PhotoPersonnelFacadeLocal;
import session.QualificationFacadeLocal;
import session.TraceurFacadeLocal;
import utils.SessionMBean;
import utils.Utilitaires;

/**
 *
 * @author Gervais
 */
public class AbstractPersonnelCtrl {

    protected String fileName;
    @EJB
    protected PersonnelFacadeLocal personnelFacadeLocal;
    protected Personnel personnel;
    protected Personnel selectedPersonnel;
    protected List<Personnel> personnels = new ArrayList<>();
    protected List<Personnel> personnelActifs = new ArrayList<>();

    @EJB
    protected PhotoPersonnelFacadeLocal photoPersonnelFacadeLocal;
    protected List<PhotoPersonnel> images = new ArrayList<>();
    protected List<PhotoPersonnel> galeries = new ArrayList<>();
    protected PhotoPersonnel selectedPhoto;

    @EJB
    protected EtablissementFacadeLocal etablissementFacadeLocal;
    protected DualListModel<Etablissement> dualList = new DualListModel<>();

    @EJB
    protected EtablissementPersonnelFacadeLocal etablissementPersonnelFacadeLocal;
    protected EtablissementPersonnel etablissementPersonnel;

    @EJB
    protected QualificationFacadeLocal qualificationFacadeLocal;
    protected List<Qualification> qualifications = new ArrayList<>();
    protected List<Qualification> selectedQualifications = new ArrayList<>();

    @EJB
    protected MatiereFacadeLocal matiereFacadeLocal;
    protected List<Matiere> matieres = new ArrayList<>();
    protected List<Matiere> selectedMatieres = new ArrayList<>();

    @EJB
    protected FonctionFacadeLocal fonctionFacadeLocal;
    protected Fonction fonction;
    protected List<Fonction> fonctions = new ArrayList<>();

    @EJB
    protected TraceurFacadeLocal traceurFacadeLocal;

    protected String mode;

    protected UploadedFile photo;
    protected String filename = utils.Utilitaires.nomImageParDefautPersonnel;
    protected String imageDir = Utilitaires.repertoireImageParDefautPersonnel;

    protected boolean showEtat = false;
    protected boolean showQualificationPanel = false;

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;
    protected boolean photoSelected;

    protected String theme = "";

    public List<Personnel> getPersonnels() {
        personnels = personnelFacadeLocal.findAll();
        return personnels;
    }

    public Personnel getSelectedPersonnel() {
        return selectedPersonnel;
    }

    public void setSelectedPersonnel(Personnel selectedPersonnel) {
        this.selectedPersonnel = selectedPersonnel;
        modifier = supprimer = detail = selectedPersonnel == null;
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
        imprimer = personnelFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
        modifier = supprimer = detail = selectedPersonnel == null;

    }

    public List<Personnel> getPersonnelActifs() {
        personnelActifs = personnelFacadeLocal.findByEtat(true);
        return personnelActifs;
    }

    public void setPersonnelActifs(List<Personnel> personnelActifs) {
        this.personnelActifs = personnelActifs;
    }

    public DualListModel<Etablissement> getDualList() {
        return dualList;
    }

    public void setDualList(DualListModel<Etablissement> dualList) {
        this.dualList = dualList;
    }

    public EtablissementPersonnel getEtablissementPersonnel() {
        return etablissementPersonnel;
    }

    public void setEtablissementPersonnel(EtablissementPersonnel etablissementPersonnel) {
        this.etablissementPersonnel = etablissementPersonnel;
    }

    public boolean isShowEtat() {
        return showEtat;
    }

    public boolean isShowQualificationPanel() {
        return showQualificationPanel;
    }

    public void setShowQualificationPanel(boolean showQualificationPanel) {
        this.showQualificationPanel = showQualificationPanel;
    }

    public void setShowEtat(boolean showEtat) {
        this.showEtat = showEtat;
    }

    public String getTheme() {
        try {
            if (SessionMBean.getUserAccount() != null) {
                theme = personnelFacadeLocal.find(SessionMBean.getUserAccount().getPersonnel().getIdpersonnel()).getTheme();
                if (theme.equals(null) || "".equals(theme)) {
                    theme = "cruze";
                }
            } else {
                theme = "cruze";
            }
        } catch (Exception e) {
            theme = "cruze";
        }
        return theme;
    }

    public void setTheme(String theme) {
        if (SessionMBean.getUserAccount() != null) {
            Personnel perso;
            perso = personnelFacadeLocal.find(SessionMBean.getUserAccount().getPersonnel().getIdpersonnel());
            perso.setTheme(theme);
            personnelFacadeLocal.edit(perso);
            System.err.println("modifié" + theme);
        }
    }

    public List<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(List<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    public List<Qualification> getSelectedQualifications() {
        return selectedQualifications;
    }

    public void setSelectedQualifications(List<Qualification> selectedQualifications) {
        this.selectedQualifications = selectedQualifications;
    }

    public List<Matiere> getMatieres() {
        return matieres;
    }

    public void setMatieres(List<Matiere> matieres) {
        this.matieres = matieres;
    }

    public List<Matiere> getSelectedMatieres() {
        return selectedMatieres;
    }

    public void setSelectedMatieres(List<Matiere> selectedMatieres) {
        this.selectedMatieres = selectedMatieres;
    }

    public UploadedFile getPhoto() {
        return photo;
    }

    public void setPhoto(UploadedFile photo) {
        this.photo = photo;
    }

    public List<PhotoPersonnel> getImages() {
        return images;
    }

    public void setImages(List<PhotoPersonnel> images) {
        this.images = images;
    }

    public PhotoPersonnel getSelectedPhoto() {
        photoSelected = selectedPhoto == null;
        return selectedPhoto;
    }

    public void setSelectedPhoto(PhotoPersonnel selectedPhoto) {
        this.selectedPhoto = selectedPhoto;
    }

    public String getImageDir() {
        return imageDir;
    }

    public void setImageDir(String imageDir) {
        this.imageDir = imageDir;
    }

    public boolean isPhotoSelected() {
        return photoSelected;
    }

    public void setPhotoSelected(boolean photoSelected) {
        this.photoSelected = photoSelected;
    }

    public List<PhotoPersonnel> getGaleries() {
        return galeries;
    }

    public void setGaleries(List<PhotoPersonnel> galeries) {
        this.galeries = galeries;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public List<Fonction> getFonctions() {
        return fonctions;
    }

    public void setFonctions(List<Fonction> fonctions) {
        this.fonctions = fonctions;
    }

}
