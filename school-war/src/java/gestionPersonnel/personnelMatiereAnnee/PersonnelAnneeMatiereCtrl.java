/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPersonnel.personnelMatiereAnnee;

import entities.Annee;
import entities.Classe;
import entities.Classematiere;
import entities.Matiere;
import entities.Personnel;
import entities.Personnelmatiereclasseannee;
import entities.Traceur;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.UtilitaireSession;

/**
 *
 * @author kenne gervais
 */
@ManagedBean(name = "personnelAnneeMatiereCtrl")
@ViewScoped
public class PersonnelAnneeMatiereCtrl extends AbstractPersonnelAnneeMatierCtrl implements PersonnelAnneeMatiereInterfaceCtrl, Serializable {

    @PostConstruct
    private void initPersonnel() {
        personnel = new Personnel();
        annee = new Annee();
        matiere = new Matiere();
        classe = new Classe();
        personnelMatiereClasse = new Personnelmatiereclasseannee();
        selectedPersonnelMatiereClasse = new Personnelmatiereclasseannee();
        traceur = new Traceur();
    }

    @Override
    public void enregistrerAffectation() {
        Personnel userConnected = UtilitaireSession.getInstance().getuser();
        if (userConnected != null) {
            if (annee.getIdannee() != null) {
                if (classe.getIdclasse() != null) {
                    if (!dualMatieres.getTarget().isEmpty()) {
                        if (personnel.getIdpersonnel() != null) {
                            for (int i = 0; i < dualMatieres.getTarget().size(); i++) {
                                personnelMatiereClasse = new Personnelmatiereclasseannee();
                                traceur = new Traceur();

                                personnelMatiereClasse.setIdannee(annee);
                                personnelMatiereClasse.setIdclasse(classe);
                                personnelMatiereClasse.setPersonnel(personnel);
                                //personnelMatiereClasse.setIdmatiere(dualMatieres.getTarget().get(i));

                                traceur.setDateaction(new Date());
                                traceur.setPersonnel(userConnected);
                                //traceur.setAction("Autorise le personnel " + personnel.getNom() + " à enseigner la matiere " + dualMatieres.getTarget().get(i).getIdtypematiere().getLibelle() + " en classe de " + classe.getNom());

                                personnelMatiereClasseFacade.create(personnelMatiereClasse);
                                traceurFacade.create(traceur);
                            }
                            dualMatieres.getTarget().clear();
                            JsfUtil.addSuccessMessage("Opération réussie !");
                        } else {
                            JsfUtil.addErrorMessage("veuillez selectioner le personnel");
                        }
                    } else {
                        JsfUtil.addErrorMessage("La liste de matière est vide!");
                    }
                } else {
                    JsfUtil.addErrorMessage("veuillez selectionner une classe !");
                }
            } else {
                JsfUtil.addErrorMessage("veuillez selectionner une année valide");
            }
        } else {
            JsfUtil.addWarningMessage("Aucune session utilisateur initiée !");
        }
    }

    @Override
    public void modifier() {
        /*if("".equals(selectedPersonnel.getMatriculepersonnel())){
         JsfUtil.addErrorMessage("Veuillez saisir le matricule !");
         }else{

         if(selectedPersonnel.getPassword().equals(confirmPassword)){
         //personnel.setPassword(new ShaHash().hash(personnel.getPassword()));
         personnelFacadeLocal.edit(selectedPersonnel);
         initPersonnel();
         JsfUtil.addSuccessMessage("Le personnel a été mise à jour avec succès !");
         }else{
         JsfUtil.addErrorMessage("Les deux mots de passe sont incorrects !");
         }
 
         }*/
    }

    @Override
    public void supprimer() {
        /*if(selectedPersonnel.getElevelivreemprunteCollection().isEmpty()){
         if(selectedPersonnel.getAbsenceeleveCollection().isEmpty()){
         if(selectedPersonnel.getActiviteCollection().isEmpty()){
         if(selectedPersonnel.getPersonmatiereclasseanneedateCollection().isEmpty()){
         if(selectedPersonnel.getLivreCollection().isEmpty()){
         personnelFacadeLocal.remove(selectedPersonnel);
         initPersonnel();
         JsfUtil.addSuccessMessage("operation réussie !");
         }else{
         JsfUtil.addSuccessMessage("Ce personnel ne peut etre supprimé !");
         }
         }else{
         JsfUtil.addSuccessMessage("Ce personnel ne peut etre supprimé !");
         }
         }else{
         JsfUtil.addSuccessMessage("Ce personnel ne peut etre supprimé !");
         }
         }else{
         JsfUtil.addSuccessMessage("Ce personnel ne peut etre supprimé !"); 
         }
         }else{
         JsfUtil.addSuccessMessage("Ce personnel ne peut etre supprimé !"); 
         } */
    }

    @Override
    public void imprimerAffectationPdf() {
        System.out.println("Impression pdf types compte");
        personnels = personnelFacadeLocal.findAll();
        //fileName = PdfPersonnel.etatsPersonnel(personnels);
    }

    @Override
    public void imprimerAffectationHtml() {
        System.out.println("Impression html types compte");
    }

    public void handleMatiereChange() {
        List<Personnelmatiereclasseannee> personnelMatieres = personnel.getPersonnelmatiereclasseanneeList();
        List<Matiere> matiereAnnees = null;
        if (personnelMatieres.isEmpty()) {
            matiereSource = matiereFacade.findAll();
        } else {
            for (int i = 0; i < personnelMatieres.size(); i++) {
                if (personnelMatieres.get(i).getIdannee().getEtatannee()) {
                    //matiereSource.add(personnelMatieres.get(i).getIdmatiere());
                }
            }
        }
        dualMatieres.setSource(matiereSource);
    }

    public void handleClasseChange() {
        if (classe.getIdclasse() != null) {
            classe = classeFacade.find(classe.getIdclasse());
            if (!matiereSource.isEmpty()) {
                matiereSource.clear();
            }
            List<Classematiere> classeMatieres = (List<Classematiere>) classe.getClassematiereList();
            List<Matiere> matier = null;

            if (!classeMatieres.isEmpty()) {

                Personnelmatiereclasseannee personnelMat;
                for (int i = 0; i < classeMatieres.size(); i++) {
                    personnelMat = personnelMatiereClasseFacade.findByClasseMatiereAnnee(classeMatieres.get(i).getIdmatiere().getIdmatiere(), classeMatieres.get(i).getIdclasse().getIdclasse(), true);
                    if (personnelMat == null) {
                        matiereSource.add(classeMatieres.get(i).getIdmatiere());
                    }
                }
            }
        } else {
            matiereSource.clear();
        }
        dualMatieres.setSource(matiereSource);
    }

}
