/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Module;
import entities.Personnel;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.expression.impl.ThisExpressionResolver;

/**
 *
 * @author gervais
 */
@ManagedBean
@SessionScoped
public class PrivilegeCtrl extends AbstractPrivilege {

    /**
     * Creates a new instance of PrivilegeCtrl
     */
    public PrivilegeCtrl() {

    }

    @PostConstruct
    private void init() {
        module = new Module();
        //this.setPrivilege();
        //Personnel user = UtilitaireSession.getInstance().getuser();
        //setPrivilege(user);
    }

    /*public static void initprivi(){
     setPrivilege();
     }*/
    public void setPrivilege() {
        Personnel perso = UtilitaireSession.getInstance().getuser();

        for (int i = 0; i < modules.size(); i++) {
            if ("GP".equals(modules.get(i).getCode())) {
                /*if(modules.get(i).getEtat()){
                 gestionPersonnel = personnelModuleFacade.findByPersonnelModule(perso.getIdpersonnel(), modules.get(i).getId(), true);
                 }
                 else{*/
                setGestionPersonnel(modules.get(i).getEtat());
                /*}  */
            }

            if ("PS".equals(modules.get(i).getCode())) {
                /*if(modules.get(i).getEtat()){
                 parametrage = personnelModuleFacade.findByPersonnelModule(perso.getIdpersonnel(), modules.get(i).getId(), true);
                 }
                 else{*/
                setParametrage(modules.get(i).getEtat());
                /*} */
            }

            if ("EC".equals(modules.get(i).getCode())) {
                /*if(modules.get(i).getEtat()){
                 gestionInscription = personnelModuleFacade.findByPersonnelModule(perso.getIdpersonnel(), modules.get(i).getId(), true);
                 }
                 else{*/
                setGestionInscription(modules.get(i).getEtat());
                /*}  */
            }

            if ("GN".equals(modules.get(i).getCode())) {
                if (modules.get(i).getEtat()) {
                    gestionNote = personnelModuleFacade.findByPersonnelModule(perso.getIdpersonnel(), modules.get(i).getId(), true);
                } else {
                    setGestionNote(modules.get(i).getEtat());
                }
            }

            if ("GPri".equals(modules.get(i).getCode())) {
                if (modules.get(i).getEtat()) {
                    gestionPrivilege = personnelModuleFacade.findByPersonnelModule(perso.getIdpersonnel(), modules.get(i).getId(), true);
                } else {
                    setGestionPrivilege(modules.get(i).getEtat());
                }
            }

            if ("GB".equals(modules.get(i).getCode())) {
                if (modules.get(i).getEtat()) {
                    bibliotheque = personnelModuleFacade.findByPersonnelModule(perso.getIdpersonnel(), modules.get(i).getId(), true);
                } else {
                    setBibliotheque(modules.get(i).getEtat());
                }
            }

            if ("GD".equals(modules.get(i).getCode())) {
                if (modules.get(i).getEtat()) {
                    gestionDiscipline = personnelModuleFacade.findByPersonnelModule(perso.getIdpersonnel(), modules.get(i).getId(), true);
                } else {
                    setGestonDiscipline(modules.get(i).getEtat());
                }
            }

            if ("GE".equals(modules.get(i).getCode())) {
                if (modules.get(i).getEtat()) {
                    gestionEtat = personnelModuleFacade.findByPersonnelModule(perso.getIdpersonnel(), modules.get(i).getId(), true);
                } else {
                    setGestionEtat(modules.get(i).getEtat());
                }
            }
        }
    }

}
