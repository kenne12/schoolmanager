/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Eleveanneeclasse;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface EleveanneeclasseFacadeLocal {

    void create(Eleveanneeclasse eleveanneeclasse);

    void edit(Eleveanneeclasse eleveanneeclasse);

    void remove(Eleveanneeclasse eleveanneeclasse);

    Eleveanneeclasse find(Object id);

    List<Eleveanneeclasse> findAll();

    List<Eleveanneeclasse> findRange(int[] range);

    int count();

    Long nextVal();

    Eleveanneeclasse getEleveAnneeClaseByAnneClasse(int eleve, int annee);

    List<Eleveanneeclasse> findByAnneeClasse(int annee, int classe);

    List<Eleveanneeclasse> get(int annee);

    List<Eleveanneeclasse> get(int annee, boolean etat);

}
