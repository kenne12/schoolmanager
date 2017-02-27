/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.batiment;

import entities.Batiment;
import entities.CompteUtilisateur;

import entities.Traceur;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.SessionMBean;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "batimentCtrl")
@ViewScoped
public class BatimentCtrl extends AbstractBatimentCtrl implements BatimentInterfaceCtrl, Serializable {

    @PostConstruct
    private void initBatiment() {
        selectedBatiment = new Batiment();
        batiment = new Batiment();
        traceur = new Traceur();
    }

    @Override
    public void enregistrerBatiment() {
        try {

            CompteUtilisateur user = SessionMBean.getUserAccount();
            Batiment bat = batimentFacadeLocal.findByCode(SessionMBean.getSchool().getId(), batiment.getCode());
            if (bat != null) {
                JsfUtil.addErrorMessage("Cet etablissement porte  deja un batiment ayant ce code");
                return;
            } else {
                if (user != null) {
                    batiment.setEtablissement(user.getEtablissement());
                    batimentFacadeLocal.create(batiment);
                    traceur.setPersonnel(user.getPersonnel());
                    traceur.setDateaction(new Date());
                    traceur.setAction("Enregistrement du batiment " + batiment.getNom());
                    traceurFcade.create(traceur);
                    initBatiment();
                    JsfUtil.addSuccessMessage("Le batiment a été enregistré");
                } else {
                    JsfUtil.addWarningMessage("Aucune session utilisateur initiée !");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifier() {
        try {
            if (selectedBatiment == null || selectedBatiment.getIdbatiment() == null) {
                return;
            }
            Batiment bat = batimentFacadeLocal.findByCode(selectedBatiment.getEtablissement().getId(), selectedBatiment.getCode());
            if (bat != null && !Objects.equals(bat.getIdbatiment(), selectedBatiment.getIdbatiment())) {
                JsfUtil.addErrorMessage("Un Batiment  portant ce code existe déjà");
                return;
            }
            batimentFacadeLocal.edit(selectedBatiment);
            JsfUtil.addSuccessMessage("Le batiment a été mis à jour");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void supprimer() {
        try {
            if (selectedBatiment == null || selectedBatiment.getIdbatiment() == null) {
                JsfUtil.addSuccessMessage("Veuillez selectionner un batiment !");
                return;
            }
            if (selectedBatiment.getSalleList().isEmpty()) {
                batimentFacadeLocal.remove(selectedBatiment);
                initBatiment();
                JsfUtil.addSuccessMessage("opération réussie");
                return;
            }
            JsfUtil.addErrorMessage("Echec de l 'operation : ce batiment comporte plusieurs salles");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void imprimerBatimentPdf() {
        System.out.println("Impression pdf types compte");
        batiments = batimentFacadeLocal.findAll();
    }

    @Override
    public void imprimerBatimentHtml() {
        System.out.println("Impression html types compte");
    }

}
