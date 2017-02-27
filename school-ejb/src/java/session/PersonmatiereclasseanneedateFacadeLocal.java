/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Personmatiereclasseanneedate;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface PersonmatiereclasseanneedateFacadeLocal {

    void create(Personmatiereclasseanneedate personmatiereclasseanneedate);

    void edit(Personmatiereclasseanneedate personmatiereclasseanneedate);

    void remove(Personmatiereclasseanneedate personmatiereclasseanneedate);

    Personmatiereclasseanneedate find(Object id);

    List<Personmatiereclasseanneedate> findAll();

    List<Personmatiereclasseanneedate> findRange(int[] range);

    int count();

}
