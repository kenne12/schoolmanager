/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.annee;

import entities.Annee;
import entities.Etablissement;
import entities.Traceur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.AnneeFacadeLocal;
import session.EtablissementFacadeLocal;
import session.TraceurFacadeLocal;
import utils.SessionMBean;

/**
 *
 * @author kenne gervais
 */
public class AbstractAnneCtrl {

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    protected Annee annee;
    protected Annee selectedAnnee;
    protected List<Annee> annees = new ArrayList<>();
    protected List<Annee> anneeCourantes = new ArrayList<>();

    @EJB
    protected EtablissementFacadeLocal EtablissementFacadeLocal;
    protected List<Etablissement> etablissements = new ArrayList<>();

    @EJB
    protected TraceurFacadeLocal traceurFacade;
    protected Traceur traceur;

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Annee> getAnnees() {
        annees = anneeFacadeLocal.findByEtablissement(SessionMBean.getSchool());
        return annees;
    }

    public Annee getSelectedAnnee() {
        return selectedAnnee;
    }

    public void setSelectedAnnee(Annee selectedAnnee) {
        this.selectedAnnee = selectedAnnee;
        modifier = supprimer = detail = selectedAnnee == null;
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
        imprimer = anneeFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Annee> getAnneeCourantes() {
        anneeCourantes = anneeFacadeLocal.findByEtat(true);
        return anneeCourantes;
    }

    public void setAnneeCourantes(List<Annee> anneeCourantes) {
        this.anneeCourantes = anneeCourantes;
    }

    public List<Etablissement> getEtablissements() {
        return etablissements;
    }

    public void setEtablissements(List<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }

}
