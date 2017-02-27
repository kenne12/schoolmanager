/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDiscipline.absence;

import entities.Annee;
import entities.Eleve;
import entities.Personnel;
import entities.Absenceeleve;
import entities.Sequenceannee;
import java.util.List;
import javax.ejb.EJB;
import session.AnneeFacadeLocal;
import session.EleveFacadeLocal;
import session.PersonnelFacadeLocal;
import session.AbsenceeleveFacadeLocal;
import session.SequenceanneeFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractAbsenceCtrl {

    protected String fileName;
    @EJB
    protected AbsenceeleveFacadeLocal absenceFacadeLocal;
    protected Absenceeleve absence;
    protected List<Absenceeleve> absences;
    protected Absenceeleve selectedAbsence;

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

    protected StringBuffer AbsenceelevesTableHtml = new StringBuffer("pas encore implementé");

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Absenceeleve> getAbsences() {
        absences = absenceFacadeLocal.getAbsenceEleveByAnneeActive(true);
        return absences;
    }

    public Absenceeleve getSelectedAbsence() {
        return selectedAbsence;
    }

    public void setSelectedAbsence(Absenceeleve selectedAbsence) {
        this.selectedAbsence = selectedAbsence;
        modifier = supprimer = detail = selectedAbsence == null;
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
        imprimer = absenceFacadeLocal.getAbsenceEleveByAnneeActive(true).isEmpty();
        return imprimer;
    }

    public StringBuffer getAbsenceelevesTableHtml() {
        return AbsenceelevesTableHtml;
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

    public Absenceeleve getAbsence() {
        return absence;
    }

    public void setAbsence(Absenceeleve absence) {
        this.absence = absence;
    }

}
