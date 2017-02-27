/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Qualification;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface QualificationFacadeLocal {

    void create(Qualification qualification);

    void edit(Qualification qualification);

    void remove(Qualification qualification);

    Qualification find(Object id);

    List<Qualification> findAll();

    List<Qualification> findRange(int[] range);

    int count();

    Long nextVal();

}
