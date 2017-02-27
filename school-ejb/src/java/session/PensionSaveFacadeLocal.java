/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Annee;
import entities.Etablissement;
import entities.PensionSave;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface PensionSaveFacadeLocal {

    void create(PensionSave pensionSave);

    void edit(PensionSave pensionSave);

    void remove(PensionSave pensionSave);

    PensionSave find(Object id);

    List<PensionSave> findAll();

    List<PensionSave> findRange(int[] range);

    int count();

    public Long nextVal();

    public Long nextVal(Annee annee);

    List<PensionSave> findByAnnee(Annee annee) throws Exception;

}
