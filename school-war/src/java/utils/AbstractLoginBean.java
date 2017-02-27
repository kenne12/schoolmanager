/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Annee;
import entities.CompteUtilisateur;

import entities.EtablissementPersonnel;
import entities.Menu;
import entities.Module;
import entities.Privilege;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.AnneeFacadeLocal;
import session.CompteUtiliasteurFacadeLocal;
import session.EtablissementFacadeLocal;
import session.EtablissementPersonnelFacadeLocal;
import session.MenuFacadeLocal;
import session.ModuleFacadeLocal;
import session.PersonnelFacadeLocal;
import session.PersonnelmoduleFacadeLocal;
import session.PrivilegeFacadeLocal;

/**
 *
 * @author gervais
 */
public class AbstractLoginBean {

    @EJB
    protected ModuleFacadeLocal moduleFacade;
    protected Module module = new Module();
    List<Module> modules = new ArrayList<>();

    @EJB
    protected CompteUtiliasteurFacadeLocal compteUtiliasteurFacadeLocal;
    protected CompteUtilisateur compteUtilisateur = new CompteUtilisateur();

    @EJB
    protected EtablissementPersonnelFacadeLocal etablissementPersonnelFacadeLocal;
    protected EtablissementPersonnel etablissementPersonnel = new EtablissementPersonnel();
    protected List<EtablissementPersonnel> etablissementPersonnels = new ArrayList<>();

    @EJB
    protected AnneeFacadeLocal anneeFacadeLocal;
    protected Annee annee = new Annee();
    protected List<Annee> annees = new ArrayList<>();

    @EJB
    protected PersonnelmoduleFacadeLocal personnelModuleFacade;

    @EJB
    protected EtablissementFacadeLocal etablissementFacadeLocal;

    @EJB
    protected PersonnelFacadeLocal personnelFacadeLocal;

    @EJB
    private MenuFacadeLocal menuFacadeLocal;

    @EJB
    protected PrivilegeFacadeLocal privilegeFacadeLocal;
    protected List<Privilege> privileges = new ArrayList<>();
    protected List<String> liens = new ArrayList<>();
    protected List<String> liensAll = new ArrayList<>();

    protected boolean gestionPersonnel;
    protected boolean gestionNote;
    protected boolean gestionPrivilege;
    protected boolean gestionDiscipline;
    protected boolean gestionInscription;
    protected boolean gestionEtat;
    protected boolean parametrage;
    protected boolean bibliotheque;

    //visibility module
    protected String gestionPersonnelVisible = "hidden";
    protected String gestionNoteVisible = "hidden";
    protected String gestionPrivilegeVisible = "hidden";
    protected String gestionDisciplineVisible = "hidden";
    protected String gestionInscriptionVisible = "hidden";
    protected String gestionEtatVisible = "hidden";
    protected String parametrageVisible = "hidden";
    protected String bibliothequeVisible = "hidden";

    //break boolean
    protected boolean showHibernatePanel = false;
    protected String hibernatePassword = "";

    //Session boolean
    protected boolean showSessionPanel = true;

    protected String imagePersonnel = "avatar-mini.png";
    protected String nomEtablissement = "--";
    protected String logoEtablissement = "logo.png";
    protected String anneeScolaire = "--";

    public boolean isGestionPersonnel() {
        return gestionPersonnel;
    }

    public void setGestionPersonnel(boolean gestionPersonnel) {
        this.gestionPersonnel = gestionPersonnel;
    }

    public boolean isGestionNote() {
        return gestionNote;
    }

    public void setGestionNote(boolean gestionNote) {
        this.gestionNote = gestionNote;
    }

    public boolean isGestionPrivilege() {
        return gestionPrivilege;
    }

    public void setGestionPrivilege(boolean gestionPrivilege) {
        this.gestionPrivilege = gestionPrivilege;
    }

    public boolean isGestionDiscipline() {
        return gestionDiscipline;
    }

    public void setGestionDiscipline(boolean gestionDiscipline) {
        this.gestionDiscipline = gestionDiscipline;
    }

    public boolean isGestionInscription() {
        return gestionInscription;
    }

    public void setGestionInscription(boolean gestionInscription) {
        this.gestionInscription = gestionInscription;
    }

    public boolean isGestionEtat() {
        return gestionEtat;
    }

    public void setGestionEtat(boolean gestionEtat) {
        this.gestionEtat = gestionEtat;
    }

    public boolean isParametrage() {
        return parametrage;
    }

    public void setParametrage(boolean parametrage) {
        this.parametrage = parametrage;
    }

