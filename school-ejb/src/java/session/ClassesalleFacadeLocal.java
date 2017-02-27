/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Classesalle;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface ClassesalleFacadeLocal {

    void create(Classesalle classesalle);

    void edit(Classesalle classesalle);

    void remove(Classesalle classesalle);

    Classesalle find(Object id);

    List<Classesalle> findAll();

    List<Classesalle> findRange(int[] range);

    int count();

    Classesalle findByClasseSalle(int idclasse, int idsalle);

}
