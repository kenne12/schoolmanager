/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.EtablissementPersonnel;
import entities.Personnel;
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
public class EtablissementPersonnelFacade extends AbstractFacade<EtablissementPersonnel> implements EtablissementPersonnelFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtablissementPersonnelFacade() {
        super(EtablissementPersonnel.class);
    }

    @Override
    public Integer nextVal() {
        Query query = em.createQuery("SELECT MAX(e.id) FROM EtablissementPersonnel e");
        Integer result;
        result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public List<EtablissementPersonnel> find(Personnel personnel) {
        List<EtablissementPersonnel> etablissementPersonnels = null;
        Query query = em.createQuery("SELECT e FROM EtablissementPersonnel e WHERE e.personnel.idpersonnel=:personnel");
        query.setParameter("personnel", personnel.getIdpersonnel());
        etablissementPersonnels = query.getResultList();
        return etablissementPersonnels;
    }

}
