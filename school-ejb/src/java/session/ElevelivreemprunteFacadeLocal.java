/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Elevelivreemprunte;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface ElevelivreemprunteFacadeLocal {

    void create(Elevelivreemprunte elevelivreemprunte);

    void edit(Elevelivreemprunte elevelivreemprunte);

    void remove(Elevelivreemprunte elevelivreemprunte);

    Elevelivreemprunte find(Object id);

    List<Elevelivreemprunte> findAll();

    List<Elevelivreemprunte> findRange(int[] range);

    int count();
    
    Long nextVal();

    List<Elevelivreemprunte> get(boolean emprunte);

    List<Elevelivreemprunte> get(int annee, int eleve, boolean etat);

    Elevelivreemprunte get(int eleve, Long livre);

    List<Elevelivreemprunte> get(int eleve, boolean etat);

}
