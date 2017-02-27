/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Enseignement;
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
public class EnseignementFacade extends AbstractFacade<Enseignement> implements EnseignementFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnseignementFacade() {
        super(Enseignement.class);
    }

    @Override
    public Enseignement findByNom(int etablissement, String nom) {
        Enseignement result = null;
        try {
            Query query = em.createQuery("SELECT e FROM Enseignement e WHERE e.etablissement.id=:etablissement AND e.nom=:nom");
            query.setParameter("nom", nom).setParameter("etablissement", etablissement);
            if (!query.getResultList().isEmpty()) {
                result = (Enseignement) query.getSingleResult();
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return result;
    }

    /**
     *
     * @param etat
     * @return
     */
    @Override
    public List<Enseignement> findByEtat(boolean etat) {
        List<Enseignement> results = null;
        Query query;
        try {
            query = em.createNamedQuery("Enseignement.findByEtat");
            query.setParameter("etat", etat);
            results = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return results;
    }

    @Override
    public List<Enseignement> findByEtablissement(int etablissement) {
        List<Enseignement> results = null;
        try {
            Query query = em.createQuery("SELECT e FROM Enseignement e WHERE e.etablissement.id=:etablissement");
            query.setParameter("etablissement", etablissement);
            results = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return results;
    }

}
