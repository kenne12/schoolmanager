/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.ClasseElementevaluation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gervais
 */
@Local
public interface ClasseElementevaluationFacadeLocal {

    void create(ClasseElementevaluation classeElementevaluation);

    void edit(ClasseElementevaluation classeElementevaluation);

    void remove(ClasseElementevaluation classeElementevaluation);

    ClasseElementevaluation find(Object id);

    List<ClasseElementevaluation> findAll();

    List<ClasseElementevaluation> findRange(int[] range);

    int count();

    Long nextVal();

    ClasseElementevaluation find(int classe, int element);
    
    List<ClasseElementevaluation>findByClasse(int classe) throws Exception;

}
