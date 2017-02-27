/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privilege.accesMenu;

import entities.Menu;
import entities.Personnel;
import entities.Personnelmenu;
import java.util.List;
import javax.ejb.EJB;
import session.PersonnelmenuFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractAccesMenuCtrl {

    protected String fileName;
    @EJB
    protected PersonnelmenuFacadeLocal personnelMenuFacade;
    protected List<Personnelmenu> personnelmenus;
    protected Personnelmenu selectedPersonnelmenu;
    protected Personnelmenu personnelmenu;

    //@EJB
    //protected PersonnelFacadeLocal personnelFacade;
    protected Personnel personnel;
    //protected List <Personnel>personnels;

    //@EJB
    //protected MenuFacadeLocal moduleFacade;
    protected Menu menu;
    //protected List<Module>modules1; 

    protected StringBuffer modulesTableHtml = new StringBuffer("pas encore implementé");

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
        imprimer = personnelMenuFacade.findAll().isEmpty();
        return imprimer;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public List<Personnelmenu> getPersonnelmenus() {
        personnelmenus = personnelMenuFacade.findAll();
        return personnelmenus;
    }

    public void setPersonnelmenus(List<Personnelmenu> personnelmenus) {
        this.personnelmenus = personnelmenus;
    }

    public Personnelmenu getSelectedPersonnelmenu() {
        return selectedPersonnelmenu;
    }

    public void setSelectedPersonnelmenu(Personnelmenu selectedPersonnelmenu) {
        this.selectedPersonnelmenu = selectedPersonnelmenu;
        detail = modifier = supprimer = selectedPersonnelmenu == null;
    }

    public Personnelmenu getPersonnelmenu() {
        return personnelmenu;
    }

    public void setPersonnelmenu(Personnelmenu personnelmenu) {
        this.personnelmenu = personnelmenu;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

}
