/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvaluation.evaluation1;

import entities.Appreciationannuelle;
import entities.Appreciationtrimestrielle;
import entities.Classe;
import entities.ClasseElementevaluation;
import entities.Eleveanneeclasse;
import entities.Evaluation;
import entities.Matiere;
import entities.Observationnote;
import entities.Personnel;
import entities.PlanningEvaluation;
import entities.Sequenceannee;
import entities.Traceur;
import entities.Trimesteannee;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import utils.JsfUtil;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "evaluation1Ctrl")
@SessionScoped
public class Evaluation1Ctrl extends AbstractEvaluation1Ctrl implements Evaluation1InterfaceCtrl, Serializable {

    @PostConstruct
    private void initEvaluation() {
        selectedEvaluation = new Evaluation();
        evaluation = new Evaluation();
        matiere = new Matiere();
        personnel = new Personnel();
        trimestre = new Trimesteannee();
        sequence = new Sequenceannee();
        traceur = new Traceur();
        observationnote = new Observationnote();
        try {
            sequences = sequenceFacade.getByAnneEtat(annee.getIdannee(), true);
            classes = classeFacadeLocal.findByEtaBlissement(etablissement.getId());
            observationnotes = observationnoteFacadeLocal.findRange(etablissement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        sequence = new Sequenceannee();
        evaluations1.clear();
    }

    public void openDialog() {
        showCreateDlg = true;
    }

    public void closeDialog() {
        evaluations1.clear();
        showCreateDlg = false;
    }

    public void prepareCreate() {
        this.init();
        this.openDialog();
        classe = new Classe();
        classeElementevaluation = new ClasseElementevaluation();
    }

    @Override
    public void enregistrerEvaluation() {
        try {
            if (!evaluations1.isEmpty()) {

                List<Evaluation> temp = new ArrayList<>();

                if (classeElementevaluation.getElementevaluation().getTrimestriel()) {

                    for (Evaluation e : evaluations1) {

                        if (e.getNote() >= 0 && e.getNote() <= 20) {

                            List<Sequenceannee> sequenceannees = sequence.getTrimestre().getSequenceanneeList();

                            if (!sequenceannees.isEmpty()) {
                                int i = 0;
                                for (Sequenceannee s : sequenceannees) {

                                    PlanningEvaluation p = planningEvaluationFacadeLocal.findByElementSequence(classeElementevaluation.getId(), s.getIdsequencean());

                                    e.setPlanningEvaluation(p);

                                    e.setIdeval(evaluationFacadeLocal.nextVal());
                                    e.setValide(false);

                                    Eleveanneeclasse eac = eleveanneeclasseFacadeLocal.getEleveAnneeClaseByAnneClasse(e.getEleve().getIdeleve(), annee.getIdannee());

                                    e.setObservation("");
                                    for (Observationnote o : observationnotes) {
                                        if (e.getNote() >= o.getBorneinferieur() && e.getNote() <= o.getBornesuperieur()) {
                                            e.setObservation(o.getAvis());
                                            break;
                                        }
                                    }

                                    Appreciationtrimestrielle appreciationtrimestrielle = new Appreciationtrimestrielle();

                                    appreciationtrimestrielle.setEleve(eac);
                                    appreciationtrimestrielle.setMatiere(e.getPlanningEvaluation().getElementEvaluation());
                                    appreciationtrimestrielle.setTrimestre(sequence.getTrimestre());
                                    appreciationtrimestrielle.setNom(e.getObservation());

                                    List<Evaluation> temp2 = evaluationFacadeLocal.findByEleveTrimestreMatiere(e.getEleve().getIdeleve(), s.getTrimestre().getIdtrimestrean(), classeElementevaluation.getId());

                                    if (i == 0) {

                                        if (temp2.isEmpty()) {
                                            appreciationtrimestrielle.setId(appreciationtrimestrielleFacadeLocal.nextVal());
                                            appreciationtrimestrielleFacadeLocal.create(appreciationtrimestrielle);
                                        } else {
                                            Double note = e.getNote();
                                            for (Evaluation e1 : temp2) {
                                                note += e1.getNote();
                                            }

                                            for (Observationnote o : observationnotes) {
                                                if (note / (temp2.size() + 1) >= o.getBorneinferieur() && note / (temp2.size() + 1) <= o.getBornesuperieur()) {
                                                    appreciationtrimestrielle.setNom(o.getAvis());
                                                    break;
                                                }
                                            }

                                            Appreciationtrimestrielle app2 = appreciationtrimestrielleFacadeLocal.find(e.getEleve().getIdeleve(), classeElementevaluation.getId(), sequence.getTrimestre().getIdtrimestrean());
                                            if (app2 != null) {
                                                app2.setNom(appreciationtrimestrielle.getNom());
                                                appreciationtrimestrielleFacadeLocal.edit(app2);
                                            } else {
                                                appreciationtrimestrielle.setId(appreciationtrimestrielleFacadeLocal.nextVal());
                                                appreciationtrimestrielle.setNom(appreciationtrimestrielle.getNom());
                                                appreciationtrimestrielleFacadeLocal.create(appreciationtrimestrielle);
                                            }

                                        }
                                    }

                                    //ici debute l'appreciation annuelle des notes
                                    Appreciationannuelle appreciationannuelle = new Appreciationannuelle();

                                    appreciationannuelle.setMatiere(classeElementevaluation);
                                    appreciationannuelle.setEleve(eac);
                                    appreciationannuelle.setAnnee(annee);

                                    List<Evaluation> temp1 = evaluationFacadeLocal.findByEleveAnneeMatiere(e.getEleve().getIdeleve(), annee.getIdannee(), e.getPlanningEvaluation().getElementEvaluation().getId());

                                    if (i == 0) {
                                        if (temp1.isEmpty()) {
                                            appreciationannuelle.setId(appreciationannuelleFacadeLocal.nextVal());
                                            appreciationannuelle.setNom(e.getObservation());
                                            appreciationannuelleFacadeLocal.create(appreciationannuelle);
                                        } else {
                                            Double note = e.getNote();
                                            for (Evaluation e1 : temp1) {
                                                note += e1.getNote();
                                            }

                                            for (Observationnote o : observationnotes) {
                                                if (note / (temp1.size() + 1) >= o.getBorneinferieur() && note / (temp1.size() + 1) <= o.getBornesuperieur()) {
                                                    appreciationannuelle.setNom(o.getAvis());
                                                    break;
                                                }
                                            }

                                            Appreciationannuelle app1 = appreciationannuelleFacadeLocal.find(e.getEleve().getIdeleve(), classeElementevaluation.getId(), annee.getIdannee());

                                            if (app1 != null) {
                                                app1.setNom(appreciationannuelle.getNom());
                                                appreciationannuelleFacadeLocal.edit(app1);
                                            } else {
                                                appreciationannuelle.setId(appreciationannuelleFacadeLocal.nextVal());
                                                appreciationannuelle.setNom(appreciationannuelle.getNom());
                                                appreciationannuelleFacadeLocal.create(appreciationannuelle);
                                            }

                                        }
                                    }

                                    evaluationFacadeLocal.create(e);
                                    i++;
                                }
                            } else {
                                temp.add(e);
                            }
                        }
                    }
                } else {
                    for (Evaluation e : evaluations1) {
                        if (e.getNote() >= 0 && e.getNote() <= 20) {

                            e.setIdeval(evaluationFacadeLocal.nextVal());
                            e.setValide(false);

                            Eleveanneeclasse eac = eleveanneeclasseFacadeLocal.getEleveAnneeClaseByAnneClasse(e.getEleve().getIdeleve(), annee.getIdannee());

                            e.setObservation("");
                            for (Observationnote o : observationnotes) {
                                if (e.getNote() >= o.getBorneinferieur() && e.getNote() <= o.getBornesuperieur()) {
                                    e.setObservation(o.getAvis());
                                    break;
                                }
                            }

                            // ici commence l'appreciation trimestrielle des notes
                            Appreciationtrimestrielle appreciationtrimestrielle = new Appreciationtrimestrielle();

                            appreciationtrimestrielle.setEleve(eac);
                            appreciationtrimestrielle.setMatiere(e.getPlanningEvaluation().getElementEvaluation());
                            appreciationtrimestrielle.setTrimestre(sequence.getTrimestre());
                            appreciationtrimestrielle.setNom(e.getObservation());

                            List<Evaluation> temp2 = evaluationFacadeLocal.findByEleveTrimestreMatiere(e.getEleve().getIdeleve(), sequence.getTrimestre().getIdtrimestrean(), classeElementevaluation.getId());

                            if (temp2.isEmpty()) {
                                appreciationtrimestrielle.setId(appreciationtrimestrielleFacadeLocal.nextVal());
                                appreciationtrimestrielleFacadeLocal.create(appreciationtrimestrielle);
                            } else {
                                Double note = e.getNote();
                                for (Evaluation e1 : temp2) {
                                    note += e1.getNote();
                                }

                                for (Observationnote o : observationnotes) {
                                    if (note >= o.getBorneinferieur() && note <= o.getBornesuperieur()) {
                                        appreciationtrimestrielle.setNom(o.getAvis());
                                        break;
                                    }
                                }

                                Appreciationtrimestrielle app2 = appreciationtrimestrielleFacadeLocal.find(e.getEleve().getIdeleve(), classeElementevaluation.getId(), sequence.getTrimestre().getIdtrimestrean());
                                if (app2 != null) {
                                    app2.setNom(appreciationtrimestrielle.getNom());
                                    appreciationtrimestrielleFacadeLocal.edit(app2);
                                } else {
                                    appreciationtrimestrielle.setId(appreciationtrimestrielleFacadeLocal.nextVal());
                                    appreciationtrimestrielle.setNom(appreciationtrimestrielle.getNom());
                                    appreciationtrimestrielleFacadeLocal.create(appreciationtrimestrielle);
                                }

                            }

                            //ici debute l'appreciation annuelle des notes
                            Appreciationannuelle appreciationannuelle = new Appreciationannuelle();

                            appreciationannuelle.setMatiere(classeElementevaluation);
                            appreciationannuelle.setEleve(eac);
                            appreciationannuelle.setAnnee(annee);

                            List<Evaluation> temp1 = evaluationFacadeLocal.findByEleveAnneeMatiere(e.getEleve().getIdeleve(), annee.getIdannee(), e.getPlanningEvaluation().getElementEvaluation().getId());

                            if (temp1.isEmpty()) {
                                appreciationannuelle.setNom(e.getObservation());
                                appreciationannuelleFacadeLocal.create(appreciationannuelle);
                            } else {
                                Double note = e.getNote();
                                for (Evaluation e1 : temp1) {
                                    note += e1.getNote();
                                }

                                for (Observationnote o : observationnotes) {
                                    if (note >= o.getBorneinferieur() && note <= o.getBornesuperieur()) {
                                        appreciationannuelle.setNom(o.getAvis());
                                        break;
                                    }
                                }

                                Appreciationannuelle app1 = appreciationannuelleFacadeLocal.find(e.getEleve().getIdeleve(), classeElementevaluation.getId(), annee.getIdannee());

                                if (app1 != null) {
                                    app1.setNom(appreciationannuelle.getNom());
                                    appreciationannuelleFacadeLocal.edit(app1);
                                } else {
                                    appreciationannuelle.setId(appreciationannuelleFacadeLocal.nextVal());
                                    appreciationannuelle.setNom(appreciationannuelle.getNom());
                                    appreciationannuelleFacadeLocal.create(appreciationannuelle);
                                }

                            }

                            evaluationFacadeLocal.create(e);

                        } else {
                            temp.add(e);
                        }
                    }
                }
                if (temp.isEmpty()) {
                    this.closeDialog();
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    this.openDialog();
                    evaluations1 = temp;
                    JsfUtil.addSuccessMessage("Opération réussie , mais veuillez corriger les données des lignes restantes");
                }
            } else {
                JsfUtil.addErrorMessage("Le tableau est vide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifier() {
        if (selectedEvaluation != null) {
            if (!selectedEvaluation.getValide()) {
                evaluationFacadeLocal.edit(selectedEvaluation);
                JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                JsfUtil.addWarningMessage("La note est verouillé et ne peut etre modifié");
            }
        } else {
            JsfUtil.addErrorMessage("Aucune ligne selectionnée");
        }
    }

    @Override
    public void supprimer() {
        if (selectedEvaluation != null) {
            if (!selectedEvaluation.getValide()) {
                evaluationFacadeLocal.remove(selectedEvaluation);
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addWarningMessage("La note est validée et ne peut etre supprimée");
            }
        } else {
            JsfUtil.addErrorMessage("Aucune ligne selectionnée");
        }
    }

    @Override
    public void imprimerEvaluationPdf() {
        System.out.println("Impression pdf types compte");
        evaluations = evaluationFacadeLocal.findAll();
    }

    @Override
    public void imprimerEvaluationHtml() {
        System.out.println("Impression html types compte");
    }

    //methode qui actualise la liste des elves et des matieres en fonction de la classe selectionnée
    public void handleMatiereEleveChange() {
        classeElementevaluations.clear();
        eleveanneeclasses.clear();
        try {

            if (classe.getIdclasse() != null) {

                classeElementevaluations = classeElementevaluationFacadeLocal.findByClasse(classe.getIdclasse());
                if (classeElementevaluations.isEmpty()) {
                    JsfUtil.addErrorMessage("Cette classe n'a aucun element d'évaluation affecté");
                } else {
                    eleveanneeclasses = eleveanneeclasseFacadeLocal.findByAnneeClasse(annee.getIdannee(), classe.getIdclasse());
                    if (eleveanneeclasses.isEmpty()) {
                        JsfUtil.addErrorMessage("Cette classe n'a aucun élève");
                    }
                }
                System.err.println("taille matiere " + classeElementevaluations.size());
                System.err.println("taille eleve " + eleveanneeclasses.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTableau() {
        evaluations1.clear();
        try {
            if (sequence.getIdsequencean() != null && classeElementevaluation.getId() != null) {
                PlanningEvaluation p = planningEvaluationFacadeLocal.findByElementSequence1(classeElementevaluation.getElementevaluation().getIdelement(), sequence.getIdsequencean());
                if (p != null) {
                    if (!eleveanneeclasses.isEmpty()) {
                        for (Eleveanneeclasse e : eleveanneeclasses) {
                            Evaluation temp = evaluationFacadeLocal.findByElevePlanning(e.getEleve().getIdeleve(), p.getIdplanning());
                            if (temp == null) {
                                evaluation = new Evaluation();
                                evaluation.setEleve(e.getEleve());
                                evaluation.setPlanningEvaluation(p);
                                evaluation.setNote(0.0);
                                evaluations1.add(evaluation);
                            }
                        }

                    } else {
                        JsfUtil.addErrorMessage("Cette classe n'a aucun élève");
                    }
                } else {
                    JsfUtil.addErrorMessage("Aucune évaluation programmée dans cette classe et à cette séquence");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
