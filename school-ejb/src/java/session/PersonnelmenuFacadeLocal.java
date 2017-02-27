/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Personnelmenu;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gervais
 */
@Local
public interface PersonnelmenuFacadeLocal {

    void create(Personnelmenu personnelmenu);

    void edit(Personnelmenu personnelmenu);

    void remove(Personnelmenu personnelmenu);

    Personnelmenu find(Object id);

    List<Personnelmenu> findAll();

    List<Personnelmenu> findRange(int[] range);

    int count();

    Personnelmenu findByPersonnelMenu(int personnel, int menu);

}
