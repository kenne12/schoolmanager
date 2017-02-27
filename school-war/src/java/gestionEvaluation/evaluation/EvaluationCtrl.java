/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionEvaluation.evaluation;

import entities.ElementEvaluation;
import entities.Eleve;
import entities.Eleveanneeclasse;
import entities.Evaluation;
import entities.Matiere;
import entities.Observationnote;
import entities.Personnel;
import entities.Personnelmatiereclasseannee;
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
import utils.SessionMBean;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "evaluationCtrl")
@SessionScoped
public class EvaluationCtrl extends AbstractEvaluationCtrl implements EvaluationInterfaceCtrl, Serializable {

    @PostConstruct
    private void initEvaluation() {
        selectedEvaluation = new Evaluation();
        evaluation = new Evaluation();
        matiere = new Matiere();
        personnel = new Personnel();
        trimestre = new Trimesteannee();
        sequence = new Sequenceannee();
        eleve = new Eleve();
        traceur = new Traceur();
        personnelMatiere = new Personnelmatiereclasseannee();
        observationnote = new Observationnote();
        try {
            sequences = sequenceFacade.getByAnneEtat(SessionMBean.getYear().getIdannee(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        elementEvaluations.clear();
        personnelMatiere = new Personnelmatiereclasseannee();
        elementEvaluation = new ElementEvaluation();
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

        List<Personnelmatiereclasseannee> tempon;
        tempon = personnelMatiereFacade.getMatiereByPersonnelAnnee(SessionMBean.getUserAccount().getPersonnel().getIdpersonnel(), SessionMBean.getYear().getIdannee());

        if (!tempon.isEmpty()) {
            for (Personnelmatiereclasseannee object : tempon) {
                classeSources.add(object.getIdclasse());
            }

            for (int i = 0; i < tempon.size(); i++) {
                if (!classeTarget.contains(tempon.get(i).getIdclasse())) {
                    personnelClasses.add(tempon.get(i));
                    classeTarget.add(tempon.get(i).getIdclasse());
                }
            }
        }
    }

    private void initCreate() {
        evaluation.setEleve(eleve);
    }

    @Override
    public void enregistrerEvaluation() {
        try {
            if (!evaluations1.isEmpty()) {
                List<Evaluation> temp = new ArrayList<>();
                for (Evaluation e : evaluations1) {
                    if (e.getNote() >= 0 && e.getNote() <= 20) {
                        e.setObservation("");
                        for (Observationnote o : this.getObservationnotes()) {
                            if (e.getNote() >= o.getBorneinferieur() && e.getNote() <= o.getBornesuperieur()) {
                                e.setObservation(o.getAvis());
                                break;
                            }
                        }
                        e.setIdeval(evaluationFacadeLocal.nextVal());
                        e.setValide(false);
                        evaluationFacadeLocal.create(e);
                    } else {
                        temp.add(e);
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
                JsfUtil.addErrorMessage("Aucune note dans le tableau");
                closeDialog();
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
        elementEvaluations.clear();
        eleves.clear();
        try {
            if (SessionMBean.getUserAccount() != null) {

                if (personnelMatiere.getId() != null) {
                    personnelMatiere = personnelMatiereFacade.find(personnelMatiere.getId());

                    personnelMatieres = personnelMatiereFacade.get(SessionMBean.getUserAccount().getPersonnel().getIdpersonnel(), SessionMBean.getYear().getIdannee(), personnelMatiere.getIdclasse().getIdclasse());

                    if (!personnelMatieres.isEmpty()) {
                        for (Personnelmatiereclasseannee p : personnelMatieres) {

                            if (!p.getIdmatiere().getElementEvaluationList().isEmpty()) {

                                for (ElementEvaluation e : p.getIdmatiere().getElementEvaluationList()) {
                                    if (classeElementevaluationFacadeLocal.find(personnelMatiere.getIdclasse().getIdclasse(), e.getIdelement()) != null) {
                                        elementEvaluations.add(e);
                                    }
                                }

                            }
                        }
                    }

                    List<Eleveanneeclasse> classes = eleveAnneeClasse.findByAnneeClasse(SessionMBean.getYear().getIdannee(), personnelMatiere.getIdclasse().getIdclasse());

                    if (!classes.isEmpty()) {
                        for (Eleveanneeclasse object : classes) {
                            eleves.add(object.getEleve());
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTableau() {
        evaluations1.clear();
        try {
            if (sequence.getIdsequencean() != null && elementEvaluation.getIdelement() != null) {
                PlanningEvaluation p = planningEvaluationFacadeLocal.findByElementSequence1(elementEvaluation.getIdelement(), sequence.getIdsequencean());
                if (p != null) {
                    if (!eleves.isEmpty()) {
                        for (Eleve e : eleves) {
                            Evaluation temp = evaluationFacadeLocal.findByElevePlanning(e.getIdeleve(), p.getIdplanning());
                            if (temp == null) {
                                evaluation = new Evaluation();
                                evaluation.setEleve(e);
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
