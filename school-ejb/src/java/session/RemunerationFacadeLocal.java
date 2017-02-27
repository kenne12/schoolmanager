/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Remuneration;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface RemunerationFacadeLocal {

    void create(Remuneration remuneration);

    void edit(Remuneration remuneration);

    void remove(Remuneration remuneration);

    Remuneration find(Object id);

    List<Remuneration> findAll();

    List<Remuneration> findRange(int[] range);

    int count();

}
