/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.PhotoPersonnel;
import java.util.List;
import javax.ejb.Stateless;
import javax.imageio.IIOException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne
 */
@Stateless
public class PhotoPersonnelFacade extends AbstractFacade<PhotoPersonnel> implements PhotoPersonnelFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PhotoPersonnelFacade() {
        super(PhotoPersonnel.class);
    }

    @Override
    public int nextId() throws IIOException {
        Query query = em.createQuery("SELECT  MAX(p.idphoto) FROM PhotoPersonnel p");
        Integer resultat = (Integer) query.getSingleResult();
        if (resultat == null) {
            return 1;
        } else {
            return resultat + 1;
        }
    }

    @Override
    public List<PhotoPersonnel> findByPersonnel(int personnel, int etablissement) throws IIOException {
        List<PhotoPersonnel> photoPersonnels = null;
        Query query = em.createQuery("SELECT p FROM PhotoPersonnel p WHERE p.personnel.idpersonnel=:personnel AND p.etablissement.id=:etablissement");
        query.setParameter("personnel", personnel).setParameter("etablissement", etablissement);
        photoPersonnels = query.getResultList();
        return photoPersonnels;
    }

    @Override
    public List<PhotoPersonnel> findByEtablissement(int etablissement, boolean etat) throws Exception {
        List<PhotoPersonnel> photoPersonnels = null;
        Query query = em.createQuery("SELECT p FROM PhotoPersonnel p WHERE p.etablissement.id=:etablissement AND p.etat=:etat");
        query.setParameter("etat", etat).setParameter("etablissement", etablissement);
        photoPersonnels = query.getResultList();
        return photoPersonnels;
    }

    @Override
    public List<PhotoPersonnel> findByEtablissement(int etablissement) throws Exception {
        List<PhotoPersonnel> photoPersonnels = null;
        Query query = em.createQuery("SELECT p FROM PhotoPersonnel p WHERE p.etablissement.id=:etablissement");
        query.setParameter("etablissement", etablissement);
        photoPersonnels = query.getResultList();
        return photoPersonnels;
    }

}
