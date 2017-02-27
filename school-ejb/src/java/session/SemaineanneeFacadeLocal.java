/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Semaineannee;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface SemaineanneeFacadeLocal {

    void create(Semaineannee semaineannee);

    void edit(Semaineannee semaineannee);

    void remove(Semaineannee semaineannee);

    Semaineannee find(Object id);

    List<Semaineannee> findAll();

    List<Semaineannee> findRange(int[] range);

    int count();

}
