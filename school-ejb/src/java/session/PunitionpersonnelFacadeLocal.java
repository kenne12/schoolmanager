/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Punitionpersonnel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface PunitionpersonnelFacadeLocal {

    void create(Punitionpersonnel punitionpersonnel);

    void edit(Punitionpersonnel punitionpersonnel);

    void remove(Punitionpersonnel punitionpersonnel);

    Punitionpersonnel find(Object id);

    List<Punitionpersonnel> findAll();

    List<Punitionpersonnel> findRange(int[] range);

    int count();

}
