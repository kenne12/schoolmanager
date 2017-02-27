/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionBibliotheque.auteur;

import entities.Auteur;
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
@ManagedBean(name = "auteurCtrl")
@ViewScoped
public class AuteurCtrl extends AbstractAuteurCtrl implements AuteurInterfaceCtrl, Serializable {
    
    @PostConstruct
    private void initAuteur() {
        selectedAuteur = new Auteur();
        auteur = new Auteur();
        traceur = new Traceur();
    }
    
    @Override
    public void enregistrerAuteur() {
        Personnel user = UtilitaireSession.getInstance().getuser();
        
        if (auteur.getNom() == null) {
            JsfUtil.addWarningMessage("Le nom de l'auteur ne peut etre vide");
            return;
        }
        
        if (user != null) {
            auteur.setId(auteurFacadeLocal.nextVal());
            auteurFacadeLocal.create(auteur);
            traceur.setPersonnel(user);
            traceur.setDateaction(new Date());
            traceur.setAction("Enregistrement de l'auteur " + auteur.getNom());
            traceurFcade.create(traceur);
            initAuteur();
            JsfUtil.addSuccessMessage("L'auteur a été enregistré avec succes");
        } else {
            JsfUtil.addWarningMessage("Aucune session utilisateur initiée !");
        }
        
    }
    
    @Override
    public void modifier() {
        Personnel user = UtilitaireSession.getInstance().getuser();
        if (selectedAuteur.getId() == null) {
            JsfUtil.addErrorMessage("veuillez selectionner un auteur");
        } else {
            if (!"".equals(selectedAuteur.getNom())) {
                if (user != null) {
                    traceur.setAction("Modification de l'auteur " + selectedAuteur.getNom());
                    traceur.setDateaction(new Date());
                    traceur.setPersonnel(user);
                    auteurFacadeLocal.edit(selectedAuteur);
                    traceurFcade.create(traceur);
                    initAuteur();
                    JsfUtil.addSuccessMessage("Auteur modifié avec succès");
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
        if (selectedAuteur == null || selectedAuteur.getId() == null) {
            JsfUtil.addSuccessMessage("Veuillez selectionner un auteur !");
        } else {
            if (selectedAuteur.getAuteurlivreList().isEmpty()) {
                if (user != null) {
                    traceur.setAction("Suprression de l'auteur" + selectedAuteur.getNom());
                    traceur.setDateaction(new Date());
                    traceur.setPersonnel(user);
                    auteurFacadeLocal.remove(selectedAuteur);
                    traceurFcade.create(traceur);
                    initAuteur();
                    JsfUtil.addSuccessMessage("opération réussie");
                } else {
                    JsfUtil.addWarningMessage("Aucune session utilisateur initiée , veuillez vous connecter !");
                }
            } else {
                JsfUtil.addWarningMessage("Vous ne pouvez supprimer cet auteur");
            }
        }
    }
    
    @Override
    public void imprimerAuteurPdf() {
        System.out.println("Impression pdf types compte");
        auteurs = auteurFacadeLocal.findAll();
//        fileName = PdfAuteur.etatsAuteur(auteurs);
    }
    
    @Override
    public void imprimerAuteurHtml() {
        System.out.println("Impression html types compte");
    }
    
}
