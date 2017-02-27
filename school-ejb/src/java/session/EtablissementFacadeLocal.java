/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Etablissement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gervais
 */
@Local
public interface EtablissementFacadeLocal {

    void create(Etablissement etablissement);

    void edit(Etablissement etablissement);

    void remove(Etablissement etablissement);

    Etablissement find(Object id);

    List<Etablissement> findAll();

    List<Etablissement> findRange(int[] range);

    int count();

    Etablissement findByNom(String nom);

    int nextId();

}
