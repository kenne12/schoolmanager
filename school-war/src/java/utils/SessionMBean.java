/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Annee;

import entities.CompteUtilisateur;
import entities.Etablissement;
import entities.Privilege;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionMBean {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("login").toString();
    }

    public static String getUserId() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("utilisateurid");
        } else {
            return null;
        }
    }

    public static CompteUtilisateur getUserAccount() {
        HttpSession session = getSession();
        if (session != null) {
            return (CompteUtilisateur) session.getAttribute("compte");
        } else {
            return null;
        }
    }

    public static Annee getYear() {
        HttpSession session = getSession();
        if (session != null) {
            return (Annee) session.getAttribute("annee");
        } else {
            return null;
        }
    }

    public static Etablissement getSchool() {
        HttpSession session = getSession();
        if (session != null) {
            return (Etablissement) session.getAttribute("etablissement");
        } else {
            return null;
        }
    }

    public static List<String> getAccess() {
        HttpSession session = getSession();
        if (session != null) {
            return (List<String>) session.getAttribute("access");
        } else {
            return null;
        }
    }

    public static List<String> getAllAccess() {
        HttpSession session = getSession();
        if (session != null) {
            return (List<String>) session.getAttribute("allAccess");
        } else {
            return null;
        }
    }

    public static List<Privilege> getPrivilege() {
        HttpSession session = getSession();
        if (session != null) {
            return (List<Privilege>) session.getAttribute("privilege");
        } else {
            return null;
        }
    }

}