    public boolean isBibliotheque() {
        return bibliotheque;
    }

    public void setBibliotheque(boolean bibliotheque) {
        this.bibliotheque = bibliotheque;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<Module> getModules() {
        modules = moduleFacade.findAll();
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    //visibilte des modules
    public String getGestionPersonnelVisible() {
        return gestionPersonnelVisible;
    }

    public void setGestionPersonnelVisible(String gestionPersonnelVisible) {
        this.gestionPersonnelVisible = gestionPersonnelVisible;
    }

    public String getGestionNoteVisible() {
        return gestionNoteVisible;
    }

    public void setGestionNoteVisible(String gestionNoteVisible) {
        this.gestionNoteVisible = gestionNoteVisible;
    }

    public String getGestionPrivilegeVisible() {
        return gestionPrivilegeVisible;
    }

    public void setGestionPrivilegeVisible(String gestionPrivilegeVisible) {
        this.gestionPrivilegeVisible = gestionPrivilegeVisible;
    }

    public String getGestionDisciplineVisible() {
        return gestionDisciplineVisible;
    }

    public void setGestionDisciplineVisible(String gestionDisciplineVisible) {
        this.gestionDisciplineVisible = gestionDisciplineVisible;
    }

    public String getGestionInscriptionVisible() {
        return gestionInscriptionVisible;
    }

    public void setGestionInscriptionVisible(String gestionInscriptionVisible) {
        this.gestionInscriptionVisible = gestionInscriptionVisible;
    }

    public String getGestionEtatVisible() {
        return gestionEtatVisible;
    }

    public void setGestionEtatVisible(String gestionEtatVisible) {
        this.gestionEtatVisible = gestionEtatVisible;
    }

    public String getParametrageVisible() {
        return parametrageVisible;
    }

    public void setParametrageVisible(String parametrageVisible) {
        this.parametrageVisible = parametrageVisible;
    }

    public String getBibliothequeVisible() {
        return bibliothequeVisible;
    }

    public void setBibliothequeVisible(String bibliothequeVisible) {
        this.bibliothequeVisible = bibliothequeVisible;
    }

    public boolean isShowHibernatePanel() {
        return showHibernatePanel;
    }

    public void setShowHibernatePanel(boolean showHibernatePanel) {
        this.showHibernatePanel = showHibernatePanel;
    }

    public String getHibernatePassword() {
        return hibernatePassword;
    }

    public void setHibernatePassword(String hibernatePassword) {
        this.hibernatePassword = hibernatePassword;
    }

    public CompteUtilisateur getCompteUtilisateur() {
        return compteUtilisateur;
    }

    public void setCompteUtilisateur(CompteUtilisateur compteUtilisateur) {
        this.compteUtilisateur = compteUtilisateur;
    }

    public EtablissementPersonnel getEtablissementPersonnel() {
        return etablissementPersonnel;
    }

    public void setEtablissementPersonnel(EtablissementPersonnel etablissementPersonnel) {
        this.etablissementPersonnel = etablissementPersonnel;
    }

    public List<EtablissementPersonnel> getEtablissementPersonnels() {
        return etablissementPersonnels;
    }

    public void setEtablissementPersonnels(List<EtablissementPersonnel> etablissementPersonnels) {
        this.etablissementPersonnels = etablissementPersonnels;
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

    public boolean isShowSessionPanel() {
        return showSessionPanel;
    }

    public void setShowSessionPanel(boolean showSessionPanel) {
        this.showSessionPanel = showSessionPanel;
    }

    public List<String> getLiensAll() {
        liensAll.clear();
        try {
            for (Menu m : menuFacadeLocal.findAll()) {
                String[] values = m.getRessource().toString().split(" ");
                for (String v : values) {
                    if (!liensAll.contains(v)) {
                        liensAll.add(v);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liensAll;
    }

    public void setLiensAll(List<String> liensAll) {
        this.liensAll = liensAll;
    }

    public String getImagePersonnel() {
        return imagePersonnel;
    }

    public void setImagePersonnel(String imagePersonnel) {
        this.imagePersonnel = imagePersonnel;
    }

    public String getNomEtablissement() {
        return nomEtablissement;
    }

    public void setNomEtablissement(String nomEtablissement) {
        this.nomEtablissement = nomEtablissement;
    }

    public String getLogoEtablissement() {
        return logoEtablissement;
    }

    public void setLogoEtablissement(String logoEtablissement) {
        this.logoEtablissement = logoEtablissement;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }
    
}
