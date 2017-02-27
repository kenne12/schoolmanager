/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.trimestre;

import entities.Traceur;
import entities.Trimestre;
import java.util.List;
import javax.ejb.EJB;
import session.TraceurFacadeLocal;
import session.TrimestreFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractTrimestreCtrl {

    protected String fileName;
    @EJB
    protected TrimestreFacadeLocal trimestreFacadeLocal;
    protected List<Trimestre> trimestres;

    protected Trimestre selectedTrimestre;
    protected Trimestre trimestre;
    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    @EJB
    protected TraceurFacadeLocal traceurFacadeLocal;
    protected Traceur traceur;

    public List<Trimestre> getTrimestres() {
        trimestres = trimestreFacadeLocal.findAll();
        return trimestres;
    }

    public Trimestre getSelectedTrimestre() {
        return selectedTrimestre;
    }

    public void setSelectedTrimestre(Trimestre selectedTrimestre) {
        this.selectedTrimestre = selectedTrimestre;
        modifier = supprimer = detail = selectedTrimestre == null;
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
        imprimer = trimestreFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Trimestre getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Trimestre trimestre) {
        this.trimestre = trimestre;
    }

}
