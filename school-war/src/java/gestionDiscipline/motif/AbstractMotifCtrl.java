/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionDiscipline.motif;

import entities.Motif;
import java.util.List;
import javax.ejb.EJB;
import session.MotifFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractMotifCtrl {

    protected String fileName;
    @EJB
    protected MotifFacadeLocal motifFacadeLocal;

    protected List<Motif> motifs;

    protected StringBuffer motifsTableHtml = new StringBuffer("pas encore implementé");
    protected Motif selectedMotif;
    protected Motif motif;
    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Motif> getMotifs() {
        motifs = motifFacadeLocal.findAll();
        return motifs;
    }

    public Motif getSelectedMotif() {
        return selectedMotif;
    }

    public void setSelectedMotif(Motif selectedMotif) {
        this.selectedMotif = selectedMotif;
        modifier = supprimer = detail = selectedMotif == null;
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
        imprimer = motifFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Motif getMotif() {
        return motif;
    }

    public void setMotif(Motif motif) {
        this.motif = motif;
    }

    public StringBuffer getMotifsTableHtml() {
        return motifsTableHtml;
    }

}
