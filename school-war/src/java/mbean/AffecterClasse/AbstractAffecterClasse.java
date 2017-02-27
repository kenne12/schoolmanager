/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.AffecterClasse;

import entities.Classe;
import entities.Classesalle;
import entities.Salle;
import java.util.List;
import javax.ejb.EJB;
import session.ClasseFacadeLocal;
import session.ClassesalleFacadeLocal;
import session.SalleFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractAffecterClasse {

    protected String fileName;
    @EJB
    protected ClasseFacadeLocal ClasseFacadeLocal;

    @EJB
    protected ClassesalleFacadeLocal classeSalleFacade;

    //@EJB protected BatimentFacadeLocal batimentFacadeLocal;
    @EJB
    protected ClasseFacadeLocal classeFacade;

    @EJB
    protected SalleFacadeLocal salleFacade;

    public AbstractAffecterClasse() {

    }

    protected List<Classesalle> listAffectation;

    protected List<Classe> Classes;

    protected StringBuffer ClasseTableHtml = new StringBuffer("pas encore implementé");

    protected Classesalle selectedAffectation = new Classesalle();

    protected Classesalle affectation = new Classesalle();

    //classe participante   
    protected Classe classe = new Classe();
    protected Salle salle = new Salle();

    //liste des tableaux des classes participantes
    protected List<Classe> ListClasse;
    protected List<Salle> ListSalle;

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Classe> getClasses() {
        Classes = ClasseFacadeLocal.findAll();
        return Classes;
    }

    public void setClasses(List<Classe> Classes) {
        this.Classes = Classes;
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
        imprimer = ClasseFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public StringBuffer getBatimentsTableHtml() {
        return ClasseTableHtml;
    }

    /*public List<Batiment> getListBatiment() {
     ListBatiment = batimentFacadeLocal.findAll();
     return ListBatiment;
     }*/
    public Classesalle getAffectation() {
        return affectation;
    }

    public void setAffectation(Classesalle affectation) {
        this.affectation = affectation;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public List<Classe> getListClasse() {
        ListClasse = classeFacade.findAll();
        return ListClasse;
    }

    public void setListClasse(List<Classe> ListClasse) {

        this.ListClasse = ListClasse;
    }

    public List<Salle> getListSalle() {
        ListSalle = salleFacade.findAll();
        return ListSalle;
    }

    public void setListSalle(List<Salle> ListSalle) {

        this.ListSalle = ListSalle;
    }

    public Classesalle getSelectedAffectation() {
        return selectedAffectation;
    }

    public void setSelectedAffectation(Classesalle selectedAffectation) {
        this.selectedAffectation = selectedAffectation;
        modifier = supprimer = detail = selectedAffectation == null;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<Classesalle> getListAffectation() {
        listAffectation = classeSalleFacade.findAll();
        return listAffectation;
    }

    public void setListAffectation(List<Classesalle> listAffectation) {
        this.listAffectation = listAffectation;
    }

}
