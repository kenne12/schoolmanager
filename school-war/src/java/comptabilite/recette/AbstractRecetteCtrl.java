/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comptabilite.recette;

import entities.Annee;
import entities.Compte;
import entities.Recette;
import entities.Operation;
import entities.Typecompte;
import entities.Typeoperation;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import session.CompteFacadeLocal;
import session.RecetteFacadeLocal;
import session.OperationFacadeLocal;
import session.TraceurFacadeLocal;
import session.TypecompteFacadeLocal;
import session.TypeoperationFacadeLocal;
import utils.SessionMBean;

/**
 *
 * @author gervais kenne
 */
public class AbstractRecetteCtrl {

    @EJB
    protected RecetteFacadeLocal recetteFacadeLocal;
    protected Recette recette = new Recette();
    protected List<Recette> recettes = new ArrayList<>();

    protected Annee annee = SessionMBean.getYear();

    @EJB
    protected OperationFacadeLocal operationFacadeLocal;
    protected Operation operation = new Operation();

    @EJB
    protected TypeoperationFacadeLocal typeoperationFacadeLocal;
    protected Typeoperation typeoperation = new Typeoperation();
    
    @EJB
    protected TypecompteFacadeLocal typecompteFacadeLocal;
    protected Typecompte typecompte = new Typecompte();
    protected List<Typecompte> typecomptes = new ArrayList<>();

    @EJB
    protected CompteFacadeLocal compteFacadeLocal;
    protected Compte compte = new Compte();
    protected List<Compte> comptes = new ArrayList<>();

    @EJB
    protected TraceurFacadeLocal traceurFacadeLocal;

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    // ce booleen permet de giser les comptes en mode modification
    protected boolean showFilter = false;

    protected String mode = "";

    protected Double soldeCrediteur = 0.0;

    public List<Typecompte> getTypecomptes() {
        try {
            typecomptes = typecompteFacadeLocal.find(SessionMBean.getSchool());
        } catch (Exception ex) {
            Logger.getLogger(AbstractRecetteCtrl.class.getName()).log(Level.SEVERE, null, ex);
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

    public Recette getRecette() {
        return recette;
    }

    public void setRecette(Recette recette) {
        this.recette = recette;
        modifier = supprimer = detail = recette == null;
    }

    public List<Recette> getRecettes() {
        try {
            recettes = recetteFacadeLocal.find(SessionMBean.getSchool(), annee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recettes;
    }

    public void setRecettes(List<Recette> recettes) {
        this.recettes = recettes;
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

    public Typeoperation getTypeoperation() {
        return typeoperation;
    }

    public void setTypeoperation(Typeoperation typeoperation) {
        this.typeoperation = typeoperation;
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
