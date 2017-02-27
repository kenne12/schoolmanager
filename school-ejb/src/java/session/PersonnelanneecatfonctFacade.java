/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Personnelanneecatfonct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne gervais
 */
@Stateless
public class PersonnelanneecatfonctFacade extends AbstractFacade<Personnelanneecatfonct> implements PersonnelanneecatfonctFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonnelanneecatfonctFacade() {
        super(Personnelanneecatfonct.class);
    }

    @Override
    public Personnelanneecatfonct findByPersonnelAnneeCategorie(int personnel, int annee, int categorie) {
        Personnelanneecatfonct result = null;
        Query query;
        try {
            query = em.createQuery("SELECT p FROM Personnelanneecatfonct p WHERE p.personnel.idpersonnel = :personnel AND p.idannee.idannee=:annee AND p.idcatpersonnel.idcatpersonnel=:categorie");
            query.setParameter("personnel", personnel);
            query.setParameter("annee", annee);
            query.setParameter("categorie", categorie);
            result = (Personnelanneecatfonct) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche");
            e.getMessage();
        }
        return result;

    }

}
