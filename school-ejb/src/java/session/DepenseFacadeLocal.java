/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Annee;
import entities.Depense;
import entities.Etablissement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface DepenseFacadeLocal {

    void create(Depense depense);

    void edit(Depense depense);

    void remove(Depense depense);

    Depense find(Object id);

    List<Depense> findAll();

    List<Depense> findRange(int[] range);

    int count();

    Long nextVal();

    List<Depense> find(Etablissement etablissement, Annee annee) throws Exception;

}
