/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.categorie;

import entities.Categorie;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.UtilitaireSession;
import utils.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@SessionScoped
public class CategorieCtrl extends AbstractCategorie implements CategorieInterfaceCtrl, Serializable {

    public CategorieCtrl() {

    }

    @PostConstruct
    private void initCategorie() {
        categorie = new Categorie();
        selectedCategorie = new Categorie();
    }

    @Override
    public void enregistrerCategorie() {
        try {
            List<Categorie> cat = categorieFacade.findByNom(SessionMBean.getSchool().getId(), categorie.getNom());
            if (cat.isEmpty()) {
                categorie.setEtablissement(SessionMBean.getSchool());
                categorieFacade.create(categorie);
                Utilitaires.saveOperation(traceurFacadeLocal, "Enregistrement de la catégorie -> " + categorie.getNom() + " , Etablissement -> " + SessionMBean.getSchool().getNom(), UtilitaireSession.getInstance().getuser());
                initCategorie();
                JsfUtil.addSuccessMessage("La categorie a été enregistrée");
            } else {
                JsfUtil.addErrorMessage("Une categorie  portant ce nom existe déjà");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void modifier() {
        try {
            if (selectedCategorie != null) {
                Categorie categorieTemp = categorieFacade.find(selectedCategorie.getIdcategorie());
                categorieFacade.edit(selectedCategorie);
                Utilitaires.saveOperation(traceurFacadeLocal, "Modification de la catégorie " + categorieTemp.getNom() + " ->" + selectedCategorie.getNom(), UtilitaireSession.getInstance().getuser());
                initCategorie();
                JsfUtil.addSuccessMessage("La categorie a été mise à jour !");
            } else {
                JsfUtil.addErrorMessage("La categorie est invalide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void supprimer() {
        try {
            if (selectedCategorie != null) {
                if (selectedCategorie.getCatanneeprixList().isEmpty()) {
                    if (selectedCategorie.getTrancheList().isEmpty()) {
                        if (selectedCategorie.getClassecategorieList().isEmpty()) {
                            categorieFacade.remove(selectedCategorie);
                            initCategorie();
                            JsfUtil.addSuccessMessage("Opération réussie !");
                        } else {
                            JsfUtil.addErrorMessage("Cette categorie est utilisée dans le parametrage des classes");
                        }
                    } else {
                        JsfUtil.addErrorMessage("Cette categorie est utilisée dans le parametrage des payement");
                    }
                } else {
                    JsfUtil.addErrorMessage("Cette categorie est utilisée dans le parametrage des payement");
                }
            } else {
                JsfUtil.addErrorMessage("Echec de l'opération !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void imprimerCategoriePdf() {

    }

    @Override
    public void imprimerCategorieHtml() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
