/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Traceur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gervais
 */
@Local
public interface TraceurFacadeLocal {

    void create(Traceur traceur);

    void edit(Traceur traceur);

    void remove(Traceur traceur);

    Traceur find(Object id);

    List<Traceur> findAll();

    List<Traceur> findRange(int[] range);

    int count();

}
