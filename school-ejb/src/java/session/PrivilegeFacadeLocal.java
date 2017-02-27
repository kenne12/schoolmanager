/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.CompteUtilisateur;
import entities.Menu;
import entities.Privilege;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface PrivilegeFacadeLocal {

    void create(Privilege privilege);

    void edit(Privilege privilege);

    void remove(Privilege privilege);

    Privilege find(Object id);

    List<Privilege> findAll();

    List<Privilege> findRange(int[] range);

    int count();

    Long nextVal();

    List<Privilege> find(CompteUtilisateur compteUtilisateur, Menu menu) throws Exception;

    List<Privilege> find(CompteUtilisateur compteUtilisateur) throws Exception;

}
