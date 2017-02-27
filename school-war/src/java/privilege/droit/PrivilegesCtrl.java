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
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "privilegesCtrl")
@ViewScoped
public class PrivilegesCtrl extends AbstractPrivilegeCtrl implements PrivilegeInterfaceCtrl, Serializable {
    
    @PostConstruct
    private void initAcces() {
        selectedPrivilege = new Privilege();
        menu = new Menu();
        privilege = new Privilege();
        personnel = new Personnel();
        privilege = new Privilege();
        compteUtilisateur = new CompteUtilisateur();
    }
    
    public void prepareCreate() {
        mode = "Create";
    }
    
    public void prepareEdit() {
        mode = "Retrait";
    }
    
    public void updateMenu() {
        menus.clear();
        try {
            if (compteUtilisateur.getIdcompte() != null) {
                compteUtilisateur = compteUtiliasteurFacadeLocal.find(compteUtilisateur.getIdcompte());
                if (privilegeFacade.find(compteUtilisateur, menuFacadeLocal.find(1)).isEmpty()) {
                    List<Menu> temps = menuFacadeLocal.findAll();
                    for (Menu m : temps) {
                        if (privilegeFacade.find(compteUtilisateur, m).isEmpty()) {
                            menus.add(m);
                        }
                    }
                } else {
                    JsfUtil.addWarningMessage("Cet Utilisateur dispose deja de tous les privileges");
                }
            } else {
                JsfUtil.addErrorMessage("Aucun compte selectionné");
                selectedMenus.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void enregistrerPrivilege() {
        try {
            if (compteUtilisateur.getIdcompte() != null) {
                if (!selectedMenus.isEmpty()) {
                    privilege.setIdmenu(menu);
                    boolean drapeau = false;
                    for (Menu m : selectedMenus) {
                        if (m.getIdmenu().equals(1)) {
                            drapeau = true;
                            break;
                        }
                    }
                    
                    if (drapeau) {
                        
                        List<Privilege> temps = privilegeFacade.find(compteUtilisateur);
                        if (!temps.isEmpty()) {
                            for (Privilege p : temps) {
                                privilegeFacade.remove(p);
                            }
                        }
                        
                        privilege = new Privilege();
                        privilege.setIdprivilege(privilegeFacade.nextVal());
                        privilege.setIdcompte(compteUtilisateur);
                        privilege.setIdmenu(menuFacadeLocal.find(1));
                        privilege.setEtat(detail);
                        privilegeFacade.create(privilege);
                    } else {
                        for (Menu m : selectedMenus) {
                            if (m.getIdmenu().equals(1)) {
                                privilege = new Privilege();
                                privilege.setIdprivilege(privilegeFacade.nextVal());
                                privilege.setIdcompte(compteUtilisateur);
                                privilege.setIdmenu(m);
                                privilege.setEtat(detail);
                                privilegeFacade.create(privilege);
                            }
                        }
                    }
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addErrorMessage("veuillez selectionner les actions");
                }
            } else {
                JsfUtil.addErrorMessage("veuillez selectionnez le personnel");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void modifier() {
        if (personnel.getIdpersonnel() != null) {
            /*if (menu.getIdmenu() != null) {
             selectedPrivilege.setPersonnel(personnel);
             selectedPrivilege.setMenu(menu);
             personnelMenuFacade.edit(selectedPrivilege);
             }*/
        } else {
            JsfUtil.addErrorMessage("aucun personnel selectionné");
        }
    }
    
    @Override
    public void supprimer() {
        /*if (selectedPrivilege.getId() != null) {
         personnelMenuFacade.remove(selectedPrivilege);
         initAcces();
         JsfUtil.addSuccessMessage("operation réussie");
         } else {
         JsfUtil.addErrorMessage("aucune ligne selectionnée !");
         }*/
    }
    
    @Override
    public void imprimerPrivilegePdf() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void imprimerPrivilegeHtml() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
