/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.categorie;

import entities.Categorie;
import entities.Etablissement;
import java.util.ArrayList;

import java.util.List;
import javax.ejb.EJB;
import session.CategorieFacadeLocal;
import session.EtablissementFacadeLocal;
import session.TraceurFacadeLocal;
import utils.SessionMBean;

/**
 *
 * @author Gervais
 */
public class AbstractCategorie {

    @EJB
    protected CategorieFacadeLocal categorieFacade;

    protected Categorie categorie = new Categorie();
    protected List<Categorie> listCategorie;
    protected Categorie selectedCategorie = new Categorie();

    @EJB
    protected EtablissementFacadeLocal etablissementFacadeLocal;
    protected List<Etablissement> etablissements = new ArrayList<>();

    @EJB
    protected TraceurFacadeLocal traceurFacadeLocal;

    public AbstractCategorie() {

    }

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
        imprimer = categorieFacade.findAll().isEmpty();
        return imprimer;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public List<Categorie> getListCategorie() {
        try {
            listCategorie = categorieFacade.findByEtablisssement(SessionMBean.getSchool().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCategorie;
    }

    public void setListCategorie(List<Categorie> listCategorie) {
        this.listCategorie = listCategorie;
    }

    public Categorie getSelectedCategorie() {
        return selectedCategorie;
    }

    public void setSelectedCategorie(Categorie selectedCategorie) {
        this.selectedCategorie = selectedCategorie;
        modifier = supprimer = detail = selectedCategorie == null;
    }

    public List<Etablissement> getEtablissements() {
        etablissements = etablissementFacadeLocal.findAll();
        return etablissements;
    }

    public void setEtablissements(List<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }

}
