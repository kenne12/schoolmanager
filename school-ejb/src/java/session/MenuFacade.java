/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Menu;
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
public class MenuFacade extends AbstractFacade<Menu> implements MenuFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuFacade() {
        super(Menu.class);
    }

    @Override
    public Menu findByNom(String nom) {
        Menu menu = null;
        Query query;
        try {
            query = em.createNamedQuery("Menu.findByNom");
            query.setParameter("nom", nom);
            menu = (Menu) query.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la reherche");
        }
        return menu;
    }

    @Override
    public List<Menu> getMenuByEtat(boolean etat) {
        List<Menu> menus = null;
        Query query;
        try {
            query = em.createNamedQuery("Menu.findByEtat");
            query.setParameter("etat", etat);
            menus = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la reherche");
        }
        return menus;
    }

}
