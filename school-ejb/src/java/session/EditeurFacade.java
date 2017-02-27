/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Editeur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne gervais
 */
@Stateless
public class EditeurFacade extends AbstractFacade<Editeur> implements EditeurFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EditeurFacade() {
        super(Editeur.class);
    }

    @Override
    public Editeur find(String nom) {
        Editeur editeur = null;
        Query query;
        try {
            query = em.createNamedQuery("Editeur.findByNom");
            query.setParameter("nom", nom);
            editeur = (Editeur) query.getSingleResult();
        } catch (Exception e) {
            e.getMessage();
        }
        return editeur;
    }

}
