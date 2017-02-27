/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Categorie;
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
public class CategorieFacade extends AbstractFacade<Categorie> implements CategorieFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategorieFacade() {
        super(Categorie.class);
    }

    @Override
    public List<Categorie> findByNom(int etablissement, String nom) {
        List<Categorie> cat = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT c FROM Categorie c WHERE c.etablissement.id=:etablissement AND UPPER(c.nom)=:nom ");
            query.setParameter("nom", nom.toUpperCase()).setParameter("etablissement", etablissement);
            cat = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("Erreur lors de la recherche de la categorie : " + nom);
        }
        return cat;
    }

    @Override
    public List<Categorie> findByEtat(boolean etat) {
        List<Categorie> categories = null;
        try {
            Query query = em.createNamedQuery("Categorie.findByEtat");
            query.setParameter("etat", etat);
            categories = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return categories;
    }

    @Override
    public List<Categorie> findByEtablisssement(int etablissement) {
        List<Categorie> categories = null;
        try {
            Query query = em.createQuery("SELECT c FROM Categorie c WHERE c.etablissement.id = :etablissement");
            query.setParameter("etablissement", etablissement);
            categories = query.getResultList();
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return categories;
    }

    @Override
    public List<Categorie> findByEtablisssement(int etablissement, boolean etat) {
        List<Categorie> categories = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT c FROM Categorie c WHERE c.etablissement.id=:etablissement AND c.etat=:etat");
            query.setParameter("etablissement", etablissement).setParameter("etat", etat);
            categories = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

}
