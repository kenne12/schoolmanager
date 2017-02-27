/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.CompteUtilisateur;
import entities.Etablissement;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author gervais
 */
@Local
public interface CompteUtiliasteurFacadeLocal {

    void create(CompteUtilisateur compteUtilisateur);

    void edit(CompteUtilisateur compteUtilisateur);

    void remove(CompteUtilisateur compteUtilisateur);

    CompteUtilisateur find(Object id);

    List<CompteUtilisateur> findAll();

    List<CompteUtilisateur> findRange(int[] range);

    int count();

    Integer nextVal();

    List<CompteUtilisateur> findByLoginPassword(String login, String password);

    List<CompteUtilisateur> find(Etablissement etablissement, boolean etat) throws Exception;
    
    List<CompteUtilisateur> findByAdmin(Etablissement etablissement, boolean etat) throws Exception;

}
