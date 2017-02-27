/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Personnelmenu;
import entities.Personnelmodule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author gervais
 */
@Stateless
public class PersonnelmenuFacade extends AbstractFacade<Personnelmenu> implements PersonnelmenuFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonnelmenuFacade() {
        super(Personnelmenu.class);
    }

    @Override
    public Personnelmenu findByPersonnelMenu(int personnel, int menu) {
        Personnelmenu result = null;
        Query query;
        try {
            query = em.createQuery("SELECT p FROM Personnelmenu p WHERE p.personnel.idpersonnel=:personnel AND p.menu.id=:menu");
            query.setParameter("personnel", personnel);
            query.setParameter("menu", menu);
            result = (Personnelmenu) query.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la recherche");
        }
        return result;
    }

}
