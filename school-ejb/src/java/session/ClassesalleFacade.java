/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Classesalle;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne gervais
 */
@Stateless
public class ClassesalleFacade extends AbstractFacade<Classesalle> implements ClassesalleFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClassesalleFacade() {
        super(Classesalle.class);
    }

    @Override
    public Classesalle findByClasseSalle(int idclasse, int idsalle) {
        Classesalle result = null;

        Query query;
        try {
            query = em.createNamedQuery("Classesalle.findByClasseSalle");
            query.setParameter("idclasse", idclasse);
            query.setParameter("idsalle", idsalle);
            result = (Classesalle) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche de l'affectation : " + idclasse);
        }
        return result;
    }

}
