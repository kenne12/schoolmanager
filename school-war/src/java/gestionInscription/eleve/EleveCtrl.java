/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionInscription.eleve;

import entities.Annee;
import entities.Classe;
import entities.Classecategorie;
import entities.Eleve;
import entities.Eleveanneeclasse;
import entities.Etablissement;
import entities.Parent;
import entities.Personnel;
import entities.Traceur;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.UtilitaireSession;
import utils.Utilitaires;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "eleveCtrl")
@SessionScoped
public class EleveCtrl extends AbstractEleveCtrl implements EleveInterfaceCtrl, Serializable {
    
    @PostConstruct
    private void initEleve() {
        selectedEleve = new Eleve();
        eleve = new Eleve();
        personnel = new Personnel();
        traceur = new Traceur();
        classe = new Classe();
        eleveAnneeClasse = new Eleveanneeclasse();
        annee = anneeFacadeLocal.findByEtatSingle(true);
        Etablissement = new Etablissement();
        parent = new Parent();
        etablissements = EtablissementFacadeLocal.findAll();
        annees = anneeFacadeLocal.findByEtat(true);
    }
    
    public void prepareCreate() {
        try {
            eleve = new Eleve();
            classe = new Classe();
            eleveAnneeClasse = new Eleveanneeclasse();
            classes.clear();
            annee = SessionMBean.getYear();
            annees = anneeFacadeLocal.findByEtablissement(SessionMBean.getSchool());
            classes = classeFacadeLocal.findByEtaBlissement(SessionMBean.getSchool().getId(), true);
            mode = "Create";
            showEtateleve = false;
            parent = new Parent();
            inscriptionMode = "oui";
            show = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void prepareEdit() {
        try {
            if (eleve != null) {
                parent = eleve.getParent();
                mode = "Edit";
                showEtateleve = true;
                annees = anneeFacadeLocal.findByEtat(SessionMBean.getUserAccount().getEtablissement().getId(), true);
                classes = classeFacadeLocal.findByEtaBlissement(SessionMBean.getUserAccount().getEtablissement().getId(), true);
            } else {
                JsfUtil.addWarningMessage("Aucune ligne sélectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void activateInscriptionMode() {
        try {
            if ("oui".equals(inscriptionMode)) {
                show = true;
            } else {
                show = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void enregistrerEleve() {
        try {
            if ("Create".equals(mode)) {
                Eleve result = eleveFacadeLocal.findByMatricule(eleve.getMatricule());
                if (result == null) {
                    
                    if (eleve.getAnneeadmission().after(eleve.getDatenaissance())) {
                        
                        parent.setIdparent(parentFacadeLocal.nextVal());
                        parentFacadeLocal.create(parent);
                        
                        eleve.setPhoto(Utilitaires.nomImageParDefautEleve);
                        eleve.setEtablissement(SessionMBean.getUserAccount().getEtablissement());
                        eleve.setIdeleve(eleveFacadeLocal.nextVal());
                        eleve.setParent(parent);
                        eleve.setEtateleve(true);
                        
                        eleveFacadeLocal.create(eleve);
                        
                        if ("oui".equals(inscriptionMode)) {
                            eleveAnneeClasse.setId(eleveanneeclasseFacadeLocal.nextVal());
                            eleveAnneeClasse.setIdannee(annee);
                            eleveAnneeClasse.setIdclasse(classe);
                            eleveAnneeClasse.setEleve(eleve);
                            eleveanneeclasseFacadeLocal.create(eleveAnneeClasse);
                        }
                        
                        Utilitaires.saveOperation(traceurFacade, "Enregistrement de l'élève" + eleve.getNom() + " " + eleve.getPrenom(), UtilitaireSession.getInstance().getuser());
                        
                        JsfUtil.addSuccessMessage("L'élève a été enregistré avec succès !");
                        
                    } else {
                        JsfUtil.addErrorMessage("la date d'inscription est anterieur par rapport à la date d'inscription !");
                    }
                } else {
                    JsfUtil.addErrorMessage("un eleve utilisant ce matricule existe deja !");
                }
            } else {
                //nous sommes ici en mode edit
                if (eleve != null) {
                    Eleve eleveTemp = eleveFacadeLocal.find(eleve.getIdeleve());
                    eleveFacadeLocal.edit(eleve);
                    parentFacadeLocal.edit(parent);
                    Utilitaires.saveOperation(traceurFacade, "Mise à jour de l'élève -> " + eleveTemp.getNom() + " " + eleveTemp.getPrenom() + " Par -> " + eleve.getNom() + " " + eleve.getPrenom(), UtilitaireSession.getInstance().getuser());
                    JsfUtil.addSuccessMessage("Eleve mis à jour avec succès ! ");
                } else {
                    JsfUtil.addErrorMessage("Aucun eleve sélectionné !");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void modifier() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("user")) {
            personnel = UtilitaireSession.getInstance().getuser();
            if ("".equals(selectedEleve.getSexe())) {
                JsfUtil.addErrorMessage("Veuillez selectionner le sexe");
            } else {
                if (selectedEleve.getAnneeadmission().after(selectedEleve.getDatenaissance())) {
                    traceur.setAction("Mise à Jour de l'élève : " + selectedEleve.getNom() + " " + selectedEleve.getPrenom());
                    traceur.setDateaction(new Date());
                    traceur.setPersonnel(personnel);
                    
                    eleveFacadeLocal.edit(selectedEleve);
                    traceurFacade.create(traceur);
                    initEleve();
                    JsfUtil.addSuccessMessage("Le élève été mis à jour avec succès !");
                } else {
                    JsfUtil.addErrorMessage("la date d'inscription est incorrecte !");
                }
            }
        } else {
            String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/login.html");
            } catch (IOException ex) {
                Logger.getLogger(EleveCtrl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void supprimer() {
        /*if(selectedEleve.getElevelivreemprunteCollection().isEmpty()){
         if(selectedEleve.getAbsenceeleveCollection().isEmpty()){
         if(selectedEleve.getEleveanneeclasseCollection().isEmpty()){
         if(selectedEleve.getEvaluationCollection().isEmpty()){
         if(selectedEleve.getTrancheCollection().isEmpty()){
         if(selectedEleve.getParentCollection().isEmpty()){
         if(selectedEleve.getPunitionCollection().isEmpty()){
         eleveFacadeLocal.remove(selectedEleve);
         initEleve();
         JsfUtil.addSuccessMessage("operation réussie !");
         }else{
         JsfUtil.addErrorMessage("cet eleve ne peut etre supprimé");
         }
                               
         }else{
         JsfUtil.addSuccessMessage("Ce eleve ne peut etre supprimé !");
         }                          
         }else{
         JsfUtil.addSuccessMessage("Ce eleve ne peut etre supprimé !");
         }
         }else{
         JsfUtil.addSuccessMessage("Ce eleve ne peut etre supprimé !");
         }
         }else{
         JsfUtil.addSuccessMessage("Ce eleve ne peut etre supprimé !");
         }
         }else{
         JsfUtil.addSuccessMessage("Ce eleve ne peut etre supprimé !"); 
         }
         }else{
         JsfUtil.addSuccessMessage("Ce eleve ne peut etre supprimé !"); 
         } */
        
    }
    
    public void updateClasse() {
        try {
            if (Etablissement != null) {
                classes = eleve.getEtablissement().getClasseList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void imprimerElevePdf() {
        
    }
    
    @Override
    public void imprimerEleveHtml() {
        System.out.println("Impression html types compte");
    }
    
    @Override
    public boolean handleAddImages() {
        System.out.println("chargement de la photo.................");
        if (photo == null || photo.getFileName() == null || photo.getFileName().equals("")) {
            return false;
        }
        if (!Utilitaires.estExtensionImage(Utilitaires.getExtension(photo.getFileName()))) {
            JsfUtil.addErrorMessage("Le fichier fournis est d'un type non pris en charge");
            return false;
        }
        return Utilitaires.handleFileUpload(photo, Utilitaires.path + "/" + Utilitaires.repertoireImageParDefautEleve + "/" + photo.getFileName());
        //System.out.println("img = "+Utilitaires.path+"/"+Utilitaires.repertoireImageParDefautEleve+"/"+photo.getFileName()) ;
    }
    
    private void createClasseEleve(Eleve eleve, Annee annee, Classecategorie classe) {
        eleveAnneeClasse.setIdannee(annee);
        eleveAnneeClasse.setEleve(eleve);
        eleveAnneeClasse.setIdclasse(classe.getIdclasse());
        eleveanneeclasseFacadeLocal.create(eleveAnneeClasse);
    }
    
}
