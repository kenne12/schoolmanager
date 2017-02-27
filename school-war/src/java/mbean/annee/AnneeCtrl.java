/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.annee;

import entities.Annee;
import entities.Personnel;
import entities.Traceur;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.UtilitaireSession;
import utils.Utilitaires;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "anneeCtrl")
@ViewScoped
public class AnneeCtrl extends AbstractAnneCtrl implements AnneeInterfaceCtrl, Serializable {
    
    @PostConstruct
    private void initAnnee() {
        selectedAnnee = new Annee();
        annee = new Annee();
        traceur = new Traceur();
        //etablissements = Utilitaires.findSchoolByPersonnel(UtilitaireSession.getInstance().getuser());
    }
    
    @Override
    public void enregistrerAnnee() {
        
        Annee an = anneeFacadeLocal.findByCode(SessionMBean.getSchool().getId(), annee.getCode(), annee.getCodefin());
        if (an != null) {
            JsfUtil.addErrorMessage("Une année ayant ces paramètres existe déjà");
        } else {
            
            annee.setIdannee(anneeFacadeLocal.nextVal());
            annee.setEtablissement(SessionMBean.getSchool());
            anneeFacadeLocal.create(annee);
            
            Utilitaires.saveOperation(traceurFacade, "Création  de l'année scolaire " + selectedAnnee.getCode(), UtilitaireSession.getInstance().getuser());
            JsfUtil.addSuccessMessage("L'année  a été enregistrée");
            initAnnee();
            
        }
        
    }
    
    @Override
    public void modifier() {
        Personnel connectedUser = UtilitaireSession.getInstance().getuser();
        if (connectedUser != null) {
            if (selectedAnnee == null) {
                JsfUtil.addErrorMessage("Veuillez selectionner une année !");
            } else {
                traceur.setAction("Modification de l'année " + selectedAnnee.getCode() + " par le personnel" + connectedUser.getNom());
                traceur.setDateaction(new Date());
                traceur.setPersonnel(connectedUser);
                anneeFacadeLocal.edit(selectedAnnee);
                traceurFacade.create(traceur);
                JsfUtil.addSuccessMessage("l'année a été mise à jour !");
                initAnnee();
            }
        }
    }
    
    @Override
    public void supprimer() {
        Personnel user = UtilitaireSession.getInstance().getuser();
        boolean isDelete = false;
        if (selectedAnnee == null || selectedAnnee.getIdannee() == null) {
            JsfUtil.addSuccessMessage("Veuillez selectionner un annee !");
        } else {
            traceur = new Traceur();
            traceur.setAction("Suprression de l'année scolaire : " + selectedAnnee.getCode());
            traceur.setDateaction(new Date());
            traceur.setPersonnel(user);
            if (selectedAnnee.getAbsenceeleveList().isEmpty()) {
                if (selectedAnnee.getActiviteanneeList().isEmpty()) {
                    if (selectedAnnee.getAnneedepenseList().isEmpty()) {
                        if (selectedAnnee.getEleveanneeclasseList().isEmpty()) {
                            if (selectedAnnee.getElevelivreemprunteList().isEmpty()) {
                                if (selectedAnnee.getPersonmatiereclasseanneedateList().isEmpty()) {
                                    if (selectedAnnee.getPersonnelanneecatfonctList().isEmpty()) {
                                        if (selectedAnnee.getPersonnelmatiereclasseanneeList().isEmpty()) {
                                            if (selectedAnnee.getPunitionList().isEmpty()) {
                                                if (selectedAnnee.getRemunerationList().isEmpty()) {
                                                    if (selectedAnnee.getSemaineanneeList().isEmpty()) {
                                                        if (selectedAnnee.getSequenceanneeList().isEmpty()) {
                                                            if (selectedAnnee.getTrancheList().isEmpty()) {
                                                                if (selectedAnnee.getTrimesteanneeList().isEmpty()) {
                                                                    if (selectedAnnee.getTrancheList().isEmpty()) {
                                                                        isDelete = true;
                                                                        anneeFacadeLocal.remove(selectedAnnee);
                                                                        traceurFacade.create(traceur);
                                                                        initAnnee();
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (isDelete) {
            JsfUtil.addSuccessMessage("opération réussie");
        } else {
            JsfUtil.addWarningMessage("Impossible de supprimer cette année car elle contient des données");
            initAnnee();
        }
        initAnnee();
    }
    
    @Override
    public void imprimerAnneePdf() {
        System.out.println("Impression pdf types compte");
        annees = anneeFacadeLocal.findAll();
    }
    
    @Override
    public void imprimerAnneeHtml() {
        System.out.println("Impression html types compte");
    }
    
}
