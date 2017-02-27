/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Elevelivreemprunte;
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
public class ElevelivreemprunteFacade extends AbstractFacade<Elevelivreemprunte> implements ElevelivreemprunteFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ElevelivreemprunteFacade() {
        super(Elevelivreemprunte.class);
    }
    
    @Override
    public Long nextVal() {
        Query query = em.createQuery("SELECT MAX(e.id) FROM Elevelivreemprunte e");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public List<Elevelivreemprunte> get(boolean emprunte) {
        List<Elevelivreemprunte> result = null;
        Query query;
        try {
            query = em.createNamedQuery("Elevelivreemprunte.findByEtatemprunt");
            query.setParameter("etatemprunt", emprunte);
            result = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return result;
    }

    @Override
    public List<Elevelivreemprunte> get(int annee, int eleve, boolean etat) {
        List<Elevelivreemprunte> result = null;
        Query query;
        try {
            query = em.createQuery("SELECT e FROM Elevelivreemprunte e WHERE e.idannee.idannee=:annee AND e.eleve.ideleve=:eleve AND e.etatemprunt=:etat");
            query.setParameter("annee", annee);
            query.setParameter("eleve", eleve);
            query.setParameter("etat", etat);
            result = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return result;
    }

    @Override
    public Elevelivreemprunte get(int eleve, Long livre) {
        Elevelivreemprunte result = null;
        Query query;
        try {
            query = em.createQuery("SELECT e FROM Elevelivreemprunte e WHERE e.eleve.ideleve=:eleve AND e.livre.idlivre=:livre");
            query.setParameter("eleve", eleve);
            query.setParameter("livre", livre);
            result = (Elevelivreemprunte) query.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
        }
        return result;
    }

    @Override
    public List<Elevelivreemprunte> get(int eleve, boolean etat) {
        List<Elevelivreemprunte> result = null;
        Query query;
        try {
            query = em.createQuery("SELECT e FROM Elevelivreemprunte e WHERE e.eleve.ideleve=:eleve AND e.etatemprunt=:etat");
            query.setParameter("eleve", eleve);
            query.setParameter("etat", etat);
            result = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return result;
    }

}
