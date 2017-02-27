/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Pension;
import entities.PensionSave;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface PensionFacadeLocal {

    void create(Pension pension);

    void edit(Pension pension);

    void remove(Pension pension);

    Pension find(Object id);

    List<Pension> findAll();

    List<Pension> findRange(int[] range);

    int count();
    
    Long nextVal();

    List<Pension> getPensionByAnneeActive(boolean etat);

    List<Pension> getPensionByEleveTranche(int eleve, int tranche);

    List<Pension> getPensionByAnneeEleve(int annee, int eleve);
    
    List<Pension> findByPensionsave(PensionSave pensionSave)throws Exception;
    
}
