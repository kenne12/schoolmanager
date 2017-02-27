/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Typeoperation;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kenne
 */
@Stateless
public class TypeoperationFacade extends AbstractFacade<Typeoperation> implements TypeoperationFacadeLocal {
    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeoperationFacade() {
        super(Typeoperation.class);
    }
    
}
