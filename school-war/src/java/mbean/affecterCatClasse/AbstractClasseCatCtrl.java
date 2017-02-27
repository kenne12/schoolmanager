/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.affecterCatClasse;

import entities.Categorie;
import entities.Classe;
import entities.Classecategorie;
import java.util.ArrayList;

import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.DualListModel;
import session.CategorieFacadeLocal;
import session.ClasseFacadeLocal;
import session.ClassecategorieFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractClasseCatCtrl {

    @EJB
    protected ClassecategorieFacadeLocal classeCategorieFacade;

    @EJB
    protected CategorieFacadeLocal categorieFacade;

    @EJB
    protected ClasseFacadeLocal classeFacade;

    protected Classecategorie classeCategorie;
    protected List<Classecategorie> classeCategories;
    protected Classecategorie selectedClasseCategorie;

    protected Categorie categorie;
    protected List<Categorie> categories;

    //liste des classes
    protected Classe classe;
    protected List<Classe> classes;
    protected List<Classe> classeSources = new ArrayList<>();
    protected List<Classe> classeTarget = new ArrayList<>();
    protected DualListModel<Classe> dualClasses = new DualListModel<>();

    //la liste des entiers qui contiendra la liste des classes selectionnées
    protected List<Integer> selectedClasses;
    protected List<Classe> selectedTarget = new ArrayList<>();

    public AbstractClasseCatCtrl() {

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
        imprimer = classeCategorieFacade.findAll().isEmpty();
        return imprimer;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Classecategorie getClasseCategorie() {
        return classeCategorie;
    }

    public void setClasseCategorie(Classecategorie classeCategorie) {
        this.classeCategorie = classeCategorie;
    }

    public Classe getClasse() {
        classes = classeFacade.findAll();
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Classecategorie getSelectedClasseCategorie() {
        classeCategories = classeCategorieFacade.findAll();
        return selectedClasseCategorie;
    }

    public void setSelectedClasseCategorie(Classecategorie selectedClasseCategorie) {
        modifier = detail = supprimer = selectedClasseCategorie == null;
        this.selectedClasseCategorie = selectedClasseCategorie;
    }

    public List<Classe> getClasses() {
        classes = classeFacade.findByEtat(true);
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public List<Integer> getSelectedClasses() {
        return selectedClasses;
    }

    public void setSelectedClasses(List<Integer> selectedClasses) {
        this.selectedClasses = selectedClasses;
    }

    public List<Categorie> getCategories() {
        categories = categorieFacade.findByEtat(true);
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    public List<Classecategorie> getClasseCategories() {
        classeCategories = classeCategorieFacade.findAll();
        return classeCategories;
    }

    public void setClasseCategories(List<Classecategorie> classeCategories) {
        this.classeCategories = classeCategories;
    }

    public List<Classe> getSelectedTarget() {
        return selectedTarget;
    }

    public void setSelectedTarget(List<Classe> selectedTarget) {
        this.selectedTarget = selectedTarget;
    }

    public List<Classe> getClasseSources() {

        return classeSources;
    }

    public void setClasseSources(List<Classe> classeSources) {

        this.classeSources = classeSources;
    }

    public List<Classe> getClasseTarget() {
        return classeTarget;
    }

    public void setClasseTarget(List<Classe> classeTarget) {
        this.classeTarget = classeTarget;
    }

    public DualListModel<Classe> getDualClasses() {
        classeSources.clear();

        for (int a = 0; a < getClasseCategories().size(); a++) {
            classeTarget.add(getClasseCategories().get(a).getIdclasse());
        }

        for (int i = 0; i < getClasses().size(); i++) {
            if (getClasseTarget().contains(getClasses().get(i))) {
                getClasseTarget().remove(getClasses().get(i));
            } else {
                classeSources.add(getClasses().get(i));
            }
        }
        dualClasses.setSource(classeSources);
        dualClasses.setTarget(classeTarget);
        return dualClasses;
    }

    public void setDualClasses(DualListModel<Classe> dualClasses) {
        this.dualClasses = dualClasses;
    }

}
