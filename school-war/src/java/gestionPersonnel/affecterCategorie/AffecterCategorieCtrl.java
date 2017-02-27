/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPersonnel.affecterCategorie;

import entities.Annee;
import entities.Categoriepersonnel;
import entities.Fonction;
import entities.Personnel;
import entities.Personnelanneecatfonct;
import java.io.Serializable;
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
public class AffecterCategorieCtrl extends AbstractAffecterCategorieCtrl implements AffecterCategorieInterfaceCtrl, Serializable {

    /*public AffecterClasseCtrl() {
        
     }*/
    @PostConstruct
    private void initAffectation() {
        annee = new Annee();
        fonction = new Fonction();
        categoriePersonnel = new Categoriepersonnel();
        personnel = new Personnel();
        personAnneeCategorie = new Personnelanneecatfonct();
    }

    private void initRegister() {
        personAnneeCategorie.setIdannee(annee);
        personAnneeCategorie.setIdcatpersonnel(categoriePersonnel);
        personAnneeCategorie.setIdfonction(fonction);
        personAnneeCategorie.setPersonnel(personnel);
    }

    @Override
    public void enregistrerAffectation() {
        if (annee.getIdannee() != null) {
            if (fonction.getIdfonction() != null) {
                if (categoriePersonnel.getIdcatpersonnel() != null) {
                    if (personnel.getIdpersonnel() != null) {
                        Personnelanneecatfonct result = persoAnneeCatFacade.findByPersonnelAnneeCategorie(personnel.getIdpersonnel(), annee.getIdannee(), categoriePersonnel.getIdcatpersonnel());
                        if (result == null) {
                            initRegister();
                            persoAnneeCatFacade.create(personAnneeCategorie);
                            initAffectation();
                            JsfUtil.addSuccessMessage("Affectation réussie !");
                        } else {
                            JsfUtil.addErrorMessage("Ce personnel a deja une fonction aucours de cette année !");
                        }
                    } else {
                        JsfUtil.addErrorMessage("le systeme n'a aucun personnel !");
                    }
                } else {
                    JsfUtil.addErrorMessage("le systeme n 'a aucune categorie !");
                }
            } else {
                JsfUtil.addErrorMessage("veuillez remplir les fonctions !");
            }
        } else {
            JsfUtil.addErrorMessage("veuillez parametrer l'année scolaire en cours !");
        }
    }

    @Override
    public void modifier() {
        /* if(selectedCategoriePersonnel!=null){
         categoriePersonnelFacade.edit(selectedCategoriePersonnel);
         initAffectation();
         JsfUtil.addSuccessMessage("La categorie a été mise a jour !");
            
         }else{
         JsfUtil.addErrorMessage("Veuillez selectionner une categorie !");
         }*/

    }

    @Override
    public void supprimer() {

    }

    @Override
    public void imprimerAffectationPdf() {

    }

    @Override
    public void imprimerAffectationHtml() {

    }
}
