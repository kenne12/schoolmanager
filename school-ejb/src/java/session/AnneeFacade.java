/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Annee;
import entities.Etablissement;
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
public class AnneeFacade extends AbstractFacade<Annee> implements AnneeFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnneeFacade() {
        super(Annee.class);
    }

    @Override
    public Integer nextVal() {
        Query query = em.createQuery("SELECT MAX(a.idannee) FROM Annee a");
        Integer result;
        result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public Annee findByCode(int etablissement, int anneedebut, int anneefin) {
        Annee annee = null;
        try {
            Query query = em.createQuery("SELECT a FROM Annee a WHERE a.etablissement.id=:etablissement AND a.code=:code AND a.codefin=:codefin");
            query.setParameter("code", anneedebut).setParameter("etablissement", etablissement).setParameter("codefin", anneefin);
            if (!query.getResultList().isEmpty()) {
                annee = (Annee) query.getResultList().get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la recheche de l'année");
        }
        return annee;
    }

    @Override
    public List<Annee> findByEtat(Boolean etat) {
        List<Annee> annees = null;
        try {
            Query query = em.createNamedQuery("Annee.findByEtatannee");
            query.setParameter("etatannee", etat);
            annees = query.getResultList();
        } catch (Exception e) {
            System.err.println("Erreur lors de la la recheche de l'année");
        }
        return annees;
    }

    @Override
    public List<Annee> findByEtat(int etablissement, boolean etat) {
        List<Annee> annees = null;
        try {
            Query query = em.createQuery("SELECT a FROM Annee a WHERE a.etablissement.id = :etablissement AND a.etatannee=:etat ORDER BY a.code, a.codefin");
            query.setParameter("etablissement", etablissement).setParameter("etat", etat);
            annees = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annees;
    }

    @Override
    public List<Annee> findByEtablissement(Etablissement etablissement) {
        List<Annee> annees = null;
        try {
            Query query = em.createQuery("SELECT a FROM Annee a WHERE a.etablissement.id = :etablissement ORDER BY a.code, a.codefin");
            query.setParameter("etablissement", etablissement.getId());
            annees = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annees;
    }

    @Override
    public Annee findByEtatSingle(Boolean etat) {
        Annee annee = null;
        try {
            Query query = em.createNamedQuery("Annee.findByEtatannee");
            query.setParameter("etatannee", etat);
            annee = (Annee) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Erreur lors de la la recheche de l'année");
        }
        return annee;
    }

    @Override
    public Annee findById(int id) {
        Annee annee = null;
        try {
            Query query = em.createNamedQuery("Annee.findByIdannee");
            query.setParameter("idannee", id);
            annee = (Annee) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Erreur lors de la la recheche de l'année");
        }
        return annee;
    }

    @Override
    public List<Annee> findDefault(Etablissement etablissement, boolean etat) throws Exception{
        List<Annee> annees = null;
        Query query = em.createQuery("SELECT a FROM Annee a WHERE a.etablissement.id=:etablissement AND a.principal=:etat ORDER BY a.code, a.codefin");
        query.setParameter("etablissement", etablissement.getId()).setParameter("etat", etat);
        annees = query.getResultList();
        return annees;
    }

}
