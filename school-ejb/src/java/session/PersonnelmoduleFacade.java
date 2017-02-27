/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Personnelmodule;
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
public class PersonnelmoduleFacade extends AbstractFacade<Personnelmodule> implements PersonnelmoduleFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonnelmoduleFacade() {
        super(Personnelmodule.class);
    }

    @Override
    public Personnelmodule findByPersonnelModule(int personnel, int module) {
        Personnelmodule result = null;
        Query query;
        try {
            query = em.createQuery("SELECT p FROM Personnelmodule p WHERE p.personnel.idpersonnel=:personnel AND p.module.id=:module");
            query.setParameter("personnel", personnel);
            query.setParameter("module", module);
            result = (Personnelmodule) query.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la recherche");
        }
        return result;
    }

    @Override
    public boolean findByPersonnelModule(int personnel, int module, boolean etat) {
        boolean result = false;
        Query query;
        try {
            query = em.createQuery("SELECT p FROM Personnelmodule p WHERE p.personnel.idpersonnel=:personnel AND p.module.id=:module AND p.etat=:etat");
            query.setParameter("personnel", personnel);
            query.setParameter("module", module);
            query.setParameter("etat", etat);
            result = (Personnelmodule) query.getSingleResult() != null;
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la recherche");
        }
        return result;
    }

    @Override
    public List<Personnelmodule> get(int personnel, boolean etat) {
        List<Personnelmodule> modules = null;
        Query query;
        try {
            query = em.createQuery("SELECT p FROM Personnelmodule p WHERE p.personnel.idpersonnel=:personnel AND p.etat=:etat");
            query.setParameter("personnel", personnel);
            query.setParameter("etat", etat);
            modules = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la recherche");
        }
        return modules;
    }

}
