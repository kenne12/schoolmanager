/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvaluation.procesVerbalNoteTrim;

import entities.Classe;
import entities.ClasseElementevaluation;
import entities.Classecategorie;
import entities.Eleveanneeclasse;
import entities.Evaluation;
import entities.PlanningEvaluation;
import entities.Sequenceannee;
import entities.Trimesteannee;
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
public class ProcesVerbalNoteTrimestrielCtrl extends AbstractProcesVeralNoteTCtrl {

    /**
     * Creates a new instance of ProcesVerbalNoteSequentielCtrl
     */
    public ProcesVerbalNoteTrimestrielCtrl() {
    }

    @PostConstruct
    private void init() {
        classe = new Classe();
        eleveanneeclasse = new Eleveanneeclasse();
        planningEvaluation = new PlanningEvaluation();
        sequenceannee = new Sequenceannee();
        categorie = new Classecategorie();
        classeElementevaluation = new ClasseElementevaluation();
        trimesteannee = new Trimesteannee();
        this.loadCategorie();
        this.loadTrimestre();
    }

    public void imprimerProces() {
        try {
            fichier = "proces_verbal_note" + "_" + trimesteannee.getIdtrimestre().getNom() + "_" + categorie.getIdclasse().getNom() + ".pdf";
            PrintUtils.printTrimestrialReportNote(this.getAnnee(), trimesteannee, categorie.getIdclasse(), classeElementevaluation, eleveanneeclasses, sequenceannees,classeElementevaluationFacadeLocal,planningEvaluationFacadeLocal,evaluationFacadeLocal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String findNote1(Eleveanneeclasse eleveanneeclasse, Sequenceannee sequenceannee) {
        String resultat = null;
        try {
            if (classeElementevaluation.getId() != null) {
                classeElementevaluation = classeElementevaluationFacadeLocal.find(classeElementevaluation.getId());
                if (!eleveanneeclasses.isEmpty()) {
                    if (!sequenceannees.isEmpty()) {
                        PlanningEvaluation p = planningEvaluationFacadeLocal.findByElementSequence(classeElementevaluation.getId(), sequenceannee.getIdsequencean());
                        if (p != null) {
                            if (evaluationFacadeLocal.findByElevePlanning(eleveanneeclasse.getEleve().getIdeleve(), p.getIdplanning()) != null) {
                                resultat = evaluationFacadeLocal.findByElevePlanning(eleveanneeclasse.getEleve().getIdeleve(), p.getIdplanning()).getNote().toString();
                            } else {
                                resultat = "Pas évalué";
                            }
                        } else {
                            resultat = "Non planifié";
                        }
                    }
                }
            } else {
                JsfUtil.addErrorMessage("Aucune unité d evaluation selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

    public String findNote(Eleveanneeclasse eleveanneeclasse, Sequenceannee annee) {
        String resultat = null;
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
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
                            ev.setObservation("PAS ENCORE EVALUE");
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

    public void updateUniteEvaluation() {
        try {
            if (categorie.getId() != null) {
                categorie = classeCategorieFacade.find(categorie.getId());
                classeElementevaluations = classeElementevaluationFacadeLocal.findByClasse(categorie.getIdclasse().getIdclasse());
                eleveanneeclasses = eleveanneeclasseFacadeLocal.findByAnneeClasse(this.getAnnee().getIdannee(), categorie.getIdclasse().getIdclasse());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateSequence() {
        try {
            if (trimesteannee.getIdtrimestrean() != null) {
                trimesteannee = trimesteanneeFacadeLocal.find(trimesteannee.getIdtrimestrean());
                sequenceannees = sequenceanneeFacadeLocal.getByTrimestre(trimesteannee.getIdtrimestrean());
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

    public void loadTrimestre() {
        try {
            if (this.getAnnee() != null) {
                trimesteannees = trimesteanneeFacadeLocal.getByAnnee(annee.getIdannee());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
