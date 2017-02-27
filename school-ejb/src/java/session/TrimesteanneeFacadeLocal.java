/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Etablissement;
import entities.Trimesteannee;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface TrimesteanneeFacadeLocal {

    void create(Trimesteannee trimesteannee);

    void edit(Trimesteannee trimesteannee);

    void remove(Trimesteannee trimesteannee);

    Trimesteannee find(Object id);

    List<Trimesteannee> findAll();

    List<Trimesteannee> findRange(int[] range);

    int count();

    Integer nextVal();

    List<Trimesteannee> findByAnneeTrimestre(int annee, int trimestre);
    
    List<Trimesteannee> findByEtablissement(Etablissement etablissement);

    List<Trimesteannee> findByEtat(boolean etat);

    List<Trimesteannee> getByAnneEtat(int annee, boolean etat);

    List<Trimesteannee> getByAnnee(int annee);
}
