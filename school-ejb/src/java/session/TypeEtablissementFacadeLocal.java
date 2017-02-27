/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.TypeEtablissement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TypeEtablissementFacadeLocal {

    void create(TypeEtablissement typeEtablissement);

    void edit(TypeEtablissement typeEtablissement);

    void remove(TypeEtablissement typeEtablissement);

    TypeEtablissement find(Object id);

    List<TypeEtablissement> findAll();

    List<TypeEtablissement> findRange(int[] range);

    int count();
    
}
