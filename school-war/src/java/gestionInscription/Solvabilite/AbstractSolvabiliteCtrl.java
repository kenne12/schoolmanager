/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionInscription.Solvabilite;

import entities.Annee;
import entities.Catanneeprix;
import entities.Classecategorie;
import entities.Eleve;
import entities.Eleveanneeclasse;
import entities.Pension;
import entities.Tranche;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.AnneeFacadeLocal;
import session.CatanneeprixFacadeLocal;
import session.ClasseFacadeLocal;
import session.ClassecategorieFacadeLocal;
import session.EleveFacadeLocal;
import session.EleveanneeclasseFacadeLocal;
import session.PensionCumuleeFacadeLocal;
import session.PensionFacadeLocal;
import session.TrancheFacadeLocal;

import utils.SessionMBean;
import utils.Solvabilite;

/**
 *
 * @author kenne gervais
 */
public class AbstractSolvabiliteCtrl {

    @EJB
    protected PensionFacadeLocal pensionFacade;
    protected Pension pension;
    protected List<Pension> pensions = new ArrayList<>();

    @EJB
    protected EleveFacadeLocal eleveFacade;
    protected Eleve eleve;
    protected List<Eleve> eleveActifs = new ArrayList<>();
    protected Eleve selectedEleve;

    //variable qui porte les eleves solvables !
    protected List<Eleve> eleveSolvables = new ArrayList<>();

    //variable qu porte les eleves insolvables
    protected List<Eleve> eleveInslvables = new ArrayList<>();

    //variables qui porte les eleves sans classe
    protected List<Eleve> elevesWithoutClass = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacade;
    protected Annee annee;
    protected List<Annee> anneesActifs = new ArrayList<>();

    @EJB
    protected TrancheFacadeLocal trancheFacade;
    protected Tranche tranche;
    protected List<Tranche> tranches = new ArrayList<>();

    @EJB
    protected ClassecategorieFacadeLocal classeCategorieFacade;
    protected Classecategorie categorie;
    protected List<Classecategorie> categories = new ArrayList<>();

    @EJB
    protected EleveanneeclasseFacadeLocal eleveAnneeFacade;
    protected Eleveanneeclasse eleveAnneeClasse;
    protected List<Eleveanneeclasse> eleveAnneeClasses = new ArrayList<>();

    @EJB
    protected CatanneeprixFacadeLocal catAnneePrixFacade;
    protected Catanneeprix catAnneePrix;

    @EJB
    protected ClasseFacadeLocal classeFacadeLocal;

    protected List<Solvabilite> solvabilites = new ArrayList<>();
    protected List<Solvabilite> solvabilites1 = new ArrayList<>();

    @EJB
    PensionCumuleeFacadeLocal pensionCumuleeFacadeLocal;

    protected List<Integer> reste = new ArrayList<>();
    protected List<Integer> paye = new ArrayList<>();

    protected boolean showTranche = true;

    protected String fichier_solvable = "";
    protected String fichier_insolvable = "";

