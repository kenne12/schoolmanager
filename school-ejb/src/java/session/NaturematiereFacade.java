/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Naturematiere;
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
public class NaturematiereFacade extends AbstractFacade<Naturematiere> implements NaturematiereFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NaturematiereFacade() {
        super(Naturematiere.class);
    }

    @Override
    public Naturematiere findByLibelle(String libelle) {
        Naturematiere nat = null;
        Query query;
        try {
            query = em.createNamedQuery("Naturematiere.findByLibelle");
            query.setParameter("libelle", "libelle");
            nat = (Naturematiere) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nat;
    }

    @Override
    public List<Naturematiere> findAllRange() {
        List<Naturematiere> naturematieres = null;
        Query query = em.createQuery("SELECT n FROM Naturematiere n ORDER BY n.libelle");
        naturematieres = query.getResultList();
        return naturematieres;
    }
}
