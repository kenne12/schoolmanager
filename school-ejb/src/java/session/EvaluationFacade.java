/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Evaluation;
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
public class EvaluationFacade extends AbstractFacade<Evaluation> implements EvaluationFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EvaluationFacade() {
        super(Evaluation.class);
    }

    @Override
    public Long nextVal() {
        Query query = em.createQuery("SELECT MAX(e.ideval) FROM Evaluation e");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public List<Evaluation> getEvaluationByPersonnel(int personnel, int annee) {
        List<Evaluation> evaluations = null;
        try {
            Query query = em.createQuery("SELECT e FROM Evaluation e WHERE e.personnel.idpersonnel=:personnel AND e.idannee.idannee=:annee");
            query.setParameter("personnel", personnel).setParameter("annee", annee);
            evaluations = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche" + e.getMessage());
        }
        return evaluations;
    }

    @Override
    public Evaluation findByEleveMatiereAnneeTrimestreSequence(int eleve, int matiere, int annee, int trimestre, int sequence) {
        Evaluation evaluation = null;
        try {
            Query query = em.createQuery("SELECT e FROM Evaluation e WHERE e.eleve.ideleve=:eleve AND e.idmatiere.idmatiere=:matiere AND e.idannee.idannee=:annee AND e.idtrimestrean.idtrimestrean=:trimestre AND e.idsequencean.idsequencean=:sequence");
            query.setParameter("eleve", eleve).setParameter("matiere", matiere);
            query.setParameter("annee", annee).setParameter("trimestre", trimestre).setParameter("sequence", sequence);
            evaluation = (Evaluation) query.getSingleResult();
        } catch (Exception e) {
            //e.printStackTrace();
            e.getMessage();
            System.err.println("erreur lors de la recherche");
        }
        return evaluation;
    }

    @Override
    public List<Evaluation> getByEleveAnneeSequence(int eleve, int annee, int sequence) {
        List<Evaluation> evaluations = null;
        try {
            Query query = em.createQuery("SELECT e FROM Evaluation e WHERE e.eleve.ideleve=:eleve AND e.planningEvaluation.sequence.idannee.idannee=:annee AND e.planningEvaluation.sequence.idsequence=:sequence");
            query.setParameter("eleve", eleve);
            query.setParameter("annee", annee);
            query.setParameter("sequence", sequence);
            evaluations = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return evaluations;
    }

    @Override
    public Evaluation findByElevePlanning(int eleve, Long planning) {
        Evaluation evaluation = null;
        try {
            Query query = em.createQuery("SELECT e FROM Evaluation e WHERE e.eleve.ideleve=:eleve AND e.planningEvaluation.idplanning=:planning");
            query.setParameter("eleve", eleve).setParameter("planning", planning);
            if (!query.getResultList().isEmpty()) {
                evaluation = (Evaluation) query.getSingleResult();
            }
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return evaluation;
    }

    @Override
    public List<Evaluation> findByAnnee(int annee) throws Exception {
        List<Evaluation> evaluations = null;
        Query query = em.createQuery("SELECT e FROM Evaluation e WHERE e.planningEvaluation.sequence.idannee.idannee=:annee ORDER BY e.planningEvaluation.elementEvaluation.classe.niveau , e.planningEvaluation.elementEvaluation.classe.niveau , e.planningEvaluation.sequence.idsequence.numero , e.eleve.nom,e.eleve.prenom,e.note");
        query.setParameter("annee", annee);
        evaluations = query.getResultList();
        return evaluations;
    }

    @Override
    public List<Evaluation> findByEleveAnneeMatiere(int eleve, int annee, Long matiere) throws Exception {
        List<Evaluation> evaluations = null;
        Query query = em.createQuery("SELECT e FROM Evaluation e WHERE e.eleve.ideleve=:eleve AND e.planningEvaluation.elementEvaluation.id=:matiere AND e.planningEvaluation.sequence.idannee.idannee=:annee");
        query.setParameter("eleve", eleve).setParameter("annee", annee).setParameter("matiere", matiere);
        evaluations = query.getResultList();
        return evaluations;
    }

    @Override
    public List<Evaluation> findByEleveTrimestreMatiere(int eleve, int trimestre, Long matiere) throws Exception {
        List<Evaluation> evaluations = null;
        Query query = em.createQuery("SELECT e FROM Evaluation e WHERE e.eleve.ideleve=:eleve AND e.planningEvaluation.elementEvaluation.id =:matiere AND e.planningEvaluation.sequence.trimestre.idtrimestrean=:trimestre");
        query.setParameter("eleve", eleve).setParameter("trimestre", trimestre).setParameter("matiere", matiere);
        evaluations = query.getResultList();
        return evaluations;
    }

}
