/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Classecategorie;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gervais
 */
@Stateless
public class ClassecategorieFacade extends AbstractFacade<Classecategorie> implements ClassecategorieFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClassecategorieFacade() {
        super(Classecategorie.class);
    }

    @Override
    public Integer nextVal() throws Exception {
        Query query = em.createQuery("SELECT  MAX(c.id) FROM Classecategorie c");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public List<Classecategorie> findByClasseCategorie(int classe, int categorie) {
        List<Classecategorie> results = null;
        Query query;
        try {
            query = em.createQuery("SELECT c FROM Classecategorie c WHERE c.idclasse.idclasse = :classe AND c.idcategorie.idcategorie=:categorie");
            query.setParameter("classe", classe).setParameter("categorie", categorie);
            results = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return results;
    }

    @Override
    public Classecategorie getClasseCategorieByIdClasse(int classe) {
        Classecategorie classeCategorie = null;
        try {
            Query temp = em.createQuery("SELECT MAX(c.id) FROM Classecategorie c WHERE c.idclasse.idclasse=:classe");
            temp.setParameter("classe", classe);
            Integer resultTemp = null;

            if (!temp.getResultList().isEmpty()) {
                resultTemp = (Integer) temp.getResultList().get(temp.getResultList().size() - 1);
            }

            if (resultTemp != null) {
                Query query = em.createQuery("SELECT c FROM Classecategorie c WHERE c.id=:id");
                query.setParameter("id", resultTemp);
                if (!query.getResultList().isEmpty()) {
                    classeCategorie = (Classecategorie) query.getResultList().get((query.getResultList().size() - 1));
                }
            }
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche de la classe de categorie " + e.getMessage());
        }
        return classeCategorie;
    }

    @Override
    public List<Classecategorie> get(boolean etat) {
        List<Classecategorie> results = null;
        try {
            Query query = em.createQuery("SELECT c FROM Classecategorie c WHERE c.idclasse.etat = :etat");
            query.setParameter("etat", etat);
            results = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return results;
    }

    @Override
    public List<Classecategorie> get(int etablissement, boolean etat) {
        List<Classecategorie> results = null;
        try {
            Query query = em.createQuery("SELECT c FROM Classecategorie c WHERE c.idclasse.etablissement.id=:etablissement AND c.idclasse.etat = :etat");
            query.setParameter("etat", etat).setParameter("etablissement", etablissement);
            results = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
        }
        return results;
    }
}
