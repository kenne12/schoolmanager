/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPersonnel.personnel;

import entities.EtablissementPersonnel;
import entities.Matiere;
import entities.Personnel;
import entities.PhotoPersonnel;
import entities.Qualification;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.UtilitaireSession;
import utils.Utilitaires;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "personnelCtrl")
@ViewScoped
public class PersonnelCtrl extends AbstractPersonnelCtrl implements PersonnelInterfaceCtrl, Serializable {

    @PostConstruct
    private void initPersonnel() {
        try {
            selectedPersonnel = new Personnel();
            personnel = new Personnel();
            etablissementPersonnel = new EtablissementPersonnel();
            selectedPhoto = new PhotoPersonnel();
        } catch (Exception ex) {
            Logger.getLogger(PersonnelCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void prepareCreate() {
        try {
            mode = "Create";
            personnel = new Personnel();
            personnel.setEnseignant(true);
            fonctions = fonctionFacadeLocal.findByEtat(SessionMBean.getSchool(), true);
            etablissementPersonnel = new EtablissementPersonnel();
            selectedPhoto = new PhotoPersonnel();
            showEtat = false;
            showQualificationPanel = true;
            selectedMatieres.clear();
            matieres = matiereFacadeLocal.findAll();
            galeries = photoPersonnelFacadeLocal.findByEtablissement(SessionMBean.getUserAccount().getEtablissement().getId(), true);
            images = photoPersonnelFacadeLocal.findByEtablissement(SessionMBean.getUserAccount().getEtablissement().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        try {
            mode = "Edit";
            showEtat = true;
            showQualificationPanel = false;
            fonctions = fonctionFacadeLocal.findByEtat(SessionMBean.getSchool(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareAddPhoto() {
        try {
            selectedPhoto = new PhotoPersonnel();
            galeries = photoPersonnelFacadeLocal.findByEtablissement(SessionMBean.getUserAccount().getEtablissement().getId(), true);
            images = photoPersonnelFacadeLocal.findByEtablissement(SessionMBean.getUserAccount().getEtablissement().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewGalerie() {
        try {
            galeries = photoPersonnelFacadeLocal.findByEtablissement(SessionMBean.getUserAccount().getEtablissement().getId(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showQualification() {
        try {
            if (mode.equals("Edit")) {
                showQualificationPanel = false;
                return;
            }
            if (personnel.getEnseignant()) {
                showQualificationPanel = true;
            } else {
                showQualificationPanel = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enregistrerPersonnel() {
        try {
            if ("Create".equals(mode)) {
                List<Personnel> results = personnelFacadeLocal.findByMatricule(personnel.getMatriculepersonnel());
                if (results.isEmpty()) {

                    personnel.setIdpersonnel(personnelFacadeLocal.nextVal());
                    personnel.setEtatpersonnel(true);
                    personnel.setTheme("hot-sneaks");
                    personnel.setAdmin(false);
                    personnelFacadeLocal.create(personnel);

                    etablissementPersonnel.setPersonnel(personnel);
                    etablissementPersonnel.setId(etablissementPersonnelFacadeLocal.nextVal());
                    etablissementPersonnel.setEtablissement(SessionMBean.getUserAccount().getEtablissement());
                    etablissementPersonnelFacadeLocal.create(etablissementPersonnel);

                    if (personnel.getEnseignant()) {
                        if (!selectedMatieres.isEmpty()) {
                            for (Matiere m : selectedMatieres) {
                                Qualification qualification = new Qualification();
                                qualification.setId(qualificationFacadeLocal.nextVal());
                                qualification.setEtablissement(etablissementPersonnel.getEtablissement());
                                qualification.setPersonnel(personnel);
                                qualification.setMatiere(m);
                                qualificationFacadeLocal.create(qualification);
                            }
                        } else {
                            JsfUtil.addErrorMessage("Aucune qualification choisie");
                        }
                    }
                    Utilitaires.saveOperation(traceurFacadeLocal, "Enregistrement du personnel -> " + personnel.getNom() + " " + personnel.getPrenom(), UtilitaireSession.getInstance().getuser());

                    JsfUtil.addSuccessMessage("Le personnel a été enregistré avec succès !");
                } else {
                    JsfUtil.addErrorMessage("Un personnel utilisant ce matricule existe deja !");
                }
            } else {
                if (personnel != null) {
                    Personnel personnelTemp = personnelFacadeLocal.find(personnel.getIdpersonnel());
                    personnelFacadeLocal.edit(personnel);
                    Utilitaires.saveOperation(traceurFacadeLocal, "Mis à jour du personnel -> " + personnelTemp.getNom() + " " + personnelTemp.getPrenom() + " par -> " + personnel.getNom() + " " + personnel.getPrenom(), UtilitaireSession.getInstance().getuser());
                    JsfUtil.addErrorMessage("Personel mis à jour avec succès");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne selectionnée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifier() {
        if ("".equals(selectedPersonnel.getMatriculepersonnel())) {
            JsfUtil.addErrorMessage("Veuillez saisir le matricule !");
        } else {
            personnelFacadeLocal.edit(selectedPersonnel);
            initPersonnel();
            JsfUtil.addSuccessMessage("Le personnel a été mise à jour avec succès !");
        }
    }

    @Override
    public void supprimer() {
        if (selectedPersonnel.getEtablissementPersonnelList().isEmpty()) {
            if (selectedPersonnel.getAbsenceeleveList().isEmpty()) {
                if (selectedPersonnel.getActiviteList().isEmpty()) {
                    if (selectedPersonnel.getPersonnelmatiereclasseanneeList().isEmpty()) {

                        personnelFacadeLocal.remove(selectedPersonnel);
                        initPersonnel();
                        JsfUtil.addSuccessMessage("operation réussie !");

                    } else {
                        JsfUtil.addSuccessMessage("Ce personnel ne peut etre supprimé !");
                    }
                } else {
                    JsfUtil.addSuccessMessage("Ce personnel ne peut etre supprimé !");
                }
            } else {
                JsfUtil.addSuccessMessage("Ce personnel ne peut etre supprimé !");
            }
        } else {
            JsfUtil.addSuccessMessage("Ce personnel ne peut etre supprimé !");
        }
    }

    public void handleDellImages() {
        try {
            if (selectedPhoto != null) {
                if (selectedPhoto.getEtat()) {
                    photoPersonnelFacadeLocal.remove(selectedPhoto);
                    List<PhotoPersonnel> temp = photoPersonnelFacadeLocal.findByPersonnel(selectedPhoto.getPersonnel().getIdpersonnel(), SessionMBean.getUserAccount().getEtablissement().getId());
                    if (!temp.isEmpty()) {
                        temp.get(temp.size() - 1).setEtat(true);
                        photoPersonnelFacadeLocal.edit(temp.get(temp.size() - 1));
                        personnel.setPhoto(temp.get(temp.size() - 1).getChemin());
                        personnelFacadeLocal.edit(personnel);
                    } else {
                        personnel.setPhoto("default.JPG");
                        personnelFacadeLocal.edit(personnel);
                    }
                } else {
                    photoPersonnelFacadeLocal.remove(selectedPhoto);
                }
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Aucune photo selectionnée");
            }
            galeries = photoPersonnelFacadeLocal.findByEtablissement(SessionMBean.getUserAccount().getEtablissement().getId(), true);
            images = photoPersonnelFacadeLocal.findByEtablissement(SessionMBean.getUserAccount().getEtablissement().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTheme(String theme) {
        try {
            if (SessionMBean.getUserAccount() != null) {
                Personnel perso;
                perso = personnelFacadeLocal.find(SessionMBean.getUserAccount().getPersonnel().getIdpersonnel());
                perso.setTheme(theme);
                personnelFacadeLocal.edit(perso);
            }
        } catch (Exception e) {
            this.theme = "cruze";
        }
    }

    @Override
    public void imprimerPersonnelPdf() {
        System.out.println("Impression pdf types compte");
        personnels = personnelFacadeLocal.findAll();
    }

    @Override
    public void imprimerPersonnelHtml() {
        System.out.println("Impression html types compte");
    }

    public void uplaodImages(FileUploadEvent fileUploadEvent) {
        try {
            photo = fileUploadEvent.getFile();
            System.out.println("chargement de la photo.................");
            if (photo == null || photo.getFileName() == null || photo.getFileName().equals("")) {
                return;
            }
            if (!Utilitaires.estExtensionImage(Utilitaires.getExtension(photo.getFileName()))) {
                JsfUtil.addErrorMessage("Le fichier fournis est d'un type non pris en charge");
                return;
            }

            if (personnel != null) {

                List<PhotoPersonnel> temp = photoPersonnelFacadeLocal.findByPersonnel(personnel.getIdpersonnel(), SessionMBean.getUserAccount().getEtablissement().getId());
                if (!temp.isEmpty()) {
                    for (PhotoPersonnel p : temp) {
                        p.setEtat(false);
                        photoPersonnelFacadeLocal.edit(p);
                    }
                }

                fileName = photo.getFileName();
                PhotoPersonnel photoPersonnel = new PhotoPersonnel();
                photoPersonnel.setIdphoto(photoPersonnelFacadeLocal.nextId());
                String nom = "PHOTO_" + personnel.getNom() + "_" + photoPersonnel.getIdphoto() + "." + Utilitaires.getExtension(photo.getFileName());

                //Utilitaires.handleFileUpload(photo, Utilitaires.path + "/" + Utilitaires.repertoireImageParDefautPersonnel + "/" + nom);
                Utilitaires.handleFileUpload(fileUploadEvent, Utilitaires.path + "/" + Utilitaires.repertoireImageParDefautPersonnel + "/" + nom);

                photoPersonnel.setNom(nom);
                photoPersonnel.setEtat(true);
                photoPersonnel.setChemin(nom);
                photoPersonnel.setPersonnel(personnel);
                photoPersonnel.setEtablissement(SessionMBean.getUserAccount().getEtablissement());
                photoPersonnelFacadeLocal.create(photoPersonnel);

                personnel.setPhoto(nom);
                personnelFacadeLocal.edit(personnel);

                galeries = photoPersonnelFacadeLocal.findByEtablissement(SessionMBean.getUserAccount().getEtablissement().getId(), true);
                images = photoPersonnelFacadeLocal.findByEtablissement(SessionMBean.getUserAccount().getEtablissement().getId());
                System.err.println("");
            } else {
                JsfUtil.addErrorMessage("Aucun personnel selectionné");
            }
            personnel = new Personnel();
            JsfUtil.addSuccessMessage("Opération réussie");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void handleAddImages() {
        try {

            System.out.println("chargement de la photo.................");
            if (photo == null || photo.getFileName() == null || photo.getFileName().equals("")) {
                return;
            }
            if (!Utilitaires.estExtensionImage(Utilitaires.getExtension(photo.getFileName()))) {
                JsfUtil.addErrorMessage("Le fichier fournis est d'un type non pris en charge");
                return;
            }

            if (personnel != null) {

                List<PhotoPersonnel> temp = photoPersonnelFacadeLocal.findByPersonnel(personnel.getIdpersonnel(), SessionMBean.getUserAccount().getEtablissement().getId());
                if (!temp.isEmpty()) {
                    for (PhotoPersonnel p : temp) {
                        p.setEtat(false);
                        photoPersonnelFacadeLocal.edit(p);
                    }
                }

                fileName = photo.getFileName();
                PhotoPersonnel photoPersonnel = new PhotoPersonnel();
                photoPersonnel.setIdphoto(photoPersonnelFacadeLocal.nextId());
                String nom = "PHOTO_" + personnel.getNom() + "_" + photoPersonnel.getIdphoto() + "." + Utilitaires.getExtension(photo.getFileName());
                Utilitaires.handleFileUpload(photo, Utilitaires.path + "/" + Utilitaires.repertoireImageParDefautPersonnel + "/" + nom);
                photoPersonnel.setNom(nom);
                photoPersonnel.setEtat(true);
                photoPersonnel.setChemin(nom);
                photoPersonnel.setPersonnel(personnel);
                photoPersonnel.setEtablissement(SessionMBean.getUserAccount().getEtablissement());
                photoPersonnelFacadeLocal.create(photoPersonnel);

                personnel.setPhoto(nom);
                personnelFacadeLocal.edit(personnel);

                galeries = photoPersonnelFacadeLocal.findByEtablissement(SessionMBean.getUserAccount().getEtablissement().getId(), true);
                images = photoPersonnelFacadeLocal.findByEtablissement(SessionMBean.getUserAccount().getEtablissement().getId());
            } else {
                JsfUtil.addErrorMessage("Aucun personnel selectionné");
            }
            personnel = new Personnel();
            JsfUtil.addSuccessMessage("Opération réussie");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
