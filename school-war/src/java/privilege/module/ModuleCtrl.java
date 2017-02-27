/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privilege.module;

import entities.Menu;
import entities.Module;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "moduleCtrl")
@ViewScoped
public class ModuleCtrl extends AbstractModuleCtrl implements ModuleInterfaceCtrl, Serializable {

    @PostConstruct
    private void initModule() {
        selectedModule = new Module();
        module = new Module();
    }

    @Override
    public void enregistrerModule() {
        if (module.getNom() == null) {
            JsfUtil.addErrorMessage("veuillez renseigner un nom de module");
        } else {
            Module result = moduleFacadeLocal.findByNom(module.getNom());
            if (result == null) {
                moduleFacadeLocal.create(module);
                initModule();
                JsfUtil.addSuccessMessage("le module a été enregistré");
            } else {
                JsfUtil.addErrorMessage("un module portant ce nom existe deja");
            }

        }

    }

    @Override
    public void modifier() {
        if ("".equals(selectedModule.getNom())) {
            JsfUtil.addErrorMessage("veuillez renseigner un nom");
        } else {
            moduleFacadeLocal.edit(selectedModule);
            initModule();
            JsfUtil.addSuccessMessage("le module a été mis à jour");
        }
    }

    @Override
    public void supprimer() {
        /*if(selectedModule == null || selectedModule.getIdModule()== null){
         JsfUtil.addSuccessMessage("Veuillez selectionner un Module !");
         }
         else{
         ModuleFacadeLocal.remove(selectedModule);
         initModule();
         JsfUtil.addSuccessMessage("opération réussie");
         } */
    }

    @Override
    public void imprimerModulePdf() {
        /*System.out.println("Impression pdf types compte");
         Modules = ModuleFacadeLocal.findAll();
         //        fileName = PdfModule.etatsModule(Modules);*/
    }

    @Override
    public void imprimerModuleHtml() {
        System.out.println("Impression html types compte");
    }

}
