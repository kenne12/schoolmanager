/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Catanneeprix;
import java.util.ArrayList;
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
public class CatanneeprixFacade extends AbstractFacade<Catanneeprix> implements CatanneeprixFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CatanneeprixFacade() {
        super(Catanneeprix.class);
    }

    @Override
    public Integer nextVal() {
        Query query = em.createQuery("SELECT  MAX(c.id) FROM Catanneeprix c");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public List<Catanneeprix> findByAnneeCategorie(int annee, int categorie) {
        List<Catanneeprix> results = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT c FROM Catanneeprix c WHERE c.idannee.idannee=:annee AND c.idcategorie.idcategorie=:categorie");
            query.setParameter("annee", annee).setParameter("categorie", categorie);
            results = query.getResultList();
            if (results.isEmpty()) {
                results = new ArrayList<>();
            }
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche");
            e.getMessage();
        }

        return results;
    }

    @Override
    public Catanneeprix typeTrangeGetCatAnnee(int annee, int categorie, boolean etat) {
        Catanneeprix result = null;
        try {
            Query query = em.createQuery("SELECT C FROM Catanneeprix C WHERE C.idannee.idannee =:annee AND C.idcategorie.idcategorie =:categorie AND C.idannee.etatannee=:etat");
            query.setParameter("annee", annee).setParameter("categorie", categorie).setParameter("etat", true);
            if (!query.getResultList().isEmpty()) {
                result = (Catanneeprix) query.getResultList().get(query.getResultList().size() - 1);
            }
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche");
            e.getMessage();
        }
        return result;
    }

    @Override
    public List<Catanneeprix> findByAnnee(int annee) {
        List<Catanneeprix> catanneeprixs = null;
        try {
            Query query = em.createQuery("SELECT c FROM Catanneeprix c WHERE c.idannee.idannee=:annee");
            query.setParameter("annee", annee);
            catanneeprixs = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return catanneeprixs;
    }

}
