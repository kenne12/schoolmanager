/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.affecterMatAClasse;

import entities.Classe;
import entities.Classecategorie;
import entities.Classematiere;
import entities.Etablissement;
import entities.Matiere;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.DualListModel;
import session.ClasseFacadeLocal;
import session.ClassecategorieFacadeLocal;
import session.ClassematiereFacadeLocal;
import session.EtablissementFacadeLocal;
import session.MatiereFacadeLocal;

/**
 *
 * @author gervais
 */
public class AbstractAffecterMatAClasseCtrl {

    @EJB
    protected ClassematiereFacadeLocal classeMatiereFacade;
    protected Classematiere classeMatiere;
    protected List<Classematiere> classeMatieres = new ArrayList<>();
    protected Classematiere selectedClasseMatiere;

    @EJB
    protected ClasseFacadeLocal classeFacade;
    protected Classe classe;
    protected List<Classe> classes = new ArrayList<>();

    @EJB
    protected MatiereFacadeLocal matiereFacade;
    protected DualListModel<Matiere> dualMatiere = new DualListModel<>();


    @EJB
    protected ClassecategorieFacadeLocal classecategorieFacadeLocal;
    protected List<Classecategorie> categories = new ArrayList<>();

    @EJB
    protected EtablissementFacadeLocal etablissementFacadeLocal;
    protected Etablissement etablissement;
    protected List<Etablissement> etablissements = new ArrayList<>();
    

    /**
     * Creates a new instance of AbstractAffcetrMatAClasseCtrl
     */
    public AbstractAffecterMatAClasseCtrl() {

    }

    protected boolean imprimer = true;
    protected boolean detail = true;
    protected boolean supprimer = true;
    protected boolean modifier = true;

    public boolean isImprimer() {
        return imprimer;
    }

    public boolean isDetail() {
        return detail;
    }

    public boolean isSupprimer() {
        return supprimer;
    }

    public boolean isModifier() {
        return modifier;
    }

    public Classematiere getClasseMatiere() {
        return classeMatiere;
    }

    public void setClasseMatiere(Classematiere classeMatiere) {
        this.classeMatiere = classeMatiere;
    }

    public Classematiere getSelectedClasseMatiere() {
        return selectedClasseMatiere;
    }

    public void setSelectedClasseMatiere(Classematiere selectedClasseMatiere) {
        this.selectedClasseMatiere = selectedClasseMatiere;
        modifier = detail = supprimer = selectedClasseMatiere == null;
    }

    

    public List<Classematiere> getClasseMatieres() {
        classeMatieres = classeMatiereFacade.findAll();
        return classeMatieres;
    }

    public void setClasseMatieres(List<Classematiere> classeMatieres) {
        this.classeMatieres = classeMatieres;
    }

    public List<Classe> getClasses() {
        classes = classeFacade.findByEtat(true);
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }



    public DualListModel<Matiere> getDualMatiere() {
        return dualMatiere;
    }

    public void setDualMatiere(DualListModel<Matiere> dualMatiere) {
        this.dualMatiere = dualMatiere;
    }

    public List<Classecategorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Classecategorie> categories) {
        this.categories = categories;
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
