/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Cycle;
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
public class CycleFacade extends AbstractFacade<Cycle> implements CycleFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CycleFacade() {
        super(Cycle.class);
    }

    @Override
    public Cycle findByNom(String nom) {
        Query query;
        Cycle cycle = null;
        try {
            query = em.createNamedQuery("Cycle.findByNom");
            query.setParameter("nom", nom);
            cycle = (Cycle) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche du cycle");
            e.getMessage();
        }

        return cycle;
    }

    @Override
    public List<Cycle> findByEtat(boolean etat) {
        Query query;
        List<Cycle> cycles = null;
        try {
            query = em.createNamedQuery("Cycle.findByEtat");
            query.setParameter("etat", etat);
            cycles = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche du cycle");
            e.getMessage();
        }
        return cycles;
    }

}
