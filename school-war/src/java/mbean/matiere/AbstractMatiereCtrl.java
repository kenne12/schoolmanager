/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.matiere;

import entities.Matiere;
import entities.Naturematiere;
import java.util.ArrayList;

import java.util.List;
import javax.ejb.EJB;
import session.MatiereFacadeLocal;
import session.NaturematiereFacadeLocal;
import session.TraceurFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractMatiereCtrl {

    @EJB
    protected MatiereFacadeLocal matiereFacadeLocal;
    protected Matiere matiere;
    protected List<Matiere> matieres = new ArrayList<>();
    protected Matiere selected;

    @EJB
    protected NaturematiereFacadeLocal natureMatiereFacade;
    protected Naturematiere natureMatiere;
    protected List<Naturematiere> listNatureMatiere;

    @EJB
    protected TraceurFacadeLocal traceurFacadeLocal;

    public AbstractMatiereCtrl() {

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
        imprimer = matiereFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public List<Matiere> getMatieres() {
        matieres = matiereFacadeLocal.findAllRange();
        return matieres;
    }

    public void setMatieres(List<Matiere> matieres) {
        this.matieres = matieres;
    }

    public Naturematiere getNatureMatiere() {
        return natureMatiere;
    }

    public void setNatureMatiere(Naturematiere natureMatiere) {
        this.natureMatiere = natureMatiere;
    }

    public List<Naturematiere> getListNatureMatiere() {
        listNatureMatiere = natureMatiereFacade.findAllRange();
        return listNatureMatiere;
    }

    public void setListNatureMatiere(List<Naturematiere> listNatureMatiere) {
        this.listNatureMatiere = listNatureMatiere;
    }

    public Matiere getSelected() {
        return selected;
    }

    public void setSelected(Matiere selected) {
        detail = modifier = supprimer = selected == null;
        this.selected = selected;
    }

}
