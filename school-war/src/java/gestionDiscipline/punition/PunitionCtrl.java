/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDiscipline.punition;

import entities.Annee;
import entities.Eleve;
import entities.Motif;
import entities.Personnel;
import entities.Punition;
import entities.Sanction;
import entities.Sequence;
import entities.Sequenceannee;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "punitionCtrl")
@ViewScoped
public class PunitionCtrl extends AbstractPunitionCtrl implements PunitionInterfaceCtrl, Serializable {

    @PostConstruct
    private void initPunition() {
        selectedPunition = new Punition();
        punition = new Punition();
        eleve = new Eleve();
        personnel = new Personnel();
        annee = new Annee();
        motif = new Motif();
        sanction = new Sanction();
        sequenceAnnee = new Sequenceannee();

    }

    private void create() {
        punition.setEleve(eleve);
        punition.setIdannee(annee);
        punition.setIdmotif(motif);
        punition.setIdsanction(sanction);
        punition.setSequence(sequenceAnnee);
        personnel.setIdpersonnel(2);
    }

    @Override
    public void enregistrerPunition() {
        if (annee.getIdannee() != null) {
            if (eleve.getIdeleve() != null) {
                if (motif.getIdmotif() != null) {
                    if (sanction.getIdsanction() != null) {
                        if (sequenceAnnee.getIdsequencean() != null) {
                            create();
                            punitionFacadeLocal.create(punition);
                            initPunition();
                            JsfUtil.addSuccessMessage("Le punition a été enregistré");
                        } else {
                            JsfUtil.addErrorMessage("veuillez selectionner une sequence");
                        }
                    } else {
                        JsfUtil.addErrorMessage("veuillez selectionner une sanction");
                    }
                } else {
                    JsfUtil.addErrorMessage("veuillez selectionner un motif");
                }
            } else {
                JsfUtil.addErrorMessage("veuillez selectionner un élève");
            }
        } else {
            JsfUtil.addErrorMessage("il n'y a aucune année scolaire, veuillez le parametrer");
        }
    }

    @Override
    public void modifier() {
        /*if(!"".equals(selectedPunition.getLibelle())){                      
         punitionFacadeLocal.edit(selectedPunition);
         initPunition();
         JsfUtil.addSuccessMessage("Le punition a été emis à jour");                            
         }else{
         JsfUtil.addErrorMessage("le libellé du punition est obligatoire");
         }*/
    }

    @Override
    public void supprimer() {
        /*if(selectedPunition == null || selectedPunition.getIdpunition()== null){
         JsfUtil.addSuccessMessage("Veuillez selectionner un punition !");
         }
         else{
         if(selectedPunition.getPunitionCollection().isEmpty()){
         if(selectedPunition.getPunitionpersonnelCollection().isEmpty()){
         punitionFacadeLocal.remove(selectedPunition);
         initPunition();
         JsfUtil.addSuccessMessage("opération réussie");
         }
         }  
         }*/
    }

    @Override
    public void imprimerPunitionPdf() {
        System.out.println("Impression pdf types compte");
        punitions = punitionFacadeLocal.findAll();
//        fileName = PdfPunition.etatsPunition(punitions);
    }

    @Override
    public void imprimerPunitionHtml() {
        System.out.println("Impression html types compte");
    }

}
