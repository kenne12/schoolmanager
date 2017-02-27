/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Etablissement;
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
public class EtablissementFacade extends AbstractFacade<Etablissement> implements EtablissementFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtablissementFacade() {
        super(Etablissement.class);
    }

    @Override
    public int nextId() {
        try {
            Query query = em.createQuery("SELECT  MAX(e.id) FROM Etablissement e");
            List listObj = query.getResultList();
            if (!listObj.isEmpty()) {
                return ((Integer) listObj.get(0)) + 1;
            } else {
                return 1;
            }
        } catch (Exception e) {
            return 1;
        }
    }

    @Override
    public Etablissement findByNom(String nom) {
        Etablissement etablissement = null;
        try {
            Query query = em.createQuery("SELECT e FROM Etablissement e WHERE e.nom =:nom");
            query.setParameter("nom", nom);
            etablissement = (Etablissement) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return etablissement;
    }

}
