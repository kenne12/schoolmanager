/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.catAnneePrix;

import entities.Annee;
import entities.Catanneeprix;
import entities.Categorie;
import entities.Etablissement;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DualListModel;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.UtilitaireSession;
import utils.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@SessionScoped
public class CategorieAnneePrixCtrl extends AbstractCategorieAnneePrixCtrl implements CatAnneePrixInterfaceCtrl, Serializable {
    
    public CategorieAnneePrixCtrl() {
        
    }
    
    @PostConstruct
    private void initAffectation() {
        categorieAnneePrix = new Catanneeprix();
        selectedCategorieAnnePrix = new Catanneeprix();
        categorie = new Categorie();
        annee = new Annee();
        etablissement = new Etablissement();
        etablissements = Utilitaires.findSchoolByPersonnel(UtilitaireSession.getInstance().getuser());
        this.load();
    }
    
    public void prepareCreate() {
        etablissement = new Etablissement();
        categorieAnneePricesTest.clear();
        dualElements = new DualListModel<>();
        annee = new Annee();
        annees.clear();
        try {
            updateCategorie();
            updateCategorie1();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void save() {
        try {
            if (SessionMBean.getYear() != null) {
                if (!categorieAnneePricesTest.isEmpty()) {
                    for (Catanneeprix c : categorieAnneePricesTest) {
                        c.setId(catAnneePrixFacade.nextVal());
                        c.setIdannee(SessionMBean.getYear());
                        catAnneePrixFacade.create(c);
                    }
                    this.load();
                    JsfUtil.addSuccessMessage("Opération reussie");
                } else {
                    JsfUtil.addErrorMessage("Le tableau est vide");
                }
            } else {
                JsfUtil.addErrorMessage("Aucune année selectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void edit() {
        try {
            if (selectedCategorieAnnePrix != null) {
                catAnneePrixFacade.edit(selectedCategorieAnnePrix);
                this.load();
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addSuccessMessage("Opetation réussie");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void delete() {
        try {
            if (selectedCategorieAnnePrix != null) {
                catAnneePrixFacade.remove(selectedCategorieAnnePrix);
                this.load();
                JsfUtil.addSuccessMessage("Operation réussie !");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void updateCategorie() {
        try {
            categories.clear();
            annees.clear();
            if (SessionMBean.getSchool() != null) {
                categories = categorieFacade.findByEtablisssement(SessionMBean.getSchool().getId());
                annees = anneeFacade.findByEtablissement(SessionMBean.getSchool());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateCategorie1() {
        try {
            dualElements.getSource().clear();
            dualElements.getTarget().clear();
            if (SessionMBean.getYear() != null) {
                if (!categories.isEmpty()) {
                    for (Categorie c : categories) {
                        if (catAnneePrixFacade.findByAnneeCategorie(SessionMBean.getYear().getIdannee(), c.getIdcategorie()).isEmpty()) {
                            dualElements.getSource().add(c);
                        }
                    }
                }
                etablissement = SessionMBean.getSchool();
                categories = categorieFacade.findByEtablisssement(SessionMBean.getSchool().getId());
                annees = anneeFacade.findByEtablissement(SessionMBean.getSchool());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void transfertData() {
        try {
            if (annee != null) {
                if (!dualElements.getTarget().isEmpty()) {
                    for (Categorie c : dualElements.getTarget()) {
                        Catanneeprix temp = new Catanneeprix();
                        temp.setIdcategorie(c);
                        categorieAnneePricesTest.add(temp);
                    }
                } else {
                    JsfUtil.addErrorMessage("Le tableau de destination est vide");
                }
            } else {
                JsfUtil.addErrorMessage("Aucune année selectionnée");
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
    
    public void load() {
        try {
            if (SessionMBean.getYear() != null) {
                categorieAnneePrices = catAnneePrixFacade.findByAnnee(SessionMBean.getYear().getIdannee());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
