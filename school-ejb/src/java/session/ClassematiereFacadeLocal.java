/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Classematiere;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gervais
 */
@Local
public interface ClassematiereFacadeLocal {

    void create(Classematiere classematiere);

    void edit(Classematiere classematiere);

    void remove(Classematiere classematiere);

    Classematiere find(Object id);

    List<Classematiere> findAll();

    List<Classematiere> findRange(int[] range);

    int count();

    Integer nextVal();

    List<Classematiere> findByClasseMatiere(int classe, int matiere);

    List<Classematiere> get(int classe);

}
