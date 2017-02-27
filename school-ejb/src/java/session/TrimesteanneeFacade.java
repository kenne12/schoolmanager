/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Etablissement;
import entities.Trimesteannee;
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
public class TrimesteanneeFacade extends AbstractFacade<Trimesteannee> implements TrimesteanneeFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrimesteanneeFacade() {
        super(Trimesteannee.class);
    }

    @Override
    public Integer nextVal() {
        Query query = em.createQuery("SELECT MAX(t.idtrimestrean) FROM Trimesteannee t");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public List<Trimesteannee> findByAnneeTrimestre(int annee, int trimestre) {
        List<Trimesteannee> resultat = null;
        try {
            Query query = em.createQuery("SELECT t From Trimesteannee t WHERE t.idannee.idannee = :annee AND t.idtrimestre.idtrimestre=:trimestre");
            query.setParameter("annee", annee).setParameter("trimestre", trimestre);
            resultat = query.getResultList();
        } catch (Exception e) {
            System.err.println("error = " + e.getMessage());
        }
        return resultat;
    }

    @Override
    public List<Trimesteannee> findByEtablissement(Etablissement etablissement) {
        List<Trimesteannee> trimesteannees = null;
        Query query = em.createQuery("SELECT t FROM Trimesteannee t WHERE t.idannee.etablissement.id=:etablissement");
        query.setParameter("etablissement", etablissement.getId());
        trimesteannees = query.getResultList();
        return trimesteannees;
    }

    @Override
    public List<Trimesteannee> findByEtat(boolean etat) {
        List<Trimesteannee> resultats = null;
        try {
            Query query = em.createNamedQuery("Trimesteannee.findByEtat");
            query.setParameter("etat", etat);
            resultats = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche des trimestre de l'année" + etat);
        }
        return resultats;
    }

    @Override
    public List<Trimesteannee> getByAnneEtat(int annee, boolean etat) {
        List<Trimesteannee> resultats = null;
        try {
            Query query = em.createQuery("SELECT t FROM Trimesteannee t WHERE t.idannee.idannee=:annee AND t.etat=:etat");
            query.setParameter("annee", annee).setParameter("etat", etat);
            resultats = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche des trimestre de l'année" + etat);
        }
        return resultats;
    }

    @Override
    public List<Trimesteannee> getByAnnee(int annee) {
        List<Trimesteannee> resultats = null;
        try {
            Query query = em.createQuery("SELECT t FROM Trimesteannee t WHERE t.idannee.idannee=:annee");
            query.setParameter("annee", annee);
            resultats = query.getResultList();
        } catch (Exception e) {
            System.err.println("" + e.getMessage());
        }
        return resultats;
    }

}
