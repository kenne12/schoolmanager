/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.classe;

import entities.Categorie;
import entities.Classe;
import entities.Classecategorie;
import entities.Cycle;
import entities.Enseignement;
import entities.Etablissement;
import entities.Traceur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.CategorieFacadeLocal;
import session.ClasseFacadeLocal;
import session.ClassecategorieFacadeLocal;
import session.CycleFacadeLocal;
import session.EnseignementFacadeLocal;
import session.EtablissementFacadeLocal;
import session.TraceurFacadeLocal;
import utils.SessionMBean;

/**
 *
 * @author Gervais
 */
public class AbstractClasseCtrl {


    @EJB
    protected ClasseFacadeLocal classeFacade;
    @EJB
    protected CycleFacadeLocal cycleFacade;
    @EJB
    protected EnseignementFacadeLocal BrancheFacade;

    @EJB
    protected TraceurFacadeLocal traceurFacade;
    protected Traceur traceur;

    protected Classe classe;
    protected Classe selectedClasse;

    protected List<Classe> classes = new ArrayList<>();
    protected List<Classe> classeActives = new ArrayList<>();

    protected Enseignement branche;
    protected List<Enseignement> listBranches = new ArrayList<>();

    protected Cycle cycle;
    protected List<Cycle> listCycles = new ArrayList<>();

    @EJB
    protected CategorieFacadeLocal categorieFacadeLocal;
    protected Categorie categorie;
    protected List<Categorie> categories = new ArrayList<>();

    @EJB
    protected ClassecategorieFacadeLocal classecategorieFacadeLocal;
    protected Classecategorie classecategorie;

    @EJB
    protected EtablissementFacadeLocal etablissementFacadeLocal;
    protected List<Etablissement> etablissements = new ArrayList<>();

    public AbstractClasseCtrl() {

    }

    protected StringBuffer ClasseTableHtml = new StringBuffer("pas encore implementé");

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
        imprimer = classeFacade.findAll().isEmpty();
        return imprimer;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;

    }

    public Classe getSelectedClasse() {
        return selectedClasse;
    }

    public void setSelectedClasse(Classe selectedClasse) {
        this.selectedClasse = selectedClasse;
        modifier = supprimer = detail = selectedClasse == null;
    }

    public List<Classe> getClasses() {
        classes = classeFacade.findByEtaBlissement(SessionMBean.getSchool().getId());
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public Enseignement getBranche() {
        return branche;
    }

    public void setBranche(Enseignement branche) {
        this.branche = branche;
    }

    public List<Enseignement> getListBranches() {
        return listBranches;
    }

    public void setListBranches(List<Enseignement> listBranches) {
        this.listBranches = listBranches;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }

    public List<Cycle> getListCycles() {
        listCycles = cycleFacade.findAll();
        return listCycles;
    }

    public void setListCycles(List<Cycle> listCycles) {
        this.listCycles = listCycles;
    }

    public List<Classe> getClasseActives() {
        classeActives = classeFacade.findByEtat(true);
        return classeActives;
    }

    public void setClasseActives(List<Classe> classeActives) {
        this.classeActives = classeActives;
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

    public List<Etablissement> getEtablissements() {
        etablissements = etablissementFacadeLocal.findAll();
        return etablissements;
    }

    public void setEtablissements(List<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }

}
