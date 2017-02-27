/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privilege.menu;

import entities.Menu;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "menuCtrl")
@ViewScoped
public class MenuCtrl extends AbstractMenuCtrl implements MenuInterfaceCtrl, Serializable {

    @PostConstruct
    private void initMenu() {
        selectedMenu = new Menu();
        menu = new Menu();
    }

    @Override
    public void enregistrerMenu() {
        if ("".equals(menu.getNom())) {
            JsfUtil.addErrorMessage("veuillez renseigner un nom de menu");
        } else {
            Menu result = menuFacadeLocal.findByNom(menu.getNom());
            if (result == null) {
                menuFacadeLocal.create(menu);
                initMenu();
                JsfUtil.addSuccessMessage("le menu a été enregistré");
            } else {
                JsfUtil.addErrorMessage("un menu portant ce nom existe deja");
            }
        }
    }

    @Override
    public void modifier() {
        if ("".equals(selectedMenu.getNom())) {
            JsfUtil.addErrorMessage("veuillez renseigner un nom");
        } else {
            menuFacadeLocal.edit(selectedMenu);
            initMenu();
            JsfUtil.addSuccessMessage("le menu a été mis à jour");
        }
    }

    @Override
    public void supprimer() {
        /* if(selectedMenu == null || selectedMenu.getIdmenu()== null){
         JsfUtil.addSuccessMessage("Veuillez selectionner un menu !");
         }
         else{
         menuFacadeLocal.remove(selectedMenu);
         initMenu();
         JsfUtil.addSuccessMessage("opération réussie");
         } */
    }

    @Override
    public void imprimerMenuPdf() {
        System.out.println("Impression pdf types compte");
        // menus = menuFacadeLocal.findAll();
//        fileName = PdfMenu.etatsMenu(menus);
    }

    @Override
    public void imprimerMenuHtml() {
        System.out.println("Impression html types compte");
    }

}
