/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Etablissement;
import entities.Fonction;
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
public class FonctionFacade extends AbstractFacade<Fonction> implements FonctionFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FonctionFacade() {
        super(Fonction.class);
    }

    @Override
    public List<Fonction> findByEtat(Etablissement etablissement, boolean etat) {
        List<Fonction> results = null;
        try {
            Query query = em.createQuery("SELECT f FROM Fonction f WHERE f.idetablissement.id=:etablissement AND f.etat=:etat");
            query.setParameter("etat", etat).setParameter("etablissement", etablissement.getId());
            results = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la recherche de la fonction");
        }
        return results;
    }

    @Override
    public List<Fonction> findByNom(String nom) {
        List<Fonction> results = null;
        Query query;
        try {
            query = em.createNamedQuery("Fonction.findByNom");
            query.setParameter("nom", nom);
            results = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la recherche de la fonction");
        }
        return null;
    }

}
