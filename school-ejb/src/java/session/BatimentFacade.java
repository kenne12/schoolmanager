/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Batiment;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne gervais
 */
@Stateless
public class BatimentFacade extends AbstractFacade<Batiment> implements BatimentFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BatimentFacade() {
        super(Batiment.class);
    }

    @Override
    public Batiment getBatiment(String code) {
        Batiment batiment = null;
        Query query;
        try {
            query = em.createNamedQuery("Batiment.findByCode");
            query.setParameter("code", code);
            batiment = (Batiment) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Erreur lors de la la recheche de l'annee : " + code);
        }
        return batiment;
    }

    /* @Override
     public List<Batiment> getBatiments(boolean etat) {
     List<Batiment> batiments = new ArrayList<>();
     Query query;
     try {
     query = em.createNamedQuery("Batiment.findByEtat");
     query.setParameter("etat", etat);
     batiments = query.getResultList();
     } catch (Exception e) {
     System.err.println("erreur lors de la recherche de l'année");
     }
     return batiments;
     }*/
    @Override
    public List<Batiment> getBatiments(String nom) {
        List<Batiment> batiments = new ArrayList<>();
        Query query;
        try {
            query = em.createNamedQuery("Batiment.findByNom");
            query.setParameter("nom", nom);
            batiments = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche du batiment de nom : " + nom);
        }
        return batiments;
    }

    @Override
    public Batiment findByCode(int etablissement, String code) {
        Query q;
        try {
            q = em.createQuery("SELECT b FROM Batiment b WHERE UPPER(b.code)= ?1 AND b.etablissement.id=?2");
            q.setParameter(1, code.toUpperCase());
            q.setParameter(2, etablissement);
            Batiment batiment = (Batiment) q.getSingleResult();
            return batiment;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public  List<Batiment> findByEtablissement(int etablissement) {
        List<Batiment> batiments = null;
        Query query = em.createQuery("SELECT b FROM Batiment b WHERE b.etablissement.id=:etablissement");
        query.setParameter("etablissement", etablissement);
        batiments = query.getResultList();
        return batiments;
    }

}
