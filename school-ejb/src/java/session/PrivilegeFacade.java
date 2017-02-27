/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.CompteUtilisateur;
import entities.Menu;
import entities.Privilege;
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
public class PrivilegeFacade extends AbstractFacade<Privilege> implements PrivilegeFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrivilegeFacade() {
        super(Privilege.class);
    }

    @Override
    public Long nextVal() {
        Query query = em.createQuery("SELECT MAX(p.idprivilege) FROM Privilege p");
        Long result = (Long) query.getSingleResult();
        if (result == null) {
            result = 1L;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public List<Privilege> find(CompteUtilisateur compteUtilisateur, Menu menu) throws Exception {
        List<Privilege> privileges = null;
        Query query = em.createQuery("SELECT p FROM Privilege p WHERE p.idcompte.idcompte=:compte AND p.idmenu.idmenu=:menu");
        query.setParameter("compte", compteUtilisateur.getIdcompte()).setParameter("menu", menu.getIdmenu());
        privileges = query.getResultList();
        return privileges;
    }
    
    @Override
    public List<Privilege> find(CompteUtilisateur compteUtilisateur) throws Exception {
        List<Privilege> privileges = null;
        Query query = em.createQuery("SELECT p FROM Privilege p WHERE p.idcompte.idcompte=:compte");
        query.setParameter("compte", compteUtilisateur.getIdcompte());
        privileges = query.getResultList();
        return privileges;
    }
    

}
