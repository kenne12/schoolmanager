/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Classematiere;

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
public class ClassematiereFacade extends AbstractFacade<Classematiere> implements ClassematiereFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClassematiereFacade() {
        super(Classematiere.class);
    }

    @Override
    public Integer nextVal() {
        Query query = em.createQuery("SELECT  MAX(c.id) FROM Classematiere c");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public List<Classematiere> findByClasseMatiere(int classe, int matiere) {
        List<Classematiere> classesMatieres = null;
        try {
            Query query = em.createQuery("SELECT c FROM Classematiere c WHERE c.idclasse.idclasse=:classe AND c.idmatiere.idmatiere =:matiere");
            query.setParameter("classe", classe).setParameter("matiere", matiere);
            classesMatieres = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return classesMatieres;
    }

    @Override
    public List<Classematiere> get(int classe) {
        List<Classematiere> classesMatieres = null;
        try {
            Query query = em.createQuery("SELECT c FROM Classematiere c WHERE c.idclasse.idclasse=:classe");
            query.setParameter("classe", classe);
            classesMatieres = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return classesMatieres;
    }

}
