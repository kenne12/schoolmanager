/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionBibliotheque.livre;

import entities.Editeur;
import entities.Livre;
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
@ManagedBean(name = "livreCtrl")
@ViewScoped
public class LivreCtrl extends AbstractLivreCtrl implements LivreInterfaceCtrl, Serializable {

    @PostConstruct
    private void initLivre() {
        selectedLivre = new Livre();
        livre = new Livre();
        traceur = new Traceur();
        editeur = new Editeur();
    }

    @Override
    public void enregistrerLivre() {
        Personnel user = UtilitaireSession.getInstance().getuser();

        if (livre.getCodeisbn() == null) {
            JsfUtil.addWarningMessage("Le code isbn du livre ne peut etre vide");
        }

        if ("".equals(livre.getTitre())) {
            JsfUtil.addErrorMessage("le titre du livre ne peut etre nul");
        }

        if (user != null) {
            Livre livr = livreFacadeLocal.getEditeur(livre.getCodeisbn());
            if (livr == null) {
                livre.setIdlivre(livreFacadeLocal.nextVal());
                livre.setEmpruntable(true);
                livre.setIdediteur(editeur);
                livreFacadeLocal.create(livre);
                traceur.setPersonnel(user);
                traceur.setDateaction(new Date());
                traceur.setAction("Enregistrement du livre " + livre.getTitre() + " " + livre.getCodeisbn());
                traceurFcade.create(traceur);
                initLivre();
                JsfUtil.addSuccessMessage("Le livre a été enregistré avec succes");
            } else {
                JsfUtil.addErrorMessage("Un livre potant ce code isbn existe deja");
            }
        } else {
            JsfUtil.addWarningMessage("Aucune session utilisateur initiée !");
        }

    }

    @Override
    public void modifier() {
        Personnel user = UtilitaireSession.getInstance().getuser();
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
        }
    }

    @Override
    public void supprimer() {
        Personnel user = UtilitaireSession.getInstance().getuser();
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

        }
    }

    @Override
    public void imprimerLivrePdf() {
        System.out.println("Impression pdf types compte");
        livres = livreFacadeLocal.findAll();
        //        fileName = PdfLivre.etatsLivre(livres);
    }

    @Override
    public void imprimerLivreHtml() {
        System.out.println("Impression html types compte");
    }

}
