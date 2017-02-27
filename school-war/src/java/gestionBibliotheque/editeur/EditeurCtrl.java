/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionBibliotheque.editeur;

import entities.Editeur;
import entities.Personnel;
import entities.Traceur;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.UtilitaireSession;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "editeurCtrl")
@ViewScoped
public class EditeurCtrl extends AbstractEditeurCtrl implements EditeurInterfaceCtrl, Serializable {

    @PostConstruct
    private void initEditeur() {
        selectedEditeur = new Editeur();
        editeur = new Editeur();
        traceur = new Traceur();
    }

    @Override
    public void enregistrerEditeur() {
        Personnel user = UtilitaireSession.getInstance().getuser();

        if (editeur.getNom() == null) {
            JsfUtil.addWarningMessage("Le nom de l'editeur ne peut etre vide");
        }
        Editeur edit = editeurFacadeLocal.find(editeur.getNom());
        if (edit != null) {
            JsfUtil.addErrorMessage("Un editeur ayant ce nom  existe déjà");
        } else {
            if (user != null) {
                editeurFacadeLocal.create(editeur);
                traceur.setPersonnel(user);
                traceur.setDateaction(new Date());
                traceur.setAction("Enregistrement de l'éditeur " + editeur.getNom());
                traceurFcade.create(traceur);
                initEditeur();
                JsfUtil.addSuccessMessage("L'éditeur a été enregistré avec succes");
            } else {
                JsfUtil.addWarningMessage("Aucune session utilisateur initiée !");
            }

        }
    }

    @Override
    public void modifier() {
        Personnel user = UtilitaireSession.getInstance().getuser();
        if (selectedEditeur.getIdediteur() == null) {
            JsfUtil.addErrorMessage("veuillez selectionner un éditeur");
        } else {
            if (!"".equals(selectedEditeur.getNom())) {
                if (user != null) {
                    traceur.setAction("Modification de l'éditeur " + selectedEditeur.getNom());
                    traceur.setDateaction(new Date());
                    traceur.setPersonnel(user);
                    editeurFacadeLocal.edit(selectedEditeur);
                    traceurFcade.create(traceur);
                    initEditeur();
                    JsfUtil.addSuccessMessage("Editeur modifié avec succès");
                } else {
                    JsfUtil.addWarningMessage("Aucune session utilisateur initiée");
                }
            } else {
                JsfUtil.addWarningMessage("Le nom de l'éditeur ne peut etre vide !");
            }
        }
    }

    @Override
    public void supprimer() {
        Personnel user = UtilitaireSession.getInstance().getuser();
        if (selectedEditeur == null || selectedEditeur.getIdediteur() == null) {
            JsfUtil.addSuccessMessage("Veuillez selectionner un editeur !");
        } else {
            if (selectedEditeur.getLivreList().isEmpty()) {
                if (user != null) {
                    traceur.setAction("Suprression de l'éditeur" + selectedEditeur.getNom());
                    traceur.setDateaction(new Date());
                    traceur.setPersonnel(user);
                    editeurFacadeLocal.remove(selectedEditeur);
                    traceurFcade.create(traceur);
                    initEditeur();
                    JsfUtil.addSuccessMessage("opération réussie");
                } else {
                    JsfUtil.addWarningMessage("Aucune session utilisateur initiée , veuillez vous connecter !");
                }
            } else {
                JsfUtil.addWarningMessage("Vous ne pouvez supprimer cet éditeur");
            }
        }
    }

    @Override
    public void imprimerEditeurPdf() {
        System.out.println("Impression pdf types compte");
        editeurs = editeurFacadeLocal.findAll();
//        fileName = PdfEditeur.etatsEditeur(editeurs);
    }

    @Override
    public void imprimerEditeurHtml() {
        System.out.println("Impression html types compte");
    }

}
