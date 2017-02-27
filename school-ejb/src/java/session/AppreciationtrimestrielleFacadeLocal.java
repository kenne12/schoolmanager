/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Appreciationtrimestrielle;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface AppreciationtrimestrielleFacadeLocal {

    void create(Appreciationtrimestrielle appreciationtrimestrielle);

    void edit(Appreciationtrimestrielle appreciationtrimestrielle);

    void remove(Appreciationtrimestrielle appreciationtrimestrielle);

    Appreciationtrimestrielle find(Object id);

    List<Appreciationtrimestrielle> findAll();

    List<Appreciationtrimestrielle> findRange(int[] range);

    int count();

    Long nextVal();

    Appreciationtrimestrielle find(int eleve, Long matiere, int trimestre) throws Exception;

}
