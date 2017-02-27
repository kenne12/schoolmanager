/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Categoriepersonnel;
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
public class CategoriepersonnelFacade extends AbstractFacade<Categoriepersonnel> implements CategoriepersonnelFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriepersonnelFacade() {
        super(Categoriepersonnel.class);
    }

    @Override
    public List<Categoriepersonnel> findByEtat(boolean etat) {
        List<Categoriepersonnel> results = null;
        Query query;
        try {
            query = em.createNamedQuery("Categoriepersonnel.findByEtat");
            query.setParameter("etat", etat);
            results = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherches des categories a letat" + etat);
            e.getMessage();
        }
        return results;
    }

    @Override
    public List<Categoriepersonnel> findByCode(String code) {
        List<Categoriepersonnel> results = null;

        Query query;
        try {
            query = em.createNamedQuery("Categoriepersonnel.findByCodecategorie");
            query.setParameter("codecategorie", code);
            results = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherches des categories a l'etat" + code);
            e.getMessage();
        }
        return results;
    }

}
