/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Appreciationannuelle;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface AppreciationannuelleFacadeLocal {

    void create(Appreciationannuelle appreciationannuelle);

    void edit(Appreciationannuelle appreciationannuelle);

    void remove(Appreciationannuelle appreciationannuelle);

    Appreciationannuelle find(Object id);

    List<Appreciationannuelle> findAll();

    List<Appreciationannuelle> findRange(int[] range);

    int count();

    Long nextVal();

    Appreciationannuelle find(int eleve, Long matiere, int annee) throws Exception;

}
