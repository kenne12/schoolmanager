/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Semaine;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface SemaineFacadeLocal {

    void create(Semaine semaine);

    void edit(Semaine semaine);

    void remove(Semaine semaine);

    Semaine find(Object id);

    List<Semaine> findAll();

    List<Semaine> findRange(int[] range);

    int count();

}
