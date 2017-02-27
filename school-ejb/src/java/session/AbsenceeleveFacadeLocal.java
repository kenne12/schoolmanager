/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Absenceeleve;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface AbsenceeleveFacadeLocal {

    void create(Absenceeleve absenceeleve);

    void edit(Absenceeleve absenceeleve);

    void remove(Absenceeleve absenceeleve);

    Absenceeleve find(Object id);

    List<Absenceeleve> findAll();

    List<Absenceeleve> findRange(int[] range);

    int count();

    List<Absenceeleve> getAbsenceEleveByAnneeActive(boolean etat);

}
