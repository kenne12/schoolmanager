/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.sequence;

import entities.Personnel;
import entities.Sequence;
import entities.Traceur;
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
@ManagedBean(name = "sequenceCtrl")
@ViewScoped
public class SequenceCtrl extends AbstractSequenceCtrl implements SequenceInterfaceCtrl, Serializable {

    @PostConstruct
    private void initSequence() {
        selectedSequence = new Sequence();
        sequence = new Sequence();
        traceur = new Traceur();
    }

    @Override
    public void enregistrerSequence() {
        Personnel user = UtilitaireSession.getInstance().getuser();
        if ("".equals(sequence.getNom())) {
            JsfUtil.addErrorMessage("Veuillez saisir le nom de la sequence");
            initSequence();
        } else {
            Sequence seq = sequenceFacadeLocal.findByNom(sequence.getNom());
            if (seq == null) {
                if (user != null) {
                    traceur.setAction("Création de la séquence : " + sequence.getNom());
                    traceur.setDateaction(new Date());
                    traceur.setPersonnel(user);
                    sequenceFacadeLocal.create(sequence);
                    traceurFacadeLocal.create(traceur);
                    initSequence();
                    JsfUtil.addSuccessMessage("Le sequence a été enregistré");
                } else {
                    initSequence();
                    JsfUtil.addWarningMessage("Aucune session utilisateur initiée");
                }

            } else {
                initSequence();
                JsfUtil.addErrorMessage("Une séquence ayant ce nom existe déjè");
            }
        }
    }

    @Override
    public void modifier() {
        Personnel user = UtilitaireSession.getInstance().getuser();
        if (selectedSequence == null || selectedSequence.getIdsequence() == null) {
            JsfUtil.addErrorMessage("Un Sequence  est invalide !");
        } else {
            if (user != null) {
                Sequence tempon = sequenceFacadeLocal.find(selectedSequence.getIdsequence());
                traceur.setAction("Modification de la séquence " + tempon.getNom() + " par " + selectedSequence.getNom());
                traceur.setPersonnel(user);
                traceur.setDateaction(new Date());
                sequenceFacadeLocal.edit(selectedSequence);
                traceurFacadeLocal.create(traceur);
                initSequence();
                JsfUtil.addSuccessMessage("Le sequence a été mis à jour");
            } else {
                initSequence();
                JsfUtil.addWarningMessage("Aucune session utilisateur initiée");
            }
        }
    }

    @Override
    public void supprimer() {
        Personnel user = UtilitaireSession.getInstance().getuser();
        if (selectedSequence == null || selectedSequence.getIdsequence() == null) {
            JsfUtil.addSuccessMessage("Veuillez selectionner un sequence !");
        } else {
            if (selectedSequence.getSemaineList().isEmpty()) {
                if (selectedSequence.getSequenceanneeList().isEmpty()) {
                    if (user != null) {
                        traceur.setAction("Suppression de la séquence : " + selectedSequence.getNom());
                        traceur.setDateaction(new Date());
                        traceur.setPersonnel(user);
                        sequenceFacadeLocal.remove(selectedSequence);
                        traceurFacadeLocal.create(traceur);
                        initSequence();
                        JsfUtil.addSuccessMessage("opération réussie");
                    } else {
                        initSequence();
                        JsfUtil.addWarningMessage("Aucune session utilisateur initiée");
                    }
                }
            }

        }
    }

    @Override
    public void imprimerSequencePdf() {
        System.out.println("Impression pdf types compte");
        sequences = sequenceFacadeLocal.findAll();
    }

    @Override
    public void imprimerSequenceHtml() {
        System.out.println("Impression html types compte");
    }

}
