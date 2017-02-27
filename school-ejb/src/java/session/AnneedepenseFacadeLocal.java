/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Anneedepense;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface AnneedepenseFacadeLocal {

    void create(Anneedepense anneedepense);

    void edit(Anneedepense anneedepense);

    void remove(Anneedepense anneedepense);

    Anneedepense find(Object id);

    List<Anneedepense> findAll();

    List<Anneedepense> findRange(int[] range);

    int count();

}
