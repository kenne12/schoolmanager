/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.observationnote;

import mbean.observationnote.*;
import entities.Traceur;
import entities.Observationnote;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.TraceurFacadeLocal;
import session.ObservationnoteFacadeLocal;
import utils.SessionMBean;

/**
 *
 * @author Gervais
 */
public class AbstractObservationNoteCtrl {

    @EJB
    protected ObservationnoteFacadeLocal observationnoteFacadeLocal;
    protected Observationnote observationnote;
    protected List<Observationnote> observationnotes = new ArrayList<>();
    protected Observationnote selectedObservationnote;

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    @EJB
    protected TraceurFacadeLocal traceurFacadeLocal;
    protected Traceur traceur;

    protected String fileName;

    public Observationnote getSelectedObservationnote() {
        return selectedObservationnote;
    }

    public void setSelectedObservationnote(Observationnote selectedObservationnote) {
        this.selectedObservationnote = selectedObservationnote;
        modifier = supprimer = detail = selectedObservationnote == null;
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
        imprimer = observationnoteFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Observationnote getObservationnote() {
        return observationnote;
    }

    public void setObservationnote(Observationnote observationnote) {
        this.observationnote = observationnote;
    }

    public List<Observationnote> getObservationnotes() {
        try {
            observationnotes = observationnoteFacadeLocal.findRange(SessionMBean.getSchool());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return observationnotes;
    }

}
