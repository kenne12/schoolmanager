/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.batiment;

import entities.Batiment;
import entities.Etablissement;
import entities.Traceur;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import session.BatimentFacadeLocal;
import session.EtablissementFacadeLocal;
import session.TraceurFacadeLocal;
import utils.SessionMBean;

/**
 *
 * @author Gervais
 */
public class AbstractBatimentCtrl {

    protected String fileName;
    @EJB
    protected BatimentFacadeLocal batimentFacadeLocal;

    protected List<Batiment> batiments;

    @EJB
    protected TraceurFacadeLocal traceurFcade;
    protected Traceur traceur;

    @EJB
    protected EtablissementFacadeLocal etablissementFacadeLocal;
    protected List<Etablissement> etablissements = new ArrayList<>();

    protected StringBuffer batimentsTableHtml = new StringBuffer("pas encore implementé");
    protected Batiment selectedBatiment;
    protected Batiment batiment;
    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Batiment> getBatiments() {
        batiments = batimentFacadeLocal.findByEtablissement(SessionMBean.getSchool().getId());
        return batiments;
    }

    public Batiment getSelectedBatiment() {
        return selectedBatiment;
    }

    public void setSelectedBatiment(Batiment selectedBatiment) {
        this.selectedBatiment = selectedBatiment;
        modifier = supprimer = detail = selectedBatiment == null;
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
        imprimer = batimentFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Batiment getBatiment() {
        return batiment;
    }

    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }

    public StringBuffer getBatimentsTableHtml() {
        return batimentsTableHtml;
    }

    public List<Etablissement> getEtablissements() {
        etablissements = etablissementFacadeLocal.findAll();
        return etablissements;
    }

    public void setEtablissements(List<Etablissement> etablissements) {
        this.etablissements = etablissements;
    }

}
