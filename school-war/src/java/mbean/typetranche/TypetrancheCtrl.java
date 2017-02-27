/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.typetranche;

import entities.Personnel;
import entities.Traceur;
import entities.Typetranche;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import utils.JsfUtil;
import utils.SessionMBean;
import utils.UtilitaireSession;

/**
 *
 * @author Gervais
 */
@ManagedBean(name = "typetrancheCtrl")
@ViewScoped
public class TypetrancheCtrl extends AbstractTypetrancheCtrl implements TypetrancheInterfaceCtrl, Serializable {
    
    @PostConstruct
    private void initTypetranche() {
        selectedTypetranche = new Typetranche();
        typetranche = new Typetranche();
        traceur = new Traceur();
    }
    
    public void prepareCreate() {
        typetranche = new Typetranche();
        typetranche.setMontantdefault(0);
    }
    
    @Override
    public void enregistrerTypetranche() {
        try {
            Personnel user = UtilitaireSession.getInstance().getuser();
            
            List<Typetranche> temp = typetrancheFacadeLocal.find(SessionMBean.getSchool(), typetranche.getNom());
            if (temp.isEmpty()) {
                if (user != null) {
                    traceur = new Traceur();
                    //traceur.setId(traceurFacadeLocal.);
                    traceur.setAction("Création du typetranche : " + typetranche.getNom());
                    traceur.setDateaction(new Date());
                    traceur.setPersonnel(SessionMBean.getUserAccount().getPersonnel());
                    typetranche.setIdtypetranche(typetrancheFacadeLocal.nextVal());
                    typetranche.setIdetablissement(SessionMBean.getSchool());
                    typetrancheFacadeLocal.create(typetranche);
                    traceurFacadeLocal.create(traceur);
                    initTypetranche();
                    JsfUtil.addSuccessMessage("Le typetranche a été enregistré");
                } else {
                    initTypetranche();
                    JsfUtil.addWarningMessage("Aucune session utilisateur initiée !");
                }
            } else {
                JsfUtil.addWarningMessage("Un typetranche ayant ce nom existe dejè");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void modifier() {
        Personnel user = UtilitaireSession.getInstance().getuser();
        traceur = new Traceur();
        if (selectedTypetranche == null || selectedTypetranche.getIdtypetranche() == null) {
            JsfUtil.addErrorMessage("veuillez selectionner un typetranche");
            initTypetranche();
        } else {
            
            traceur.setAction("Modification du typetranche : " + selectedTypetranche.getNom());
            traceur.setPersonnel(user);
            traceur.setDateaction(new Date());
            typetrancheFacadeLocal.edit(selectedTypetranche);
            traceurFacadeLocal.create(traceur);
            initTypetranche();
            JsfUtil.addSuccessMessage("Le typetranche a été mis à jour");
            
        }
    }
    
    @Override
    public void supprimer() {
        Personnel user = UtilitaireSession.getInstance().getuser();
        if (selectedTypetranche == null || selectedTypetranche.getIdtypetranche() == null) {
            JsfUtil.addSuccessMessage("Veuillez selectionner un typetranche !");
        } else {
            if (selectedTypetranche.getTrancheList().isEmpty()) {
                if (user != null) {
                    traceur.setDateaction(new Date());
                    traceur.setPersonnel(null);
                    traceur.setAction("Suppression du typetranche" + selectedTypetranche.getNom());
                    typetrancheFacadeLocal.remove(selectedTypetranche);
                    traceurFacadeLocal.create(traceur);
                    initTypetranche();
                    JsfUtil.addSuccessMessage("opération réussie");
                } else {
                    JsfUtil.addWarningMessage("Aucune session utilisateur initiée !");
                    initTypetranche();
                }
            } else {
                initTypetranche();
                JsfUtil.addWarningMessage("Impossible de supprimer ce type de tranche est relié à plusieurs tranche de paiement");
            }
        }
    }
    
    @Override
    public void imprimerTypetranchePdf() {
        System.out.println("Impression pdf types compte");
        typetranches = typetrancheFacadeLocal.findAll();
    }
    
    @Override
    public void imprimerTypetrancheHtml() {
        System.out.println("Impression html types compte");
    }
    
}
