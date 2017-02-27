/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Editeur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface EditeurFacadeLocal {

    void create(Editeur editeur);

    void edit(Editeur editeur);

    void remove(Editeur editeur);

    Editeur find(Object id);

    List<Editeur> findAll();

    List<Editeur> findRange(int[] range);

    int count();

    Editeur find(String nom);

}
