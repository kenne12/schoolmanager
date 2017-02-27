/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Compte;
import entities.Etablissement;
import entities.Typecompte;
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
public class CompteFacade extends AbstractFacade<Compte> implements CompteFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteFacade() {
        super(Compte.class);
    }

    @Override
    public Long nextVal() {
        Query query = em.createQuery("SELECT MAX(c.idcompte) FROM Compte c");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public List<Compte> find(Etablissement etablissement) throws Exception {
        List<Compte> comptes = null;
        Query query = em.createQuery("SELECT c FROM Compte c WHERE c.idetablissement.id=:etablissement ORDER BY c.idtypecompte.classe, c.classe");
        query.setParameter("etablissement", etablissement.getId());
        comptes = query.getResultList();
        return comptes;
    }

    @Override
    public List<Compte> find(Etablissement etablissement, Integer classe) throws Exception {
        List<Compte> comptes = null;
        Query query = em.createQuery("SELECT c FROM Compte c WHERE c.idetablissement.id=:etablissement AND c.classe=:classe ORDER BY c.idtypecompte.classe , c.classe");
        query.setParameter("etablissement", etablissement.getId()).setParameter("classe", classe);
        comptes = query.getResultList();
        return comptes;
    }

    @Override
    public List<Compte> find(Typecompte typecompte) throws Exception {
        List<Compte> comptes = null;
        Query query = em.createQuery("SELECT c FROM Compte c WHERE c.idtypecompte.idtypecompte=:typecompte ORDER BY c.classe,c.libelle");
        query.setParameter("typecompte", typecompte.getIdtypecompte());
        comptes = query.getResultList();
        return comptes;
    }

}
