/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.natureMatiere;

import entities.Naturematiere;

import java.util.List;
import javax.ejb.EJB;
import session.NaturematiereFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractNatureMatiereCtrl {

    @EJB
    protected NaturematiereFacadeLocal natureMatiereFacade;

    protected Naturematiere natureMatiere;

    protected Naturematiere selectedNatureMatiere;

    protected List<Naturematiere> listNatureMatiere;

    public AbstractNatureMatiereCtrl() {

    }

    protected StringBuffer ClasseTableHtml = new StringBuffer("pas encore implementé");

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
        imprimer = natureMatiereFacade.findAll().isEmpty();
        return imprimer;
    }

    public StringBuffer getBatimentsTableHtml() {
        return ClasseTableHtml;
    }

    /*public List<Batiment> getListBatiment() {
     ListBatiment = batimentFacadeLocal.findAll();
     return ListBatiment;
     }*/
    public Naturematiere getNatureMatiere() {
        return natureMatiere;
    }

    public void setNatureMatiere(Naturematiere natureMatiere) {
        this.natureMatiere = natureMatiere;
    }

    public Naturematiere getSelectedNatureMatiere() {
        return selectedNatureMatiere;
    }

    public void setSelectedNatureMatiere(Naturematiere selectedNatureMatiere) {
        this.selectedNatureMatiere = selectedNatureMatiere;
        detail = supprimer = modifier = selectedNatureMatiere == null;
    }

    public List<Naturematiere> getListNatureMatiere() {
        listNatureMatiere = natureMatiereFacade.findAllRange();
        return listNatureMatiere;
    }

    public void setListNatureMatiere(List<Naturematiere> listNatureMatiere) {
        this.listNatureMatiere = listNatureMatiere;
    }

}
