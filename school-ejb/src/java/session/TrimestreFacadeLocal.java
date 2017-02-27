/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Trimestre;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface TrimestreFacadeLocal {

    void create(Trimestre trimestre);

    void edit(Trimestre trimestre);

    void remove(Trimestre trimestre);

    Trimestre find(Object id);

    List<Trimestre> findAll();

    List<Trimestre> findRange(int[] range);

    int count();

    Trimestre findByNom(String nom);

}
