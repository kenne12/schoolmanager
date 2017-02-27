/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.TypeEtablissement;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kenne
 */
@Stateless
public class TypeEtablissementFacade extends AbstractFacade<TypeEtablissement> implements TypeEtablissementFacadeLocal {
    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeEtablissementFacade() {
        super(TypeEtablissement.class);
    }
    
}
