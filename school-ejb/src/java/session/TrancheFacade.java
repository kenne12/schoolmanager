/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Annee;
import entities.Categorie;
import entities.Tranche;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne
 */
@Stateless
public class TrancheFacade extends AbstractFacade<Tranche> implements TrancheFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrancheFacade() {
        super(Tranche.class);
    }

    @Override
    public Integer nextVal() {
        Query query = em.createQuery("SELECT MAX(t.idtranche) FROM Tranche t");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public Tranche getTypeTrancheById(int id) {
        Tranche typeTranche = null;
        try {
            Query query = em.createNamedQuery("Tranche.findByIdtypetranche");
            query.setParameter("idtypetranche", id);
            typeTranche = (Tranche) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche de l'année" + id);
            e.getMessage();
        }

        return typeTranche;
    }

    @Override
    public List<Tranche> findByCategorieAnnee(Categorie categorie, Annee annee) {
        List<Tranche> tranches = null;
        Query query = em.createQuery("SELECT t FROM Tranche t WHERE t.idcategorie.idcategorie=:categorie AND t.annee.idannee=:annee");
        query.setParameter("categorie", categorie.getIdcategorie()).setParameter("annee", annee.getIdannee());
        tranches = query.getResultList();
        return tranches;
    }

    @Override
    public List<Tranche> getByAnneeCategorie(int annee, int categorie, boolean etat) {
        List<Tranche> results = null;
        try {
            Query query = em.createQuery("SELECT t FROM Tranche t WHERE t.annee.idannee =:annee  AND t.idcategorie.idcategorie=:categorie AND t.annee.etatannee=:etat ");
            query.setParameter("annee", annee).setParameter("categorie", categorie).setParameter("etat", etat);
            results = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche !");
            e.getMessage();
        }
        return results;
    }

    @Override
    public List<Tranche> getTypeTrancheByAnneeActive(boolean etat) {
        List<Tranche> results = null;

        try {
            Query query = em.createQuery("SELECT t FROM Tranche t WHERE t.annee.etatannee=:etat");
            query.setParameter("etat", etat);
            results = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche !");
            e.getMessage();
        }
        return results;
    }

    @Override
    public Tranche getTypeTrancheByIdIdcatClasse(int categorie, int annee, int typeTranche) {
        Tranche result = null;
        try {
            Query query = em.createQuery("SELECT t FROM Tranche t WHERE t.idcategorie.idcategorie=:categorie AND t.annee.idannee=:annee AND t.idtypetranche=:typeTranche");
            query.setParameter("categorie", categorie);
            query.setParameter("annee", annee);
            query.setParameter("typeTranche", typeTranche);
            result = (Tranche) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche !");
            e.getMessage();
        }
        return result;
    }

    @Override
    public List<Tranche> findByAnnee(int annee) {
        List<Tranche> typetranches = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT t FROM Tranche t WHERE t.annee.idannee=:annee");
            query.setParameter("annee", annee);
            typetranches = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return typetranches;
    }

}
