/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDiscipline.absence;

import entities.Absenceeleve;
import entities.Annee;
import entities.Eleve;
import entities.Personnel;
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
@ManagedBean(name = "absenceCtrl")
@ViewScoped
public class AbsenceCtrl extends AbstractAbsenceCtrl implements AbsenceInterfaceCtrl, Serializable {

    @PostConstruct
    private void initAbsence() {
        selectedAbsence = new Absenceeleve();
        absence = new Absenceeleve();
        eleve = new Eleve();
        personnel = new Personnel();
        annee = new Annee();
        sequenceAnnee = new Sequenceannee();

    }

    private void create() {
        absence.setEleve(eleve);
        absence.setIdannee(annee);
        absence.setSequence(sequenceAnnee);
        personnel.setIdpersonnel(2);
    }

    @Override
    public void enregistrerAbsence() {
        if (annee.getIdannee() != null) {
            if (eleve.getIdeleve() != null) {

                if (sequenceAnnee.getIdsequencean() != null) {
                    create();
                    absenceFacadeLocal.create(absence);
                    initAbsence();
                    JsfUtil.addSuccessMessage("Le absence a été enregistré");
                } else {
                    JsfUtil.addErrorMessage("veuillez selectionner une sequence");
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
        /*if(!"".equals(selectedAbsence.getLibelle())){                      
         absenceFacadeLocal.edit(selectedAbsence);
         initAbsence();
         JsfUtil.addSuccessMessage("Le absence a été emis à jour");                            
         }else{
         JsfUtil.addErrorMessage("le libellé du absence est obligatoire");
         }*/
    }

    @Override
    public void supprimer() {
        /*if(selectedAbsence == null || selectedAbsence.getIdabsence()== null){
         JsfUtil.addSuccessMessage("Veuillez selectionner un absence !");
         }
         else{
         if(selectedAbsence.getAbsenceCollection().isEmpty()){
         if(selectedAbsence.getAbsencepersonnelCollection().isEmpty()){
         absenceFacadeLocal.remove(selectedAbsence);
         initAbsence();
         JsfUtil.addSuccessMessage("opération réussie");
         }
         }  
         }*/
    }

    @Override
    public void imprimerAbsencePdf() {
        System.out.println("Impression pdf types compte");
        absences = absenceFacadeLocal.getAbsenceEleveByAnneeActive(true);
//        fileName = PdfAbsence.etatsAbsence(absences);
    }

    @Override
    public void imprimerAbsenceHtml() {
        System.out.println("Impression html types compte");
    }

}
