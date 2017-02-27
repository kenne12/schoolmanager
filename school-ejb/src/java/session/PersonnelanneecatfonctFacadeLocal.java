/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Personnelanneecatfonct;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface PersonnelanneecatfonctFacadeLocal {

    void create(Personnelanneecatfonct personnelanneecatfonct);

    void edit(Personnelanneecatfonct personnelanneecatfonct);

    void remove(Personnelanneecatfonct personnelanneecatfonct);

    Personnelanneecatfonct find(Object id);

    List<Personnelanneecatfonct> findAll();

    List<Personnelanneecatfonct> findRange(int[] range);

    int count();

    Personnelanneecatfonct findByPersonnelAnneeCategorie(int personnel, int annee, int categorie);

}
