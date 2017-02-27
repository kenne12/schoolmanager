/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Livre;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne gervais
 */
@Stateless
public class LivreFacade extends AbstractFacade<Livre> implements LivreFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LivreFacade() {
        super(Livre.class);
    }

    @Override
    public Long nextVal() {
        Query query = em.createQuery("SELECT MAX(L.idlivre) FROM Livre L");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public Livre getEditeur(String isbn) {
        Livre livre = null;
        Query query;
        try {
            query = em.createNamedQuery("Livre.findByCodeisbn");
            query.setParameter("codeisbn", isbn);
            livre = (Livre) query.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
        }
        return livre;
    }

}
