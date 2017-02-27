/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.ElementEvaluation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gervais
 */
@Local
public interface ElementEvaluationFacadeLocal {

    void create(ElementEvaluation elementEvaluation);

    void edit(ElementEvaluation elementEvaluation);

    void remove(ElementEvaluation elementEvaluation);

    ElementEvaluation find(Object id);

    List<ElementEvaluation> findAll();

    List<ElementEvaluation> findRange(int[] range);

    int count();
    
    Integer nextVal();
    
    List<ElementEvaluation> findByLibelle(int matiere, String libelle);
    
    List<ElementEvaluation> findAllRange();
    
}
