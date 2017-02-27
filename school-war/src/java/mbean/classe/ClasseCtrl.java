/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.classe;

import entities.Categorie;
import entities.Classe;
import entities.Classecategorie;
import entities.Cycle;
import entities.Enseignement;
import entities.Personnel;
import entities.Traceur;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.UtilitaireSession;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@ViewScoped
public class ClasseCtrl extends AbstractClasseCtrl implements ClasseInterfaceCtrl, Serializable {

    public ClasseCtrl() {

    }

    @PostConstruct
    private void initClasse() {
        classe = new Classe();
        selectedClasse = new Classe();
        cycle = new Cycle();
        branche = new Enseignement();
        traceur = new Traceur();
        classecategorie = new Classecategorie();
        categorie = new Categorie();
    }

    public void prepareCreate() {
        try {
            listBranches = BrancheFacade.findByEtablissement(SessionMBean.getSchool().getId());
            categories = categorieFacadeLocal.findByEtablisssement(SessionMBean.getSchool().getId());
            classe = new Classe();
            cycle = new Cycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void prepareEdit() {
        try {
            listBranches = BrancheFacade.findByEtablissement(SessionMBean.getSchool().getId());
            categories = categorieFacadeLocal.findByEtablisssement(SessionMBean.getSchool().getId());
            if (selectedClasse != null) {
                branche = selectedClasse.getIdbranche();
                cycle = selectedClasse.getIdcycle();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void enregistrerClasse() {
        try {

            classe.setIdbranche(branche);
            classe.setIdcycle(cycle);
            classe.setEtat(true);
            if (classe != null) {
                Personnel user = UtilitaireSession.getInstance().getuser();
                if (user != null) {
                    traceur.setAction("enregistrement de la classe " + classe.getNom());
                    traceur.setPersonnel(user);
                    traceur.setDateaction(new Date());

                    if (categorie.getIdcategorie() != null) {
                        classe.setIdclasse(classeFacade.nextVal());
                        classecategorie.setIdclasse(classe);
                        classecategorie.setIdcategorie(categorie);
                        classe.setEtablissement(SessionMBean.getSchool());
                        classeFacade.create(classe);
                        classecategorieFacadeLocal.create(classecategorie);
                        traceurFacade.create(traceur);
                        initClasse();
                        JsfUtil.addSuccessMessage("La classe a été crée avec succès");
                    } else {
                        classe.setEtablissement(SessionMBean.getSchool());
                        classeFacade.create(classe);
                        traceurFacade.create(traceur);
                        initClasse();
                        JsfUtil.addSuccessMessage("La classe a été crée avec succès");
                    }
                } else {
                    JsfUtil.addWarningMessage("Aucune session utilisateur initiée");
                }
            } else {
                JsfUtil.addErrorMessage("la classe est non valide");
            }
            classes = classeFacade.findByEtaBlissement(SessionMBean.getSchool().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifier() {
        try {
            if (selectedClasse != null) {
                selectedClasse.setIdbranche(BrancheFacade.find(branche.getIdbranche()));
                selectedClasse.setIdcycle(cycle);
                classeFacade.edit(selectedClasse);
                initClasse();
                JsfUtil.addSuccessMessage("La classe a été mise à jour !");
            } else {
                JsfUtil.addErrorMessage("la classe est non valide");
            }
            classes = classeFacade.findByEtaBlissement(SessionMBean.getSchool().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimer() {
        try {
            if (selectedClasse != null) {
                classeFacade.remove(selectedClasse);
                initClasse();
                JsfUtil.addSuccessMessage("operation réussie !");
            } else {
                JsfUtil.addErrorMessage("la classe est non valide");
            }
            classes = classeFacade.findByEtaBlissement(SessionMBean.getSchool().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //methode qui filtre les brache , les categorie en fonction des etablissements selectionn
    public void updateBranche() {
        try {
            if (classe.getEtablissement() != null) {
                listBranches = classe.getEtablissement().getEnseignementList();
                categories = categorieFacadeLocal.findByEtablisssement(classe.getEtablissement().getId(), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void imprimerClassePdf() {

    }

    @Override
    public void imprimerClasseHtml() {

    }

}
