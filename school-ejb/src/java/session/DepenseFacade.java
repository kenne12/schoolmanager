/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Annee;
import entities.Depense;
import entities.Etablissement;
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
public class DepenseFacade extends AbstractFacade<Depense> implements DepenseFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepenseFacade() {
        super(Depense.class);
    }

    @Override
    public Long nextVal() {
        Query query = em.createQuery("SELECT MAX(d.iddepense) FROM Depense d");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1L;
        }
        return result;
    }

    @Override
    public List<Depense> find(Etablissement etablissement, Annee annee) throws Exception {
        List<Depense> depenses = null;
        Query query = em.createQuery("SELECT d FROM Depense d WHERE d.idetablissement.id=:etablissement AND d.idannee.idannee=:annee ORDER BY d.idoperation.idcompte.classe");
        query.setParameter("etablissement", etablissement.getId()).setParameter("annee", annee.getIdannee());
        depenses = query.getResultList();
        return depenses;
    }

}
