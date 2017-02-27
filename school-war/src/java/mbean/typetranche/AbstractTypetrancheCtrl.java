/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.typetranche;

import entities.Traceur;
import entities.Typetranche;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.TraceurFacadeLocal;
import session.TypetrancheFacadeLocal;
import utils.SessionMBean;

/**
 *
 * @author Gervais
 */
public class AbstractTypetrancheCtrl {

    @EJB
    protected TypetrancheFacadeLocal typetrancheFacadeLocal;
    protected Typetranche typetranche;
    protected List<Typetranche> typetranches = new ArrayList<>();
    protected Typetranche selectedTypetranche;

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    @EJB
    protected TraceurFacadeLocal traceurFacadeLocal;
    protected Traceur traceur;

    protected String fileName;

    public Typetranche getSelectedTypetranche() {
        return selectedTypetranche;
    }

    public void setSelectedTypetranche(Typetranche selectedTypetranche) {
        this.selectedTypetranche = selectedTypetranche;
        modifier = supprimer = detail = selectedTypetranche == null;
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
        imprimer = typetrancheFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Typetranche getTypetranche() {
        return typetranche;
    }

    public void setTypetranche(Typetranche typetranche) {
        this.typetranche = typetranche;
    }

    public List<Typetranche> getTypetranches() {
        try {
            typetranches = typetrancheFacadeLocal.find(SessionMBean.getSchool());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return typetranches;
    }

}
