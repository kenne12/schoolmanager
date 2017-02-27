/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privilege.compteUtilisateur;

import entities.CompteUtilisateur;
import entities.Etablissement;
import entities.Personnel;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.CompteUtiliasteurFacadeLocal;
import session.EtablissementFacadeLocal;
import session.PersonnelFacadeLocal;
import session.TraceurFacadeLocal;

/**
 *
 * @author gervais
 */
public class AbstractCompteUtilisateurCtrl {

    @EJB
    protected CompteUtiliasteurFacadeLocal compteUtiliasteurFacadeLocal;
    protected CompteUtilisateur compteUtilisateur;
    protected List<CompteUtilisateur> compteUtiliasteurs = new ArrayList<>();

    @EJB
    protected PersonnelFacadeLocal personnelFacadeLocal;
    protected Personnel personnel;
    protected List<Personnel> personnels = new ArrayList<>();

    @EJB
    protected EtablissementFacadeLocal etablissementFacadeLocal;
    protected Etablissement etablissement;
    protected List<Etablissement> etablissements = new ArrayList<>();

    @EJB
    protected TraceurFacadeLocal traceurFacadeLocal;

    protected boolean detail = true;
    protected boolean showEtat = false;
    protected boolean showPersonnelMenu = false;

    protected String mode = "";

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }

    public boolean isShowEtat() {
        return showEtat;
    }

    public void setShowEtat(boolean showEtat) {
        this.showEtat = showEtat;
    }

    public boolean isShowPersonnelMenu() {
        return showPersonnelMenu;
    }

    public void setShowPersonnelMenu(boolean showPersonnelMenu) {
        this.showPersonnelMenu = showPersonnelMenu;
    }

    public CompteUtilisateur getCompteUtilisateur() {
        return compteUtilisateur;
    }

    public void setCompteUtilisateur(CompteUtilisateur compteUtilisateur) {
        this.compteUtilisateur = compteUtilisateur;
    }

    public List<CompteUtilisateur> getCompteUtiliasteurs() {
        compteUtiliasteurs = compteUtiliasteurFacadeLocal.findAll();
        return compteUtiliasteurs;
    }

    public void setCompteUtiliasteurs(List<CompteUtilisateur> compteUtiliasteurs) {
        this.compteUtiliasteurs = compteUtiliasteurs;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public List<Personnel> getPersonnels() {
        return personnels;
    }

    public void setPersonnels(List<Personnel> personnels) {
        this.personnels = personnels;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public List<Etablissement> getEtablissements() {
        return etablissements;
    }

    public void setEtablissements(List<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }

}
