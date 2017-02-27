/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Etablissement;
import entities.Observationnote;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface ObservationnoteFacadeLocal {

    void create(Observationnote observationnote);

    void edit(Observationnote observationnote);

    void remove(Observationnote observationnote);

    Observationnote find(Object id);

    List<Observationnote> findAll();

    List<Observationnote> findRange(int[] range);

    int count();

    Integer nextVal();

    List<Observationnote> findRange(Etablissement etablissement) throws Exception;

    List<Observationnote> find(Etablissement etablissement, String avis) throws Exception;
}