    public AbstractSolvabiliteCtrl() {

    }

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;
    protected boolean imprimer1 = true;

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
        imprimer = solvabilites.isEmpty();
        return imprimer;
    }

    public boolean isImprimer1() {
        imprimer1 = solvabilites1.isEmpty();
        return imprimer1;
    }

    public void setImprimer1(boolean imprimer1) {
        this.imprimer1 = imprimer1;
    }

    public Pension getPension() {
        return pension;
    }

    public void setPension(Pension pension) {
        this.pension = pension;
    }

    public List<Pension> getPensions() {
        pensions = pensionFacade.getPensionByAnneeActive(true);
        return pensions;
    }

    public void setPensions(List<Pension> pensions) {
        this.pensions = pensions;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public List<Eleve> getEleveActifs() {
        eleveActifs = eleveFacade.findByEtat(true);
        return eleveActifs;
    }

    public void setEleveActifs(List<Eleve> eleveActifs) {
        this.eleveActifs = eleveActifs;
    }

    public Annee getAnnee() {
        if (SessionMBean.getUserAccount() != null) {
            List<Annee> annees = anneeFacade.findByEtat(SessionMBean.getUserAccount().getEtablissement().getId(), true);
            if (!annees.isEmpty()) {
                annee = annees.get(annees.size() - 1);
            }
        }
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Annee> getAnneesActifs() {
        anneesActifs = anneeFacade.findByEtat(true);
        return anneesActifs;
    }

    public void setAnneesActifs(List<Annee> anneesActifs) {
        this.anneesActifs = anneesActifs;
    }

    public Tranche getTranche() {
        return tranche;
    }

    public void setTranche(Tranche tranche) {
        this.tranche = tranche;
    }

    public List<Tranche> getTranches() {
        return tranches;
    }

    public void setTranches(List<Tranche> tranches) {
        this.tranches = tranches;
    }

    public List<Eleve> getEleveSolvables() {
        return eleveSolvables;
    }

    public void setEleveSolvables(List<Eleve> eleveSolvables) {
        this.eleveSolvables = eleveSolvables;
    }

    public List<Eleve> getEleveInslvables() {
        return eleveInslvables;
    }

    public void setEleveInslvables(List<Eleve> eleveInslvables) {
        this.eleveInslvables = eleveInslvables;
    }

    public Eleve getSelectedEleve() {
        return selectedEleve;
    }

    public void setSelectedEleve(Eleve selectedEleve) {
        this.selectedEleve = selectedEleve;
    }

    public List<Eleve> getElevesWithoutClass() {
        return elevesWithoutClass;
    }

    public void setElevesWithoutClass(List<Eleve> elevesWithoutClass) {
        this.elevesWithoutClass = elevesWithoutClass;
    }

    public Classecategorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Classecategorie categorie) {
        this.categorie = categorie;
    }

    public List<Classecategorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Classecategorie> categories) {
        this.categories = categories;
    }

    public Eleveanneeclasse getEleveAnneeClasse() {
        return eleveAnneeClasse;
    }

    public void setEleveAnneeClasse(Eleveanneeclasse eleveAnneeClasse) {
        this.eleveAnneeClasse = eleveAnneeClasse;
    }

    public List<Eleveanneeclasse> getEleveAnneeClasses() {
        return eleveAnneeClasses;
    }

    public void setEleveAnneeClasses(List<Eleveanneeclasse> eleveAnneeClasses) {
        this.eleveAnneeClasses = eleveAnneeClasses;
    }

    public Catanneeprix getCatAnneePrix() {
        return catAnneePrix;
    }

    public void setCatAnneePrix(Catanneeprix catAnneePrix) {
        this.catAnneePrix = catAnneePrix;
    }

    public List<Integer> getReste() {
        return reste;
    }

    public void setReste(List<Integer> reste) {
        this.reste = reste;
    }

    public List<Integer> getPaye() {
        return paye;
    }

    public void setPaye(List<Integer> paye) {
        this.paye = paye;
    }

    public boolean isShowTranche() {
        return showTranche;
    }

    public void setShowTranche(boolean showTranche) {
        this.showTranche = showTranche;
    }

    public List<Solvabilite> getSolvabilites() {
        return solvabilites;
    }

    public void setSolvabilites(List<Solvabilite> solvabilites) {
        this.solvabilites = solvabilites;
    }

    public List<Solvabilite> getSolvabilites1() {
        return solvabilites1;
    }

    public void setSolvabilites1(List<Solvabilite> solvabilites1) {
        this.solvabilites1 = solvabilites1;
    }

    public String getFichier_solvable() {
        return fichier_solvable;
    }

    public void setFichier_solvable(String fichier_solvable) {
        this.fichier_solvable = fichier_solvable;
    }

    public String getFichier_insolvable() {
        return fichier_insolvable;
    }

    public void setFichier_insolvable(String fichier_insolvable) {
        this.fichier_insolvable = fichier_insolvable;
    }

}
