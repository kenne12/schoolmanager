/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Categorie;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface CategorieFacadeLocal {

    void create(Categorie categorie);

    void edit(Categorie categorie);

    void remove(Categorie categorie);

    Categorie find(Object id);

    List<Categorie> findAll();

    List<Categorie> findRange(int[] range);

    int count();

    List<Categorie> findByNom(int etablissement, String nom);

    List<Categorie> findByEtat(boolean etat);

    List<Categorie> findByEtablisssement(int etablissement);

    List<Categorie> findByEtablisssement(int etablissement, boolean etat);

}
