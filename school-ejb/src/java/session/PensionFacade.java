/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Pension;
import entities.PensionSave;
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
public class PensionFacade extends AbstractFacade<Pension> implements PensionFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PensionFacade() {
        super(Pension.class);
    }

    @Override
    public Long nextVal() {
        Query query = em.createQuery("SELECT MAX(t.idpension) FROM Pension t");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public List<Pension> getPensionByAnneeActive(boolean etat) {
        List<Pension> tranches = null;
        try {
            Query query = em.createQuery("SELECT t FROM Pension t WHERE t.idannee.etatannee=:etat");
            query.setParameter("etat", etat);
            tranches = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return tranches;

    }

    @Override
    public List<Pension> getPensionByEleveTranche(int eleve, int tranche) {
        List<Pension> results = null;
        try {
            Query query = em.createQuery("SELECT t FROM Pension t WHERE t.eleve.ideleve=:eleve AND t.idtranche.idtranche=:tranche");
            query.setParameter("eleve", eleve).setParameter("tranche", tranche);
            results = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche !" + e.getMessage());
        }
        return results;
    }

    @Override
    public List<Pension> getPensionByAnneeEleve(int annee, int eleve) {
        List<Pension> results = null;
        try {
            Query query = em.createQuery("SELECT t FROM Pension t WHERE t.idannee.idannee=:annee AND t.eleve.ideleve=:eleve");
            query.setParameter("annee", annee).setParameter("eleve", eleve);
            results = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche !" + e.getMessage());
        }
        return results;
    }

    @Override
    public List<Pension> findByPensionsave(PensionSave pensionSave) throws Exception {
        List<Pension> pensions = null;
        Query query = em.createQuery("SELECT p FROM Pension p WHERE p.pensionsave.idpensionsave=:pension");
        query.setParameter("pension", pensionSave.getIdpensionsave());
        pensions = query.getResultList();
        return pensions;
    }

}
