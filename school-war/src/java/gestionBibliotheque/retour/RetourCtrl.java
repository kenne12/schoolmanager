/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionBibliotheque.retour;

import entities.Annee;
import entities.Eleve;
import entities.Elevelivreemprunte;
import entities.Livre;
import entities.Personnel;
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
 * @author Gervais
 */
@ManagedBean(name = "retourCtrl")
@ViewScoped
public class RetourCtrl extends AbstractRetourCtrl implements RetourInterfaceCtrl, Serializable {

    @PostConstruct
    private void initLivre() {
        selectedRetour = new Elevelivreemprunte();
        livre = new Livre();
        traceur = new Traceur();
        livre = new Livre();
        eleve = new Eleve();
        annee = new Annee();
        retour = new Elevelivreemprunte();
    }

    @Override
    public void enregistrerRetour() {
        Personnel user = UtilitaireSession.getInstance().getuser();
        if (user != null) {
            Annee an = anneeFacade.findByEtatSingle(true);
            if (eleve.getIdeleve() != null) {
                if (!dualLivre.getTarget().isEmpty()) {
                    for (Livre object : dualLivre.getTarget()) {
                        traceur = new Traceur();
                        Elevelivreemprunte test = retourFacadeLocal.get(eleve.getIdeleve(), object.getIdlivre());
                        traceur.setDateaction(new Date());
                        traceur.setPersonnel(user);
                        test.setEtatemprunt(true);
                        retourFacadeLocal.edit(test);
                        traceur.setAction("L'eleve " + eleve.getNom() + " " + eleve.getPrenom() + " A rétouné le livre " + object.getTitre());
                        traceurFcade.create(traceur);
                    }
                    List<Elevelivreemprunte> update = retourFacadeLocal.get(eleve.getIdeleve(), false);
                    if (!update.isEmpty()) {
                        dualLivre.getSource().clear();
                        for (Elevelivreemprunte obj : update) {
                            dualLivre.getSource().add(obj.getLivre());
                        }
                    } else {
                        dualLivre.getSource().clear();
                    }
                    dualLivre.getTarget().clear();
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addWarningMessage("Aucun livre n'est sélectionné");
                }
            } else {
                JsfUtil.addErrorMessage("Veuillez sélectionner un élève");
            }
        } else {
            JsfUtil.addWarningMessage("Aucune session utilisateur initiée !");
        }
    }

    @Override
    public void modifier() {
        /*Personnel user = UtilitaireSession.getInstance().getuser();
         if ("".equals(selectedLivre.getCodeisbn())) {
         JsfUtil.addErrorMessage("veuillez selectionner un livre");
         } else {
         if (!"".equals(selectedLivre.getTitre())) {
         if (user != null) {
         traceur.setAction("Modification de le livre " + selectedLivre.getTitre());
         traceur.setDateaction(new Date());
         traceur.setPersonnel(user);
         livreFacadeLocal.edit(selectedLivre);
         traceurFcade.create(traceur);
         initLivre();
         JsfUtil.addSuccessMessage("Livre modifié avec succès");
         } else {
         JsfUtil.addWarningMessage("Aucune session utilisateur initiée");
         }
         } else {
         JsfUtil.addWarningMessage("Le nom de l'éditeur ne peut etre vide !");
         }
         }*/
    }

    @Override
    public void supprimer() {
        /*Personnel user = UtilitaireSession.getInstance().getuser();
         if (selectedLivre == null || "".equals(selectedLivre.getCodeisbn())) {
         JsfUtil.addSuccessMessage("Veuillez selectionner un livre !");
         } else {

         if (user != null) {
         traceur.setAction("Suprression de l'livre" + selectedLivre.getTitre());
         traceur.setDateaction(new Date());
         traceur.setPersonnel(user);
         livreFacadeLocal.remove(selectedLivre);
         traceurFcade.create(traceur);
         initLivre();
         JsfUtil.addSuccessMessage("opération réussie");
         } else {
         JsfUtil.addWarningMessage("Aucune session utilisateur initiée , veuillez vous connecter !");
         }

         }*/
    }

    @Override
    public void imprimerRetourPdf() {
        System.out.println("Impression pdf types compte");
        //livres = livreFacadeLocal.findAll();
        //        fileName = PdfLivre.etatsLivre(livres);
    }

    @Override
    public void imprimerRetourHtml() {
        System.out.println("Impression html types compte");
    }

    public void handleEleveChange() {
        annee = anneeFacade.findByEtatSingle(true);
        if (annee != null) {
            if (eleve.getIdeleve() != null) {
                eleve = eleveFacade.find(eleve.getIdeleve());
                dualLivre.getSource().clear();
                dualLivre.getTarget().clear();
                List<Elevelivreemprunte> eleveEmprunts = retourFacadeLocal.get(eleve.getIdeleve(), false);
                if (eleveEmprunts.isEmpty()) {
                    canretour = true;
                    dualLivre.getSource().clear();
                } else {
                    dualLivre.getSource().clear();
                    for (Elevelivreemprunte object : eleveEmprunts) {
                        livreTarget.add(object.getLivre());
                    }
                    canretour = false;
                    dualLivre.setSource(livreTarget);
                }
            } else {
                dualLivre.getSource().clear();
            }
        } else {
            canretour = true;
            dualLivre.getSource().clear();
        }
    }
}
