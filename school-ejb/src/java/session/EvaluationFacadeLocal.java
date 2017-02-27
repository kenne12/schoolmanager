/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Evaluation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface EvaluationFacadeLocal {

    void create(Evaluation evaluation);

    void edit(Evaluation evaluation);

    void remove(Evaluation evaluation);

    Evaluation find(Object id);

    List<Evaluation> findAll();

    List<Evaluation> findRange(int[] range);

    int count();

    Long nextVal();

    List<Evaluation> getEvaluationByPersonnel(int personnel, int annee);

    Evaluation findByEleveMatiereAnneeTrimestreSequence(int eleve, int matiere, int annee, int trimestre, int sequence);

    //cette interface permet de la liste des matiere composées par un eleve aucours d'une nnee
    List<Evaluation> getByEleveAnneeSequence(int eleve, int annee, int sequence);

    Evaluation findByElevePlanning(int eleve, Long planning);

    List<Evaluation> findByAnnee(int annee) throws Exception;

    List<Evaluation> findByEleveAnneeMatiere(int eleve, int annee, Long matiere) throws Exception;

    List<Evaluation> findByEleveTrimestreMatiere(int eleve, int trimestre, Long matiere) throws Exception;

}
