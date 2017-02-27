/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.affecterTrimestre;

import entities.Annee;
import entities.Etablissement;
import entities.Trimesteannee;
import entities.Trimestre;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.UtilitaireSession;
import utils.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean(name = "affecterTrimCtrl")
@ViewScoped
public class AffecterTrimCtrl extends AbstractAffecterTrimCtrl implements AffecterTrimInterfaceCtrl, Serializable {

    /**
     * Creates a new instance of AffecterTrimCtrl
     */
    public AffecterTrimCtrl() {
    }

    @PostConstruct
    private void initAffecter() {
        trimestre = new Trimestre();
        annee = new Annee();
        trimestreAnnee = new Trimesteannee();
        selectedTrimesteAnnee = new Trimesteannee();
        trimestreSource = trimestreFacade.findAll();
        trimestreTarget = new ArrayList<>();
        etablissement = new Etablissement();
        etablissements = Utilitaires.findSchoolByPersonnel(UtilitaireSession.getInstance().getuser());
        loadTrimestre();
    }

    public void prepareCreate() {
        try {
            dualsTrimestre.getSource().clear();
            dualsTrimestre.getTarget().clear();
            annees.clear();
            etablissement = new Etablissement();
            trimestreAnnee = new Trimesteannee();
            updateAnnee();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        try {
            if (selectedTrimesteAnnee != null) {
                annees = selectedTrimesteAnnee.getIdannee().getEtablissement().getAnneeList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enregistrerTrimestre() {
        try {
            if (annee != null) {
                if (!dualsTrimestre.getTarget().isEmpty()) {
                    for (Trimestre t : dualsTrimestre.getTarget()) {
                        trimestreAnnee.setIdtrimestrean(trimesteanneeFacade.nextVal());
                        trimestreAnnee.setEtat(true);
                        trimestreAnnee.setIdannee(annee);
                        trimestreAnnee.setIdtrimestre(t);
                        if (trimesteanneeFacade.findByAnneeTrimestre(annee.getIdannee(), t.getIdtrimestre()).isEmpty()) {
                            trimesteanneeFacade.create(trimestreAnnee);
                        }
                        Utilitaires.saveOperation(traceurFacadeLocal, "Creation du trimestre -> " + t.getNom() + " etablissement -> " + annee.getEtablissement().getNom(), UtilitaireSession.getInstance().getuser());
                    }
                    JsfUtil.addSuccessMessage("Opération effectué avec succès");
                    loadTrimestre();
                } else {
                    JsfUtil.addErrorMessage("La liste de trimestre de destination est vide");
                }
            } else {
                JsfUtil.addErrorMessage("Veuillez selectionner une année");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void edit(Trimesteannee trimesteannee) {
        try {
            trimesteanneeFacade.edit(trimesteannee);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifier() {
        try {
            if (selectedTrimesteAnnee != null) {
                trimesteanneeFacade.edit(selectedTrimesteAnnee);
                JsfUtil.addSuccessMessage("Trimestre mis à jour");
                loadTrimestre();
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer() {
        try {
            if (selectedTrimesteAnnee != null) {
                if (selectedTrimesteAnnee.getSequenceanneeList().isEmpty()) {
                    trimesteanneeFacade.remove(selectedTrimesteAnnee);
                    Utilitaires.saveOperation(traceurFacadeLocal, "Suppression du trimestre - " + selectedTrimesteAnnee.getIdtrimestre().getNom(), UtilitaireSession.getInstance().getuser());
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addErrorMessage("Ce trimestre comporte plusieurs séquences et ne peut etre supprimé");
                }
                loadTrimestre();
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void imprimerTrimestrePdf() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imprimerTrimestreHtml() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateAnnee() {
        try {
            if (SessionMBean.getSchool() != null) {
                annees = anneeFacade.findByEtablissement(SessionMBean.getSchool());
            } else {
                JsfUtil.addErrorMessage("Etablissement  null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTrimestre() {

        try {
            dualsTrimestre.getSource().clear();
            dualsTrimestre.getTarget().clear();
            if (annee != null) {                
                List<Trimesteannee> resultats = annee.getTrimesteanneeList();
                if (resultats.isEmpty()) {
                    dualsTrimestre.setSource(trimestreFacade.findAll());
                } else {
                    List<Trimestre> trimestres1 = new ArrayList<>();
                    for (Trimesteannee t : resultats) {
                        trimestres1.add(t.getIdtrimestre());
                    }
                    List<Trimestre> trimestresAll = trimestreFacade.findAll();

                    for (Trimestre t : trimestresAll) {
                        if (!trimestres1.contains(t)) {
                            dualsTrimestre.getSource().add(t);
                        }
                    }
                }
            } else {
                JsfUtil.addErrorMessage("Aucune année selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTrimestre() {
        try {
            trimestreAnnees = trimesteanneeFacade.getByAnnee(SessionMBean.getYear().getIdannee());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
