/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPersonnel.fonction;

import entities.Fonction;
import java.util.List;
import javax.ejb.EJB;
import session.FonctionFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractFonctionCtrl {

    protected String fileName;
    @EJB
    protected FonctionFacadeLocal fonctionFacadeLocal;

    protected List<Fonction> fonctions;

    protected StringBuffer fonctionsTableHtml = new StringBuffer("pas encore implementé");
    protected Fonction selectedFonction;
    protected Fonction fonction;
    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Fonction> getFonctions() {
        fonctions = fonctionFacadeLocal.findAll();
        return fonctions;
    }

    public Fonction getSelectedFonction() {
        return selectedFonction;
    }

    public void setSelectedFonction(Fonction selectedFonction) {
        this.selectedFonction = selectedFonction;
        modifier = supprimer = detail = selectedFonction == null;
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
        imprimer = fonctionFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    public StringBuffer getFonctionsTableHtml() {
        return fonctionsTableHtml;
    }

}
