/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDiscipline.sanction;

import entities.Sanction;
import java.util.List;
import javax.ejb.EJB;
import session.SanctionFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractSanctionCtrl {

    protected String fileName;
    @EJB
    protected SanctionFacadeLocal sanctionFacadeLocal;

    protected List<Sanction> sanctions;

    protected StringBuffer sanctionsTableHtml = new StringBuffer("pas encore implementé");
    protected Sanction selectedSanction;
    protected Sanction sanction;
    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Sanction> getSanctions() {
        sanctions = sanctionFacadeLocal.findAll();
        return sanctions;
    }

    public Sanction getSelectedSanction() {
        return selectedSanction;
    }

    public void setSelectedSanction(Sanction selectedSanction) {
        this.selectedSanction = selectedSanction;
        modifier = supprimer = detail = selectedSanction == null;
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
        imprimer = sanctionFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Sanction getSanction() {
        return sanction;
    }

    public void setSanction(Sanction sanction) {
        this.sanction = sanction;
    }

    public StringBuffer getSanctionsTableHtml() {
        return sanctionsTableHtml;
    }

}
