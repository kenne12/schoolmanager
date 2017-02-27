/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Motif;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface MotifFacadeLocal {

    void create(Motif motif);

    void edit(Motif motif);

    void remove(Motif motif);

    Motif find(Object id);

    List<Motif> findAll();

    List<Motif> findRange(int[] range);

    int count();

    Motif getMotifByLibelle(String libelle);

}
