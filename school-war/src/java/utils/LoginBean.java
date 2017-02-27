/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.CompteUtilisateur;
import entities.Module;
import entities.Personnel;
import entities.Privilege;
import entities.Traceur;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import session.PersonnelFacadeLocal;
import session.TraceurFacadeLocal;

/**
 *
 * @author romuald
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends AbstractLoginBean implements Serializable {

    /**
     * Creates a new instance of LoginBean
     */
    @EJB
    protected TraceurFacadeLocal traceurFacadeLocal;
    protected Traceur traceur;

    public Traceur getTraceur() {
        return traceur;
    }

    public void setTraceur(Traceur traceur) {
        this.traceur = traceur;
    }

    @EJB
    private PersonnelFacadeLocal personnelFacade;
    private Personnel personnel;
    private Personnel personnelConnected;

    String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

    @PostConstruct
    public void init() {
        personnel = new Personnel();
    }

    public LoginBean() {

    }

    public void login() {
        try {
            if (!compteUtiliasteurFacadeLocal.findByLoginPassword(compteUtilisateur.getLogin(), compteUtilisateur.getPassword()).isEmpty()) {
                compteUtilisateur = compteUtiliasteurFacadeLocal.findByLoginPassword(compteUtilisateur.getLogin(), compteUtilisateur.getPassword()).get(0);
                if (compteUtilisateur.getPersonnel().getEtatpersonnel()) {
                    if (compteUtilisateur.getEtat()) {
                        personnel = compteUtilisateur.getPersonnel();
                        UtilitaireSession.getInstance().setuser(personnel);

                        HttpSession session = SessionMBean.getSession();
                        session.setAttribute("compte", compteUtilisateur);

                        traceur = new Traceur();
                        Personnel personne = UtilitaireSession.getInstance().getuser();
                        String nomUtilisateur = personnel.getNom();
                        traceur.setAction("Connection du personnel : " + nomUtilisateur + "");
                        traceur.setDateaction(new Date());
                        traceur.setPersonnel(personne);
                        //traceurFacadeLocal.create(traceur);

                        etablissementPersonnels = etablissementPersonnelFacadeLocal.find(personnel);

                        setPriv();

                        this.initPrivile();
                        session.setAttribute("access", liens);
                        session.setAttribute("privilege", privileges);
                        session.setAttribute("allAccess", this.getLiensAll());

                        if (personnel.getPhoto().equals(null)) {
                            imagePersonnel = "avatar-mini.png";
                        } else {
                            File f = new File(Utilitaires.path + "/photos/photo_personnels/" + personnel.getPhoto());
                            if (f.exists()) {
                                imagePersonnel = "" + personnel.getPhoto();
                            } else {
                                imagePersonnel = "avatar-mini.png";
                            }
                        }

                        JsfUtil.addSuccessMessage("Bienvenue dans votre espace de travail");
                        System.out.println("++++++++++++++++  Uitlisateur connecté +++++++++++++++++++ ");
                        FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/index.html");

                    } else {
                        JsfUtil.addWarningMessage("Le compte est désactivé");
                    }
                } else {
                    JsfUtil.addErrorMessage("Personnel inactif");
                }
            } else {
                compteUtilisateur = new CompteUtilisateur();
                JsfUtil.addErrorMessage("Echec d'authentification, verifiez vos paramètres de connexion");
            }
        } catch (Exception ex) {
            System.err.println("pas de compte <erreur de recherche d: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    //methode qui permet de deconnecter un utilisateur
    public void deconnexion() {
        traceur = new Traceur();
        Personnel user = UtilitaireSession.getInstance().getuser();
        traceur.setAction("déconnxion du personnel : " + user.getNom() + " " + user.getPrenom());
        traceur.setDateaction(new Date());
        traceur.setPersonnel(user);
        traceurFacadeLocal.create(traceur);
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

            UtilitaireSession.getInstance().setuser(null);
            FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/login.html");

        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //methode qui met en veille un utilisateur
    public void hibbernate() {
        try {
            showHibernatePanel = true;
            hibernatePassword = "";
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void unHibbernate() {
        try {
            if (hibernatePassword.equals(SessionMBean.getUserAccount().getPassword())) {
                showHibernatePanel = false;
            } else {
                showHibernatePanel = true;
                JsfUtil.addErrorMessage("Mot de passe incorrect");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public Object getUser() {
        return personnel;
    }

    public void setUser(Object user) {
        this.personnel = (Personnel) user;
    }

    public Personnel getUserconnected() {
        personnelConnected = UtilitaireSession.getInstance().getuser();
        String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        if (personnelConnected == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/login.html");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Uitlisateur déconnecté +++++++++++++++++++ ");
        }
        return personnelConnected;
    }

    public void filterYear() {
        try {
            if (etablissementPersonnel.getId() != null) {
                etablissementPersonnel = etablissementPersonnelFacadeLocal.find(etablissementPersonnel.getId());
                annees = anneeFacadeLocal.findByEtablissement(etablissementPersonnel.getEtablissement());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initSession() {
        try {
            HttpSession session = SessionMBean.getSession();
            etablissementPersonnel = etablissementPersonnelFacadeLocal.find(etablissementPersonnel.getId());
            session.setAttribute("etablissement", etablissementPersonnel.getEtablissement());
            annee = anneeFacadeLocal.find(annee.getIdannee());
            session.setAttribute("annee", annee);

            nomEtablissement = etablissementPersonnel.getEtablissement().getNom().toUpperCase();
            anneeScolaire = annee.getCode().toString().toUpperCase() + " - " + annee.getCodefin().toString().toUpperCase();

            if (etablissementPersonnel.getEtablissement().getLogo().equals(null)) {
                logoEtablissement = "logo.png";
            } else {
                File f = new File(Utilitaires.path + "/photos/logo_batiments/" + etablissementPersonnel.getEtablissement().getLogo());
                if (f.exists()) {
                    logoEtablissement = "" + etablissementPersonnel.getEtablissement().getLogo();
                } else {
                    logoEtablissement = "logo.png";
                }
            }
            showSessionPanel = false;
            System.err.println("session initialisé");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public void initPrivile() throws Exception {
        privileges = privilegeFacadeLocal.find(compteUtilisateur);
        if (!privileges.isEmpty()) {
            for (Privilege p : privileges) {
                String[] chaines = p.getIdmenu().getRessource().split(" ");
                for (String s : chaines) {
                    if (!liens.contains(s)) {
                        liens.add(s);
                    }
                }
            }
        } else {
            System.err.println("Utilisateur sans privilege");
        }
    }

    public void setPriv() {
        watcher();
        List<Module> modules1 = moduleFacade.getModules(true);
        for (int i = 0; i < modules1.size(); i++) {

            if ("GP".equals(modules1.get(i).getCode())) {
                if (modules1.get(i).getEtat()) {
                    gestionPersonnelVisible = "visible";
                    gestionPersonnel = !personnelModuleFacade.findByPersonnelModule(personnel.getIdpersonnel(), modules1.get(i).getId(), true);
                } else {
                    setGestionPersonnel(!modules1.get(i).getEtat());
                }
            }

            if ("PS".equals(modules1.get(i).getCode())) {
                if (modules1.get(i).getEtat() != false) {
                    parametrageVisible = "visible";
                    parametrage = !personnelModuleFacade.findByPersonnelModule(personnel.getIdpersonnel(), modules1.get(i).getId(), true);
                } else {
                    setParametrage(!modules1.get(i).getEtat());
                }
            }

            if ("EC".equals(modules1.get(i).getCode())) {
                if (modules1.get(i).getEtat()) {
                    gestionInscriptionVisible = "visible";
                    gestionInscription = !personnelModuleFacade.findByPersonnelModule(personnel.getIdpersonnel(), modules1.get(i).getId(), true);
                } else {
                    setGestionInscription(!modules1.get(i).getEtat());
                }
            }

            if ("GN".equals(modules1.get(i).getCode())) {
                if (modules1.get(i).getEtat()) {
                    gestionNoteVisible = "visible";
                    gestionNote = !personnelModuleFacade.findByPersonnelModule(personnel.getIdpersonnel(), modules1.get(i).getId(), true);
                } else {
                    setGestionNote(!modules1.get(i).getEtat());
                }
            }

            if ("GPri".equals(modules1.get(i).getCode())) {
                if (modules1.get(i).getEtat()) {
                    gestionPrivilegeVisible = "visible";
                    gestionPrivilege = !personnelModuleFacade.findByPersonnelModule(personnel.getIdpersonnel(), modules1.get(i).getId(), true);
                } else {
                    setGestionPrivilege(!modules1.get(i).getEtat());
                }
            }

            if ("GB".equals(modules1.get(i).getCode())) {
                if (modules1.get(i).getEtat()) {
                    bibliothequeVisible = "visible";
                    bibliotheque = !personnelModuleFacade.findByPersonnelModule(personnel.getIdpersonnel(), modules1.get(i).getId(), true);
                } else {
                    setBibliotheque(!modules1.get(i).getEtat());
                }
            }

            if ("GD".equals(modules1.get(i).getCode())) {
                if (modules1.get(i).getEtat()) {
                    gestionDisciplineVisible = "visible";
                    gestionDiscipline = !personnelModuleFacade.findByPersonnelModule(personnel.getIdpersonnel(), modules1.get(i).getId(), true);
                } else {
                    setGestionDiscipline(!modules1.get(i).getEtat());
                }
            }

            if ("GE".equals(modules1.get(i).getCode())) {
                if (modules1.get(i).getEtat()) {
                    gestionEtatVisible = "visible";
                    gestionEtat = !personnelModuleFacade.findByPersonnelModule(personnel.getIdpersonnel(), modules1.get(i).getId(), true);
                } else {
                    setGestionEtat(!modules1.get(i).getEtat());
                }
            }
        }
    }

    public static void watcher() {
        try {
            if (!FacesContext.getCurrentInstance().getExternalContext().getSessionMap().containsKey("user")) {
                String sc = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
                FacesContext.getCurrentInstance().getExternalContext().redirect(sc + "/login.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
