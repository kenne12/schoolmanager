/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Matiere;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface MatiereFacadeLocal {

    void create(Matiere matiere);

    void edit(Matiere matiere);

    void remove(Matiere matiere);

    Matiere find(Object id);

    List<Matiere> findAll();

    List<Matiere> findRange(int[] range);

    int count();

    Integer nextVal();
    
    List<Matiere>findByLibelle(int natureMatiere , String libelle);
    
    List<Matiere> findAllRange();

}
