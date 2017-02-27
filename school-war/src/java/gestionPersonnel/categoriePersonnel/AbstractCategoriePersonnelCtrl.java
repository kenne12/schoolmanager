/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPersonnel.categoriePersonnel;

import entities.Categoriepersonnel;
import java.util.List;
import javax.ejb.EJB;
import session.CategoriepersonnelFacadeLocal;

/**
 *
 * @author kenne gervais
 */
public class AbstractCategoriePersonnelCtrl {

    @EJB
    protected CategoriepersonnelFacadeLocal categoriePersonnelFacade;

    protected Categoriepersonnel categoriePersonnel;
    protected Categoriepersonnel selectedCategoriePersonnel;
    protected List<Categoriepersonnel> categoriePersonnels;

    protected String fileName;

    public AbstractCategoriePersonnelCtrl() {

    }

    protected StringBuffer ClasseTableHtml = new StringBuffer("pas encore implementé");

    //classe participante   
    //liste des tableaux des classes participantes
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
        imprimer = categoriePersonnelFacade.findAll().isEmpty();
        return imprimer;
    }

    public StringBuffer getBatimentsTableHtml() {
        return ClasseTableHtml;
    }

    /*public List<Batiment> getListBatiment() {
     ListBatiment = batimentFacadeLocal.findAll();
     return ListBatiment;
     }*/
    public Categoriepersonnel getCategoriePersonnel() {
        return categoriePersonnel;
    }

    public void setCategoriePersonnel(Categoriepersonnel categoriePersonnel) {
        this.categoriePersonnel = categoriePersonnel;
    }

    public List<Categoriepersonnel> getCategoriePersonnels() {
        categoriePersonnels = categoriePersonnelFacade.findByEtat(true);
        return categoriePersonnels;
    }

    public void setCategoriePersonnels(List<Categoriepersonnel> categoriePersonnels) {
        this.categoriePersonnels = categoriePersonnels;
    }

    public Categoriepersonnel getSelectedCategoriePersonnel() {
        return selectedCategoriePersonnel;
    }

    public void setSelectedCategoriePersonnel(Categoriepersonnel selectedCategoriePersonnel) {
        this.selectedCategoriePersonnel = selectedCategoriePersonnel;
        detail = modifier = supprimer = selectedCategoriePersonnel == null;
    }

}
