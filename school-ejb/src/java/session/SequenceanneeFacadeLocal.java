/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Sequenceannee;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface SequenceanneeFacadeLocal {

    void create(Sequenceannee sequenceannee);

    void edit(Sequenceannee sequenceannee);

    void remove(Sequenceannee sequenceannee);

    Sequenceannee find(Object id);

    List<Sequenceannee> findAll();

    List<Sequenceannee> findRange(int[] range);

    int count();

    Integer nextVal();

    List<Sequenceannee> findByAnneeSequence(int annee, int sequence);

    List<Sequenceannee> findByTrimestreSequence(int trimestre, int sequence);

    List<Sequenceannee> findByEtat(boolean etat);

    List<Sequenceannee> getAnneeSequenceByAnneeActive(boolean etat);

    List<Sequenceannee> getByAnneEtat(int annee, boolean etat);

    List<Sequenceannee> getByAnnee(int annee);

    List<Sequenceannee> getByTrimestre(int trimestre);

}
