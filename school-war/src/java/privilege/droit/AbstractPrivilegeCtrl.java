/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privilege.droit;

import entities.CompteUtilisateur;
import entities.Menu;
import entities.Personnel;
import entities.Privilege;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.CompteUtiliasteurFacadeLocal;
import session.MenuFacadeLocal;
import session.PrivilegeFacadeLocal;
import utils.SessionMBean;

/**
 *
 * @author Gervais
 */
public class AbstractPrivilegeCtrl {

    protected String fileName;
    @EJB
    protected PrivilegeFacadeLocal privilegeFacade;
    protected List<Privilege> privileges = new ArrayList<>();
    protected Privilege selectedPrivilege;
    protected Privilege privilege;

    @EJB
    protected CompteUtiliasteurFacadeLocal compteUtiliasteurFacadeLocal;
    protected CompteUtilisateur compteUtilisateur;
    protected List<CompteUtilisateur> compteUtiliasteurs = new ArrayList<>();

    @EJB
    protected MenuFacadeLocal menuFacadeLocal;
    protected Menu menu;
    protected List<Menu> menus = new ArrayList<>();
    protected List<Menu> selectedMenus = new ArrayList<>();

    protected Personnel personnel;

    protected String mode = "";

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
        imprimer = privilegeFacade.findAll().isEmpty();
        return imprimer;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public List<Privilege> getPrivileges() {
        privileges = privilegeFacade.findAll();
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    public Privilege getSelectedPrivilege() {
        return selectedPrivilege;
    }

    public void setSelectedPrivilege(Privilege selectedPrivilege) {
        this.selectedPrivilege = selectedPrivilege;
        detail = modifier = supprimer = selectedPrivilege == null;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public CompteUtilisateur getCompteUtilisateur() {
        return compteUtilisateur;
    }

    public void setCompteUtilisateur(CompteUtilisateur compteUtilisateur) {
        this.compteUtilisateur = compteUtilisateur;
    }

    

    public List<CompteUtilisateur> getCompteUtiliasteurs() {
        try {
            compteUtiliasteurs = compteUtiliasteurFacadeLocal.find(SessionMBean.getSchool(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compteUtiliasteurs;
    }

    public void setCompteUtiliasteurs(List<CompteUtilisateur> compteUtiliasteurs) {
        this.compteUtiliasteurs = compteUtiliasteurs;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<Menu> getSelectedMenus() {
        return selectedMenus;
    }

    public void setSelectedMenus(List<Menu> selectedMenus) {
        this.selectedMenus = selectedMenus;
    }
    
    

}
