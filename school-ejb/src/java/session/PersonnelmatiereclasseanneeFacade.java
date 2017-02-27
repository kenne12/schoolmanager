/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Personnelmatiereclasseannee;
import java.util.ArrayList;
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
public class PersonnelmatiereclasseanneeFacade extends AbstractFacade<Personnelmatiereclasseannee> implements PersonnelmatiereclasseanneeFacadeLocal {

    @PersistenceContext(unitName = "school-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonnelmatiereclasseanneeFacade() {
        super(Personnelmatiereclasseannee.class);
    }

    @Override
    public Integer nextVal() {
        Query query = em.createQuery("SELECT  MAX(p.id) FROM Personnelmatiereclasseannee p");
        Integer result = (Integer) query.getSingleResult();
        if (result == null) {
            result = 1;
        } else {
            result += 1;
        }
        return result;
    }

    @Override
    public Personnelmatiereclasseannee findByMatClassePersonnel(int matiere, int classe) {
        Personnelmatiereclasseannee result = null;
        Query query;
        try {
            query = em.createQuery("SELECT p FROM Personnelmatiereclasseannee p WHERE p.idmatiere.idmatiere = :matiere AND p.idclasse.idclasse=:classe");
            query.setParameter("matiere", matiere).setParameter("classe", classe);
            result = (Personnelmatiereclasseannee) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche !");
            e.getMessage();
        }
        return result;
    }

    @Override
    public List<Personnelmatiereclasseannee> getByanneeActive(boolean etat) {
        List<Personnelmatiereclasseannee> results = null;
        Query query;
        try {
            query = em.createQuery("SELECT p FROM Personnelmatiereclasseannee p WHERE p.idannee.etatannee=:etat");
            query.setParameter("etat", etat);
            results = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la recherche");
        }
        return results;
    }

    @Override
    public Personnelmatiereclasseannee findByMatClassePersonnelAnnee(int matiere, int classe, int personnel, int annee) {
        Personnelmatiereclasseannee result = null;
        try {
            Query query = em.createQuery("SELECT p FROM Personnelmatiereclasseannee p WHERE p.idmatiere.idmatiere = :matiere AND p.idclasse.idclasse=:classe AND p.personnel.idpersonnel=:personnel AND p.idannee.idannee=:annee");
            query.setParameter("matiere", matiere).setParameter("classe", classe);
            query.setParameter("personnel", personnel).setParameter("annee", annee);
            result = (Personnelmatiereclasseannee) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche !");
            e.getMessage();
        }
        return result;
    }

    //methode qui recherche les matieres qu'enseigne un prof aucours de l'annee active
    @Override
    public List<Personnelmatiereclasseannee> getMatiereByPersonnelAnnee(int personnel, int annee) {
        List<Personnelmatiereclasseannee> results = null;
        Query query;
        try {
            query = em.createQuery("SELECT p FROM Personnelmatiereclasseannee p WHERE p.personnel.idpersonnel=:personnel AND p.idannee.idannee =:annee");
            query.setParameter("personnel", personnel);
            query.setParameter("annee", annee);
            results = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la recherche");
        }
        return results;
    }

    @Override
    public Personnelmatiereclasseannee findByClasseMatiereAnnee(int matiere, int classe, boolean annee) {
        Personnelmatiereclasseannee result = null;
        try {
            Query query = em.createQuery("SELECT p FROM Personnelmatiereclasseannee p WHERE p.idmatiere.idmatiere = :matiere AND p.idclasse.idclasse=:classe AND p.idannee.etatannee=:annee");
            query.setParameter("matiere", matiere);
            query.setParameter("classe", classe);
            query.setParameter("annee", annee);
            result = (Personnelmatiereclasseannee) query.getSingleResult();
        } catch (Exception e) {
            System.err.println("erreur lors de la recherche !");
            e.getMessage();
        }
        return result;
    }

    //methode qui retoune la liste des matieres enseignees par un personnel aucours dune anne
    @Override
    public List<Personnelmatiereclasseannee> get(int personnel, int annee, int classe) {
        List<Personnelmatiereclasseannee> results = null;

        try {
            Query query = em.createQuery("SELECT p FROM Personnelmatiereclasseannee p WHERE p.personnel.idpersonnel=:personnel AND p.idannee.idannee =:annee AND p.idclasse.idclasse = :classe");
            query.setParameter("personnel", personnel).setParameter("annee", annee).setParameter("classe", classe);
            results = query.getResultList();
        } catch (Exception e) {
            e.getMessage();
            System.err.println("erreur lors de la recherche");
        }
        return results;
    }

    @Override
    public List<Personnelmatiereclasseannee> get(int annee, int classe, boolean etat) {
        List<Personnelmatiereclasseannee> results = new ArrayList<>();
        try {
            Query query = em.createQuery("SELECT p FROM Personnelmatiereclasseannee p WHERE p.idannee.idannee = :annee AND p.idclasse.idclasse =:classe AND p.etat =:etat");
            query.setParameter("annee", annee).setParameter("classe", classe).setParameter("etat", etat);
            results = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    

}
