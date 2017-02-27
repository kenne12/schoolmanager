/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Motif;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne gervais
 */
@Stateless
public class MotifFacade extends AbstractFacade<Motif> implements MotifFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MotifFacade() {
        super(Motif.class);
    }

    @Override
    public Motif getMotifByLibelle(String libelle) {
        Motif motif = null;
        Query query;
        try {

        } catch (Exception e) {
            e.getMessage();
            query = em.createNamedQuery("Motif.findByLibelle");
            query.setParameter("libelle", libelle);
            motif = (Motif) query.getSingleResult();
            System.err.println("erreur lors de la recherche");
        }
        return motif;
    }

}
