/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvaluation.classeElementEvaluation;

import entities.Classe;
import entities.ClasseElementevaluation;
import entities.Classecategorie;
import entities.ElementEvaluation;
import entities.Etablissement;
import entities.Matiere;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import org.primefaces.model.DualListModel;
import session.ClasseElementevaluationFacadeLocal;
import session.ClasseFacadeLocal;
import session.ClassecategorieFacadeLocal;
import session.ClassematiereFacadeLocal;
import session.ElementEvaluationFacadeLocal;
import session.EtablissementFacadeLocal;

/**
 *
 * @author gervais
 */
public class AbstractClasseElementEvaluation {
    @EJB
    protected ClasseElementevaluationFacadeLocal classeElementevaluationFacadeLocal;
    protected ClasseElementevaluation classeElementevaluation;
    protected List<ClasseElementevaluation>classeElementevaluations = new ArrayList<>();
    protected List<ClasseElementevaluation>classeElementevaluationTemp = new ArrayList<>();
    
    @EJB
    protected ElementEvaluationFacadeLocal elementEvaluationFacadeLocal; 
    protected ElementEvaluation elementEvaluation;
    protected List<ElementEvaluation>elementEvaluations = new ArrayList<>();
    protected DualListModel<ElementEvaluation>dualElements = new DualListModel<>();
    
    @EJB
    protected EtablissementFacadeLocal etablissementFacadeLocal;
    protected Etablissement etablissement;
    protected List<Etablissement>etablissements = new ArrayList<>();
    
    @EJB
    protected ClasseFacadeLocal classeFacadeLocal;
    protected Classe classe;
    protected List<Classe>classes = new ArrayList<>();
    
    @EJB
    protected ClassecategorieFacadeLocal classecategorieFacadeLocal;
    protected List<Classecategorie> categories = new ArrayList<>();
    
    @EJB
    protected ClassematiereFacadeLocal classematiereFacadeLocal;
    
    protected List<Matiere>matieresTest = new ArrayList<>();
    
    protected boolean  detail = true;

    public boolean isDetail() {
        return detail;
    }

    public void setDetail(boolean detail) {
        this.detail = detail;
    }
    
    

    public ClasseElementevaluation getClasseElementevaluation() {
        return classeElementevaluation;
    }

    public void setClasseElementevaluation(ClasseElementevaluation classeElementevaluation) {
        this.classeElementevaluation = classeElementevaluation;
    }

    public List<ClasseElementevaluation> getClasseElementevaluations() {
        classeElementevaluations = classeElementevaluationFacadeLocal.findAll();
        return classeElementevaluations;
    }

    public void setClasseElementevaluations(List<ClasseElementevaluation> classeElementevaluations) {
        this.classeElementevaluations = classeElementevaluations;
    }
    
    
    

    public ElementEvaluation getElementEvaluation() {
        return elementEvaluation;
    }

    public void setElementEvaluation(ElementEvaluation elementEvaluation) {
        this.elementEvaluation = elementEvaluation;
    }

    public List<ElementEvaluation> getElementEvaluations() {
        return elementEvaluations;
    }

    public void setElementEvaluations(List<ElementEvaluation> elementEvaluations) {
        this.elementEvaluations = elementEvaluations;
    }

    public DualListModel<ElementEvaluation> getDualElements() {
        return dualElements;
    }

    public void setDualElements(DualListModel<ElementEvaluation> dualElements) {
        this.dualElements = dualElements;
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

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void setClasses(List<Classe> classes) {
        this.classes = classes;
    }

    public List<Classecategorie> getCategories() {
        return categories;
    }

    public void setCategories(List<Classecategorie> categories) {
        this.categories = categories;
    }    

    public List<ClasseElementevaluation> getClasseElementevaluationTemp() {
        return classeElementevaluationTemp;
    }

    public void setClasseElementevaluationTemp(List<ClasseElementevaluation> classeElementevaluationTemp) {
        this.classeElementevaluationTemp = classeElementevaluationTemp;
    }
    
    
    
}
