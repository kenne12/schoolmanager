/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.sessionController;

import entities.Personnel;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import utils.JsfUtil;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "sessionCtrl")
@ViewScoped
public class SessionCtrl extends AbstractSessionCtrl implements SessionInterfaceCtrl, Serializable {

    @PostConstruct
    private void initPersonnel() {
        selectedPersonnel = new Personnel();
        personnel = new Personnel();
    }

    @Override
    public String authentifier() {
        personnel = personnelFacadeLocal.fingByLoginPassword(login, password);
        if (personnel != null) {
            JsfUtil.addSuccessMessage("c'est cool");
            if (personnel.getEtatpersonnel()) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("current_user", personnel);
                return "index.xhtml?faces-redirect=true";
            } else {
                return "login.xhtml?faces-redirect=true";
            }
        } else {
            JsfUtil.addErrorMessage("Vos identifiants sont incorrects");
            return "login.xhtml?faces-redirect=true";
        }
    }

    @Override
    public String deconnecter() {
        personnel = new Personnel();
        //conecte = false;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("current_user");
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
        return "login.xhtml?faces-redirect=true";
    }

    @Override
    public void privilege() {

    }

    public void watcher() {
        try {
            if (!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("current_user")) {
                ((FacesContext.getCurrentInstance()).getExternalContext()).redirect("login.xhtml?faces-redirect=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
