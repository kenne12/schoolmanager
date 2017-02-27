/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Salle;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne gervais
 */
@Stateless
public class SalleFacade extends AbstractFacade<Salle> implements SalleFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SalleFacade() {
        super(Salle.class);
    }

    @Override
    public Salle findSalleByCode(String code) {
        Salle salle = null;
        Query query;
        try {
            query = em.createNamedQuery("Salle.findByCode");
            query.setParameter("code", code);
            salle = (Salle) query.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("Erreur lors de la recheche de la salle : " + code);
        }
        return salle;
    }

}
