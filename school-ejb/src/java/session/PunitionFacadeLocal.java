/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Punition;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface PunitionFacadeLocal {

    void create(Punition punition);

    void edit(Punition punition);

    void remove(Punition punition);

    Punition find(Object id);

    List<Punition> findAll();

    List<Punition> findRange(int[] range);

    int count();

    List<Punition> getPunitionAnneeActive(boolean etat);

}
