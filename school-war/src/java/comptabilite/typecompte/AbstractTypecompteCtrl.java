/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comptabilite.typecompte;

import entities.Typecompte;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import session.TraceurFacadeLocal;
import session.TypecompteFacadeLocal;
import utils.SessionMBean;

/**
 *
 * @author gervais kenne
 */
public class AbstractTypecompteCtrl {

    @EJB
    protected TypecompteFacadeLocal typecompteFacadeLocal;
    protected Typecompte typecompte = new Typecompte();
    protected List<Typecompte> typecomptes = new ArrayList<>();

    @EJB
    protected TraceurFacadeLocal traceurFacadeLocal;

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    protected String mode = "";

    public List<Typecompte> getTypecomptes() {
        try {
            typecomptes = typecompteFacadeLocal.find(SessionMBean.getSchool());
        } catch (Exception ex) {
            Logger.getLogger(AbstractTypecompteCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return typecomptes;
    }

    public boolean isDetail() {
        return detail;
    }

    public boolean isModifier() {
        return modifier;
    }

    public Typecompte getTypecompte() {
        return typecompte;
    }

    public void setTypecompte(Typecompte typecompte) {
        this.typecompte = typecompte;
        modifier = supprimer = detail = typecompte == null;
    }

    public void setCycle(Typecompte typecompte) {
        this.typecompte = typecompte;
    }

    public boolean isSupprimer() {
        return supprimer;
    }

}
