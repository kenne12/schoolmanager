/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.cycle;

import entities.Cycle;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "cycleCtrl")
@ViewScoped
public class CycleCtrl extends AbstractCycleCtrl implements CycleInterfaceCtrl, Serializable {

    @PostConstruct
    private void initCycle() {
        selectedCycle = new Cycle();
        cycle = new Cycle();
    }

    @Override
    public void enregistrerCycle() {
        if (cycle != null) {
            Cycle result = cycleFacadeLocal.findByNom(cycle.getNom());
            if (result == null) {
                cycleFacadeLocal.create(cycle);
                initCycle();
                JsfUtil.addSuccessMessage("operation réussie !");
            } else {
                JsfUtil.addErrorMessage("une cycle portant ce nom existe deja !");
            }
        } else {
            JsfUtil.addErrorMessage("echec !");
        }
    }

    @Override
    public void modifier() {
        if (selectedCycle != null) {
            cycleFacadeLocal.edit(selectedCycle);
            initCycle();
            JsfUtil.addSuccessMessage("le cycle a été mise à jour !");
        } else {
            JsfUtil.addErrorMessage("echec !");
        }
    }

    @Override
    public void supprimer() {
        if (selectedCycle == null || selectedCycle.getIdcycle() == null) {
            JsfUtil.addSuccessMessage("Veuillez selectionner un cycle !");
        } else {
            if (selectedCycle.getClasseList().isEmpty()) {
                cycleFacadeLocal.remove(selectedCycle);
                initCycle();
                JsfUtil.addSuccessMessage("opération réussie");
            } else {
                JsfUtil.addErrorMessage("impossible de supprimer, ce cycle contient des données");
                initCycle();
            }

        }
    }

    @Override
    public void imprimerCyclePdf() {

    }

    @Override
    public void imprimerCycleHtml() {
        System.out.println("Impression html types compte");
    }

}
