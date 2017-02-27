/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privilege.accesModule;

import entities.Module;
import entities.Personnel;
import entities.Personnelmodule;
import entities.Traceur;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.UtilitaireSession;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "accesModuleCtrl")
@ViewScoped
public class AccesModuleCtrl extends AbstractAccesModuleCtrl implements AccesModuleInterfaceCtrl, Serializable {

    @PostConstruct
    private void initAcces() {
        selectedPersonnelmodule = new Personnelmodule();
        module = new Personnelmodule();
        module1 = new Module();
        personnel = new Personnel();
        traceur = new Traceur();
    }

    @Override
    public void enregistrerAccesModule() {
        Personnel connectedUser = UtilitaireSession.getInstance().getuser();
        if (connectedUser != null) {

            if (personnel.getIdpersonnel() != null) {
                personnel = personnelFacade.find(personnel.getIdpersonnel());
                moduleTarget = dualModules.getTarget();
                if (!moduleTarget.isEmpty()) {
                    for (int i = 0; i < moduleTarget.size(); i++) {
                        module = new Personnelmodule();
                        traceur = new Traceur();

                        module.setModule(moduleTarget.get(i));
                        module.setPersonnel(personnel);

                        Personnelmodule result;
                        result = moduleFacadeLocal.findByPersonnelModule(personnel.getIdpersonnel(), moduleTarget.get(i).getId());

                        traceur.setAction("Attrubution du privilège " + moduleTarget.get(i).getNom() + " Au personnel " + personnel.getNom() + " par " + connectedUser.getNom());
                        traceur.setPersonnel(connectedUser);
                        traceur.setDateaction(new Date());

                        if (result == null) {
                            module.setEtat(true);
                            moduleFacadeLocal.create(module);
                            traceurFacade.create(traceur);
                        } else {
                            /*if(result.getEtat()){
                             initAcces();
                             JsfUtil.addSuccessMessage("le personnel selectionné a deja ce rivilège!");  
                             }
                             else{*/
                            traceur.setAction("Réactivation du privilège " + moduleTarget.get(i).getNom() + " Au personnel " + personnel.getNom());
                            result.setEtat(true);
                            moduleFacadeLocal.edit(result);
                            traceurFacade.create(traceur);
                            /*}*/
                        }
                    }
                    initAcces();
                    moduleTarget.clear();
                    dualModules.setTarget(moduleTarget);
                    JsfUtil.addSuccessMessage("operation réussie");
                } else {
                    JsfUtil.addErrorMessage("Aucun module selectioné !");
                }
            } else {
                JsfUtil.addErrorMessage("veuillez selectionnez le personnel");
            }
        } else {
            JsfUtil.addWarningMessage("Aucune session utilisateur initiée");
        }
    }

    @Override
    public void modifier() {
        Personnel connectedUser = UtilitaireSession.getInstance().getuser();
        if (connectedUser != null) {
            if (selectedPersonnelmodule.getPersonnel() != null) {
                if (selectedPersonnelmodule.getModule() != null) {
                    traceur = new Traceur();
                    traceur.setDateaction(new Date());
                    traceur.setPersonnel(connectedUser);
                    traceur.setAction("Modification du privilège " + selectedPersonnelmodule.getModule().getNom() + " du Personnel " + selectedPersonnelmodule.getPersonnel().getNom() + " par " + connectedUser.getNom());
                    moduleFacadeLocal.edit(selectedPersonnelmodule);
                    traceurFacade.create(traceur);
                    initAcces();
                    JsfUtil.addSuccessMessage("Opération réussie");
                }
            } else {
                JsfUtil.addErrorMessage("aucun personnel selectionné");
            }
        } else {
            JsfUtil.addWarningMessage("Aucune session utilisateur initiée");
        }
    }

    @Override
    public void supprimer() {
        if (selectedPersonnelmodule.getId() != null) {
            moduleFacadeLocal.remove(selectedPersonnelmodule);
            initAcces();
            JsfUtil.addSuccessMessage("operation réussie");
        } else {
            JsfUtil.addErrorMessage("aucune ligne selectionnée !");
        }
    }

    @Override
    public void imprimerAccesModulePdf() {

    }

    @Override
    public void imprimerAccesModuleHtml() {
        System.out.println("Impression html types compte");
    }

    //methode qui charge les privileges manquant d'un personnel selectionné
    public void handlePersonnelChange() {
        moduleSources.clear();
        moduleTarget.clear();
        if (personnel.getIdpersonnel() != null) {
            personnel = personnelFacade.find(personnel.getIdpersonnel());
            List<Personnelmodule> access = moduleFacadeLocal.get(personnel.getIdpersonnel(), true);

            if (!access.isEmpty()) {
                for (int c = 0; c < access.size(); c++) {
                    moduleTarget.add(access.get(c).getModule());
                }

                for (int i = 0; i < modules1.size(); i++) {
                    if (moduleTarget.contains(modules1.get(i))) {
                        moduleTarget.remove(modules1.get(i));
                    } else {
                        moduleSources.add(modules1.get(i));
                    }
                }
            } else {
                moduleSources.addAll(moduleFacade.getModules(true));
                System.err.println("L'utilisateur n ' a pas de privilege");
            }
        } else {
            moduleSources.clear();
        }
        dualModules.setSource(moduleSources);
        dualModules.setTarget(moduleTarget);
        System.out.println("" + personnel.getNom());
    }

}
