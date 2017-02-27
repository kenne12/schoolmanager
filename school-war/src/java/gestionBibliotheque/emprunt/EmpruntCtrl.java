/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionBibliotheque.emprunt;

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
import utils.PrintUtils;
import utils.UtilitaireSession;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "empruntCtrl")
@ViewScoped
public class EmpruntCtrl extends AbstractEmpruntCtrl implements EmpruntInterfaceCtrl, Serializable {

    @PostConstruct
    private void initLivre() {
        selectedEmprunt = new Elevelivreemprunte();
        livre = new Livre();
        traceur = new Traceur();
        livre = new Livre();
        eleve = new Eleve();
        annee = new Annee();
        emprunt = new Elevelivreemprunte();
    }

    @Override
    public void enregistrerEmprunt() {
        Personnel user = UtilitaireSession.getInstance().getuser();

        if (user != null) {
            Annee an = anneeFacade.findByEtatSingle(true);
            if (eleve.getIdeleve() != null) {
                if (!dualLivre.getTarget().isEmpty()) {
                    if (dualLivre.getSource().size() < 3) {
                        for (Livre object : dualLivre.getTarget()) {
                            emprunt = new Elevelivreemprunte();
                            traceur = new Traceur();
                            Elevelivreemprunte test = empruntFacadeLocal.get(eleve.getIdeleve(), object.getIdlivre());
                            traceur.setDateaction(new Date());
                            traceur.setPersonnel(user);
                            if (test == null) {
                                emprunt.setId(empruntFacadeLocal.nextVal());
                                emprunt.setDateemprunt(new Date());
                                emprunt.setDateremise(null);
                                emprunt.setEleve(eleve);
                                emprunt.setLivre(object);
                                emprunt.setEtatemprunt(false);
                                emprunt.setIdannee(an);
                                traceur.setAction("Emprunt du livre " + object.getTitre() + " a l'éleve " + eleve.getNom());
                                empruntFacadeLocal.create(emprunt);
                                traceurFcade.create(traceur);
                            } else {
                                test.setDateemprunt(new Date());
                                test.setDateremise(null);
                                test.setEtatemprunt(false);
                                test.setIdannee(annee);
                                traceur.setAction("L'eleve " + eleve.getNom() + " " + eleve.getPrenom() + " A réeùprunté le livre " + object.getTitre());
                                traceurFcade.create(traceur);
                            }
                        }
                        dualLivre.getSource().clear();
                        dualLivre.getTarget().clear();
                        JsfUtil.addSuccessMessage("Opération réussie");
                    } else {
                        JsfUtil.addWarningMessage("vous ne pouvez emprunter plus de 2 documents !");
                    }
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
    public void imprimerEmpruntPdf() {
        System.err.println("c'est cool");
        Annee an = anneeFacade.findByEtatSingle(true);
        Personnel user = UtilitaireSession.getInstance().getuser();
        if (user != null) {
            if (an != null) {
                PrintUtils.printLentBook(emprunts, an);
                traceur.setAction("Impression du rapport d'emprunt de documents");
                traceur.setDateaction(new Date());
                traceur.setPersonnel(user);
                traceurFcade.create(traceur);
                visualise = false;
                JsfUtil.addSuccessMessage("Impression réalisée avec succès");
            } else {
                System.err.println("c'est pas bon");
            }
        } else {
            JsfUtil.addWarningMessage("Aucune session utilisateur initiée, veuillez vous connecter !");
        }
    }

    @Override
    public void imprimerEmpruntHtml() {
        System.out.println("Impression html types compte");
    }

    public void handleEleveChange() {
        annee = anneeFacade.findByEtatSingle(true);
        if (annee != null) {
            if (eleve.getIdeleve() != null) {
                eleve = eleveFacade.find(eleve.getIdeleve());
                dualLivre.getSource().clear();
                dualLivre.getTarget().clear();
                List<Elevelivreemprunte> eleveEmprunts = empruntFacadeLocal.get(annee.getIdannee(), eleve.getIdeleve(), false);
                if (eleveEmprunts.isEmpty()) {
                    canemprunt = false;
                    livreSource = livreFacade.findAll();
                    dualLivre.setSource(livreSource);
                } else {
                    dualLivre.getSource().clear();
                    for (Elevelivreemprunte object : eleveEmprunts) {
                        livreTarget.add(object.getLivre());
                    }
                    canemprunt = true;
                    dualLivre.setTarget(livreTarget);
                }
            } else {
                canemprunt = true;
                dualLivre.getSource().clear();
            }
        } else {
            dualLivre.getSource().clear();
        }
    }

    public void nothing() {
        System.err.println("imprimer avec succes");
    }
}
