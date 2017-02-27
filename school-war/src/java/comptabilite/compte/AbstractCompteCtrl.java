/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comptabilite.compte;

import comptabilite.depense.DepenseInterfaceCtrl;
import entities.Compte;
import entities.Depense;
import entities.Typecompte;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import session.CompteFacadeLocal;
import session.DepenseFacadeLocal;
import session.TraceurFacadeLocal;
import session.TypecompteFacadeLocal;
import utils.SessionMBean;

/**
 *
 * @author gervais kenne
 */
public class AbstractCompteCtrl {

    @EJB
    protected DepenseFacadeLocal depenseFacadeLocal;
    protected Depense depense = new Depense();
    protected List<Depense> depenses = new ArrayList<>();

    @EJB
    protected CompteFacadeLocal compteFacadeLocal;
    protected Compte compte = new Compte();
    protected List<Compte> comptes = new ArrayList<>();

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
            Logger.getLogger(AbstractCompteCtrl.class.getName()).log(Level.SEVERE, null, ex);
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

    }

    public void setCycle(Typecompte typecompte) {
        this.typecompte = typecompte;
    }

    public boolean isSupprimer() {
        return supprimer;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
        modifier = supprimer = detail = compte == null;
    }

    public List<Compte> getComptes() {
        try {
            comptes = compteFacadeLocal.find(SessionMBean.getSchool());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comptes;
    }

    public void setComptes(List<Compte> comptes) {
        this.comptes = comptes;
    }

    public Depense getDepense() {
        return depense;
    }

    public void setDepense(Depense depense) {
        this.depense = depense;
    }

    public List<Depense> getDepenses() {
        return depenses;
    }

    public void setDepenses(List<Depense> depenses) {
        this.depenses = depenses;
    }
    
    
    

}
