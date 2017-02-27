/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Travauxdirige;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface TravauxdirigeFacadeLocal {

    void create(Travauxdirige travauxdirige);

    void edit(Travauxdirige travauxdirige);

    void remove(Travauxdirige travauxdirige);

    Travauxdirige find(Object id);

    List<Travauxdirige> findAll();

    List<Travauxdirige> findRange(int[] range);

    int count();

}
