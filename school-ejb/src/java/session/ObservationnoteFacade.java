/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Etablissement;
import entities.Observationnote;
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
public class ObservationnoteFacade extends AbstractFacade<Observationnote> implements ObservationnoteFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObservationnoteFacade() {
        super(Observationnote.class);
    }

    @Override
    public Integer nextVal() {
        Query query = em.createQuery("SELECT MAX(o.idobservationnote) FROM Observationnote o");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public List<Observationnote> findRange(Etablissement etablissement) throws Exception {
        List<Observationnote> observationnotes = null;
        Query query = em.createQuery("SELECT o FROM Observationnote o WHERE o.idetablissement.id=:etablissement ORDER BY o.borneinferieur,o.bornesuperieur");
        query.setParameter("etablissement", etablissement.getId());
        observationnotes = query.getResultList();
        return observationnotes;
    }

    @Override
    public List<Observationnote> find(Etablissement etablissement, String avis) throws Exception {
        List<Observationnote> observationnotes = null;
        Query query = em.createQuery("SELECT o FROM Observationnote o WHERE o.idetablissement.id=:etablissement AND UPPER(o.avis)=(:avis)");
        query.setParameter("etablissement", etablissement.getId()).setParameter("avis", avis);
        observationnotes = query.getResultList();
        return observationnotes;
    }

}
