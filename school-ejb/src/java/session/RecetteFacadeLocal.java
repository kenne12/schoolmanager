/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Annee;
import entities.Etablissement;
import entities.Recette;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface RecetteFacadeLocal {

    void create(Recette recette);

    void edit(Recette recette);

    void remove(Recette recette);

    Recette find(Object id);

    List<Recette> findAll();

    List<Recette> findRange(int[] range);

    int count();

    Long nextVal();

    Long nextVal(Annee annee);

    List<Recette> find(Etablissement etablissement, Annee annee) throws Exception;

}
