/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Eleveanneeclasse;
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
public class EleveanneeclasseFacade extends AbstractFacade<Eleveanneeclasse> implements EleveanneeclasseFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EleveanneeclasseFacade() {
        super(Eleveanneeclasse.class);
    }

    @Override
    public Long nextVal() {
        Query query = em.createQuery("SELECT MAX(e.id) FROM Eleveanneeclasse e");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1;
        }
        return result;
    }

    //methode qui retourne la classe de l'eleve pendant l'année en cours
    @Override
    public Eleveanneeclasse getEleveAnneeClaseByAnneClasse(int eleve, int annee) {
        Eleveanneeclasse result = null;
        Query query;
        try {
            query = em.createQuery("SELECT e FROM Eleveanneeclasse e WHERE e.eleve.ideleve=:eleve AND e.idannee.idannee=:annee ORDER BY e.eleve.nom , e.eleve.prenom, e.eleve.matricule");
            query.setParameter("eleve", eleve);
            query.setParameter("annee", annee);
            result = (Eleveanneeclasse) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche");
            e.getMessage();
        }
        return result;
    }

    @Override
    public List<Eleveanneeclasse> findByAnneeClasse(int annee, int classe) {
        List<Eleveanneeclasse> results = null;
        Query query;
        try {
            query = em.createQuery("SELECT e FROM Eleveanneeclasse e WHERE e.idannee.idannee=:annee AND e.idclasse.idclasse=:classe ORDER BY e.eleve.nom , e.eleve.prenom, e.eleve.matricule");
            query.setParameter("annee", annee);
            query.setParameter("classe", classe);
            results = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche");
            e.getMessage();
        }
        return results;
    }

    @Override
    public List<Eleveanneeclasse> get(int annee) {
        List<Eleveanneeclasse> results = null;
        Query query;
        try {
            query = em.createQuery("SELECT e FROM Eleveanneeclasse e WHERE e.idannee.idannee=:annee ORDER BY e.eleve.nom , e.eleve.prenom, e.eleve.matricule");
            query.setParameter("annee", annee);
            results = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche");
            e.getMessage();
        }
        return results;
    }
    
    @Override
    public List<Eleveanneeclasse> get(int annee , boolean etat) {
        List<Eleveanneeclasse> results = null;
        Query query;
        try {
            query = em.createQuery("SELECT e FROM Eleveanneeclasse e WHERE e.idannee.idannee=:annee AND e.eleve.etateleve=:etat ORDER BY e.eleve.nom , e.eleve.prenom, e.eleve.matricule");
            query.setParameter("annee", annee).setParameter("etat", etat);
            results = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche");
            e.getMessage();
        }
        return results;
    }

}
