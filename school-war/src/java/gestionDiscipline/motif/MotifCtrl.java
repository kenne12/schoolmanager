/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDiscipline.motif;

import entities.Motif;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "motifCtrl")
@ViewScoped
public class MotifCtrl extends AbstractMotifCtrl implements MotifInterfaceCtrl, Serializable {

    @PostConstruct
    private void initMotif() {
        selectedMotif = new Motif();
        motif = new Motif();
    }

    @Override
    public void enregistrerMotif() {
        if (!"".equals(motif.getLibelle())) {
            Motif result = motifFacadeLocal.getMotifByLibelle(motif.getLibelle());
            if (result == null) {
                motifFacadeLocal.create(motif);
                initMotif();
                JsfUtil.addSuccessMessage("Le motif a été enregistré");
            } else {
                JsfUtil.addErrorMessage("Un type de motif portant ce nom existe déjà");
            }
        } else {
            JsfUtil.addErrorMessage("le libellé du motif est obligatoire");
        }
    }

    @Override
    public void modifier() {
        if (!"".equals(selectedMotif.getLibelle())) {
            motifFacadeLocal.edit(selectedMotif);
            initMotif();
            JsfUtil.addSuccessMessage("Le motif a été emis à jour");
        } else {
            JsfUtil.addErrorMessage("le libellé du motif est obligatoire");
        }
    }

    @Override
    public void supprimer() {
        if (selectedMotif == null || selectedMotif.getIdmotif() == null) {
            JsfUtil.addSuccessMessage("Veuillez selectionner un motif !");
        } else {
            if (selectedMotif.getPunitionList().isEmpty()) {
                if (selectedMotif.getPunitionpersonnelList().isEmpty()) {
                    motifFacadeLocal.remove(selectedMotif);
                    initMotif();
                    JsfUtil.addSuccessMessage("opération réussie");
                }
            }
        }
    }

    @Override
    public void imprimerMotifPdf() {
        System.out.println("Impression pdf types compte");
        motifs = motifFacadeLocal.findAll();
//        fileName = PdfMotif.etatsMotif(motifs);
    }

    @Override
    public void imprimerMotifHtml() {
        System.out.println("Impression html types compte");
    }

}
