/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Etablissement;
import entities.Typetranche;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne
 */
@Stateless
public class TypetrancheFacade extends AbstractFacade<Typetranche> implements TypetrancheFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypetrancheFacade() {
        super(Typetranche.class);
    }

    @Override
    public Integer nextVal() {
        Query query = em.createQuery("SELECT MAX(t.idtypetranche) FROM Typetranche t");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public List<Typetranche> find(Etablissement etablissement) throws Exception {
        List<Typetranche> typetranches = null;
        Query query = em.createQuery("SELECT t FROM Typetranche t WHERE t.idetablissement.id=:etablissement ORDER BY t.nom");
        query.setParameter("etablissement", etablissement.getId());
        typetranches = query.getResultList();
        return typetranches;

    }

    @Override
    public List<Typetranche> find(Etablissement etablissement, String nom) throws Exception {
        List<Typetranche> typetranches = null;
        Query query = em.createQuery("SELECT t FROM Typetranche t WHERE t.idetablissement.id=:etablissement AND UPPER(t.nom)=(:nom)");
        query.setParameter("etablissement", etablissement.getId()).setParameter("nom", nom);
        typetranches = query.getResultList();
        return typetranches;
    }

}
