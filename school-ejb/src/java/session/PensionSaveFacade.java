/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Annee;
import entities.Etablissement;
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
public class PensionSaveFacade extends AbstractFacade<PensionSave> implements PensionSaveFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PensionSaveFacade() {
        super(PensionSave.class);
    }

    @Override
    public Long nextVal() {
        Query query = em.createQuery("SELECT MAX(p.idpensionsave) FROM PensionSave p");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1L;
        }
        return result;
    }

    @Override
    public Long nextVal(Annee annee) {
        Query query = em.createQuery("SELECT COUNT(p.idpensionsave) FROM PensionSave p WHERE p.annee.idannee=:annee");
        query.setParameter("annee", annee.getIdannee());
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1L;
        }
        return result;
    }

    @Override
    public List<PensionSave> findByAnnee(Annee annee) throws Exception {
        List<PensionSave> pensionSaves = null;
        Query query = em.createQuery("SELECT p FROM PensionSave p WHERE p.annee.idannee=:annee");
        query.setParameter("annee", annee.getIdannee());
        pensionSaves = query.getResultList();
        return pensionSaves;
    }

}
