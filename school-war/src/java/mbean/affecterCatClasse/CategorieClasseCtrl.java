/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.affecterCatClasse;

import entities.Categorie;
import entities.Classe;
import entities.Classecategorie;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import utils.JsfUtil;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@SessionScoped
public class CategorieClasseCtrl extends AbstractClasseCatCtrl implements CategorieClasseInterfaceCtrl, Serializable {

    /*public CategorieCtrl() {
        
     }*/
    @PostConstruct
    private void initClasseCategorie() {
        classeCategorie = new Classecategorie();
        selectedClasseCategorie = new Classecategorie();
        classe = new Classe();
        categorie = new Categorie();
    }
    
    @Override
    public void enregistrerClasseCategorie() {
        try {
            if (categorie != null) {
                if (!dualClasses.getTarget().isEmpty()) {
                    classeTarget = dualClasses.getTarget();
                    for (int i = 0; i < classeTarget.size(); i++) {
                        classeCategorie.setId(classeCategorieFacade.nextVal());
                        classeCategorie.setIdcategorie(categorie);
                        classeCategorie.setIdclasse(classeTarget.get(i));
                        classeCategorieFacade.create(classeCategorie);
                        initClasseCategorie();
                    }
                    classeTarget.clear();
                    dualClasses.setTarget(classeTarget);
                    JsfUtil.addSuccessMessage("affectation réussie");
                } else {
                    JsfUtil.addErrorMessage("La liste des classe est vide");
                }
            } else {
                JsfUtil.addErrorMessage("La liste des categorie est vide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void modifier() {
        if (selectedClasses.isEmpty()) {
            JsfUtil.addErrorMessage("Veuillez selectionner une classe");
        } else {
            
            JsfUtil.addSuccessMessage("le tableau est chargé");
        }
        /* if(selectedCategorie!=null){
         categorieFacade.edit(selectedCategorie);
         initCategorie();
         JsfUtil.addSuccessMessage("La categorie a été mise à jour !");
         }
         else{
         JsfUtil.addErrorMessage("La categorie est invalide");
         }*/
    }
    
    @Override
    public void supprimer() {
        /*if(selectedCategorie!=null){
         categorieFacade.remove(selectedCategorie);
         initCategorie();
         JsfUtil.addSuccessMessage("Opération réussie !");
         }
         else{
         JsfUtil.addErrorMessage("Echec de l'opération !");
         }*/
    }
    
    @Override
    public void imprimerClasseCategoriePdf() {
        
    }
    
    @Override
    public void imprimerClasseCategorieHtml() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
