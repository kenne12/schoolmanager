/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Catanneeprix;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface CatanneeprixFacadeLocal {

    void create(Catanneeprix catanneeprix);

    void edit(Catanneeprix catanneeprix);

    void remove(Catanneeprix catanneeprix);

    Catanneeprix find(Object id);

    List<Catanneeprix> findAll();

    List<Catanneeprix> findRange(int[] range);

    int count();

    Integer nextVal();

    List<Catanneeprix> findByAnneeCategorie(int annee, int categorie);

    Catanneeprix typeTrangeGetCatAnnee(int annee, int categorie, boolean etat);
    
    List<Catanneeprix> findByAnnee(int annee);
    

}
