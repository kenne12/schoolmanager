/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Annee;
import entities.Categorie;
import entities.Tranche;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne
 */
@Local
public interface TrancheFacadeLocal {

    void create(Tranche tranche);

    void edit(Tranche tranche);

    void remove(Tranche tranche);

    Tranche find(Object id);

    List<Tranche> findAll();

    List<Tranche> findRange(int[] range);

    int count();

    Integer nextVal();

    Tranche getTypeTrancheById(int id);

    List<Tranche> findByCategorieAnnee(Categorie categorie, Annee annee);

    List<Tranche> getByAnneeCategorie(int annee, int categorie, boolean etat);

    List<Tranche> getTypeTrancheByAnneeActive(boolean etat);

    Tranche getTypeTrancheByIdIdcatClasse(int categorie, int annee, int typeTranche);

    List<Tranche> findByAnnee(int annee);

}
