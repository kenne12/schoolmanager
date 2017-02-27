/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDiscipline.sanction;

import entities.Sanction;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "sanctionCtrl")
@ViewScoped
public class SanctionCtrl extends AbstractSanctionCtrl implements SanctionInterfaceCtrl, Serializable {

    @PostConstruct
    private void initSanction() {
        selectedSanction = new Sanction();
        sanction = new Sanction();
    }

    @Override
    public void enregistrerSanction() {
        if (!"".equals(sanction.getLibelle())) {
            Sanction result = sanctionFacadeLocal.findBylibelle(sanction.getLibelle());
            if (result == null) {
                sanctionFacadeLocal.create(sanction);
                initSanction();
                JsfUtil.addSuccessMessage("Le sanction a été enregistré");
            } else {
                JsfUtil.addErrorMessage("Un type de sanction portant ce nom existe déjà");
            }
        } else {
            JsfUtil.addErrorMessage("le libellé du sanction est obligatoire");
        }
    }

    @Override
    public void modifier() {
        if (!"".equals(selectedSanction.getLibelle())) {
            sanctionFacadeLocal.edit(selectedSanction);
            initSanction();
            JsfUtil.addSuccessMessage("Le sanction a été emis à jour");
        } else {
            JsfUtil.addErrorMessage("le libellé du sanction est obligatoire");
        }
    }

    @Override
    public void supprimer() {
        if (selectedSanction == null || selectedSanction.getIdsanction() == null) {
            JsfUtil.addSuccessMessage("Veuillez selectionner un sanction !");
        } else {
            if (selectedSanction.getPunitionList().isEmpty()) {
                if (selectedSanction.getPunitionpersonnelList().isEmpty()) {
                    sanctionFacadeLocal.remove(selectedSanction);
                    initSanction();
                    JsfUtil.addSuccessMessage("opération réussie");
                }
            }
        }
    }

    @Override
    public void imprimerSanctionPdf() {
        System.out.println("Impression pdf types compte");
        sanctions = sanctionFacadeLocal.findAll();
//        fileName = PdfSanction.etatsSanction(sanctions);
    }

    @Override
    public void imprimerSanctionHtml() {
        System.out.println("Impression html types compte");
    }

}
