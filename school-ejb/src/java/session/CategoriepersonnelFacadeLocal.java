/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Categoriepersonnel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface CategoriepersonnelFacadeLocal {

    void create(Categoriepersonnel categoriepersonnel);

    void edit(Categoriepersonnel categoriepersonnel);

    void remove(Categoriepersonnel categoriepersonnel);

    Categoriepersonnel find(Object id);

    List<Categoriepersonnel> findAll();

    List<Categoriepersonnel> findRange(int[] range);

    int count();

    List<Categoriepersonnel> findByEtat(boolean etat);

    List<Categoriepersonnel> findByCode(String code);

}
