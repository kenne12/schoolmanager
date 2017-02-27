/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Eleve;
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
public class EleveFacade extends AbstractFacade<Eleve> implements EleveFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EleveFacade() {
        super(Eleve.class);
    }

    @Override
    public Eleve findByMatricule(String matricule) {
        Eleve eleve = null;
        try {
            Query query = em.createNamedQuery("Eleve.findByMatricule");
            query.setParameter("matricule", matricule);
            eleve = (Eleve) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return eleve;
    }

    @Override
    public List<Eleve> findByEtat(boolean etat) {
        List<Eleve> eleves = null;
        try {
            Query query = em.createNamedQuery("Eleve.findByEtateleve");
            query.setParameter("etateleve", etat);
            eleves = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la recherche de l'eleve ayant pour etat" + etat);
        }
        return eleves;
    }

    @Override
    public Eleve findById(int id) {
        Eleve eleve = null;
        try {
            Query query = em.createNamedQuery("Eleve.findByIdeleve");
            query.setParameter("ideleve", id);
            eleve = (Eleve) query.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la recherche de l'eleve ayant pour matricule" + id);
        }
        return eleve;
    }

    @Override
    public Integer nextVal() {
        Query query = em.createQuery("SELECT MAX(e.ideleve) FROM Eleve e");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result += 1;
        }
        return result;
    }
}
