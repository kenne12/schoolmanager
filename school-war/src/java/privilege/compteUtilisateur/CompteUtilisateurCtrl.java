/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privilege.compteUtilisateur;

import entities.CompteUtilisateur;
import entities.EtablissementPersonnel;
import entities.Personnel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.SessionMBean;

/**
 *
 * @author gervais
 */
@ManagedBean
@ViewScoped
public class CompteUtilisateurCtrl extends AbstractCompteUtilisateurCtrl implements CompteUtilisateurInterface {

    /**
     * Creates a new instance of CompteUtilisateurCtrl
     */
    public CompteUtilisateurCtrl() {

    }

    @PostConstruct
    private void init() {

    }

    public void activeButton() {
        detail = false;
    }

    public void deactiveButton() {
        detail = true;
    }

    public void prepareCreate() {
        try {
            showEtat = false;
            showPersonnelMenu = false;
            mode = "Create";
            compteUtilisateur = new CompteUtilisateur();
            compteUtilisateur.setDatecreation(new Date());
            personnels.clear();
            if (SessionMBean.getUserAccount() != null) {

                List<Personnel> personnels1 = new ArrayList<>();
                List<Personnel> personnels2 = new ArrayList<>();
                for (EtablissementPersonnel e : SessionMBean.getUserAccount().getEtablissement().getEtablissementPersonnelList()) {
                    personnels1.add(e.getPersonnel());
                }

                for (CompteUtilisateur c : SessionMBean.getUserAccount().getEtablissement().getCompteUtilisateurList()) {
                    personnels2.add(c.getPersonnel());
                }

                for (Personnel p : personnels1) {
                    if (!personnels2.contains(p)) {
                        personnels.add(p);
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void prepareEdit() {
        personnels.clear();
        try {
            showEtat = true;
            showPersonnelMenu = true;
            if (SessionMBean.getUserAccount() != null) {

                for (EtablissementPersonnel e : SessionMBean.getUserAccount().getEtablissement().getEtablissementPersonnelList()) {
                    personnels.add(e.getPersonnel());
                }
            }
            mode = "Edit";
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void save() {
        try {
            if (mode.equals("Create")) {
                compteUtilisateur.setIdcompte(compteUtiliasteurFacadeLocal.nextVal());
                compteUtilisateur.setEtablissement(SessionMBean.getUserAccount().getEtablissement());
                compteUtilisateur.setEtat(true);
                compteUtiliasteurFacadeLocal.create(compteUtilisateur);
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                if (compteUtilisateur != null) {
                    compteUtiliasteurFacadeLocal.edit(compteUtilisateur);
                    JsfUtil.addSuccessMessage("Operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne sélectionée");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit() {

    }

    @Override
    public void delete() {
        try {
            if (compteUtilisateur != null) {
                compteUtiliasteurFacadeLocal.remove(compteUtilisateur);
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne sélectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void print() {

    }

}
