/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.tranche;

import entities.Annee;
import entities.Categorie;
import entities.Etablissement;
import entities.Tranche;
import entities.Typetranche;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.AnneeFacadeLocal;
import session.CatanneeprixFacadeLocal;
import session.CategorieFacadeLocal;
import session.EtablissementFacadeLocal;
import session.TraceurFacadeLocal;
import session.TrancheFacadeLocal;
import session.TypetrancheFacadeLocal;

/**
 *
 * @author kenne gervais
 */
public class AbstractTrancheCtrl {

    @EJB
    protected TrancheFacadeLocal trancheFacade;
    protected Tranche tranche;
    protected List<Tranche> tranches = new ArrayList<>();
    protected List<Tranche> tranchesTest = new ArrayList<>();
    protected Tranche selectedTranche;

    @EJB
    protected TypetrancheFacadeLocal typetrancheFacadeLocal;
    protected Typetranche typetranche;
    protected List<Typetranche> typetranches = new ArrayList<>();
    protected List<Typetranche> selectedTypetranches = new ArrayList<>();

    @EJB
    protected CategorieFacadeLocal categorieFacade;
    protected Categorie categorie;
    protected List<Categorie> categories = new ArrayList<>();

    @EJB
    protected EtablissementFacadeLocal etablissementFacadeLocal;
    protected Etablissement etablissement;
    protected List<Etablissement> etablissements = new ArrayList<>();

    @EJB
    protected CatanneeprixFacadeLocal catAnneePrixFacade;

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    protected Annee annee;
    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected TraceurFacadeLocal traceurFacadeLocal;

    public AbstractTrancheCtrl() {

    }

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    protected boolean showDialog = false;

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
        imprimer = trancheFacade.findAll().isEmpty();
        return imprimer;
    }

    public Tranche getTranche() {
        return tranche;
    }

    public void setTranche(Tranche tranche) {
        this.tranche = tranche;
    }

    public Tranche getSelectedTranche() {
        return selectedTranche;
    }

    public void setSelectedTranche(Tranche selectedTranche) {
        this.selectedTranche = selectedTranche;
        detail = modifier = supprimer = selectedTranche == null;
    }

    public List<Tranche> getTranches() {
        return tranches;
    }

    public void setTranches(List<Tranche> tranches) {
        this.tranches = tranches;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
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

    public List<Annee> getAnnees() {
        return annees;
    }

    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
    }

    public List<Tranche> getTranchesTest() {
        return tranchesTest;
    }

    public void setTranchesTest(List<Tranche> tranchesTest) {
        this.tranchesTest = tranchesTest;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }

    public Typetranche getTypetranche() {
        return typetranche;
    }

    public void setTypetranche(Typetranche typetranche) {
        this.typetranche = typetranche;
    }

    public List<Typetranche> getTypetranches() {
        return typetranches;
    }

    public void setTypetranches(List<Typetranche> typetranches) {
        this.typetranches = typetranches;
    }

    public List<Typetranche> getSelectedTypetranches() {
        return selectedTypetranches;
    }

    public void setSelectedTypetranches(List<Typetranche> selectedTypetranches) {
        this.selectedTypetranches = selectedTypetranches;
    }

}
