/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privilege.accesMenu;

import entities.Menu;
import entities.Personnel;
import entities.Personnelmenu;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "accesMenuCtrl")
@ViewScoped
public class AccesMenuCtrl extends AbstractAccesMenuCtrl implements AccesMenuInterfaceCtrl, Serializable {

    @PostConstruct
    private void initAcces() {
        selectedPersonnelmenu = new Personnelmenu();
        menu = new Menu();
        personnelmenu = new Personnelmenu();
        personnel = new Personnel();
    }

    @Override
    public void enregistrerAccesMenu() {
        if (personnel.getIdpersonnel() != null) {
            if (menu.getIdmenu() != null) {
                personnelmenu.setMenu(menu);
                personnelmenu.setPersonnel(personnel);
                Personnelmenu result;
                result = personnelMenuFacade.findByPersonnelMenu(personnel.getIdpersonnel(), menu.getIdmenu());
                if (result == null) {
                    personnelMenuFacade.create(personnelmenu);
                    initAcces();
                    JsfUtil.addSuccessMessage("Autorisation réussie !");
                } else {
                    if (result.getEtat()) {
                        initAcces();
                        JsfUtil.addSuccessMessage("le personnel selectionné a deja ce rivilège!");
                    } else {
                        result.setEtat(true);
                        personnelMenuFacade.edit(result);
                        JsfUtil.addSuccessMessage("le privilège a été reactivé");
                    }
                }
                System.err.println("personnel");
            } else {
                JsfUtil.addErrorMessage("veuillez selectionner lemenu");
            }
        } else {
            JsfUtil.addErrorMessage("veuillez selectionnez le personnel");
        }
    }

    @Override
    public void modifier() {
        if (personnel.getIdpersonnel() != null) {
            if (menu.getIdmenu() != null) {
                selectedPersonnelmenu.setPersonnel(personnel);
                selectedPersonnelmenu.setMenu(menu);
                personnelMenuFacade.edit(selectedPersonnelmenu);
            }
        } else {
            JsfUtil.addErrorMessage("aucun personnel selectionné");
        }
    }

    @Override
    public void supprimer() {
        if (selectedPersonnelmenu.getId() != null) {
            personnelMenuFacade.remove(selectedPersonnelmenu);
            initAcces();
            JsfUtil.addSuccessMessage("operation réussie");
        } else {
            JsfUtil.addErrorMessage("aucune ligne selectionnée !");
        }
    }

    @Override
    public void imprimerAccesMenuPdf() {
        /*System.out.println("Impression pdf types compte");
         Modules = ModuleFacadeLocal.findAll();
         //        fileName = PdfModule.etatsModule(Modules);*/
    }

    @Override
    public void imprimerAccesMenuHtml() {
        System.out.println("Impression html types compte");
    }

}
