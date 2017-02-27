/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privilege.mouchard;

import entities.Traceur;
import java.util.List;
import javax.ejb.EJB;
import session.TraceurFacadeLocal;

/**
 *
 * @author Gervais
 */
public class AbstractMouchardCtrl {

    protected String fileName;
    @EJB
    protected TraceurFacadeLocal traceurFacadeLocal;

    protected List<Traceur> traceurs;

    protected StringBuffer traceursTableHtml = new StringBuffer("pas encore implementé");
    protected Traceur selectedTraceur;
    protected Traceur traceur;
    protected boolean detail = true;
    protected boolean modifier = true;
    protected boolean supprimer = true;
    protected boolean imprimer = true;

    public List<Traceur> getTraceurs() {
        traceurs = traceurFacadeLocal.findAll();
        return traceurs;
    }

    public Traceur getSelectedTraceur() {
        return selectedTraceur;
    }

    public void setSelectedTraceur(Traceur selectedTraceur) {
        this.selectedTraceur = selectedTraceur;
        detail = selectedTraceur == null;
    }

    public boolean isDetail() {
        return detail;
    }

    public boolean isImprimer() {
        imprimer = traceurFacadeLocal.findAll().isEmpty();
        return imprimer;
    }

    public Traceur getTraceur() {
        return traceur;
    }

    public void setTraceur(Traceur traceur) {
        this.traceur = traceur;
    }

    public StringBuffer getTraceursTableHtml() {
        return traceursTableHtml;
    }

}
