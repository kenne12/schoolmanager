/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.matiere;

import entities.Matiere;
import entities.Naturematiere;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.UtilitaireSession;
import utils.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class MatiereCtrl extends AbstractMatiereCtrl implements MatiereInterfaceCtrl, Serializable {

    public MatiereCtrl() {

    }

    @PostConstruct
    private void initTypeMatiere() {
        natureMatiere = new Naturematiere();
        matiere = new Matiere();
        selected = new Matiere();
    }

    @Override
    public void save() {
        if (natureMatiere.getIdnaturematiere() != null) {
            matiere.setIdnaturematiere(natureMatiere);
            List<Matiere> matiereTemp = matiereFacadeLocal.findByLibelle(natureMatiere.getIdnaturematiere(), matiere.getLibelle());
            if (matiereTemp.isEmpty()) {
                matiere.setIdmatiere(matiereFacadeLocal.nextVal());
                matiereFacadeLocal.create(matiere);
                Utilitaires.saveOperation(traceurFacadeLocal, "Enregistrement matière -> " + matiere.getLibelle(), UtilitaireSession.getInstance().getuser());
                initTypeMatiere();
                JsfUtil.addSuccessMessage("La matière a été enregistré");

            } else {
                JsfUtil.addErrorMessage("Une matière  portant ce nom existe déjà");
            }
        } else {
            JsfUtil.addErrorMessage("veuillez selectionnez une nature de matière");
        }
    }

    @Override
    public void edit() {

        if (selected != null) {
            matiereFacadeLocal.edit(selected);
            initTypeMatiere();
            JsfUtil.addSuccessMessage("Le type matiere  a été mise à jour !");
        } else {
            JsfUtil.addErrorMessage("Le type de matiere est invalide");
        }
    }

    @Override
    public void delete() {
        if (selected != null) {
            if (selected.getClassematiereList().isEmpty()) {
                if (selected.getPersonmatiereclasseanneedateList().isEmpty()) {

                    matiereFacadeLocal.remove(selected);
                    Utilitaires.saveOperation(traceurFacadeLocal, "Suppression de la matière -> " + selected.getLibelle(), UtilitaireSession.getInstance().getuser());
                    initTypeMatiere();
                    JsfUtil.addSuccessMessage("Opération réussie !");
                } else {
                    JsfUtil.addErrorMessage("impossible de supprimer");
                }
            } else {
                JsfUtil.addErrorMessage("impossible de supprimer");
            }
        } else {
            JsfUtil.addErrorMessage("Echec de l'opération !");
        }
    }

    @Override
    public void imprimerTypeMatierePdf() {

    }

    @Override
    public void imprimerTypeMatiereHtml() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
