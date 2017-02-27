/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Sequenceannee;
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
public class SequenceanneeFacade extends AbstractFacade<Sequenceannee> implements SequenceanneeFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SequenceanneeFacade() {
        super(Sequenceannee.class);
    }

    @Override
    public Integer nextVal() {
        Query query = em.createQuery("SELECT MAX(s.idsequencean) FROM Sequenceannee s");
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
    public List<Sequenceannee> findByAnneeSequence(int annee, int sequence) {
        List<Sequenceannee> results = null;
        try {
            Query query = em.createQuery("SELECT s FROM Sequenceannee s WHERE s.idannee.idannee = :annee AND s.idsequence.idsequence =:sequence");
            query.setParameter("annee", annee).setParameter("sequence", sequence);
            results = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche de la sequence de l'année ");
            e.getMessage();
        }
        return results;
    }

    @Override
    public List<Sequenceannee> findByTrimestreSequence(int trimestre, int sequence) {
        List<Sequenceannee> results = null;
        try {
            Query query = em.createQuery("SELECT s FROM Sequenceannee s WHERE s.trimestre.idtrimestrean=:trimestre AND s.idsequence.idsequence =:sequence");
            query.setParameter("trimestre", trimestre).setParameter("sequence", sequence);
            results = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche de la sequence de l'année ");
            e.getMessage();
        }
        return results;
    }

    @Override
    public List<Sequenceannee> findByEtat(boolean etat) {
        List<Sequenceannee> results = null;
        try {
            Query query = em.createQuery("SELECT s FROM Sequenceannee s WHERE s.etat=:etat");
            query.setParameter("etat", etat);
            results = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche de la sequence de l'année ");
            e.getMessage();
        }
        return results;
    }

    @Override
    public List<Sequenceannee> getAnneeSequenceByAnneeActive(boolean etat) {
        List<Sequenceannee> results = null;
        try {
            Query query = em.createQuery("SELECT s FROM Sequenceannee s WHERE s.idannee.etatannee=:etat");
            query.setParameter("etat", etat);
            results = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche de la sequence de l'année ");
            e.getMessage();
        }
        return results;
    }

    @Override
    public List<Sequenceannee> getByAnneEtat(int annee, boolean etat) {
        List<Sequenceannee> resultats = null;
        try {
            Query query = em.createQuery("SELECT t FROM Sequenceannee t WHERE t.idannee.idannee=:annee AND t.etat=:etat ORDER BY t.trimestre.idtrimestre,t.idsequence.numero");
            query.setParameter("annee", annee).setParameter("etat", etat);
            resultats = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultats;
    }

    @Override
    public List<Sequenceannee> getByAnnee(int annee) {
        List<Sequenceannee> resultats = null;
        try {
            Query query = em.createQuery("SELECT t FROM Sequenceannee t WHERE t.idannee.idannee=:annee  ORDER BY t.idsequencean , t.trimestre.idtrimestre , t.idsequence.nom");
            query.setParameter("annee", annee);
            resultats = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche des trimestre de l'année");
        }
        return resultats;
    }

    @Override
    public List<Sequenceannee> getByTrimestre(int trimestre) {
        List<Sequenceannee> sequenceannees = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT s FROM Sequenceannee s WHERE S.trimestre.idtrimestrean=:trimestre");
            query.setParameter("trimestre", trimestre);
            sequenceannees = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sequenceannees;
    }
}
