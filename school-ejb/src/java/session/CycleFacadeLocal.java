/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Cycle;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface CycleFacadeLocal {

    void create(Cycle cycle);

    void edit(Cycle cycle);

    void remove(Cycle cycle);

    Cycle find(Object id);

    List<Cycle> findAll();

    List<Cycle> findRange(int[] range);

    int count();

    Cycle findByNom(String nom);

    List<Cycle> findByEtat(boolean etat);
}
