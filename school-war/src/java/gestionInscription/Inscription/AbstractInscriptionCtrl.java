/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionInscription.Inscription;

import entities.Annee;
import entities.Catanneeprix;
import entities.Classe;
import entities.Classecategorie;
import entities.Compte;
import entities.Eleve;
import entities.Eleveanneeclasse;
import entities.Operation;
import entities.Pension;
import entities.PensionSave;
import entities.Tranche;
import entities.Typecompte;
import entities.Typeoperation;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.AnneeFacadeLocal;
import session.CatanneeprixFacadeLocal;
import session.ClasseFacadeLocal;
import session.ClassecategorieFacadeLocal;
import session.CompteFacadeLocal;
import session.EleveFacadeLocal;
import session.EleveanneeclasseFacadeLocal;
import session.OperationFacadeLocal;
import session.PensionCumuleeFacadeLocal;

import session.PensionFacadeLocal;
import session.PensionSaveFacadeLocal;
import session.TraceurFacadeLocal;
import session.TrancheFacadeLocal;
import session.TypecompteFacadeLocal;
import session.TypeoperationFacadeLocal;
import utils.SessionMBean;

/**
 *
 * @author kenne gervais
 */
public class AbstractInscriptionCtrl {

    @EJB
    protected PensionFacadeLocal pensionFacadeLocal;
    protected Pension pension;
    protected List<Pension> pensions = new ArrayList<>();
    protected List<Pension> pensionApayer = new ArrayList<>();
    protected Pension selectedPension;

    @EJB
    protected EleveFacadeLocal eleveFacade;
    protected Eleve eleve;
    protected List<Eleve> eleveActifs = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacade;
    protected Annee annee = new Annee();

    @EJB
    protected TrancheFacadeLocal trancheFacade;
    protected Tranche tranche;
    protected List<Tranche> tranches = new ArrayList<>();
    protected List<Tranche> pensionInsolvables = new ArrayList<>();

    @EJB
    protected ClasseFacadeLocal classeFacade;
    protected Classe classe;
    protected List<Classe> classes = new ArrayList<>();

    @EJB
    protected ClassecategorieFacadeLocal classeCategorieFacade;
    protected Classecategorie categorie;
    protected List<Classecategorie> categories = new ArrayList<>();

    @EJB
    protected EleveanneeclasseFacadeLocal eleveAnneeFacade;
    protected Eleveanneeclasse eleveanneeclasse;
    protected List<Eleveanneeclasse> eleveanneeclasses = new ArrayList<>();

    @EJB
    protected CatanneeprixFacadeLocal catAnneePrixFacade;
    protected Catanneeprix catAnneePrix;

    @EJB
    protected PensionCumuleeFacadeLocal pensionCumuleeFacadeLocal;

    @EJB
    protected PensionSaveFacadeLocal pensionSaveFacadeLocal;
    protected PensionSave pensionSave = new PensionSave();
    protected List<PensionSave> pensionSaves = new ArrayList<>();

    @EJB
    protected TypecompteFacadeLocal typecompteFacadeLocal;
    protected Typecompte typecompte = new Typecompte();
    protected List<Typecompte> typecomptes = new ArrayList<>();

    @EJB
    protected CompteFacadeLocal compteFacadeLocals;
    protected Compte compte = new Compte();
    protected List<Compte> comptes = new ArrayList<>();

    @EJB
    protected OperationFacadeLocal operationFacadeLocal;
    protected Operation operation = new Operation();

    @EJB
    protected TypeoperationFacadeLocal typeoperationFacadeLocal;
    protected Typeoperation typeoperation = new Typeoperation();

    @EJB
    protected TraceurFacadeLocal traceurFacadeLocal;

    protected String fileName;

    public AbstractInscriptionCtrl() {

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
        return imprimer;
    }

    public Pension getPension() {
        return pension;
    }

    public void setPension(Pension pension) {
        this.pension = pension;
    }

    public List<Pension> getPensions() {
        pensions = pensionFacadeLocal.getPensionByAnneeActive(true);
        return pensions;
    }

    public void setPensions(List<Pension> pensions) {
        this.pensions = pensions;
    }

    public Pension getSelectedPension() {
        return selectedPension;
    }

    public void setSelectedPension(Pension selectedPension) {
        this.selectedPension = selectedPension;
        detail = modifier = supprimer = selectedPension == null;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public List<Eleve> getEleveActifs() {
        return eleveActifs;
    }

    public void setEleveActifs(List<Eleve> eleveActifs) {
        this.eleveActifs = eleveActifs;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public Tranche getTypePension() {
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

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<Classe> getClasses() {
        classes = classeFacade.findAll();
        return classes;
    }

    public Classecategorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Classecategorie categorie) {
        this.categorie = categorie;
    }

    public List<Classecategorie> getCategories() {
        categories = classeCategorieFacade.get(true);
        return categories;
    }

    public void setCategories(List<Classecategorie> categories) {
        this.categories = categories;
    }

    public Eleveanneeclasse getEleveanneeclasse() {
        return eleveanneeclasse;
    }

    public void setEleveanneeclasse(Eleveanneeclasse eleveanneeclasse) {
        this.eleveanneeclasse = eleveanneeclasse;
    }

    public List<Eleveanneeclasse> getEleveanneeclasses() {
        return eleveanneeclasses;
    }

    public void setEleveanneeclasses(List<Eleveanneeclasse> eleveanneeclasses) {
        this.eleveanneeclasses = eleveanneeclasses;
    }

    public List<Pension> getPensionApayer() {
        return pensionApayer;
    }

    public void setPensionApayer(List<Pension> pensionApayer) {
        this.pensionApayer = pensionApayer;
    }

    public Typecompte getTypecompte() {
        return typecompte;
    }

    public void setTypecompte(Typecompte typecompte) {
        this.typecompte = typecompte;
    }

    public List<Typecompte> getTypecomptes() {
        try {
            typecomptes = typecompteFacadeLocal.find(SessionMBean.getSchool());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return typecomptes;
    }

    public void setTypecomptes(List<Typecompte> typecomptes) {
        this.typecomptes = typecomptes;
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

    public Typeoperation getTypeoperation() {
        return typeoperation;
    }

    public void setTypeoperation(Typeoperation typeoperation) {
        this.typeoperation = typeoperation;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public PensionSave getPensionSave() {
        return pensionSave;
    }

    public void setPensionSave(PensionSave pensionSave) {
        this.pensionSave = pensionSave;
        detail = modifier = supprimer = pensionSave == null;
    }

    public List<PensionSave> getPensionSaves() {
        try {
            pensionSaves = pensionSaveFacadeLocal.findByAnnee(annee);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pensionSaves;
    }

    public void setPensionSaves(List<PensionSave> pensionSaves) {
        this.pensionSaves = pensionSaves;
    }

}
