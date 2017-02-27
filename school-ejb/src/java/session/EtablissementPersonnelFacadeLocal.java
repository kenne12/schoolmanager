/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.EtablissementPersonnel;
import entities.Personnel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gervais
 */
@Local
public interface EtablissementPersonnelFacadeLocal {

    void create(EtablissementPersonnel etablissementPersonnel);

    void edit(EtablissementPersonnel etablissementPersonnel);

    void remove(EtablissementPersonnel etablissementPersonnel);

    EtablissementPersonnel find(Object id);

    List<EtablissementPersonnel> findAll();

    List<EtablissementPersonnel> findRange(int[] range);

    int count();

    Integer nextVal();

    List<EtablissementPersonnel> find(Personnel personnel);
}
