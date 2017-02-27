/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.branche;

import entities.Enseignement;
import java.util.List;
import javax.ejb.EJB;
import session.EnseignementFacadeLocal;
import utils.SessionMBean;

/**
 *
 * @author gervais kenne
 */
public class AbstractEnseignementCtrl {

    protected String fileName;
    @EJB
    protected EnseignementFacadeLocal enseignementFacadeLocal;

    protected List<Enseignement> enseignements;

    protected StringBuffer enseignementsTableHtml = new StringBuffer("pas encore implementé");
    protected Enseignement selectedEnseignement;
    protected Enseignement enseignement;
    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Enseignement> getEnseignements() {
        enseignements = enseignementFacadeLocal.findByEtablissement(SessionMBean.getSchool().getId());
        return enseignements;
    }

    public Enseignement getSelectedEnseignement() {
        return selectedEnseignement;
    }

    public void setSelectedEnseignement(Enseignement selectedEnseignement) {
        this.selectedEnseignement = selectedEnseignement;
        modifier = supprimer = detail = selectedEnseignement == null;
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
        imprimer = enseignementFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Enseignement getEnseignement() {
        return enseignement;
    }

    public void setEnseignement(Enseignement enseignement) {
        this.enseignement = enseignement;
    }

    public StringBuffer getEnseignementsTableHtml() {
        return enseignementsTableHtml;
    }

}
