/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Personnel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne gervais
 */
@Stateless
public class PersonnelFacade extends AbstractFacade<Personnel> implements PersonnelFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonnelFacade() {
        super(Personnel.class);
    }

    @Override
    public Integer nextVal() {
        Query query = em.createQuery("SELECT MAX(p.idpersonnel) FROM Personnel p");
        Integer result;
        result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public List<Personnel> findByEtat(boolean etat) {
        List<Personnel> results = null;
        Query query;
        try {
            query = em.createNamedQuery("Personnel.findByEtatpersonnel");
            query.setParameter("etatpersonnel", etat);
            results = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la recherche du personnel");
        }
        return results;
    }

    @Override
    public List<Personnel> findByMatricule(String matricule) {
        List<Personnel> results = null;
        Query query;
        try {
            query = em.createNamedQuery("Personnel.findByMatriculepersonnel");
            query.setParameter("matriculepersonnel", matricule);
            results = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la recherche du personnel");
        }
        return results;
    }

    @Override
    public Personnel fingByLoginPassword(String login, String password) {
        Personnel personnel = null;
        Query query;
        try {
            query = em.createQuery("SELECT p FROM Personnel p WHERE p.login=:login AND p.password=:password");
            query.setParameter("login", login);
            query.setParameter("password", password);
            personnel = (Personnel) query.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la recherche du personnel");
        }
        return personnel;
    }

}
