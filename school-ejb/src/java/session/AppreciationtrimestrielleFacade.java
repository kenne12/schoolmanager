/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Appreciationtrimestrielle;
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
public class AppreciationtrimestrielleFacade extends AbstractFacade<Appreciationtrimestrielle> implements AppreciationtrimestrielleFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppreciationtrimestrielleFacade() {
        super(Appreciationtrimestrielle.class);
    }

    @Override
    public Long nextVal() {
        Query query = em.createQuery("SELECT MAX(t.id) FROM Appreciationannuelle t");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public Appreciationtrimestrielle find(int eleve, Long matiere, int trimestre) throws Exception {
        Appreciationtrimestrielle appreciationtrimestrielle = null;
        Query query = em.createQuery("SELECT a FROM Appreciationtrimestrielle a WHERE a.eleve.eleve.ideleve =:eleve AND a.matiere.elementevaluation.idelement=:matiere AND a.trimestre.idtrimestrean=:trimestre");
        query.setParameter("eleve", eleve).setParameter("matiere", matiere).setParameter("trimestre", trimestre);
        List<Appreciationtrimestrielle> appreciationtrimestrielles = query.getResultList();
        if (!appreciationtrimestrielles.isEmpty()) {
            appreciationtrimestrielle = appreciationtrimestrielles.get(0);
        }
        return appreciationtrimestrielle;
    }

}
