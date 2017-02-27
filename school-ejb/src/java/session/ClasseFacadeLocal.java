/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Classe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface ClasseFacadeLocal {

    void create(Classe classe);

    void edit(Classe classe);

    void remove(Classe classe);

    Classe find(Object id);

    List<Classe> findAll();

    List<Classe> findRange(int[] range);

    int count();

    Integer nextVal();

    Classe findClassByNom(String nom);

    List<Classe> findByEtat(boolean etat);

    List<Classe> findByEtaBlissement(int etablissement, boolean etat);

    List<Classe> findByEtaBlissement(int etablissement);

}
