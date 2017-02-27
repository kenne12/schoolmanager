/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Etablissement;
import entities.Typecompte;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TypecompteFacadeLocal {

    void create(Typecompte typecompte);

    void edit(Typecompte typecompte);

    void remove(Typecompte typecompte);

    Typecompte find(Object id);

    List<Typecompte> findAll();

    List<Typecompte> findRange(int[] range);

    int count();

    Integer nextVal();

    List<Typecompte> find(Etablissement etablissement) throws Exception;

    List<Typecompte> find(Etablissement etablissement, Integer classe) throws Exception;

}
