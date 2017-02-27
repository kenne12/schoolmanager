/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Personnelmodule;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gervais
 */
@Local
public interface PersonnelmoduleFacadeLocal {

    void create(Personnelmodule personnelmodule);

    void edit(Personnelmodule personnelmodule);

    void remove(Personnelmodule personnelmodule);

    Personnelmodule find(Object id);

    List<Personnelmodule> findAll();

    List<Personnelmodule> findRange(int[] range);

    int count();

    Personnelmodule findByPersonnelModule(int personnel, int module);

    boolean findByPersonnelModule(int personnel, int module, boolean etat);

    List<Personnelmodule> get(int personnel, boolean etat);

}
