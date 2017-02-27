/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.PlanningEvaluation;
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
public class PlanningEvaluationFacade extends AbstractFacade<PlanningEvaluation> implements PlanningEvaluationFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlanningEvaluationFacade() {
        super(PlanningEvaluation.class);
    }

    @Override
    public Long nextVal() {
        Query query = em.createQuery("SELECT MAX(p.idplanning) FROM PlanningEvaluation p");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public PlanningEvaluation findByElementSequence(Long classeElement, int sequence) {
        PlanningEvaluation planningEvaluation = null;
        try {
            Query query = em.createQuery("SELECT p FROM PlanningEvaluation p WHERE  p.elementEvaluation.id=:element AND p.sequence.idsequencean=:sequence");
            query.setParameter("element", classeElement).setParameter("sequence", sequence);

            if (!query.getResultList().isEmpty()) {
                planningEvaluation = (PlanningEvaluation) query.getResultList().get(0);
            }
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return planningEvaluation;
    }

    @Override
    public PlanningEvaluation findByElementSequence1(int element, int sequence) {
        PlanningEvaluation planningEvaluation = null;
        try {
            Query query = em.createQuery("SELECT p FROM PlanningEvaluation p WHERE  p.elementEvaluation.elementevaluation.idelement=:element AND p.sequence.idsequencean=:sequence");
            query.setParameter("element", element).setParameter("sequence", sequence);

            if (!query.getResultList().isEmpty()) {
                planningEvaluation = (PlanningEvaluation) query.getResultList().get(0);
            }
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return planningEvaluation;
    }

    @Override
    public List<PlanningEvaluation> findByAnnee(int annee) {
        List<PlanningEvaluation> planningEvaluations = null;
        try {
            Query query = em.createQuery("SELECT p FROM PlanningEvaluation p WHERE p.sequence.trimestre.idannee.idannee=:annee");
            query.setParameter("annee", annee);
            planningEvaluations = query.getResultList();
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return planningEvaluations;
    }

    @Override
    public List<PlanningEvaluation> findByClasse(int classe, int sequence) throws Exception {
        List<PlanningEvaluation> planningEvaluations = null;
        Query query = em.createQuery("SELECT p FROM PlanningEvaluation p WHERE p.elementEvaluation.classe.idclasse=:classe AND p.sequence.idsequencean=:sequence ORDER BY p.idplanning");
        query.setParameter("classe", classe).setParameter("sequence", sequence);
        planningEvaluations = query.getResultList();
        return planningEvaluations;

    }

}
