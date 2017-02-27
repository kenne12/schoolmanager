/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comptabilite.depense;

import entities.Annee;
import entities.Compte;
import entities.Depense;
import entities.Operation;
import entities.Typecompte;
import entities.Typeoperation;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import session.CompteFacadeLocal;
import session.DepenseFacadeLocal;
import session.OperationFacadeLocal;
import session.TraceurFacadeLocal;
import session.TypecompteFacadeLocal;
import session.TypeoperationFacadeLocal;
import utils.SessionMBean;

/**
 *
 * @author gervais kenne
 */
public class AbstractDepenseCtrl {

    @EJB
    protected DepenseFacadeLocal depenseFacadeLocal;
    protected Depense depense = new Depense();
    protected List<Depense> depenses = new ArrayList<>();

    protected Annee annee = SessionMBean.getYear();

    @EJB
    protected OperationFacadeLocal operationFacadeLocal;
    protected Operation operation = new Operation();
    protected Operation operation1 = new Operation();

    @EJB
    protected TypeoperationFacadeLocal typeoperationFacadeLocal;
    protected Typeoperation typeoperation = new Typeoperation();

    @EJB
    protected CompteFacadeLocal compteFacadeLocal;
    protected Compte compte = new Compte();
    protected Compte compte1 = new Compte();
    protected List<Compte> comptes = new ArrayList<>();
    protected List<Compte> comptes2 = new ArrayList<>();

    @EJB
    protected TypecompteFacadeLocal typecompteFacadeLocal;
    protected Typecompte typecompte = new Typecompte();
    protected Typecompte typecompte1 = new Typecompte();
    protected List<Typecompte> typecomptes = new ArrayList<>();

    @EJB
    protected TraceurFacadeLocal traceurFacadeLocal;

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;
    
    // ce booleen permet de giser les comptes en mode modification
    protected boolean showFilter = false;

    protected String mode = "";
    
    protected  Double soldeDediteur = 0.0;
    protected  Double soldeCrediteur = 0.0;

    public List<Typecompte> getTypecomptes() {
        try {
            typecomptes = typecompteFacadeLocal.find(SessionMBean.getSchool());
        } catch (Exception ex) {
            Logger.getLogger(AbstractDepenseCtrl.class.getName()).log(Level.SEVERE, null, ex);
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
    }

    public List<Compte> getComptes() {
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
        modifier = supprimer = detail = depense == null;
    }

    public List<Depense> getDepenses() {
        try {
            depenses = depenseFacadeLocal.find(SessionMBean.getSchool(), annee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return depenses;
    }

    public void setDepenses(List<Depense> depenses) {
        this.depenses = depenses;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation1() {
        return operation1;
    }

    public void setOperation1(Operation operation1) {
        this.operation1 = operation1;
    }

    public Typeoperation getTypeoperation() {
        return typeoperation;
    }

    public void setTypeoperation(Typeoperation typeoperation) {
        this.typeoperation = typeoperation;
    }

    public Compte getCompte1() {
        return compte1;
    }

    public void setCompte1(Compte compte1) {
        this.compte1 = compte1;
    }

    public Typecompte getTypecompte1() {
        return typecompte1;
    }

    public void setTypecompte1(Typecompte typecompte1) {
        this.typecompte1 = typecompte1;
    }

    public List<Compte> getComptes2() {
        return comptes2;
    }

    public void setComptes2(List<Compte> comptes2) {
        this.comptes2 = comptes2;
    }

    public Double getSoldeDediteur() {
        return soldeDediteur;
    }

    public void setSoldeDediteur(Double soldeDediteur) {
        this.soldeDediteur = soldeDediteur;
    }

    public Double getSoldeCrediteur() {
        return soldeCrediteur;
    }

    public void setSoldeCrediteur(Double soldeCrediteur) {
        this.soldeCrediteur = soldeCrediteur;
    }

    public boolean isShowFilter() {
        return showFilter;
    }

    public void setShowFilter(boolean showFilter) {
        this.showFilter = showFilter;
    }
    
    
    
    

}
