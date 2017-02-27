/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.trimestre;

import entities.Personnel;
import entities.Traceur;
import entities.Trimestre;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.UtilitaireSession;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "trimestreCtrl")
@ViewScoped
public class TrimestreCtrl extends AbstractTrimestreCtrl implements TrimestreInterfaceCtrl, Serializable {

    @PostConstruct
    private void initTrimestre() {
        selectedTrimestre = new Trimestre();
        trimestre = new Trimestre();
        traceur = new Traceur();
    }

    @Override
    public void enregistrerTrimestre() {
        Personnel user = UtilitaireSession.getInstance().getuser();
        if (trimestre == null) {
            initTrimestre();
            JsfUtil.addErrorMessage("le trimestre est invalide !");
        } else {
            Trimestre trim = trimestreFacadeLocal.findByNom(trimestre.getNom());
            if (trim == null) {
                if (user != null) {
                    traceur = new Traceur();
                    traceur.setAction("Création du trimestre : " + trimestre.getNom());
                    traceur.setDateaction(new Date());
                    traceur.setPersonnel(user);
                    trimestreFacadeLocal.create(trimestre);
                    traceurFacadeLocal.create(traceur);
                    initTrimestre();
                    JsfUtil.addSuccessMessage("Le trimestre a été enregistré");
                } else {
                    initTrimestre();
                    JsfUtil.addWarningMessage("Aucune session utilisateur initiée !");
                }
            } else {
                JsfUtil.addWarningMessage("Un trimestre ayant ce nom existe dejè");
            }
        }
    }

    @Override
    public void modifier() {
        Personnel user = UtilitaireSession.getInstance().getuser();
        traceur = new Traceur();
        if (selectedTrimestre == null || selectedTrimestre.getIdtrimestre() == null) {
            JsfUtil.addErrorMessage("veuillez selectionner un trimestre");
            initTrimestre();
        } else {
            if (user != null) {
                traceur.setAction("Modification du trimestre : " + selectedTrimestre.getNom());
                traceur.setPersonnel(user);
                traceur.setDateaction(new Date());
                trimestreFacadeLocal.edit(selectedTrimestre);
                traceurFacadeLocal.create(traceur);
                initTrimestre();
                JsfUtil.addSuccessMessage("Le trimestre a été mis à jour");
            } else {
                initTrimestre();
                JsfUtil.addWarningMessage("Aucune session utilisateur initiée !");
            }
        }
    }

    @Override
    public void supprimer() {
        Personnel user = UtilitaireSession.getInstance().getuser();
        if (selectedTrimestre == null || selectedTrimestre.getIdtrimestre() == null) {
            JsfUtil.addSuccessMessage("Veuillez selectionner un trimestre !");
        } else {
            if (selectedTrimestre.getTrimesteanneeList().isEmpty()) {
                if (user != null) {
                    traceur.setDateaction(new Date());
                    traceur.setPersonnel(null);
                    traceur.setAction("Suppression du trimestre" + selectedTrimestre.getNom());
                    trimestreFacadeLocal.remove(selectedTrimestre);
                    traceurFacadeLocal.create(traceur);
                    initTrimestre();
                    JsfUtil.addSuccessMessage("opération réussie");
                } else {
                    JsfUtil.addWarningMessage("Aucune session utilisateur initiée !");
                    initTrimestre();
                }
            } else {
                initTrimestre();
                JsfUtil.addWarningMessage("Impossible de supprimer ce trimestre car il contient des données");
            }
        }
    }

    @Override
    public void imprimerTrimestrePdf() {
        System.out.println("Impression pdf types compte");
        trimestres = trimestreFacadeLocal.findAll();
    }

    @Override
    public void imprimerTrimestreHtml() {
        System.out.println("Impression html types compte");
    }

}
