/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.PensionCumulee;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gervais
 */
@Stateless
public class PensionCumuleeFacade extends AbstractFacade<PensionCumulee> implements PensionCumuleeFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PensionCumuleeFacade() {
        super(PensionCumulee.class);
    }

    @Override
    public Long nextVal() {
        Query query = em.createQuery("SELECT MAX(p.id) FROM PensionCumulee p");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public PensionCumulee findByEleve(int eleve, int annee) {
        PensionCumulee pensionCumulee = null;
        try {
            Query query = em.createQuery("SELECT p FROM PensionCumulee p WHERE p.eleve.ideleve=:eleve AND p.annee.idannee=:annee");
            query.setParameter("eleve", eleve).setParameter("annee", annee);
            List<PensionCumulee> result = query.getResultList();
            if (!result.isEmpty()) {
                pensionCumulee = (PensionCumulee) result.get(0);
            }
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return pensionCumulee;
    }

}
