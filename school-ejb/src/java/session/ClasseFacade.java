/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Classe;
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
public class ClasseFacade extends AbstractFacade<Classe> implements ClasseFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClasseFacade() {
        super(Classe.class);
    }

    @Override
    public Integer nextVal() {
        Query query = em.createQuery("SELECT MAX(c.idclasse) FROM Classe c");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public Classe findClassByNom(String nom) {
        Classe classe = null;
        try {
            Query query = em.createNamedQuery("Classe.findByNom");
            query.setParameter("nom", nom);
            classe = (Classe) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("Erreur lors de la recherche de la classe avec le code : " + nom);
        }
        return classe;
    }

    @Override
    public List<Classe> findByEtat(boolean etat) {
        List<Classe> classes = null;
        try {
            Query query = em.createNamedQuery("Classe.findByEtat");
            query.setParameter("etat", etat);
            classes = query.getResultList();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche de la classe");
            e.getMessage();
        }
        return classes;
    }

    @Override
    public List<Classe> findByEtaBlissement(int etablissement, boolean etat) {
        List<Classe> classes = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT c FROM Classe c WHERE c.etablissement.id =:etablissement AND c.etat=:etat ORDER BY c.niveau , c.niveau");
            query.setParameter("etablissement", etablissement).setParameter("etat", etat);
            classes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

    @Override
    public List<Classe> findByEtaBlissement(int etablissement) {
        List<Classe> classes = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT c FROM Classe c WHERE c.etablissement.id =:etablissement ORDER BY c.niveau,c.nom");
            query.setParameter("etablissement", etablissement);
            classes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

}
