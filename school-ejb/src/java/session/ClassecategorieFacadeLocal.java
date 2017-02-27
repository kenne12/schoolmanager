/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Classecategorie;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gervais
 */
@Local
public interface ClassecategorieFacadeLocal {

    void create(Classecategorie classecategorie);

    void edit(Classecategorie classecategorie);

    void remove(Classecategorie classecategorie);

    Classecategorie find(Object id);

    List<Classecategorie> findAll();

    List<Classecategorie> findRange(int[] range);

    int count();

    Integer nextVal() throws Exception;

    List<Classecategorie> findByClasseCategorie(int classe, int categorie);

    //methode qui retrouve une classe categorie a partir de son idclasse
    Classecategorie getClasseCategorieByIdClasse(int classe);

    //methode qui recupere les categories selon l"etat de la classe
    List<Classecategorie> get(boolean etat);

    List<Classecategorie> get(int etablissement, boolean etat);

}
