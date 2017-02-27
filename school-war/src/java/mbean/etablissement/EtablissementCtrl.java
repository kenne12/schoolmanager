/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.etablissement;

import entities.Adresse;
import entities.Annee;
import entities.Batiment;
import entities.Categorie;
import entities.Classe;
import entities.CompteUtilisateur;
import entities.Enseignement;
import entities.Etablissement;
import entities.EtablissementPersonnel;
import entities.Personnel;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import utils.JsfUtil;
import utils.SessionMBean;

/**
 *
 * @author kenne
 */
@ManagedBean
@SessionScoped
public class EtablissementCtrl extends AbstractEtablissementCtrl implements EtablissementInterfaceCtrl, Serializable {

    /**
     * Creates a new instance of EtablissementCtrl
     */
    public EtablissementCtrl() {
        
    }
    
    public void init() {
        etablissement = new Etablissement();
        batiment = new Batiment();
        classe = new Classe();
        personnel = new Personnel();
        compteUtilisateur = new CompteUtilisateur();
        etablissementPersonnel = new EtablissementPersonnel();
        personnel = new Personnel();
        adresse2 = new Adresse();
        annee = new Annee();
    }
    
    public void prepareCreate() {
        mode = "Create";
        System.err.println("prepare create apellé");
        this.init();
        adresse2 = new Adresse();
        try {
            fonctions = fonctionFacadeLocal.findByEtat(SessionMBean.getSchool(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void prepareCreateCategorie() {
        this.prepareView();
        categorie = new Categorie();
    }
    
    public void prepareCreateBatiment() {
        this.prepareView();
        batiment = new Batiment();
    }
    
    public void prepareCreateClasse() {
        try {
            if (etablissement != null) {
                this.prepareView();
                enseignements = etablissement.getEnseignementList();
                classe = new Classe();
            } else {
                JsfUtil.addErrorMessage("Aucun établissement sélectionné !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void prepareCreateBranche() {
        this.prepareView();
        branche = new Enseignement();
    }
    
    public void prepareEdit() {
        mode = "Edit";
        try {
            if (etablissement != null) {
                
                fonctions = fonctionFacadeLocal.findByEtat(SessionMBean.getSchool(), true);
                
                adresse2 = etablissement.getAdresse();
                List<CompteUtilisateur> temps = compteUtiliasteurFacadeLocal.findByAdmin(etablissement, true);
                if (!temps.isEmpty()) {
                    compteUtilisateur = temps.get(0);
                    personnel = compteUtilisateur.getPersonnel();
                    
                    if (personnel.getFonction() != null) {
                        fonction = personnel.getFonction();
                    }
                }
                
                List<Annee> temps1 = anneeFacadeLocal.findDefault(etablissement, true);
                if (!temps1.isEmpty()) {
                    annee = temps1.get(0);
                }
                
                if (etablissement.getType() != null) {
                    typeEtablissement = etablissement.getType();
                }
                
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void prepareView() {
        try {
            if (etablissement != null) {
                classes = etablissement.getClasseList();
                etablissementPersonnels = etablissement.getEtablissementPersonnelList();
                batiments = etablissement.getBatimentList();
                categories = etablissement.getCategorieList();
                //categories = categorieFacadeLocal.findByEtablisssement(selectedEtablissement.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public void enregistrerEtablissement() {
        try {
            
            if ("Create".equals(mode)) {
                
                Boolean drapeau = true;
                String msg = "";
                
                if (typeEtablissement.getId() != null) {
                    typeEtablissement = typeEtablissementFacadeLocal.find(typeEtablissement.getId());
                    etablissement.setType(typeEtablissement);
                }
                
                if (fonction.getIdfonction() != null) {
                    fonction = fonctionFacadeLocal.find(fonction.getIdfonction());
                    personnel.setFonction(fonction);
                }
                
                adresse2.setId(adresseFacadeLocal.nextId());
                adresseFacadeLocal.create(adresse2);
                etablissement.setId(etablissementFacadeLocal.nextId());
                etablissement.setAdresse(adresse2);
                etablissementFacadeLocal.create(etablissement);
                
                personnel.setIdpersonnel(personnelFacadeLocal.nextVal());
                personnel.setEtatpersonnel(true);
                personnel.setAdmin(true);
                personnel.setTheme("hot-sneaks");
                personnelFacadeLocal.create(personnel);
                
                etablissementPersonnel.setId(etablissementPersonnelFacadeLocal.nextVal());
                etablissementPersonnel.setEtablissement(etablissement);
                etablissementPersonnel.setPersonnel(personnel);
                etablissementPersonnelFacadeLocal.create(etablissementPersonnel);
                
                annee.setIdannee(anneeFacadeLocal.nextVal());
                annee.setEtablissement(etablissement);
                annee.setPrincipal(true);
                anneeFacadeLocal.create(annee);
                
                compteUtilisateur.setIdcompte(compteUtiliasteurFacadeLocal.nextVal());
                compteUtilisateur.setPersonnel(personnel);
                compteUtilisateur.setEtablissement(etablissement);
                compteUtilisateur.setPrincipale(true);
                compteUtilisateur.setEtat(true);
                compteUtiliasteurFacadeLocal.create(compteUtilisateur);
                
                init();
                JsfUtil.addSuccessMessage("Etalissement créé avec succès !");
            } else {
                if (etablissement != null) {
                    etablissementFacadeLocal.edit(etablissement);
                    if (adresse2 != null) {
                        adresseFacadeLocal.edit(adresse2);
                    }
                    
                    if (compteUtilisateur != null) {
                        compteUtiliasteurFacadeLocal.edit(compteUtilisateur);
                    }
                    
                    if (personnel != null) {
                        personnelFacadeLocal.edit(personnel);
                    }
                    
                    if (annee != null) {
                        anneeFacadeLocal.edit(annee);
                    }
                    
                    JsfUtil.addSuccessMessage("Opération réussie");
                } else {
                    JsfUtil.addErrorMessage("Aucune ligne selectionnée");
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void saveCategorie() {
        try {
            categorie.setEtablissement(etablissement);
            categorie.setEtat(true);
            categorieFacadeLocal.create(categorie);
            JsfUtil.addSuccessMessage("Opération réussie");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void saveBatiment() {
        try {
            if (etablissement != null) {
                batiment.setEtablissement(etablissement);
                batimentFacadeLocal.create(batiment);
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Aucun etablissement sélectionné");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void saveClasse() {
        try {
            if (etablissement != null) {
                classe.setEtablissement(etablissement);
                classe.setEtat(true);
                classeFacadeLocal.create(classe);
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Aucun etablissement sélectionné");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void saveBranche() {
        try {
            if (etablissement != null) {
                branche.setEtablissement(etablissement);
                brancheFacadeLocal.create(branche);
                JsfUtil.addSuccessMessage("Opération réussie");
            } else {
                JsfUtil.addErrorMessage("Aucun etablissement sélectionné");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void modifier() {
        try {
            if (etablissement != null) {
                etablissementFacadeLocal.edit(etablissement);
                init();
                JsfUtil.addSuccessMessage("Etablissement mis à jour !");
            } else {
                JsfUtil.addErrorMessage("Aucune ligne sélectionnée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void supprimer() {
        try {
            if (etablissement != null) {
                if (etablissement.getBatimentList().isEmpty()) {
                    if (etablissement.getAnneeList().isEmpty()) {
                        if (etablissement.getCategorieList().isEmpty()) {
                            if (etablissement.getClasseList().isEmpty()) {
                                if (etablissement.getEleveList().isEmpty()) {
                                    if (etablissement.getEnseignementList().isEmpty()) {
                                        if (etablissement.getLivreList().isEmpty()) {
                                            if (etablissement.getCompteUtilisateurList().isEmpty()) {
                                                adresseFacadeLocal.remove(etablissement.getAdresse());
                                                etablissementFacadeLocal.remove(etablissement);
                                                JsfUtil.addSuccessMessage("Opération réussie");
                                            }
                                        } else {
                                            utils.JsfUtil.addErrorMessage("Echec de l'operation");
                                        }
                                    } else {
                                        JsfUtil.addErrorMessage("Echec de l'operation");
                                    }
                                } else {
                                    JsfUtil.addErrorMessage("Cet etablissement a plusieurs élèves et ne peut etre supprimé");
                                }
                            } else {
                                JsfUtil.addErrorMessage("Cet etablissement a plusieurs classe et ne peut etre supprimé");
                            }
                        } else {
                            JsfUtil.addErrorMessage("Echec de l'opération");
                        }
                    }
                } else {
                    JsfUtil.addErrorMessage("Cet etablissement a plusieurs batiments et ne peut etre supprimé");
                }
            } else {
                JsfUtil.addErrorMessage("Echec de l'operation : aucun etablissement selectionné !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
