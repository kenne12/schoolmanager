/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Sequence;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface SequenceFacadeLocal {

    void create(Sequence sequence);

    void edit(Sequence sequence);

    void remove(Sequence sequence);

    Sequence find(Object id);

    List<Sequence> findAll();

    List<Sequence> findRange(int[] range);

    int count();

    Sequence findByNom(String nom);

}
