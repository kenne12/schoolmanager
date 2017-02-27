/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Naturematiere;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface NaturematiereFacadeLocal {

    void create(Naturematiere naturematiere);

    void edit(Naturematiere naturematiere);

    void remove(Naturematiere naturematiere);

    Naturematiere find(Object id);

    List<Naturematiere> findAll();

    List<Naturematiere> findRange(int[] range);

    int count();

    Naturematiere findByLibelle(String libelle);
    
    List<Naturematiere> findAllRange();

}
