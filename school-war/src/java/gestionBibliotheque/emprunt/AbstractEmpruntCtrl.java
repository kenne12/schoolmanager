/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionBibliotheque.emprunt;

import entities.Annee;
import entities.Eleve;
import entities.Eleveanneeclasse;
import entities.Livre;
import entities.Elevelivreemprunte;

import entities.Traceur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.DualListModel;
import session.AnneeFacadeLocal;
import session.EleveFacadeLocal;
import session.EleveanneeclasseFacadeLocal;
import session.LivreFacadeLocal;
import session.ElevelivreemprunteFacadeLocal;

import session.TraceurFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractEmpruntCtrl {

    @EJB
    protected ElevelivreemprunteFacadeLocal empruntFacadeLocal;

    protected List<Elevelivreemprunte> emprunts;
    protected Elevelivreemprunte selectedEmprunt;
    protected Elevelivreemprunte emprunt;

    @EJB
    protected LivreFacadeLocal livreFacade;
    protected Livre livre;
    protected List<Livre> livres = new ArrayList<>();
    protected List<Livre> livreSource = new ArrayList<>();
    protected List<Livre> livreTarget = new ArrayList<>();
    protected DualListModel<Livre> dualLivre = new DualListModel<>();

    @EJB
    protected EleveFacadeLocal eleveFacade;
    protected Eleve eleve;
    protected List<Eleve> eleves = new ArrayList<>();

    @EJB
    protected TraceurFacadeLocal traceurFcade;
    protected Traceur traceur;

    @EJB
    protected AnneeFacadeLocal anneeFacade;
    protected Annee annee;
    @EJB
    protected EleveanneeclasseFacadeLocal eleveAnneeClasseFacade;
    protected List<Eleveanneeclasse> eleveanneeclasses;
    protected Eleveanneeclasse eleveanneeclasse;

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;
    protected boolean canemprunt = true;
    protected boolean visualise = true;

    public List<Elevelivreemprunte> getElevelivreempruntes() {
        emprunts = empruntFacadeLocal.findAll();
        return emprunts;
    }

    public Elevelivreemprunte getSelectedEmprunt() {
        return selectedEmprunt;
    }

    public void setSelectedEmprunt(Elevelivreemprunte selectedElevelivreemprunte) {
        modifier = supprimer = detail = selectedElevelivreemprunte == null;
        this.selectedEmprunt = selectedElevelivreemprunte;
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
        imprimer = empruntFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Elevelivreemprunte getElevelivreemprunte() {
        return emprunt;
    }

    public boolean isCanemprunt() {
        return canemprunt;
    }

    public void setCanemprunt(boolean canemprunt) {
        this.canemprunt = canemprunt;
    }

    public boolean isVisualise() {
        return visualise;
    }

    public void setElevelivreemprunte(Elevelivreemprunte emprunt) {
        this.emprunt = emprunt;
    }

    public Traceur getTraceur() {
        return traceur;
    }

    public void setTraceur(Traceur traceur) {
        this.traceur = traceur;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public List<Livre> getLivres() {
        livres = livreFacade.findAll();
        return livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    public List<Elevelivreemprunte> getEmprunts() {
        emprunts = empruntFacadeLocal.get(false);
        return emprunts;
    }

    public void setEmprunts(List<Elevelivreemprunte> emprunts) {
        this.emprunts = emprunts;
    }

    public Elevelivreemprunte getEmprunt() {
        return emprunt;
    }

    public void setEmprunt(Elevelivreemprunte emprunt) {
        this.emprunt = emprunt;
    }

    public List<Livre> getLivreSource() {
        return livreSource;
    }

    public void setLivreSource(List<Livre> livreSource) {
        this.livreSource = livreSource;
    }

    public List<Livre> getLivreTarget() {
        return livreTarget;
    }

    public void setLivreTarget(List<Livre> livreTarget) {
        this.livreTarget = livreTarget;
    }

    public DualListModel<Livre> getDualLivre() {
        /*livreSource = livreFacade.findAll();
         dualLivre.setSource(livreSource);
         dualLivre.setTarget(livreTarget);*/
        return dualLivre;
    }

    public void setDualLivre(DualListModel<Livre> dualLivre) {
        this.dualLivre = dualLivre;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public List<Eleve> getEleves() {
        eleves.clear();
        List<Eleveanneeclasse> eleveannees = eleveAnneeClasseFacade.findAll();
        if (!eleveannees.isEmpty()) {
            for (Eleveanneeclasse object : eleveannees) {
                eleves.add(object.getEleve());
            }
        }
        return eleves;
    }

    public void setEleves(List<Eleve> eleves) {
        this.eleves = eleves;
    }

}
