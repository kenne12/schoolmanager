/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.affecterSequence;

import entities.Annee;
import entities.Etablissement;
import entities.Sequenceannee;
import entities.Sequence;
import entities.Trimesteannee;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DualListModel;
import session.AnneeFacadeLocal;
import session.EtablissementFacadeLocal;
import session.SequenceanneeFacadeLocal;
import session.SequenceFacadeLocal;
import session.TraceurFacadeLocal;
import session.TrimesteanneeFacadeLocal;
import utils.SessionMBean;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@SessionScoped
public class AbstractAffecterSeqCtrl {

    /**
     * Creates a new instance of AbstractAffecterTrimCtrl
     */
    public AbstractAffecterSeqCtrl() {
    }

    @EJB
    protected SequenceanneeFacadeLocal sequenceAnneeFacade;
    protected Sequenceannee sequenceAnnee = new Sequenceannee();
    protected Sequenceannee selectedSequenceAnnee = new Sequenceannee();
    protected List<Sequenceannee> sequenceAnnees = new ArrayList<>();

    @EJB
    protected SequenceFacadeLocal sequenceFacadeLocal;
    protected Sequence sequence = new Sequence();
    protected List<Sequence> sequenceSource = new ArrayList<>();
    protected List<Sequence> sequenceTarget = new ArrayList<>();
    protected DualListModel<Sequence> dualsSequence = new DualListModel<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    protected Annee annee = new Annee();
    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected EtablissementFacadeLocal etablissementFacadeLocal;
    protected Etablissement etablissement;
    protected List<Etablissement> etablissements = new ArrayList<>();

    @EJB
    protected TrimesteanneeFacadeLocal trimesteanneeFacadeLocal;
    protected Trimesteannee trimesteannee;
    protected List<Trimesteannee> trimesteannees = new ArrayList<>();

    @EJB
    protected TraceurFacadeLocal traceurFacadeLocal;

    protected boolean imprimer = true;
    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;

    public boolean isImprimer() {
        imprimer = sequenceAnneeFacade.findAll().isEmpty();
        return imprimer;
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

    public Sequenceannee getSequenceAnnee() {
        return sequenceAnnee;
    }

    public void setSequenceAnnee(Sequenceannee sequenceAnnee) {
        this.sequenceAnnee = sequenceAnnee;
    }

    public Sequenceannee getSelectedSequenceAnnee() {
        return selectedSequenceAnnee;
    }

    public void setSelectedSequenceAnnee(Sequenceannee selectedSequenceAnnee) {
        this.selectedSequenceAnnee = selectedSequenceAnnee;
        modifier = supprimer = detail = selectedSequenceAnnee == null;
    }

    public List<Sequenceannee> getSequenceAnnees() {
        try {
            sequenceAnnees = sequenceAnneeFacade.getByAnnee(SessionMBean.getYear().getIdannee());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sequenceAnnees;
    }

    public void setSequenceAnnees(List<Sequenceannee> sequenceAnnees) {
        this.sequenceAnnees = sequenceAnnees;
    }

    public Sequence getSequence() {
        return sequence;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

    public List<Sequence> getSequenceSource() {
        sequenceSource = sequenceFacadeLocal.findAll();
        return sequenceSource;
    }

    public void setSequenceSource(List<Sequence> sequenceSource) {
        this.sequenceSource = sequenceSource;
    }

    public List<Sequence> getSequenceTarget() {
        return sequenceTarget;
    }

    public void setSequenceTarget(List<Sequence> sequenceTarget) {
        this.sequenceTarget = sequenceTarget;
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

    public DualListModel<Sequence> getDualsSequence() {
        return dualsSequence;
    }

    public void setDualsSequence(DualListModel<Sequence> dualsSequence) {
        this.dualsSequence = dualsSequence;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public List<Etablissement> getEtablissements() {
        return etablissements;
    }

    public void setEtablissements(List<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }

    public Trimesteannee getTrimesteannee() {
        return trimesteannee;
    }

    public void setTrimesteannee(Trimesteannee trimesteannee) {
        this.trimesteannee = trimesteannee;
    }

    public List<Trimesteannee> getTrimesteannees() {
        return trimesteannees;
    }

    public void setTrimesteannees(List<Trimesteannee> trimesteannees) {
        this.trimesteannees = trimesteannees;
    }

}
