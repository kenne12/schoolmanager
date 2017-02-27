/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Annee;
import entities.Etablissement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface AnneeFacadeLocal {

    void create(Annee annee);

    void edit(Annee annee);

    void remove(Annee annee);

    Annee find(Object id);

    List<Annee> findAll();

    List<Annee> findRange(int[] range);

    int count();

    Integer nextVal();

    Annee findByCode(int etalissement, int anneedebut, int anneefin);

    List<Annee> findByEtat(Boolean etat);

    List<Annee> findByEtat(int etablissement, boolean etat);

    List<Annee> findByEtablissement(Etablissement etablissement);

    Annee findByEtatSingle(Boolean etat);

    Annee findById(int id);
    
    List<Annee> findDefault(Etablissement etablissement, boolean etat) throws Exception;
}
