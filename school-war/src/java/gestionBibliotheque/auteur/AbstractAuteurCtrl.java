/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionBibliotheque.auteur;

import entities.Auteur;
import entities.Traceur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.AuteurFacadeLocal;
import session.TraceurFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractAuteurCtrl {

    protected String fileName;
    @EJB
    protected AuteurFacadeLocal auteurFacadeLocal;

    protected List<Auteur> auteurs;
    protected Auteur selectedAuteur;
    protected Auteur auteur;

    protected List<String> pays = new ArrayList<>();

    @EJB
    protected TraceurFacadeLocal traceurFcade;
    protected Traceur traceur;

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Auteur> getAuteurs() {
        auteurs = auteurFacadeLocal.findAll();
        return auteurs;
    }

    public Auteur getSelectedAuteur() {
        return selectedAuteur;
    }

    public void setSelectedAuteur(Auteur selectedAuteur) {
        modifier = supprimer = detail = selectedAuteur == null;
        this.selectedAuteur = selectedAuteur;
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
        imprimer = auteurFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public List<String> getPays() {
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
