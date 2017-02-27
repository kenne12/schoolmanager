/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDiscipline.punition;

import entities.Annee;
import entities.Eleve;
import entities.Motif;
import entities.Personnel;
import entities.Punition;
import entities.Sanction;
import entities.Sequenceannee;
import java.util.List;
import javax.ejb.EJB;
import session.AnneeFacadeLocal;
import session.EleveFacadeLocal;
import session.PersonnelFacadeLocal;
import session.PunitionFacadeLocal;
import session.SequenceanneeFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractPunitionCtrl {

    protected String fileName;
    @EJB
    protected PunitionFacadeLocal punitionFacadeLocal;

    @EJB
    protected AnneeFacadeLocal anneeFacade;
    protected Annee annee;
    protected List<Annee> annees;

    @EJB
    protected EleveFacadeLocal eleveFacade;
    protected Eleve eleve;
    protected List<Eleve> eleves;

    @EJB
    protected PersonnelFacadeLocal personnelFacade;
    protected Personnel personnel;

    @EJB
    protected SequenceanneeFacadeLocal sequenceAnneeFcade;
    protected Sequenceannee sequenceAnnee;
    protected List<Sequenceannee> sequenceAnnees;

    protected Motif motif;
    protected Sanction sanction;

    protected List<Punition> punitions;

    protected StringBuffer punitionsTableHtml = new StringBuffer("pas encore implementé");
    protected Punition selectedPunition;
    protected Punition punition;
    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Punition> getPunitions() {
        punitions = punitionFacadeLocal.getPunitionAnneeActive(true);
        return punitions;
    }

    public Punition getSelectedPunition() {
        return selectedPunition;
    }

    public void setSelectedPunition(Punition selectedPunition) {
        this.selectedPunition = selectedPunition;
        modifier = supprimer = detail = selectedPunition == null;
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
        imprimer = punitionFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Punition getPunition() {
        return punition;
    }

    public void setPunition(Punition punition) {
        this.punition = punition;
    }

    public StringBuffer getPunitionsTableHtml() {
        return punitionsTableHtml;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Annee> getAnnees() {
        return annees;
    }

    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public List<Eleve> getEleves() {
        eleves = eleveFacade.findByEtat(true);
        return eleves;
    }

    public void setEleves(List<Eleve> eleves) {
        this.eleves = eleves;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Motif getMotif() {
        return motif;
    }

    public void setMotif(Motif motif) {
        this.motif = motif;
    }

    public Sanction getSanction() {
        return sanction;
    }

    public void setSanction(Sanction sanction) {
        this.sanction = sanction;
    }

    public Sequenceannee getSequenceAnnee() {
        return sequenceAnnee;
    }

    public void setSequenceAnnee(Sequenceannee sequenceAnnee) {
        this.sequenceAnnee = sequenceAnnee;
    }

    public List<Sequenceannee> getSequenceAnnees() {
        sequenceAnnees = sequenceAnneeFcade.getAnneeSequenceByAnneeActive(true);
        return sequenceAnnees;
    }

    public void setSequenceAnnees(List<Sequenceannee> sequenceAnnees) {
        this.sequenceAnnees = sequenceAnnees;
    }

}
