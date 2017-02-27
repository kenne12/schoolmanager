/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Personnel;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author pogo
 */
@ManagedBean
@SessionScoped
public class UtilitaireSession {

    /**
     * Creates a new instance of UtilitaireSession
     */
    /**
     * L'instance de Session
     */
    private static final UtilitaireSession instance = new UtilitaireSession();
    /**
     * Identifiant de la variable nom dans la session
     */
    private final String user = "user";
    //  private final String lisgroupe = "lisgroupe";
    //  private final String role = "role";

    /**
     * Constructeur prive du singleton
     */
    private UtilitaireSession() {
        super();
    }

    /**
     * Fonction permettant de recuperer l'instance de Session
     *
     * @return l'instance de Session
     */
    public static UtilitaireSession getInstance() {
        return instance;
    }

    /**
     * Fonction permettant de recuperer l'instance de Session
     *
     * @return l'instance de Session
     */
    public void destroy() {
        //     FacesContext fc = FacesContext.getCurrentInstance();
        //     fc.getExternalContext().invalidateSession();
        FacesContext fc = FacesContext.getCurrentInstance();
        getSession(fc).invalidate();
    }

    /**
     * Fonction qui vérifie le contexte
     *
     * @return vrai si le contexte permet de recuperer une session
     */
    private boolean isContextOk(FacesContext fc) {
        boolean res = (fc != null
                && fc.getExternalContext() != null
                && fc.getExternalContext().getSession(false) != null);
        return res;
    }

    /**
     * Fonction qui recupere une session à partir du faces context
     */
    private HttpSession getSession(FacesContext fc) {
        return (HttpSession) fc.getExternalContext().getSession(false);
    }

    /**
     * Fonction qui permet de recuperer un objet dans la session
     *
     * @param cle La cle de l'objet a recuperer
     * @return l'objet correspondant
     */
    public Object get(String cle) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Object res = null;
        if (isContextOk(fc)) {
            res = getSession(fc).getAttribute(cle);
        }
        return res;
    }

    /**
     * Procedure qui permet d'enregistrer une variable dans la session
     *
     * @param cle La cle qui permet d'identifier la varaible dans la session
     * @param valeur La valeur a enregistrer
     */
    public void set(String cle, Object valeur) {
        FacesContext fc = FacesContext.getCurrentInstance();
        if (fc != null && fc.getExternalContext() != null) {
            getSession(fc).setAttribute(cle, valeur);
        }
    }

    /**
     * Procedure qui permet d'enregistrer l'utilisateur dans la session
     *
     * @param us Le nom a enregistrer
     */
    public void setuser(Personnel us) {
        set(user, us);
    }

    /**
     * Fonction qui permet de récupérer le nom dans la session
     *
     * @return le nom a recuperer
     */
    public Personnel getuser() {
        return ((Personnel) get(user));
    }

}
