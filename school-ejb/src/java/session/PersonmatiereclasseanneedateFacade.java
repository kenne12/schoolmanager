/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Personmatiereclasseanneedate;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kenne gervais
 */
@Stateless
public class PersonmatiereclasseanneedateFacade extends AbstractFacade<Personmatiereclasseanneedate> implements PersonmatiereclasseanneedateFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonmatiereclasseanneedateFacade() {
        super(Personmatiereclasseanneedate.class);
    }

}
