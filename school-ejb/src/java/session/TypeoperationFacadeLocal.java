/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Typeoperation;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TypeoperationFacadeLocal {

    void create(Typeoperation typeoperation);

    void edit(Typeoperation typeoperation);

    void remove(Typeoperation typeoperation);

    Typeoperation find(Object id);

    List<Typeoperation> findAll();

    List<Typeoperation> findRange(int[] range);

    int count();
    
}
