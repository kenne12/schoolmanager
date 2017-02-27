package mbean.tranche;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import converter.util.JsfUtil;
import entities.Annee;
import entities.Catanneeprix;
import entities.Categorie;
import entities.Etablissement;
import entities.Tranche;
import entities.Typetranche;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import utils.SessionMBean;
import utils.UtilitaireSession;
import utils.Utilitaires;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@SessionScoped
public class TrancheCtrl extends AbstractTrancheCtrl implements TrancheInterfaceCtrl, Serializable {

    @PostConstruct
    private void initTypeTranche() {
        etablissements = Utilitaires.findSchoolByPersonnel(UtilitaireSession.getInstance().getuser());
        categorie = new Categorie();
        tranche = new Tranche();
        selectedTranche = new Tranche();
        annee = new Annee();
        etablissement = new Etablissement();
        this.load();
    }

    private void create() {
        tranche.setIdcategorie(categorie);
        tranche.setAnnee(annee);
        trancheFacade.create(tranche);
    }

    public void prepareCreate() {
        initTypeTranche();
        typetranches.clear();
        showDialog = true;
        updateCategorie();
    }

    public void prepareEdit() {
        try {
            if (selectedTranche != null) {
                categories = categorieFacade.findByEtablisssement(selectedTranche.getIdcategorie().getEtablissement().getId(), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDialog() {
        showDialog = false;
    }

    /*debut de la methode qui enregistre une tranche de pension aucours d'une année****/
    @Override
    public void save() {
        try {

            if (!tranchesTest.isEmpty()) {
                List<Tranche> temp = new ArrayList<>();
                for (Tranche t : tranchesTest) {
                    if (t.getDatefin().after(t.getDatedebut())) {
                        t.setIdtranche(trancheFacade.nextVal());
                        trancheFacade.create(t);
                        Utilitaires.saveOperation(traceurFacadeLocal, "Création de la tranche de paiement -> " + t.getNom() + " Catégorie -> " + t.getIdcategorie().getNom() + " Montant -> " + t.getPrix(), UtilitaireSession.getInstance().getuser());
                    } else {
                        temp.add(t);
                    }
                }
                if (temp.isEmpty()) {
                    JsfUtil.addSuccessMessage("Opération réussie");
                    showDialog = false;
                } else {
                    showDialog = true;
                    JsfUtil.addSuccessMessage("Certains enregistrement n'ont pas ete sauvegarder pour motif de date incorrecte !");

                }
                tranchesTest = temp;
                this.load();
            } else {
                JsfUtil.addErrorMessage("Le tableau est vide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*fin de la methode qui enregistre une tranche de pension aucours d'une année****/

    public String reinit() {
        tranche = new Tranche();
        return null;
    }

    public void addTypeTranche() {
        try {

            if (!selectedTypetranches.isEmpty()) {
                for (Typetranche object : selectedTypetranches) {
                    if (tranchesTest.isEmpty()) {
                        Tranche t2 = new Tranche();
                        t2.setAnnee(SessionMBean.getYear());
                        t2.setIdcategorie(tranche.getIdcategorie());
                        t2.setNom(object.getNom());
                        t2.setEtat(true);
                        t2.setIdtypetranche(object);
                        tranchesTest.add(t2);
                        t2.setPrix(object.getMontantdefault());
                    }

                    boolean drapeau = false;
                    for (Tranche t1 : tranchesTest) {
                        if (t1.getIdtypetranche().equals(object)) {
                            drapeau = true;
                            break;
                        }
                    }

                    if (!drapeau) {

                        int tailleInitial = 0;
                        List<Catanneeprix> results = catAnneePrixFacade.findByAnneeCategorie(SessionMBean.getYear().getIdannee(), tranche.getIdcategorie().getIdcategorie());
                        if (!results.isEmpty()) {
                            tailleInitial = results.get(0).getNombretranche();
                        }

                        int tailleContenu = 0;
                        List<Tranche> temp = trancheFacade.getByAnneeCategorie(SessionMBean.getYear().getIdannee(), tranche.getIdcategorie().getIdcategorie(), true);

                        if (!temp.isEmpty()) {
                            tailleContenu = temp.size();
                        }

                        if (!tranchesTest.isEmpty()) {
                            int conteur = 0;
                            for (Tranche t1 : tranchesTest) {
                                if (t1.getIdcategorie().getIdcategorie().equals(tranche.getIdcategorie().getIdcategorie())) {
                                    conteur += 1;
                                }
                            }
                            tailleContenu += conteur;
                        }

                        if (tailleContenu < tailleInitial) {

                            Tranche t2 = new Tranche();
                            t2.setAnnee(SessionMBean.getYear());
                            t2.setIdcategorie(tranche.getIdcategorie());
                            t2.setNom(object.getNom());
                            t2.setEtat(true);
                            t2.setIdtypetranche(object);
                            t2.setPrix(object.getMontantdefault());
                            tranchesTest.add(t2);

                            JsfUtil.addSuccessMessage("Ajouté avec succès");
                        } else {
                            JsfUtil.addErrorMessage("Vous avez atteind la limite des tranche à enregistrer pour cette categorie");
                        }
                    }
                }
            } else {
                JsfUtil.addErrorMessage("Aucun élement selectionné");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit() {
        try {
            if (selectedTranche != null) {
                if (selectedTranche.getDatefin().after(selectedTranche.getDatedebut())) {

                    trancheFacade.edit(selectedTranche);
                    this.load();
                    JsfUtil.addSuccessMessage("Opération de mise à jour  !");

                } else {
                    JsfUtil.addErrorMessage("les dates sont incorrectes");
                }
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        try {
            if (selectedTranche != null) {

                if (selectedTranche.getPensionList().isEmpty()) {
                    trancheFacade.remove(selectedTranche);
                    this.load();
                    Utilitaires.saveOperation(traceurFacadeLocal, "Supression de la tranche de paiement -> " + selectedTranche.getNom() + " Catégorie -> " + selectedTranche.getIdcategorie().getNom() + " Montant -> " + selectedTranche.getPrix(), UtilitaireSession.getInstance().getuser());
                    JsfUtil.addSuccessMessage("Operation réussie !");
                } else {
                    JsfUtil.addErrorMessage("cette categorie porte des données et ne peut etre supprimé");
                }
            } else {
                JsfUtil.addErrorMessage("Aucune ligne selectionnée");
            }
        } catch (Exception e) {
        }
    }

    public void updateAnnee() {
        try {
            categories.clear();
            annees.clear();
            if (etablissement.getId() != null) {
                etablissement = etablissementFacadeLocal.find(etablissement.getId());
                categories = etablissement.getCategorieList();
                annees = anneeFacadeLocal.findByEtat(etablissement.getId(), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateCategorie() {
        try {
            categories.clear();
            if (SessionMBean.getYear() != null) {
                if (!catAnneePrixFacade.findByAnnee(SessionMBean.getYear().getIdannee()).isEmpty()) {

                    for (Catanneeprix c : catAnneePrixFacade.findByAnnee(SessionMBean.getYear().getIdannee())) {
                        List<Tranche> results = trancheFacade.getByAnneeCategorie(c.getIdannee().getIdannee(), c.getIdcategorie().getIdcategorie(), true);
                        if (results.isEmpty() | results.size() < c.getNombretranche()) {
                            categories.add(c.getIdcategorie());
                        }
                    }
                } else {
                    categories = categorieFacade.findByEtablisssement(SessionMBean.getSchool().getId(), true);
                }
            } else {
                JsfUtil.addErrorMessage(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTypeTranche() {
        try {
            if (tranche.getIdcategorie() != null) {

                if (trancheFacade.findByCategorieAnnee(tranche.getIdcategorie(), SessionMBean.getYear()).isEmpty()) {
                    typetranches = typetrancheFacadeLocal.findAll();
                } else {

                    List<Typetranche> typetranches1 = typetrancheFacadeLocal.findAll();
                    List<Typetranche> typetranches2 = new ArrayList<>();

                    typetranches.clear();
                    for (Tranche t : trancheFacade.findByCategorieAnnee(tranche.getIdcategorie(), SessionMBean.getYear())) {
                        typetranches2.add(t.getIdtypetranche());
                    }

                    for (Typetranche t1 : typetranches1) {
                        if (!typetranches2.contains(t1)) {
                            typetranches.add(t1);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void imprimerTypeTranchePdf() {

    }

    @Override
    public void imprimerTypeTrancheHtml() {

    }

    public void load() {
        try {
            if (SessionMBean.getYear() != null) {
                tranches = trancheFacade.findByAnnee(SessionMBean.getYear().getIdannee());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeTranche(Tranche tranche) {
        try {
            List<Tranche> temp = tranchesTest;
            int i = 0;
            for (Tranche t : temp) {

                if (t.getIdtypetranche().getIdtypetranche().equals(t.getIdtypetranche().getIdtypetranche())) {
                    temp.remove(i);
                    break;
                }
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
