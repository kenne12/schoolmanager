/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.etablissement;

import entities.Adresse;
import entities.Annee;
import entities.Batiment;
import entities.Categorie;
import entities.Classe;
import entities.CompteUtilisateur;

import entities.Enseignement;
import entities.Etablissement;
import entities.EtablissementPersonnel;
import entities.Fonction;
import entities.Personnel;
import entities.TypeEtablissement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import session.AdresseFacadeLocal;
import session.AnneeFacadeLocal;
import session.BatimentFacadeLocal;
import session.CategorieFacadeLocal;
import session.ClasseFacadeLocal;
import session.CompteUtiliasteurFacadeLocal;
import session.EnseignementFacadeLocal;
import session.EtablissementFacadeLocal;
import session.EtablissementPersonnelFacadeLocal;
import session.FonctionFacadeLocal;
import session.PersonnelFacadeLocal;
import session.TypeEtablissementFacadeLocal;

/**
 *
 * @author gervais kenne
 */
public class AbstractEtablissementCtrl {


    @EJB
    protected EtablissementFacadeLocal etablissementFacadeLocal;
    protected Etablissement etablissement = new Etablissement();
    protected List<Etablissement> etablissements = new ArrayList<Etablissement>();

    @EJB
    protected TypeEtablissementFacadeLocal typeEtablissementFacadeLocal;
    protected TypeEtablissement typeEtablissement = new TypeEtablissement();
    protected List<TypeEtablissement> typeEtablissements = new ArrayList<>();

    protected List<EtablissementPersonnel> etablissementPersonnels = new ArrayList<EtablissementPersonnel>();

    @EJB
    protected AdresseFacadeLocal adresseFacadeLocal;
    protected Adresse adresse2 = new Adresse();

    @EJB
    protected BatimentFacadeLocal batimentFacadeLocal;
    protected Batiment batiment = new Batiment();
    protected List<Batiment> batiments = new ArrayList<Batiment>();

    @EJB
    protected CategorieFacadeLocal categorieFacadeLocal;
    protected Categorie categorie = new Categorie();
    protected List<Categorie> categories = new ArrayList<Categorie>();

    @EJB
    protected EnseignementFacadeLocal brancheFacadeLocal;
    protected Enseignement branche = new Enseignement();

    @EJB
    protected ClasseFacadeLocal classeFacadeLocal;
    protected Classe classe = new Classe();
    protected List<Classe> classes = new ArrayList<Classe>();

    @EJB
    protected EnseignementFacadeLocal enseignementFacadeLocal;
    protected List<Enseignement> enseignements = new ArrayList<>();

    @EJB
    protected PersonnelFacadeLocal personnelFacadeLocal;
    protected Personnel personnel = new Personnel();

    @EJB
    protected CompteUtiliasteurFacadeLocal compteUtiliasteurFacadeLocal;
    protected CompteUtilisateur compteUtilisateur = new CompteUtilisateur();

    @EJB
    protected EtablissementPersonnelFacadeLocal etablissementPersonnelFacadeLocal;
    protected EtablissementPersonnel etablissementPersonnel;

    @EJB
    protected FonctionFacadeLocal fonctionFacadeLocal;
    protected Fonction fonction = new Fonction();
    protected List<Fonction> fonctions = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    protected Annee annee = new Annee();

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    protected String mode = "";

    public List<Etablissement> getEtablissements() {
        etablissements = etablissementFacadeLocal.findAll();
        return etablissements;
    }

    public boolean isDetail() {
        return detail;
    }

    public boolean isModifier() {
        return modifier;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
        modifier = supprimer = detail = etablissement == null;
    }

    public void setCycle(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public boolean isSupprimer() {
        return supprimer;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public List<Batiment> getBatiments() {
        return batiments;
    }

    public void setBatiments(List<Batiment> batiments) {
        this.batiments = batiments;
    }

    public List<EtablissementPersonnel> getEtablissementPersonnels() {
        return etablissementPersonnels;
    }

    public void setEtablissementPersonnels(List<EtablissementPersonnel> EtablissementPersonnels) {
        this.etablissementPersonnels = EtablissementPersonnels;
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Categorie> categories) {
        this.categories = categories;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Batiment getBatiment() {
        return batiment;
    }

    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Enseignement getBranche() {
        return branche;
    }

    public void setBranche(Enseignement branche) {
        this.branche = branche;
    }

    public List<Enseignement> getEnseignements() {
        return enseignements;
    }

    public void setEnseignements(List<Enseignement> enseignements) {
        this.enseignements = enseignements;
    }

    public List<TypeEtablissement> getTypeEtablissements() {
        typeEtablissements = typeEtablissementFacadeLocal.findAll();
        return typeEtablissements;
    }

    public void setTypeEtablissements(List<TypeEtablissement> typeEtablissements) {
        this.typeEtablissements = typeEtablissements;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public List<Fonction> getFonctions() {
        return fonctions;
    }

    public void setFonctions(List<Fonction> fonctions) {
        this.fonctions = fonctions;
    }

    public CompteUtilisateur getCompteUtilisateur() {
        return compteUtilisateur;
    }

    public void setCompteUtilisateur(CompteUtilisateur compteUtilisateur) {
        this.compteUtilisateur = compteUtilisateur;
    }

    public Adresse getAdresse2() {
        return adresse2;
    }

    public void setAdresse2(Adresse adresse2) {
        this.adresse2 = adresse2;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public TypeEtablissement getTypeEtablissement() {
        return typeEtablissement;
    }

    public void setTypeEtablissement(TypeEtablissement typeEtablissement) {
        this.typeEtablissement = typeEtablissement;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }
    
    
    
    

}
