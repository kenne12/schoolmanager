/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionInscription.repartirClasse;

import entities.Annee;
import entities.Classe;
import entities.Eleve;
import entities.Eleveanneeclasse;
import entities.Personnel;
import entities.Traceur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.DualListModel;
import session.AnneeFacadeLocal;
import session.EleveFacadeLocal;
import session.EleveanneeclasseFacadeLocal;
import session.TraceurFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractEleveClasseCtrl {

    @EJB
    protected EleveFacadeLocal eleveFacadeLocal;

    protected List<Eleve> eleveSource = new ArrayList<>();
    protected List<Eleve> eleveTarget = new ArrayList<>();

    protected DualListModel<Eleve> dualeleves = new DualListModel<>();

    protected StringBuffer eleveTableHtml = new StringBuffer("pas encore implementé");
    protected Eleve selectedEleve;
    protected Eleve eleve;

    @EJB
    protected TraceurFacadeLocal traceurFacade;
    protected Traceur traceur;

    @EJB
    protected EleveanneeclasseFacadeLocal eleveAnneeFacade;
    protected Eleveanneeclasse eleveAnneeClasse;
    protected List<Eleveanneeclasse> eleveAnnees;
    protected Eleveanneeclasse selectedEleveAnnee;

    protected Personnel personnel;
    protected Classe classe;

    @EJB
    protected AnneeFacadeLocal anneeFacade;
    protected Annee annee;

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Eleve> getEleveSource() {
        eleveSource = eleveFacadeLocal.findByEtat(true);
        return eleveSource;
    }

    public void setEleveSource(List<Eleve> eleveSource) {
        this.eleveSource = eleveSource;
    }

    public List<Eleve> getEleveTarget() {

        return eleveTarget;
    }

    public void setEleveTarget(List<Eleve> eleveTarget) {
        this.eleveTarget = eleveTarget;
    }

    public Eleve getSelectedEleve() {
        return selectedEleve;
    }

    public void setSelectedEleve(Eleve selectedEleve) {
        this.selectedEleve = selectedEleve;
        modifier = supprimer = detail = selectedEleve == null;
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
        imprimer = eleveFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public StringBuffer getElevesTableHtml() {
        return eleveTableHtml;
    }

    public Traceur getTraceur() {
        return traceur;
    }

    public void setTraceur(Traceur traceur) {
        this.traceur = traceur;
    }

    public DualListModel<Eleve> getDualeleves() {
        return dualeleves;
    }

    public void setDualeleves(DualListModel<Eleve> dualeleves) {
        this.dualeleves = dualeleves;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Eleveanneeclasse> getEleveAnnees() {
        eleveAnnees = eleveAnneeFacade.get(anneeFacade.findByEtatSingle(true).getIdannee());
        return eleveAnnees;
    }

    public void setEleveAnnees(List<Eleveanneeclasse> eleveAnnees) {
        this.eleveAnnees = eleveAnnees;
    }

    public Eleveanneeclasse getSelectedEleveAnnee() {
        return selectedEleveAnnee;
    }

    public void setSelectedEleveAnnee(Eleveanneeclasse selectedEleveAnnee) {
        this.selectedEleveAnnee = selectedEleveAnnee;
    }

}
