/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionBibliotheque.livre;

import entities.Editeur;
import entities.Livre;
import entities.Traceur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.EditeurFacadeLocal;
import session.LivreFacadeLocal;
import session.TraceurFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractLivreCtrl {

    @EJB
    protected LivreFacadeLocal livreFacadeLocal;

    protected List<Livre> livres;
    protected Livre selectedLivre;
    protected Livre livre;

    @EJB
    protected EditeurFacadeLocal editeurFacade;
    protected Editeur editeur;
    protected List<Editeur> editeurs = new ArrayList<>();

    @EJB
    protected TraceurFacadeLocal traceurFcade;
    protected Traceur traceur;

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Livre> getLivres() {
        livres = livreFacadeLocal.findAll();
        return livres;
    }

    public Livre getSelectedLivre() {
        return selectedLivre;
    }

    public void setSelectedLivre(Livre selectedLivre) {
        modifier = supprimer = detail = selectedLivre == null;
        this.selectedLivre = selectedLivre;
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
        imprimer = livreFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Traceur getTraceur() {
        return traceur;
    }

    public void setTraceur(Traceur traceur) {
        this.traceur = traceur;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public List<Editeur> getEditeurs() {
        editeurs = editeurFacade.findAll();
        return editeurs;
    }

    public void setEditeurs(List<Editeur> editeurs) {
        this.editeurs = editeurs;
    }

}
