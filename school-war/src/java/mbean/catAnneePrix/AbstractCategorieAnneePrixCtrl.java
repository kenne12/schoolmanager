/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.catAnneePrix;

import entities.Annee;
import entities.Catanneeprix;
import entities.Categorie;
import entities.Etablissement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.DualListModel;
import session.AnneeFacadeLocal;
import session.CatanneeprixFacadeLocal;
import session.CategorieFacadeLocal;
import session.EtablissementFacadeLocal;

/**
 *
 * @author kenne gervais
 */
public class AbstractCategorieAnneePrixCtrl {

    @EJB
    protected CatanneeprixFacadeLocal catAnneePrixFacade;
    protected Catanneeprix categorieAnneePrix;
    protected List<Catanneeprix> categorieAnneePrices = new ArrayList<>();
    protected List<Catanneeprix> categorieAnneePricesTest = new ArrayList<>();
    protected Catanneeprix selectedCategorieAnnePrix;

    @EJB
    protected EtablissementFacadeLocal etablissementFacadeLocal;
    protected Etablissement etablissement;
    protected List<Etablissement> etablissements = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacade;
    protected Annee annee;
    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected CategorieFacadeLocal categorieFacade;
    protected Categorie categorie;
    protected List<Categorie> categories = new ArrayList<>();
    protected DualListModel<Categorie> dualElements = new DualListModel<>();

    public AbstractCategorieAnneePrixCtrl() {

    }

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

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
        imprimer = catAnneePrixFacade.findAll().isEmpty();
        return imprimer;
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

    public Catanneeprix getSelectedCategorieAnnePrix() {
        return selectedCategorieAnnePrix;
    }

    public void setSelectedCategorieAnnePrix(Catanneeprix selectedCategorieAnnePrix) {
        this.selectedCategorieAnnePrix = selectedCategorieAnnePrix;
        modifier = supprimer = detail = selectedCategorieAnnePrix == null;
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    public Catanneeprix getCategorieAnneePrix() {
        return categorieAnneePrix;
    }

    public void setCategorieAnneePrix(Catanneeprix categorieAnneePrix) {
        this.categorieAnneePrix = categorieAnneePrix;
    }

    public List<Catanneeprix> getCategorieAnneePrices() {
        return categorieAnneePrices;
    }

    public void setCategorieAnneePrices(List<Catanneeprix> categorieAnneePrices) {
        this.categorieAnneePrices = categorieAnneePrices;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
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

    public DualListModel<Categorie> getDualElements() {
        return dualElements;
    }

    public void setDualElements(DualListModel<Categorie> dualElements) {
        this.dualElements = dualElements;
    }

    public List<Catanneeprix> getCategorieAnneePricesTest() {
        return categorieAnneePricesTest;
    }

    public void setCategorieAnneePricesTest(List<Catanneeprix> categorieAnneePricesTest) {
        this.categorieAnneePricesTest = categorieAnneePricesTest;
    }

}
