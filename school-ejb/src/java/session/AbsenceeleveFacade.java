/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Absenceeleve;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author kenne gervais
 */
@Stateless
public class AbsenceeleveFacade extends AbstractFacade<Absenceeleve> implements AbsenceeleveFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AbsenceeleveFacade() {
        super(Absenceeleve.class);
    }

    @Override
    public List<Absenceeleve> getAbsenceEleveByAnneeActive(boolean etat) {
        List<Absenceeleve> absences = null;
        Query query;
        try {
            query = em.createQuery("SELECT a FROM Absenceeleve a WHERE a.idannee.etatannee=:etat");
            query.setParameter("etat", etat);
            absences = query.getResultList();

        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la recherche");
        }
        return absences;
    }

}
