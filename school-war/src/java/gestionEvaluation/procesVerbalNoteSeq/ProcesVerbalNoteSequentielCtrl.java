/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvaluation.procesVerbalNoteSeq;

import entities.Classe;
import entities.Classecategorie;
import entities.Eleveanneeclasse;
import entities.Evaluation;
import entities.PlanningEvaluation;
import entities.Sequenceannee;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.PrintUtils;

import utils.SessionMBean;

/**
 *
 * @author kenne
 */
@ManagedBean
@ViewScoped
public class ProcesVerbalNoteSequentielCtrl extends AbstractProcesVeralNoteCtrl {

    /**
     * Creates a new instance of ProcesVerbalNoteSequentielCtrl
     */
    public ProcesVerbalNoteSequentielCtrl() {
    }

    @PostConstruct
    private void init() {
        classe = new Classe();
        eleveanneeclasse = new Eleveanneeclasse();
        planningEvaluation = new PlanningEvaluation();
        sequenceannee = new Sequenceannee();
        categorie = new Classecategorie();
        this.loadCategorie();
        this.loadSequence();
    }

    public void imprimerProces() {
        try {
            fichier = "proces_verbal_note" + "_" + sequenceannee.getIdsequence().getNom() + "_" + categorie.getIdclasse().getNom() + ".pdf";
            PrintUtils.printSequentialReportNote(this.getAnnee(), sequenceannee, categorie.getIdclasse(), evaluations);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findNote() {
        evaluations.clear();
        try {
            if (planningEvaluation.getIdplanning() != null) {
                planningEvaluation = planningEvaluationFacadeLocal.find(planningEvaluation.getIdplanning());
                eleveanneeclasses = eleveanneeclasseFacadeLocal.findByAnneeClasse(this.getAnnee().getIdannee(), planningEvaluation.getElementEvaluation().getClasse().getIdclasse());
                if (!eleveanneeclasses.isEmpty()) {
                    for (Eleveanneeclasse e : eleveanneeclasses) {
                        if (evaluationFacadeLocal.findByElevePlanning(e.getEleve().getIdeleve(), planningEvaluation.getIdplanning()) != null) {
                            evaluations.add(evaluationFacadeLocal.findByElevePlanning(e.getEleve().getIdeleve(), planningEvaluation.getIdplanning()));
                        } else {
                            Evaluation ev = new Evaluation();
                            ev.setEleve(e.getEleve());
                            ev.setNote(null);
                            ev.setObservation("*");
                            ev.setPlanningEvaluation(planningEvaluation);
                            evaluations.add(ev);
                        }
                    }
                }
            } else {
                JsfUtil.addErrorMessage("Aucune évaluation selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEvaluation() {
        try {
            if (categorie.getId() != null) {
                if (sequenceannee.getIdsequencean() != null) {
                    categorie = classeCategorieFacade.find(categorie.getId());
                    sequenceannee = sequenceanneeFacadeLocal.find(sequenceannee.getIdsequencean());
                    planningEvaluations = planningEvaluationFacadeLocal.findByClasse(categorie.getIdclasse().getIdclasse(), sequenceannee.getIdsequencean());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadCategorie() {
        try {
            if (SessionMBean.getUserAccount() != null) {
                List<Classe> classeTemp = classeFacadeLocal.findByEtaBlissement(SessionMBean.getUserAccount().getEtablissement().getId());
                if (!classeTemp.isEmpty()) {

                    for (Classe c : classeTemp) {
                        Classecategorie categorieTemp = classeCategorieFacade.getClasseCategorieByIdClasse(c.getIdclasse());
                        if (categorieTemp != null) {
                            categories.add(categorieTemp);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSequence() {
        try {
            if (this.getAnnee() != null) {
                sequenceannees = sequenceanneeFacadeLocal.getByAnnee(annee.getIdannee());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
