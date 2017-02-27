/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPersonnel.fonction;

import entities.Fonction;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "fonctionCtrl")
@ViewScoped
public class FonctionCtrl extends AbstractFonctionCtrl implements FonctionInterfaceCtrl, Serializable {

    @PostConstruct
    private void initFonction() {
        selectedFonction = new Fonction();
        fonction = new Fonction();
    }

    @Override
    public void enregistrerFonction() {
        if (fonction != null) {
            if ("".equals(fonction.getNom())) {
                JsfUtil.addErrorMessage("veuillez remplir un nom de fonction !");
            } else {
                List<Fonction> results;
                results = fonctionFacadeLocal.findByNom(fonction.getNom());
                if (results == null) {
                    fonctionFacadeLocal.create(fonction);
                    initFonction();
                    JsfUtil.addSuccessMessage("operation réussie !");
                } else {
                    JsfUtil.addErrorMessage("Une fonction de ce nom existe dejà !");
                    initFonction();
                }

            }
        } else {
            JsfUtil.addErrorMessage("Fonction incorrectes");
        }
    }

    @Override
    public void modifier() {
        if (selectedFonction != null) {
            if ("".equals(selectedFonction.getNom())) {
                JsfUtil.addErrorMessage("veuillez remplir un nom de fonction !");
            } else {
                fonctionFacadeLocal.edit(selectedFonction);
                initFonction();
                JsfUtil.addSuccessMessage("la fonction a eté mise à jour !");
            }
        } else {
            JsfUtil.addErrorMessage("Fonction incorrectes");
        }
        /* if (selectedFonction == null || selectedFonction.getIdfonction() == null) {
         return;
         }
         Fonction bat = fonctionFacadeLocal.findByCode(selectedFonction.getCode());
         if (bat != null && !Objects.equals(bat.getIdfonction(), selectedFonction.getIdfonction())) {
         JsfUtil.addErrorMessage("Un Fonction  portant ce code existe déjà");
         return;
         }
         fonctionFacadeLocal.edit(selectedFonction);
         JsfUtil.addSuccessMessage("Le fonction a été mis à jour");*/
    }

    @Override
    public void supprimer() {
        if (selectedFonction == null || selectedFonction.getIdfonction() == null) {
            JsfUtil.addSuccessMessage("Veuillez selectionner un fonction !");
        } else {
            if (selectedFonction.getPersonnelanneecatfonctList().isEmpty()) {
                fonctionFacadeLocal.remove(selectedFonction);
                initFonction();
                JsfUtil.addSuccessMessage("opération réussie");
            }
        }
    }

    @Override
    public void imprimerFonctionPdf() {
        System.out.println("Impression pdf types compte");
        fonctions = fonctionFacadeLocal.findAll();
//        fileName = PdfFonction.etatsFonction(fonctions);
    }

    @Override
    public void imprimerFonctionHtml() {
        System.out.println("Impression html types compte");
    }

}
