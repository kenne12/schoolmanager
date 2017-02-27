/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbean.salle;

import entities.Batiment;
import entities.Salle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import session.SalleFacadeLocal;

/**
 *
 * @author kenne gervais
 */
@ManagedBean
@SessionScoped
public class SalleCtrl1 {

    @EJB
    protected SalleFacadeLocal salleFacadeLocal;
    protected Salle salle = new Salle();

    protected Batiment batiment = new Batiment();

    /**
     * Creates a new instance of SalleCtrl1
     */
    public SalleCtrl1() {
        salle = new Salle();
        batiment = new Batiment();
    }

    public void enregistrerClasse() {
        System.out.println(salle.getCode());
        System.out.println(salle.getNombrebanc());
        System.out.println(salle.getNombreplace());
        System.out.println(salle.getIdbatiment().getIdbatiment());
        batiment.setIdbatiment(1);
        System.out.println("batiment" + batiment.getIdbatiment());

    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public Batiment getBatiment() {
        return batiment;
    }

    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }

}
