/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Personnelprivilege;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface PersonnelprivilegeFacadeLocal {

    void create(Personnelprivilege personnelprivilege);

    void edit(Personnelprivilege personnelprivilege);

    void remove(Personnelprivilege personnelprivilege);

    Personnelprivilege find(Object id);

    List<Personnelprivilege> findAll();

    List<Personnelprivilege> findRange(int[] range);

    int count();

}
