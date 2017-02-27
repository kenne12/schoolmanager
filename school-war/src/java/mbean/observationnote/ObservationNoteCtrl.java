/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.observationnote;

import entities.Personnel;
import entities.Traceur;
import entities.Observationnote;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.UtilitaireSession;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "observationnoteCtrl")
@ViewScoped
public class ObservationNoteCtrl extends AbstractObservationNoteCtrl implements ObservationNoteInterfaceCtrl, Serializable {

    @PostConstruct
    private void initObservationnote() {
        selectedObservationnote = new Observationnote();
        observationnote = new Observationnote();
        traceur = new Traceur();
    }

    public void prepareCreate() {
        observationnote = new Observationnote();
        observationnote.setBorneinferieur(0d);
        observationnote.setBornesuperieur(0d);
    }

    @Override
    public void enregistrerObservationnote() {
        try {
            Personnel user = UtilitaireSession.getInstance().getuser();

            List<Observationnote> temp = observationnoteFacadeLocal.find(SessionMBean.getSchool(), observationnote.getAvis());
            if (temp.isEmpty()) {
                if (user != null) {
                    traceur = new Traceur();
                    //traceur.setId(traceurFacadeLocal.);
                    traceur.setAction("Création du observationnote : " + observationnote.getAvis());
                    traceur.setDateaction(new Date());
                    traceur.setPersonnel(SessionMBean.getUserAccount().getPersonnel());
                    observationnote.setIdobservationnote(observationnoteFacadeLocal.nextVal());
                    observationnote.setIdetablissement(SessionMBean.getSchool());
                    observationnoteFacadeLocal.create(observationnote);
                    traceurFacadeLocal.create(traceur);
                    initObservationnote();
                    JsfUtil.addSuccessMessage("Le observationnote a été enregistré");
                } else {
                    initObservationnote();
                    JsfUtil.addWarningMessage("Aucune session utilisateur initiée !");
                }
            } else {
                JsfUtil.addWarningMessage("Un observationnote ayant ce nom existe dejè");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifier() {
        Personnel user = UtilitaireSession.getInstance().getuser();
        traceur = new Traceur();
        if (selectedObservationnote == null || selectedObservationnote.getIdobservationnote() == null) {
            JsfUtil.addErrorMessage("veuillez selectionner un observationnote");
            initObservationnote();
        } else {

            traceur.setAction("Modification du observationnote : " + selectedObservationnote.getAvis());
            traceur.setPersonnel(user);
            traceur.setDateaction(new Date());
            observationnoteFacadeLocal.edit(selectedObservationnote);
            traceurFacadeLocal.create(traceur);
            initObservationnote();
            JsfUtil.addSuccessMessage("Le observationnote a été mis à jour");

        }
    }

    @Override
    public void supprimer() {
        Personnel user = UtilitaireSession.getInstance().getuser();
        if (selectedObservationnote == null || selectedObservationnote.getIdobservationnote() == null) {
            JsfUtil.addSuccessMessage("Veuillez selectionner un observationnote !");
        } else {

            if (user != null) {
                traceur.setDateaction(new Date());
                traceur.setPersonnel(null);
                traceur.setAction("Suppression du observationnote" + selectedObservationnote.getAvis());
                observationnoteFacadeLocal.remove(selectedObservationnote);
                traceurFacadeLocal.create(traceur);
                initObservationnote();
                JsfUtil.addSuccessMessage("opération réussie");
            } else {
                JsfUtil.addWarningMessage("Aucune session utilisateur initiée !");
                initObservationnote();
            }

        }
    }

    @Override
    public void imprimerObservationnotePdf() {
        System.out.println("Impression pdf types compte");
        observationnotes = observationnoteFacadeLocal.findAll();
    }

    @Override
    public void imprimerObservationnoteHtml() {
        System.out.println("Impression html types compte");
    }

}
