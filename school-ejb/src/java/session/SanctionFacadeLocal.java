/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Sanction;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface SanctionFacadeLocal {

    void create(Sanction sanction);

    void edit(Sanction sanction);

    void remove(Sanction sanction);

    Sanction find(Object id);

    List<Sanction> findAll();

    List<Sanction> findRange(int[] range);

    int count();

    Sanction findBylibelle(String libelle);

}
