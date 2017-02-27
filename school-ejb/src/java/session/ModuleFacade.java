/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Module;
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
public class ModuleFacade extends AbstractFacade<Module> implements ModuleFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModuleFacade() {
        super(Module.class);
    }

    @Override
    public Module findByNom(String nom) {
        Module menu = null;
        Query query;
        try {
            query = em.createNamedQuery("Module.findByNom");
            query.setParameter("nom", nom);
            menu = (Module) query.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la reherche");
        }
        return menu;
    }

    @Override
    public List<Module> getModules(boolean etat) {
        List<Module> modules = null;
        Query query;
        try {
            query = em.createNamedQuery("Module.findByEtat");
            query.setParameter("etat", etat);
            modules = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la reherche");
        }
        return modules;
    }

}
