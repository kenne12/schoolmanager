/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.sessionController;

import entities.Personnel;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.UploadedFile;
import session.PersonnelFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractSessionCtrl {

    protected String fileName;
    @EJB
    protected PersonnelFacadeLocal personnelFacadeLocal;

    protected List<Personnel> personnels;
    protected List<Personnel> personnelActifs;

    protected StringBuffer personnelTableHtml = new StringBuffer("pas encore implementé");
    protected Personnel selectedPersonnel;
    protected Personnel personnel;

    String login = "";
    String password = "";

    protected UploadedFile file;

    protected String confirmPassword;
    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    //protected ShaHash shaHsh;
    public List<Personnel> getPersonnels() {
        personnels = personnelFacadeLocal.findAll();
        return personnels;
    }

    public Personnel getSelectedPersonnel() {
        return selectedPersonnel;
    }

    public void setSelectedPersonnel(Personnel selectedPersonnel) {
        this.selectedPersonnel = selectedPersonnel;
        modifier = supprimer = detail = selectedPersonnel == null;
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
        imprimer = personnelFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public StringBuffer getPersonnelsTableHtml() {
        return personnelTableHtml;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<Personnel> getPersonnelActifs() {
        personnelActifs = personnelFacadeLocal.findByEtat(true);
        return personnelActifs;
    }

    public void setPersonnelActifs(List<Personnel> personnelActifs) {
        this.personnelActifs = personnelActifs;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
