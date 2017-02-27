/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privilege.module;

import entities.Module;
import java.util.List;
import javax.ejb.EJB;
import session.ModuleFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractModuleCtrl {

    protected String fileName;
    @EJB
    protected ModuleFacadeLocal moduleFacadeLocal;

    protected List<Module> modules;

    protected StringBuffer modulesTableHtml = new StringBuffer("pas encore implementé");
    protected Module selectedModule;
    protected Module module;
    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Module> getModules() {
        modules = moduleFacadeLocal.findAll();
        return modules;
    }

    public Module getSelectedModule() {
        return selectedModule;
    }

    public void setSelectedModule(Module selectedModule) {
        this.selectedModule = selectedModule;
        modifier = supprimer = detail = selectedModule == null;
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

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public StringBuffer getModulesTableHtml() {
        return modulesTableHtml;
    }

}
