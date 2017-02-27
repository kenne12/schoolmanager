/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Traceur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author gervais
 */
@Stateless
public class TraceurFacade extends AbstractFacade<Traceur> implements TraceurFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TraceurFacade() {
        super(Traceur.class);
    }

}
