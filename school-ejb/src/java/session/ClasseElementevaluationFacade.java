/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.ClasseElementevaluation;
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
public class ClasseElementevaluationFacade extends AbstractFacade<ClasseElementevaluation> implements ClasseElementevaluationFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClasseElementevaluationFacade() {
        super(ClasseElementevaluation.class);
    }

    @Override
    public Long nextVal() {
        Query query = em.createQuery("SELECT MAX(c.id) FROM ClasseElementevaluation c");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public ClasseElementevaluation find(int classe, int element) {
        ClasseElementevaluation classeElementevaluation = null;
        try {
            Query query = em.createQuery("SELECT c FROM ClasseElementevaluation C WHERE c.classe.idclasse=:classe AND c.elementevaluation.idelement=:element");
            query.setParameter("classe", classe).setParameter("element", element);
            classeElementevaluation = (ClasseElementevaluation) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return classeElementevaluation;
    }

    @Override
    public List<ClasseElementevaluation> findByClasse(int classe) throws Exception {
        List<ClasseElementevaluation> classeElementevaluations = null;
        try {
            Query query = em.createQuery("SELECT c FROM ClasseElementevaluation c WHERE c.classe.idclasse=:classe ORDER BY c.elementevaluation.matiere.libelle , c.elementevaluation.nom");
            query.setParameter("classe", classe);
            classeElementevaluations = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classeElementevaluations;
    }

}
