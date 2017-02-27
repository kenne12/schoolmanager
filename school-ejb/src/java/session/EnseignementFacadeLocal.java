/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Enseignement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface EnseignementFacadeLocal {

    void create(Enseignement enseignement);

    void edit(Enseignement enseignement);

    void remove(Enseignement enseignement);

    Enseignement find(Object id);

    List<Enseignement> findAll();

    List<Enseignement> findRange(int[] range);

    int count();

    Enseignement findByNom( int etablissement ,  String nom);

    List<Enseignement> findByEtat(boolean etat);
    
    List<Enseignement> findByEtablissement(int etablissement);

}
