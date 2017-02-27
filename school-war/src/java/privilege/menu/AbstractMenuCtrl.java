/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privilege.menu;

import entities.Menu;
import java.util.List;
import javax.ejb.EJB;
import session.MenuFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractMenuCtrl {

    protected String fileName;
    @EJB
    protected MenuFacadeLocal menuFacadeLocal;

    protected List<Menu> menus;
    protected List<Menu> menuActifs;

    protected StringBuffer menusTableHtml = new StringBuffer("pas encore implementé");
    protected Menu selectedMenu;
    protected Menu menu;
    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Menu> getMenus() {
        menus = menuFacadeLocal.findAll();
        return menus;
    }

    public Menu getSelectedMenu() {
        return selectedMenu;
    }

    public void setSelectedMenu(Menu selectedMenu) {
        this.selectedMenu = selectedMenu;
        modifier = supprimer = detail = selectedMenu == null;
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
        imprimer = menuFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public StringBuffer getMenusTableHtml() {
        return menusTableHtml;
    }

    public List<Menu> getMenuActifs() {
        menuActifs = menuFacadeLocal.getMenuByEtat(true);
        return menuActifs;
    }

    public void setMenuActifs(List<Menu> menuActifs) {
        this.menuActifs = menuActifs;
    }

}
