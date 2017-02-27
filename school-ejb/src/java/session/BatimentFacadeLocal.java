/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Batiment;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kenne gervais
 */
@Local
public interface BatimentFacadeLocal {

    void create(Batiment batiment);

    void edit(Batiment batiment);

    void remove(Batiment batiment);

    Batiment find(Object id);

    List<Batiment> findAll();

    List<Batiment> findRange(int[] range);

    int count();

    /**
     * @since 08 07 2015 : 14h20 version1.0 larissa
     * @param code
     * @return le bâtiment dont le code est passé en paramètre
     */
    public Batiment getBatiment(String code);

    public List<Batiment> getBatiments(String nom);

    public Batiment findByCode(int etablissement, String code);
    
    public  List<Batiment> findByEtablissement(int etablissement);

}
