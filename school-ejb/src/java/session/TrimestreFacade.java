/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Trimestre;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne gervais
 */
@Stateless
public class TrimestreFacade extends AbstractFacade<Trimestre> implements TrimestreFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrimestreFacade() {
        super(Trimestre.class);
    }

    @Override
    public Trimestre findByNom(String nom) {
        Trimestre trimestre = null;
        Query query;
        try {
            query = em.createQuery("SELECT t FROM Trimestre t WHERE t.nom =:nom");
            query.setParameter("nom", nom);
            trimestre = (Trimestre) query.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
            e.getCause();
        }
        return trimestre;
    }

}
