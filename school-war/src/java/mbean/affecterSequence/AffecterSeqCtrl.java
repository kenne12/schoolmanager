/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.affecterSequence;

import entities.Annee;
import entities.Etablissement;
import entities.Sequenceannee;
import entities.Sequence;
import entities.Trimesteannee;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.UtilitaireSession;
import utils.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean(name = "affecterSeqCtrl")
@ViewScoped
public class AffecterSeqCtrl extends AbstractAffecterSeqCtrl implements AffecterSeqInterfaceCtrl, Serializable {

    /**
     * Creates a new instance of AffecterSeqCtrl
     */
    public AffecterSeqCtrl() {
    }

    @PostConstruct
    private void initAffecter() {
        sequence = new Sequence();
        annee = new Annee();
        sequenceAnnee = new Sequenceannee();
        selectedSequenceAnnee = new Sequenceannee();
        trimesteannee = new Trimesteannee();
        etablissement = new Etablissement();

    }

    public void prepareCreate() {
        try {
            etablissement = new Etablissement();
            dualsSequence = new DualListModel<>();
            annees.clear();
            trimesteannees.clear();
            trimesteannee = new Trimesteannee();
            annee = new Annee();
            updateTrimestre();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        try {
            trimesteannees = trimesteanneeFacadeLocal.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {

        try {
            if (trimesteannee != null) {
                if (!dualsSequence.getTarget().isEmpty()) {
                    for (Sequence s : dualsSequence.getTarget()) {
                        sequenceAnnee = new Sequenceannee();
                        sequenceAnnee.setIdsequencean(sequenceAnneeFacade.nextVal());
                        sequenceAnnee.setIdsequence(s);
                        sequenceAnnee.setTrimestre(trimesteannee);
                        sequenceAnnee.setEtat(true);
                        sequenceAnnee.setIdannee(trimesteannee.getIdannee());
                        if (sequenceAnneeFacade.findByTrimestreSequence(trimesteannee.getIdtrimestrean(), s.getIdsequence()).isEmpty()) {
                            sequenceAnneeFacade.create(sequenceAnnee);
                        }
                    }
                    JsfUtil.addSuccessMessage("Operation réussie ");
                } else {
                    JsfUtil.addErrorMessage("Aucune sequence dans la destination");
                }
            } else {
                JsfUtil.addErrorMessage("Aucun trimestre selectionné");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit() {
        try {
            if (selectedSequenceAnnee != null) {
                sequenceAnneeFacade.edit(selectedSequenceAnnee);
                JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne sélectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        try {
            if (selectedSequenceAnnee != null) {
                if (selectedSequenceAnnee.getAbsenceeleveList().isEmpty()) {

                    if (selectedSequenceAnnee.getPunitionList().isEmpty()) {
                        sequenceAnneeFacade.remove(selectedSequenceAnnee);
                        Utilitaires.saveOperation(traceurFacadeLocal, "Suppression : " + selectedSequenceAnnee.getIdsequence().getNom() + " Annee : " + selectedSequenceAnnee.getTrimestre().getIdannee().getCode(), UtilitaireSession.getInstance().getuser());
                        JsfUtil.addSuccessMessage("Opération réussie");
                    } else {
                        JsfUtil.addErrorMessage("Plusieurs punitions sont enregistrées a cette sequence");
                    }

                } else {
                    JsfUtil.addErrorMessage("Plusieurs absence sont enregistré a cette sequence");
                }
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTrimestre() {
        try {
            dualsSequence.getSource().clear();
            dualsSequence.getTarget().clear();
            trimesteannees.clear();

            if (SessionMBean.getYear() != null) {
                trimesteannees = trimesteanneeFacadeLocal.getByAnnee(SessionMBean.getYear().getIdannee());
            }

            if (!trimesteannees.isEmpty()) {
                List<Sequenceannee> sequenceanneesTemp = new ArrayList<>();
                for (Trimesteannee t : trimesteannees) {

                    List<Sequenceannee> sequenceanneesTemp1 = sequenceAnneeFacade.getByTrimestre(t.getIdtrimestrean());
                    System.err.println("il a " + sequenceanneesTemp1.size());

                    if (!sequenceanneesTemp1.isEmpty()) {
                        sequenceanneesTemp.addAll(sequenceanneesTemp1);
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSequence() {
        try {
            dualsSequence.getSource().clear();
            dualsSequence.getTarget().clear();
            if (!trimesteannees.isEmpty()) {

                if (trimesteannee != null) {

                    List<Sequenceannee> sequenceanneesTemp = new ArrayList<>();
                    for (Trimesteannee t : trimesteannees) {

                        List<Sequenceannee> sequenceanneesTemp1 = sequenceAnneeFacade.getByTrimestre(t.getIdtrimestrean());
                        if (!sequenceanneesTemp1.isEmpty()) {
                            sequenceanneesTemp.addAll(sequenceanneesTemp1);
                        }
                    }

                    if (sequenceanneesTemp.isEmpty()) {

                        dualsSequence.setSource(sequenceFacadeLocal.findAll());
                    } else {

                        List<Sequence> sequencesTemp = new ArrayList<>();
                        List<Sequence> sequencesAll = sequenceFacadeLocal.findAll();
                        for (Sequenceannee s : sequenceanneesTemp) {
                            sequencesTemp.add(s.getIdsequence());
                        }

                        for (Sequence s : sequencesAll) {
                            if (!sequencesTemp.contains(s)) {
                                dualsSequence.getSource().add(s);
                            }
                        }
                    }

                } else {
                    JsfUtil.addErrorMessage("Aucun trimestre selectionné");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print() {

    }

}
