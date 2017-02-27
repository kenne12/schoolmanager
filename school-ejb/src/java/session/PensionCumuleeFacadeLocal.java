/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.PensionCumulee;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gervais
 */
@Local
public interface PensionCumuleeFacadeLocal {

    void create(PensionCumulee pensionCumulee);

    void edit(PensionCumulee pensionCumulee);

    void remove(PensionCumulee pensionCumulee);

    PensionCumulee find(Object id);

    List<PensionCumulee> findAll();

    List<PensionCumulee> findRange(int[] range);

    int count();
    
    Long nextVal();
    
    PensionCumulee findByEleve(int eleve,int annee);
    
}
