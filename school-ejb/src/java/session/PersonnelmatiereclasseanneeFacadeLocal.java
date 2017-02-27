/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Personnelmatiereclasseannee;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface PersonnelmatiereclasseanneeFacadeLocal {

    void create(Personnelmatiereclasseannee personnelmatiereclasseannee);

    void edit(Personnelmatiereclasseannee personnelmatiereclasseannee);

    void remove(Personnelmatiereclasseannee personnelmatiereclasseannee);

    Personnelmatiereclasseannee find(Object id);

    List<Personnelmatiereclasseannee> findAll();

    List<Personnelmatiereclasseannee> findRange(int[] range);

    int count();

    Integer nextVal();

    Personnelmatiereclasseannee findByMatClassePersonnel(int matiere, int classe);

    List<Personnelmatiereclasseannee> getByanneeActive(boolean etat);

    Personnelmatiereclasseannee findByMatClassePersonnelAnnee(int matiere, int classe, int personnel, int annee);

    List<Personnelmatiereclasseannee> getMatiereByPersonnelAnnee(int personnel, int annee);

    Personnelmatiereclasseannee findByClasseMatiereAnnee(int matiere, int classe, boolean annee);

    List<Personnelmatiereclasseannee> get(int personnel, int annee, int classe);

    List<Personnelmatiereclasseannee> get(int annee, int classe, boolean etat);



}
