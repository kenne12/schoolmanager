/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Parent;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface ParentFacadeLocal {

    void create(Parent parent);

    void edit(Parent parent);

    void remove(Parent parent);

    Parent find(Object id);

    List<Parent> findAll();

    List<Parent> findRange(int[] range);

    int count();

    Integer nextVal();

}
