/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Sanction;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne gervais
 */
@Stateless
public class SanctionFacade extends AbstractFacade<Sanction> implements SanctionFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SanctionFacade() {
        super(Sanction.class);
    }

    @Override
    public Sanction findBylibelle(String libelle) {
        Sanction sanction = null;
        Query query;
        try {
            query = em.createNamedQuery("Sanction.findByLibelle");
            query.setParameter("libelle", libelle);
            sanction = (Sanction) query.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la recherche");
        }
        return sanction;
    }
}
