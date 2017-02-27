/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Punition;
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
public class PunitionFacade extends AbstractFacade<Punition> implements PunitionFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PunitionFacade() {
        super(Punition.class);
    }

    @Override
    public List<Punition> getPunitionAnneeActive(boolean etat) {
        List<Punition> punitions = null;
        Query query;
        try {
            query = em.createQuery("SELECT p FROM Punition p WHERE p.idannee.etatannee=:etat");
            query.setParameter("etat", etat);
            punitions = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la recherche");
        }
        return punitions;
    }

}
