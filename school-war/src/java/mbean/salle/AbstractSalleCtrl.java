/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.salle;

import entities.Batiment;
import entities.Salle;
import java.util.List;
import javax.ejb.EJB;
import session.BatimentFacadeLocal;
import session.SalleFacadeLocal;
import utils.SessionMBean;

/**
 *
 * @author gervais kenne
 */
public class AbstractSalleCtrl {

    @EJB
    protected SalleFacadeLocal salleFacadeLocal;

    @EJB
    protected BatimentFacadeLocal batimentFacadeLocal;

    public AbstractSalleCtrl() {

    }

    protected List<Salle> salles;
    protected List<Batiment> ListBatiment;

    protected StringBuffer salleTableHtml = new StringBuffer("pas encore implementé");
    protected Salle selectedSalle;
    protected Salle salle;

    protected Batiment batiment;
    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Salle> getSalles() {
        salles = salleFacadeLocal.findAll();
        return salles;
    }

    public void setSalles(List<Salle> salles) {
        this.salles = salles;
    }

    public Salle getSelectedSalle() {
        return this.selectedSalle;
    }

    public void setSelectedSalle(Salle selectedSalle) {
        this.selectedSalle = selectedSalle;
        modifier = supprimer = detail = selectedSalle == null;
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
        imprimer = salleFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Batiment getBatiment() {
        return batiment;
    }

    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }

    public StringBuffer getBatimentsTableHtml() {
        return salleTableHtml;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public List<Batiment> getListBatiment() {
        ListBatiment = batimentFacadeLocal.findByEtablissement(SessionMBean.getSchool().getId());
        return ListBatiment;
    }

    public void setListBatiment(List<Batiment> ListBatiment) {
        this.ListBatiment = ListBatiment;
    }

}
