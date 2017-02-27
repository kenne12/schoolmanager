/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionInscription.repartirClasse;

import entities.Annee;
import entities.Classe;
import entities.Eleve;
import entities.Eleveanneeclasse;
import entities.Personnel;
import entities.Traceur;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DualListModel;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.UtilitaireSession;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "eleveClasseCtrl")
@SessionScoped
public class EleveClasseCtrl extends AbstractEleveClasseCtrl implements EleveClasseInterfaceCtrl, Serializable {
    
    @PostConstruct
    private void initEleve() {
        selectedEleve = new Eleve();
        eleve = new Eleve();
        personnel = new Personnel();
        traceur = new Traceur();
        dualeleves = new DualListModel<>(eleveSource, eleveTarget);
        annee = new Annee();
        classe = new Classe();
        eleveAnneeClasse = new Eleveanneeclasse();
    }
    
    @Override
    public void repartirClasse() {
        
        if (annee != null) {
            if (!dualeleves.getTarget().isEmpty()) {
                if (classe != null) {
                    for (int i = 0; i < dualeleves.getTarget().size(); i++) {
                        eleveAnneeClasse = new Eleveanneeclasse();
                        eleveAnneeClasse.setId(eleveAnneeFacade.nextVal());
                        eleveAnneeClasse.setEleve(dualeleves.getTarget().get(i));
                        eleveAnneeClasse.setIdannee(annee);
                        eleveAnneeClasse.setIdclasse(classe);
                        traceur.setAction("Attribution de la classe : " + classe.getNom() + " à lélève " + dualeleves.getTarget().get(i).getNom() + " " + dualeleves.getTarget().get(i).getPrenom());
                        traceur.setDateaction(new Date());
                        traceur.setPersonnel(SessionMBean.getUserAccount().getPersonnel());
                        eleveAnneeFacade.create(eleveAnneeClasse);
                        traceurFacade.create(traceur);
                    }
                    initEleve();
                    dualeleves.getTarget().clear();
                    dualeleves.getSource().clear();
                    JsfUtil.addSuccessMessage("Operation réussie !");
                } else {
                    JsfUtil.addWarningMessage("Aucune classe n'est selectionnée !");
                }
            } else {
                JsfUtil.addWarningMessage("Aucun élève selectionné");
            }
            
        } else {
            JsfUtil.addWarningMessage("Aucune année n est selectionnée !");
        }
    }
    
    @Override
    public void modifier() {
        
    }
    
    @Override
    public void supprimer() {
        
    }
    
    @Override
    public void imprimerElevePdf() {
        
    }
    
    @Override
    public void imprimerEleveHtml() {
        System.out.println("Impression html types compte");
    }
    
    @Override
    public boolean handleAddImages() {
        return false;
    }
    
    public void handleEleveChange() {
        dualeleves.getSource().clear();
        dualeleves.getTarget().clear();
        if (annee.getIdannee() != null) {
            List<Eleveanneeclasse> eleveClasse = eleveAnneeFacade.get(annee.getIdannee());
            
            if (!eleveClasse.isEmpty()) {
                for (int i = 0; i < eleveClasse.size(); i++) {
                    eleveTarget.add(eleveClasse.get(i).getEleve());
                }
            }
            
            List<Eleve> eleveAll = eleveFacadeLocal.findByEtat(true);
            if (!eleveAll.isEmpty()) {
                if (!eleveSource.isEmpty()) {
                    eleveSource.clear();
                }
                for (int a = 0; a < eleveAll.size(); a++) {
                    if (!eleveTarget.contains(eleveAll.get(a))) {
                        eleveSource.add(eleveAll.get(a));
                    }
                }
                eleveTarget.clear();
                dualeleves.setSource(eleveSource);
                
            }
        }
    }
}
