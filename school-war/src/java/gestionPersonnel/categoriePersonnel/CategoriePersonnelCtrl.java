/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPersonnel.categoriePersonnel;

import converter.util.JsfUtil;
import entities.Categoriepersonnel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@SessionScoped
public class CategoriePersonnelCtrl extends AbstractCategoriePersonnelCtrl implements CategoriePersoInterfaceCtrl, Serializable {

    /*public AffecterClasseCtrl() {
        
     }*/
    @PostConstruct
    private void initTypeTranche() {

        categoriePersonnel = new Categoriepersonnel();
        selectedCategoriePersonnel = new Categoriepersonnel();
    }

    @Override
    public void enregistrerTypeTranche() {

        if (categoriePersonnel != null) {
            List<Categoriepersonnel> results;
            results = categoriePersonnelFacade.findByCode(categoriePersonnel.getCodecategorie());
            if (results.isEmpty()) {
                categoriePersonnelFacade.create(categoriePersonnel);
                initTypeTranche();
                JsfUtil.addSuccessMessage("La categorie a été crée avec succès");
            } else {
                JsfUtil.addErrorMessage("La categorie existe dejà !");
                initTypeTranche();
            }
        } else {
            JsfUtil.addErrorMessage("veuiller remplir les données");
        }

    }

    @Override
    public void modifier() {
        if (selectedCategoriePersonnel != null) {
            categoriePersonnelFacade.edit(selectedCategoriePersonnel);
            initTypeTranche();
            JsfUtil.addSuccessMessage("La categorie a été mise a jour !");

        } else {
            JsfUtil.addErrorMessage("Veuillez selectionner une categorie !");
        }

    }

    @Override
    public void supprimer() {
        if (selectedCategoriePersonnel.getPersonnelanneecatfonctList().isEmpty()) {
            categoriePersonnelFacade.remove(selectedCategoriePersonnel);
            initTypeTranche();
            JsfUtil.addSuccessMessage("operation réussie !");
        } else {
            initTypeTranche();
            JsfUtil.addErrorMessage("Cette catégorie est deja utilisée !");
        }

    }

    @Override
    public void imprimerTypeTranchePdf() {

    }

    @Override
    public void imprimerTypeTrancheHtml() {

    }
}
