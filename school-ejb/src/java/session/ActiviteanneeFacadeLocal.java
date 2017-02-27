/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Activiteannee;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface ActiviteanneeFacadeLocal {

    void create(Activiteannee activiteannee);

    void edit(Activiteannee activiteannee);

    void remove(Activiteannee activiteannee);

    Activiteannee find(Object id);

    List<Activiteannee> findAll();

    List<Activiteannee> findRange(int[] range);

    int count();

}
