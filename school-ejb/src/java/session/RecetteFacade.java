/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Annee;
import entities.Depense;
import entities.Etablissement;
import entities.Recette;
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
public class RecetteFacade extends AbstractFacade<Recette> implements RecetteFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecetteFacade() {
        super(Recette.class);
    }

    @Override
    public Long nextVal() {
        Query query = em.createQuery("SELECT MAX(r.idrecette) FROM Recette r");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1L;
        }
        return result;
    }

    @Override
    public Long nextVal(Annee annee) {
        Query query = em.createQuery("SELECT COUNT(r.idrecette) FROM Recette r WHERE r.idannee.idannee=:annee");
        query.setParameter("annee", annee.getIdannee());
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1L;
        }
        return result;
    }

    @Override
    public List<Recette> find(Etablissement etablissement, Annee annee) throws Exception {
        List<Recette> recettes = null;
        Query query = em.createQuery("SELECT r FROM Recette r WHERE r.idetablissement.id=:etablissement AND r.idannee.idannee=:annee ORDER BY r.idoperation.idcompte.classe");
        query.setParameter("etablissement", etablissement.getId()).setParameter("annee", annee.getIdannee());
        recettes = query.getResultList();
        return recettes;
    }

}
