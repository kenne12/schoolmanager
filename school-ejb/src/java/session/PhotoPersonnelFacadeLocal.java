/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.PhotoPersonnel;
import java.util.List;
import javax.ejb.Local;
import javax.imageio.IIOException;

/**
 *
 * @author kenne
 */
@Local
public interface PhotoPersonnelFacadeLocal {

    void create(PhotoPersonnel photoPersonnel);

    void edit(PhotoPersonnel photoPersonnel);

    void remove(PhotoPersonnel photoPersonnel);

    PhotoPersonnel find(Object id);

    List<PhotoPersonnel> findAll();

    List<PhotoPersonnel> findRange(int[] range);

    int count();

    int nextId() throws IIOException;

    List<PhotoPersonnel> findByPersonnel(int personnel, int etablissement) throws IIOException;

    List<PhotoPersonnel> findByEtablissement(int etablissement, boolean etat) throws Exception;

    List<PhotoPersonnel> findByEtablissement(int etablissement) throws Exception;
}
