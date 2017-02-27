/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.PlanningEvaluation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gervais
 */
@Local
public interface PlanningEvaluationFacadeLocal {

    void create(PlanningEvaluation planningEvaluation);

    void edit(PlanningEvaluation planningEvaluation);

    void remove(PlanningEvaluation planningEvaluation);

    PlanningEvaluation find(Object id);

    List<PlanningEvaluation> findAll();

    List<PlanningEvaluation> findRange(int[] range);

    int count();

    Long nextVal();

    PlanningEvaluation findByElementSequence(Long classeElement, int sequence);

    PlanningEvaluation findByElementSequence1(int element, int sequence);

    List<PlanningEvaluation> findByAnnee(int annee);

    List<PlanningEvaluation> findByClasse(int classe, int sequence)throws Exception;

}
