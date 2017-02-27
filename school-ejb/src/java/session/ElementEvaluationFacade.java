/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.ElementEvaluation;
import java.util.ArrayList;
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
public class ElementEvaluationFacade extends AbstractFacade<ElementEvaluation> implements ElementEvaluationFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ElementEvaluationFacade() {
        super(ElementEvaluation.class);
    }

    @Override
    public Integer nextVal() {
        Query query = em.createQuery("SELECT  MAX(e.idelement) FROM ElementEvaluation e");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public List<ElementEvaluation> findByLibelle(int matiere, String libelle) {
        List<ElementEvaluation> elementEvaluations = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT e FROM ElementEvaluation e WHERE e.matiere.idmatiere =:matiere AND UPPER(e.nom)=:libelle");
            query.setParameter("matiere", matiere).setParameter("libelle", libelle);
            elementEvaluations = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return elementEvaluations;
    }

    @Override
    public List<ElementEvaluation> findAllRange() {
        List<ElementEvaluation> elementEvaluations = null;
        Query query = em.createQuery("SELECT e FROM ElementEvaluation e ORDER BY e.matiere.idnaturematiere.libelle , e.matiere.libelle, e.nom");
        elementEvaluations = query.getResultList();
        return elementEvaluations;
    }

}
