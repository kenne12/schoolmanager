/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Etablissement;
import entities.Typecompte;
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
public class TypecompteFacade extends AbstractFacade<Typecompte> implements TypecompteFacadeLocal {
    
    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public TypecompteFacade() {
        super(Typecompte.class);
    }
    
    @Override
    public Integer nextVal() {
        Query query = em.createQuery("SELECT MAX(t.idtypecompte) FROM Typecompte t");
        Integer result;
        result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result += 1;
        }
        return result;
    }
    
    @Override
    public List<Typecompte> find(Etablissement etablissement) throws Exception {
        List<Typecompte> typecomptes = null;
        Query query = em.createQuery("SELECT t FROM Typecompte t WHERE t.idetablissement.id=:etablissement ORDER BY t.classe");
        query.setParameter("etablissement", etablissement.getId());
        typecomptes = query.getResultList();
        return typecomptes;
    }
    
    @Override
    public List<Typecompte> find(Etablissement etablissement, Integer classe) throws Exception {
        List<Typecompte> typecomptes = null;
        Query query = em.createQuery("SELECT t FROM Typecompte t WHERE t.idetablissement.id=:etablissement AND t.classe=:classe");
        query.setParameter("etablissement", etablissement.getId()).setParameter("classe", classe);
        typecomptes = query.getResultList();
        return typecomptes;
    }
    
}
