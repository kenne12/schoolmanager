/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Compte;
import entities.Etablissement;
import entities.Typecompte;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface CompteFacadeLocal {

    void create(Compte compte);

    void edit(Compte compte);

    void remove(Compte compte);

    Compte find(Object id);

    List<Compte> findAll();

    List<Compte> findRange(int[] range);

    int count();

    Long nextVal();

    List<Compte> find(Etablissement etablissement) throws Exception;
    
    List<Compte> find(Etablissement etablissement, Integer classe) throws Exception;
    
    List<Compte> find(Typecompte typecompte) throws Exception;
}
