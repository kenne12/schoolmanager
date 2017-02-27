/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Matiere;
import java.util.ArrayList;
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
public class MatiereFacade extends AbstractFacade<Matiere> implements MatiereFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MatiereFacade() {
        super(Matiere.class);
    }

    @Override
    public Integer nextVal() {
        Query query = em.createQuery("SELECT  MAX(m.idmatiere) FROM Matiere m");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public List<Matiere> findByLibelle(int natureMatiere, String libelle) {
        List<Matiere> matieres = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT m FROM Matiere m WHERE m.idnaturematiere.idnaturematiere=:naturematiere AND UPPER (m.libelle)=:libelle");
            query.setParameter("naturematiere", natureMatiere).setParameter("libelle", libelle.toUpperCase());
            matieres = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matieres;
    }

    @Override
    public List<Matiere> findAllRange() {
        List<Matiere> matieres = null;
        Query query = em.createQuery("SELECT m FROM Matiere m ORDER BY m.idnaturematiere.libelle,m.libelle");
        matieres = query.getResultList();
        return matieres;
    }

}
