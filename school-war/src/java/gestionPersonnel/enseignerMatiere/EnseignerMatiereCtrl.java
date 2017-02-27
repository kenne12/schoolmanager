/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionPersonnel.enseignerMatiere;

import entities.Annee;
import entities.Classe;
import entities.Classematiere;
import entities.Etablissement;
import entities.EtablissementPersonnel;
import entities.Matiere;
import entities.Personnel;
import entities.Personnelmatiereclasseannee;
import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;
import utils.JsfUtil;
import utils.UtilitaireSession;
import utils.Utilitaires;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "enseignerMatiereCtrl")
@ViewScoped

public class EnseignerMatiereCtrl extends AbstractEnseignerMatiereCtrl implements EnseignerMatiereInterfaceCtrl, Serializable {

    private void init() {
        annee = new Annee();
        classe = new Classe();
        personnel = new Personnel();
        enseigneMatiere = new Personnelmatiereclasseannee();
        etablissement = new Etablissement();
        dualMatiere = new DualListModel<>();
        enseigneMatieres = Utilitaires.findByPersonnel(UtilitaireSession.getInstance().getuser());
    }

    @PostConstruct
    private void initProgramme() {
        this.init();
        etablissements = Utilitaires.findSchoolByPersonnel(UtilitaireSession.getInstance().getuser());
    }

    private void initEnseigne() {
        enseigneMatiere.setIdannee(annee);
        enseigneMatiere.setIdclasse(classe);
        enseigneMatiere.setPersonnel(personnel);
    }

    public void prepareCreate() {
        this.init();
    }

    @Override
    public void edit() {
        try {
            if(selectedEnseigneMatiere!=null){
                
            }else{
                JsfUtil.addErrorMessage("Aucune ligne sélectionsée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        try {
            if (selectedEnseigneMatiere != null) {
                ensignerMatiereFacade.remove(selectedEnseigneMatiere);
                initProgramme();
                JsfUtil.addSuccessMessage("Operation réussie");
            } else {
                JsfUtil.addErrorMessage("echec,aucune ligne selectionnée !");
            }
            enseigneMatieres = Utilitaires.findByPersonnel(UtilitaireSession.getInstance().getuser());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void imprimerEnseignerMatierePdf() {
        personnels = personnelFacadeLocal.findAll();
    }

    @Override
    public void imprimerEnseignerMatieretHml() {
        System.out.println("Impression html types compte");
    }

    @Override
    public void save() {
        try {
            if (annee.getIdannee() != null) {
                if (classe.getIdclasse() != null) {

                    if (personnel.getIdpersonnel() != null) {
                        for (Matiere m : dualMatiere.getTarget()) {
                            Personnelmatiereclasseannee result = ensignerMatiereFacade.findByMatClassePersonnelAnnee(m.getIdmatiere(), classe.getIdclasse(), personnel.getIdpersonnel(), annee.getIdannee());
                            if (result == null) {
                                enseigneMatiere.setId(ensignerMatiereFacade.nextVal());
                                enseigneMatiere.setEtat(true);
                                enseigneMatiere.setIdmatiere(m);
                                this.initEnseigne();
                                ensignerMatiereFacade.create(enseigneMatiere);
                            } else {
                                result.setEtat(true);
                                ensignerMatiereFacade.edit(result);
                            }
                        }
                        enseigneMatieres = Utilitaires.findByPersonnel(UtilitaireSession.getInstance().getuser());
                        JsfUtil.addSuccessMessage("Opération réussie");

                    } else {
                        JsfUtil.addErrorMessage("veuillez selectioner le personnel");
                    }

                } else {
                    JsfUtil.addErrorMessage("veuillez selectionner une classe !");
                }
            } else {
                JsfUtil.addErrorMessage("veuillez selectionner une année valide");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateAnneePersonnel() {
        try {
            annees.clear();
            personnels.clear();
            if (etablissement != null) {
                annees = etablissement.getAnneeList();
                List<EtablissementPersonnel> temp = etablissement.getEtablissementPersonnelList();
                if (!temp.isEmpty()) {
                    for (EtablissementPersonnel e : temp) {
                        personnels.add(e.getPersonnel());
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateMatiere() {
        try {
            dualMatiere = new DualListModel<>();
            if (classe != null) {
                classe = classeFacade.find(classe.getIdclasse());
                if (annee != null) {

                    List<Classematiere> temps = classe.getClassematiereList();
                    if (!temps.isEmpty()) {

                        List<Matiere> matiereAll = new ArrayList<>();
                        List<Matiere> matiereProgrammes = new ArrayList<>();

                        for (Classematiere c : temps) {
                            matiereAll.add(c.getIdmatiere());
                        }

                        List<Personnelmatiereclasseannee> classeMatierProgrammes = ensignerMatiereFacade.get(annee.getIdannee(), classe.getIdclasse(), true);
                        if (!classeMatierProgrammes.isEmpty()) {

                            for (Personnelmatiereclasseannee p : classeMatierProgrammes) {
                                matiereProgrammes.add(p.getIdmatiere());
                            }

                            for (Matiere m : matiereAll) {
                                if (!matiereProgrammes.contains(m)) {
                                    dualMatiere.getSource().add(m);
                                }
                            }

                        } else {
                            dualMatiere.getSource().addAll(matiereAll);
                        }
                    } else {
                        JsfUtil.addWarningMessage("Aucune matiere n'est definie pour cette classe");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        dualMatiere = new DualListModel<>();
    }

}
