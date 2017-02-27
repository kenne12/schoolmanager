/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Appreciationannuelle;
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
public class AppreciationannuelleFacade extends AbstractFacade<Appreciationannuelle> implements AppreciationannuelleFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppreciationannuelleFacade() {
        super(Appreciationannuelle.class);
    }

    @Override
    public Long nextVal() {
        Query query = em.createQuery("SELECT MAX(a.id) FROM Appreciationannuelle a");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public Appreciationannuelle find(int eleve, Long matiere, int annee) throws Exception {
        Appreciationannuelle appreciationannuelle = null;
        Query query = em.createQuery("SELECT a FROM Appreciationannuelle a WHERE a.annee.idannee=:annee AND a.matiere.id=:matiere AND a.eleve.eleve.ideleve=:eleve");
        query.setParameter("annee", annee).setParameter("matiere", matiere).setParameter("eleve", eleve);
        List<Appreciationannuelle> appreciationannuelles = query.getResultList();
        if (!appreciationannuelles.isEmpty()) {
            appreciationannuelle = appreciationannuelles.get(0);
        }
        return appreciationannuelle;
    }

}
