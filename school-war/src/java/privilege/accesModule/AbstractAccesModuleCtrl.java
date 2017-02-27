/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privilege.accesModule;

import entities.Module;
import entities.Personnel;
import entities.Personnelmodule;
import entities.Traceur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.DualListModel;
import session.ModuleFacadeLocal;
import session.PersonnelFacadeLocal;
import session.PersonnelmoduleFacadeLocal;
import session.TraceurFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractAccesModuleCtrl {

    @EJB
    protected PersonnelmoduleFacadeLocal moduleFacadeLocal;
    protected List<Personnelmodule> modules;
    protected Personnelmodule selectedPersonnelmodule;
    protected Personnelmodule module;

    @EJB
    protected PersonnelFacadeLocal personnelFacade;
    protected Personnel personnel;
    protected List<Personnel> personnels;

    @EJB
    protected ModuleFacadeLocal moduleFacade;
    protected Module module1;
    protected List<Module> modules1;

    protected List<Module> moduleSources = new ArrayList<>();
    protected List<Module> moduleTarget = new ArrayList<>();
    protected DualListModel<Module> dualModules = new DualListModel<>();

    @EJB
    protected TraceurFacadeLocal traceurFacade;
    protected Traceur traceur;

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Personnelmodule> getPersonnelmodules() {
        modules = moduleFacadeLocal.findAll();
        return modules;
    }

    public Personnelmodule getSelectedPersonnelmodule() {
        return selectedPersonnelmodule;
    }

    public void setSelectedPersonnelmodule(Personnelmodule selectedPersonnelmodule) {
        this.selectedPersonnelmodule = selectedPersonnelmodule;
        modifier = supprimer = detail = selectedPersonnelmodule == null;
    }

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
        imprimer = moduleFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Personnelmodule getPersonnelmodule() {
        return module;
    }

    public void setPersonnelmodule(Personnelmodule module) {
        this.module = module;
    }

    public Module getModule1() {
        return module1;
    }

    public void setModule1(Module module1) {
        this.module1 = module1;
    }

    public List<Module> getModules1() {
        modules1 = moduleFacade.getModules(true);
        return modules1;
    }

    public void setModules1(List<Module> modules1) {
        this.modules1 = modules1;
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

    public List<Personnelmodule> getModules() {
        return modules;
    }

    public void setModules(List<Personnelmodule> modules) {
        this.modules = modules;
    }

    public Personnelmodule getModule() {
        return module;
    }

    public void setModule(Personnelmodule module) {
        this.module = module;
    }

    public List<Module> getModuleSources() {
        return moduleSources;
    }

    public void setModuleSources(List<Module> moduleSources) {
        this.moduleSources = moduleSources;
    }

    public List<Module> getModuleTarget() {
        return moduleTarget;
    }

    public void setModuleTarget(List<Module> moduleTarget) {
        this.moduleTarget = moduleTarget;
    }

    public DualListModel<Module> getDualModules() {
        return dualModules;
    }

    public void setDualModules(DualListModel<Module> dualModules) {
        this.dualModules = dualModules;
    }

}
