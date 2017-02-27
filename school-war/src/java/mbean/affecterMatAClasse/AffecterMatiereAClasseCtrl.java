/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.affecterMatAClasse;

import entities.Classe;
import entities.Classematiere;
import entities.Etablissement;
import entities.Matiere;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DualListModel;
import utils.JsfUtil;
import utils.UtilitaireSession;
import utils.Utilitaires;

/**
 *
 * @author gervais
 */
@ManagedBean
@SessionScoped
public class AffecterMatiereAClasseCtrl extends AbstractAffecterMatAClasseCtrl implements AffecterMatiereInterface {

    /**
     * Creates a new instance of AffecterMatiereAClasse
     */
    private void init() {
        classe = new Classe();
        dualMatiere = new DualListModel<>();
        etablissement = new Etablissement();
        classeMatiere = new Classematiere();
    }

    @PostConstruct
    private void initAffectation() {
        this.init();
        selectedClasseMatiere = new Classematiere();
        etablissements = Utilitaires.findSchoolByPersonnel(UtilitaireSession.getInstance().getuser());
        categories = classecategorieFacadeLocal.get(true);
    }

    public void prepareCreate() {
        this.init();
    }

    public void AffecterMatiereAClasseCtrl() {

    }

    @Override
    public void save() {
        try {
            if (classe != null) {
                if (!dualMatiere.getTarget().isEmpty()) {
                    for (Matiere m : dualMatiere.getTarget()) {
                        classeMatiere = new Classematiere();
                        classeMatiere.setId(classeMatiereFacade.nextVal());
                        classeMatiere.setIdclasse(classe);
                        classeMatiere.setIdmatiere(m);
                        classeMatiereFacade.create(classeMatiere);
                    }
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addWarningMessage("Aucune matiere selectionnée");
                }
            } else {
                JsfUtil.addErrorMessage("La liste de classe est vide !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit() {
        try {
            if (selectedClasseMatiere != null) {
                classeMatiereFacade.edit(selectedClasseMatiere);
                JsfUtil.addSuccessMessage("Opération réussie ");

            } else {
                JsfUtil.addErrorMessage("Aucune ligne sélectionnée !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete() {
        try {
            if (selectedClasseMatiere != null) {
                
                classeMatiereFacade.remove(selectedClasseMatiere);
                JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne sélectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    

    @Override
    public void imprimerAffectationPdf() {

    }

    @Override
    public void imprimerAffectationHtml() {

    }

    public void updateClasse() {
        try {
            if (etablissement != null) {
                categories = classecategorieFacadeLocal.get(etablissement.getId(), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleClasseChange() {
        try {
            dualMatiere.getSource().clear();
            dualMatiere.getTarget().clear();

            List<Classematiere> results = classeMatiereFacade.get(classe.getIdclasse());
            if (!results.isEmpty()) {
                List<Matiere> temp = matiereFacade.findAll();
                List<Matiere> temp1 = new ArrayList<>();

                for (Classematiere c : results) {
                    temp1.add(c.getIdmatiere());
                }

                for (Matiere m : temp) {
                    if (!temp1.contains(m)) {
                        dualMatiere.getSource().add(m);
                    }
                }

            } else {
                dualMatiere.getSource().addAll(matiereFacade.findAll());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
