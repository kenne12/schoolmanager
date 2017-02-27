/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.affecterTrimestre;

import entities.Annee;
import entities.Etablissement;
import entities.Trimesteannee;
import entities.Trimestre;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DualListModel;
//import org.primefaces.model.DualListModel;
import session.AnneeFacadeLocal;
import session.EtablissementPersonnelFacadeLocal;
import session.TraceurFacadeLocal;
import session.TrimesteanneeFacadeLocal;
import session.TrimestreFacadeLocal;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@SessionScoped
public class AbstractAffecterTrimCtrl {

    /**
     * Creates a new instance of AbstractAffecterTrimCtrl
     */
    public AbstractAffecterTrimCtrl() {
    }

    @EJB
    protected TrimesteanneeFacadeLocal trimesteanneeFacade;
    protected Trimesteannee trimestreAnnee = new Trimesteannee();
    protected Trimesteannee selectedTrimesteAnnee = new Trimesteannee();
    protected List<Trimesteannee> trimestreAnnees = new ArrayList<>();

    @EJB
    protected TrimestreFacadeLocal trimestreFacade;
    protected Trimestre trimestre = new Trimestre();
    protected List<Trimestre> trimestreSource = new ArrayList<>();
    protected List<Trimestre> trimestreTarget = new ArrayList<>();
    protected DualListModel<Trimestre> dualsTrimestre = new DualListModel<>();

    @EJB
    protected AnneeFacadeLocal anneeFacade;
    protected Annee annee = new Annee();
    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected EtablissementPersonnelFacadeLocal etablissementPersonnelFacadeLocal;
    protected Etablissement etablissement;
    protected List<Etablissement> etablissements = new ArrayList<>();

    @EJB
    protected TraceurFacadeLocal traceurFacadeLocal;

    protected boolean imprimer = true;
    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;

    public boolean isImprimer() {
        imprimer = trimesteanneeFacade.findAll().isEmpty();
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

    public Trimesteannee getTrimestreAnnee() {
        return trimestreAnnee;
    }

    public void setTrimestreAnnee(Trimesteannee trimestreAnnee) {
        this.trimestreAnnee = trimestreAnnee;
    }

    public Trimesteannee getSelectedTrimesteAnnee() {
        return selectedTrimesteAnnee;
    }

    public void setSelectedTrimesteAnnee(Trimesteannee selectedTrimesteAnnee) {
        this.selectedTrimesteAnnee = selectedTrimesteAnnee;
        modifier = supprimer = detail = selectedTrimesteAnnee == null;
    }

    public List<Trimesteannee> getTrimestreAnnees() {
        return trimestreAnnees;
    }

    public void setTrimestreAnnees(List<Trimesteannee> trimestreAnnees) {
        this.trimestreAnnees = trimestreAnnees;
    }

    public Trimestre getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Trimestre trimestre) {
        this.trimestre = trimestre;
    }

    public List<Trimestre> getTrimestreSource() {
        trimestreSource = trimestreFacade.findAll();
        return trimestreSource;
    }

    public void setTrimestreSource(List<Trimestre> trimestreSource) {
        this.trimestreSource = trimestreSource;
    }

    public List<Trimestre> getTrimestreTarget() {
        return trimestreTarget;
    }

    public void setTrimestreTarget(List<Trimestre> trimestreTarget) {
        this.trimestreTarget = trimestreTarget;
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

    public DualListModel<Trimestre> getDualsTrimestre() {
        return dualsTrimestre;
    }

    public void setDualsTrimestre(DualListModel<Trimestre> dualsTrimestre) {
        this.dualsTrimestre = dualsTrimestre;
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

}
