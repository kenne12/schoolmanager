/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.CompteUtilisateur;
import entities.Etablissement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gervais
 */
@Stateless
public class CompteUtiliasteurFacade extends AbstractFacade<CompteUtilisateur> implements CompteUtiliasteurFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteUtiliasteurFacade() {
        super(CompteUtilisateur.class);
    }

    @Override
    public Integer nextVal() {
        Query query = em.createQuery("SELECT MAX(c.idcompte) FROM CompteUtilisateur c");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public List<CompteUtilisateur> findByLoginPassword(String login, String password) {
        List<CompteUtilisateur> compteUtiliasteurs = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT c FROM CompteUtilisateur c WHERE c.login=:login AND c.password=:password");
            query.setParameter("login", login).setParameter("password", password);
            compteUtiliasteurs = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compteUtiliasteurs;
    }

    @Override
    public List<CompteUtilisateur> find(Etablissement etablissement, boolean etat) throws Exception {
        List<CompteUtilisateur> compteUtiliasteurs = null;
        Query query = em.createQuery("SELECT c FROM CompteUtilisateur c WHERE c.etablissement.id=:etablissement AND c.etat=:etat");
        query.setParameter("etablissement", etablissement.getId()).setParameter("etat", etat);
        compteUtiliasteurs = query.getResultList();
        return compteUtiliasteurs;
    }

    @Override
    public List<CompteUtilisateur> findByAdmin(Etablissement etablissement, boolean etat) throws Exception {
        List<CompteUtilisateur> compteUtiliasteurs = null;
        Query query = em.createQuery("SELECT c FROM CompteUtilisateur c WHERE c.etablissement.id=:etablissement AND c.principale=:etat");
        query.setParameter("etablissement", etablissement.getId()).setParameter("etat", etat);
        compteUtiliasteurs = query.getResultList();
        return compteUtiliasteurs;
    }

}
