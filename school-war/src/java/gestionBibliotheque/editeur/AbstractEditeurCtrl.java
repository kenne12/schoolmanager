/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionBibliotheque.editeur;

import entities.Editeur;
import entities.Traceur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.EditeurFacadeLocal;
import session.TraceurFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractEditeurCtrl {

    protected String fileName;
    @EJB
    protected EditeurFacadeLocal editeurFacadeLocal;

    protected List<Editeur> editeurs = new ArrayList<>();
    protected Editeur selectedEditeur;
    protected Editeur editeur;

    protected List<String> pays = new ArrayList<>();

    @EJB
    protected TraceurFacadeLocal traceurFcade;
    protected Traceur traceur;

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Editeur> getEditeurs() {
        editeurs = editeurFacadeLocal.findAll();
        return editeurs;
    }

    public Editeur getSelectedEditeur() {
        return selectedEditeur;
    }

    public void setSelectedEditeur(Editeur selectedEditeur) {
        modifier = supprimer = detail = selectedEditeur == null;
        this.selectedEditeur = selectedEditeur;
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
        imprimer = editeurFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public List<String> getPays() {
        pays = new ArrayList<>();
        pays.add("Cameroun");
        pays.add("Canada");
        pays.add("France");
        return pays;
    }

    public void setPays(List<String> pays) {
        this.pays = pays;
    }

    public Traceur getTraceur() {
        return traceur;
    }

    public void setTraceur(Traceur traceur) {
        this.traceur = traceur;
    }

}
