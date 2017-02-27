/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.sequence;

import entities.Sequence;
import entities.Traceur;
import java.util.List;
import javax.ejb.EJB;
import session.SequenceFacadeLocal;
import session.TraceurFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractSequenceCtrl {

    @EJB
    protected SequenceFacadeLocal sequenceFacadeLocal;

    protected Sequence sequence;
    protected Sequence selectedSequence;
    protected List<Sequence> sequences;

    @EJB
    protected TraceurFacadeLocal traceurFacadeLocal;
    protected Traceur traceur;

    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Sequence> getSequences() {
        sequences = sequenceFacadeLocal.findAll();
        return sequences;
    }

    public Sequence getSelectedSequence() {
        return selectedSequence;
    }

    public void setSelectedSequence(Sequence selectedSequence) {
        this.selectedSequence = selectedSequence;
        modifier = supprimer = detail = selectedSequence == null;
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
        imprimer = sequenceFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Sequence getSequence() {
        return sequence;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }

}
