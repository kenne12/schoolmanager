/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPersonnel.affecterCategorie;

import entities.Annee;
import entities.Categoriepersonnel;
import entities.Fonction;
import entities.Personnel;
import entities.Personnelanneecatfonct;
import java.util.List;
import javax.ejb.EJB;
import session.AnneeFacadeLocal;
import session.CategoriepersonnelFacadeLocal;
import session.FonctionFacadeLocal;
import session.PersonnelFacadeLocal;
import session.PersonnelanneecatfonctFacadeLocal;

/**
 *
 * @author kenne gervais
 */
public class AbstractAffecterCategorieCtrl {

    @EJB
    protected PersonnelanneecatfonctFacadeLocal persoAnneeCatFacade;
    protected Personnelanneecatfonct personAnneeCategorie;
    protected Personnelanneecatfonct selectedPersoAnneeCategorie;
    protected List<Personnelanneecatfonct> persoAnneeCategories;

    @EJB
    protected FonctionFacadeLocal fonctionFacade;
    protected Fonction fonction;
    protected List<Fonction> fonctions;

    @EJB
    protected PersonnelFacadeLocal personnelFacade;
    protected Personnel personnel;
    protected List<Personnel> personnels;

    @EJB
    protected AnneeFacadeLocal anneeFacade;
    protected Annee annee;
    protected List<Annee> annees;

    @EJB
    protected CategoriepersonnelFacadeLocal categoriePersonnelFacade;

    protected Categoriepersonnel categoriePersonnel;
    protected List<Categoriepersonnel> categoriePersonnels;

    protected String fileName;

    public AbstractAffecterCategorieCtrl() {

    }

    protected StringBuffer ClasseTableHtml = new StringBuffer("pas encore implementé");

    //classe participante   
    //liste des tableaux des classes participantes
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
        imprimer = categoriePersonnelFacade.findAll().isEmpty();
        return imprimer;
    }

    public StringBuffer getBatimentsTableHtml() {
        return ClasseTableHtml;
    }

    /*public List<Batiment> getListBatiment() {
     ListBatiment = batimentFacadeLocal.findAll();
     return ListBatiment;
     }*/
    public Categoriepersonnel getCategoriePersonnel() {
        return categoriePersonnel;
    }

    public void setCategoriePersonnel(Categoriepersonnel categoriePersonnel) {
        this.categoriePersonnel = categoriePersonnel;
    }

    public List<Categoriepersonnel> getCategoriePersonnels() {
        categoriePersonnels = categoriePersonnelFacade.findByEtat(true);
        return categoriePersonnels;
    }

    public void setCategoriePersonnels(List<Categoriepersonnel> categoriePersonnels) {
        this.categoriePersonnels = categoriePersonnels;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Annee> getAnnees() {
        annees = anneeFacade.findByEtat(true);
        return annees;
    }

    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public List<Fonction> getFonctions() {
        fonctions = fonctionFacade.findAll();
        return fonctions;
    }

    public void setFonctions(List<Fonction> fonctions) {
        this.fonctions = fonctions;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public List<Personnel> getPersonnels() {
        personnels = personnelFacade.findByEtat(true);
        return personnels;
    }

    public void setPersonnels(List<Personnel> personnels) {
        this.personnels = personnels;
    }

    public List<Personnelanneecatfonct> getPersoAnneeCategories() {
        persoAnneeCategories = persoAnneeCatFacade.findAll();
        return persoAnneeCategories;
    }

    public void setPersoAnneeCategories(List<Personnelanneecatfonct> persoAnneeCategories) {
        this.persoAnneeCategories = persoAnneeCategories;
    }

    public Personnelanneecatfonct getSelectedPersoAnneeCategorie() {
        return selectedPersoAnneeCategorie;
    }

    public void setSelectedPersoAnneeCategorie(Personnelanneecatfonct selectedPersoAnneeCategorie) {
        this.selectedPersoAnneeCategorie = selectedPersoAnneeCategorie;
        detail = modifier = supprimer = selectedPersoAnneeCategorie == null;
    }

    public Personnelanneecatfonct getPersonAnneeCategorie() {
        //personAnneeCategorie = persoAnneeCatFacade.findAll();
        return personAnneeCategorie;
    }

    public void setPersonAnneeCategorie(Personnelanneecatfonct personAnneeCategorie) {
        this.personAnneeCategorie = personAnneeCategorie;
    }

}
