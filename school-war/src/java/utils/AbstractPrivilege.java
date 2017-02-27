/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Module;
import java.util.List;
import javax.ejb.EJB;
import session.ModuleFacadeLocal;
import session.PersonnelmoduleFacadeLocal;

/**
 *
 * @author gervais
 */
public class AbstractPrivilege {

    @EJB
    protected ModuleFacadeLocal moduleFacade;
    protected Module module = new Module();
    List<Module> modules;

    @EJB
    protected PersonnelmoduleFacadeLocal personnelModuleFacade;

    protected boolean gestionPersonnel = false;
    protected boolean gestionNote = false;
    protected boolean gestionPrivilege = false;
    protected boolean gestionDiscipline = false;
    protected boolean gestionInscription = false;
    protected boolean gestionEtat = false;
    protected boolean parametrage = false;
    protected boolean bibliotheque = false;

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

    public void setGestonDiscipline(boolean gestonDiscipline) {
        this.gestionDiscipline = gestonDiscipline;
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
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

}
