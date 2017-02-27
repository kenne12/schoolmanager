/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.cycle;

import entities.Cycle;
import java.util.List;
import javax.ejb.EJB;
import session.CycleFacadeLocal;

/**
 *
 * @author gervais kenne
 */
public class AbstractCycleCtrl {

    protected String fileName;
    @EJB
    protected CycleFacadeLocal cycleFacadeLocal;

    protected List<Cycle> cycles;

    protected StringBuffer cyclesTableHtml = new StringBuffer("pas encore implementé");
    protected Cycle selectedCycle;
    protected Cycle cycle;
    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Cycle> getCycles() {
        cycles = cycleFacadeLocal.findAll();
        return cycles;
    }

    public Cycle getSelectedCycle() {
        return selectedCycle;
    }

    public void setSelectedCycle(Cycle selectedCycle) {
        this.selectedCycle = selectedCycle;
        modifier = supprimer = detail = selectedCycle == null;
    }

    public boolean isDetail() {
        return detail;
    }

    public boolean isModifier() {
        return modifier;
    }

    public boolean isSupprimer() {
        return supprimer;
    }

    public boolean isImprimer() {
        imprimer = cycleFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }

    public StringBuffer getCyclesTableHtml() {
        return cyclesTableHtml;
    }

}
