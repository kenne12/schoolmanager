/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Travauxdirige;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kenne gervais
 */
@Stateless
public class TravauxdirigeFacade extends AbstractFacade<Travauxdirige> implements TravauxdirigeFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TravauxdirigeFacade() {
        super(Travauxdirige.class);
    }

}
