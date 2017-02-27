/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Etablissement;
import entities.Typetranche;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TypetrancheFacadeLocal {

    void create(Typetranche typetranche);

    void edit(Typetranche typetranche);

    void remove(Typetranche typetranche);

    Typetranche find(Object id);

    List<Typetranche> findAll();

    List<Typetranche> findRange(int[] range);

    int count();

    Integer nextVal();
    
    List<Typetranche> find(Etablissement etablissement) throws Exception;

    List<Typetranche> find(Etablissement etablissement, String nom) throws Exception;
    
    

}
