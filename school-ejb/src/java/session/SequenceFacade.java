/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Sequence;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne gervais
 */
@Stateless
public class SequenceFacade extends AbstractFacade<Sequence> implements SequenceFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SequenceFacade() {
        super(Sequence.class);
    }

    @Override
    public Sequence findByNom(String nom) {
        Sequence sequence = null;
        Query query;
        try {
            query = em.createNamedQuery("Sequence.findByNom");
            query.setParameter("nom", nom);
            sequence = (Sequence) query.getSingleResult();
        } catch (Exception e) {
            e.getCause();
            e.getMessage();
        }
        return sequence;
    }

}
