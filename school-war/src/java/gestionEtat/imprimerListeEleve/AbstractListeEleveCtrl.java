/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEtat.imprimerListeEleve;

import entities.Annee;
import entities.Classe;
import entities.Classecategorie;
import entities.Eleveanneeclasse;
import entities.Personnel;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.AnneeFacadeLocal;
import session.ClasseFacadeLocal;
import session.ClassecategorieFacadeLocal;
import session.EleveanneeclasseFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractListeEleveCtrl {

    Personnel personnel;

    @EJB
    protected AnneeFacadeLocal anneeFacade;
    Annee annee;

    @EJB
    protected ClassecategorieFacadeLocal classecategorieFacadeLocal;
    protected Classecategorie classeCategorie;
    protected List<Classecategorie> classecategories = new ArrayList<>();

    @EJB
    protected EleveanneeclasseFacadeLocal eleveAnneeClasseFacade;
    protected Eleveanneeclasse eleveAnneeClasse;
    protected Eleveanneeclasse selectedEleveAnneeClasse;
    protected List<Eleveanneeclasse> eleveAnneeClasses = new ArrayList<>();

    @EJB
    protected ClasseFacadeLocal classeFacade;
    protected Classe classe;

    @EJB
    protected ClassecategorieFacadeLocal categorieFacadeLocal;
    protected Classecategorie categorie;
    protected List<Classecategorie> categories = new ArrayList<>();

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
        //     imprimer = getEvaluations().isEmpty();
        return imprimer;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<Classecategorie> getCategories() {
        categories = categorieFacadeLocal.get(true);
        return categories;
    }

    public void setCategories(List<Classecategorie> categories) {
        this.categories = categories;
    }

    public List<Classecategorie> getClassecategories() {
        classecategories = classecategorieFacadeLocal.get(true);
        return classecategories;
    }

    public void setClassecategories(List<Classecategorie> classecategories) {
        this.classecategories = classecategories;
    }

    public Classecategorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Classecategorie categorie) {
        this.categorie = categorie;
    }

    public Eleveanneeclasse getEleveAnneeClasse() {
        return eleveAnneeClasse;
    }

    public void setEleveAnneeClasse(Eleveanneeclasse eleveAnneeClasse) {
        this.eleveAnneeClasse = eleveAnneeClasse;
    }

    public List<Eleveanneeclasse> getEleveAnneeClasses() {
        Annee an = anneeFacade.findByEtatSingle(true);
        eleveAnneeClasses = eleveAnneeClasseFacade.get(an.getIdannee());
        return eleveAnneeClasses;
    }

    public void setEleveAnneeClasses(List<Eleveanneeclasse> eleveAnneeClasses) {
        this.eleveAnneeClasses = eleveAnneeClasses;
    }

    public Eleveanneeclasse getSelectedEleveAnneeClasse() {

        return selectedEleveAnneeClasse;
    }

    public void setSelectedEleveAnneeClasse(Eleveanneeclasse selectedEleveAnneeClasse) {
        detail = supprimer = modifier = selectedEleveAnneeClasse == null;
        this.selectedEleveAnneeClasse = selectedEleveAnneeClasse;
    }

}
