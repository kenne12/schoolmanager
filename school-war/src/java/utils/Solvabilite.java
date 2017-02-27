/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.Eleve;

/**
 *
 * @author kenne
 */
public class Solvabilite {

    private Eleve eleve;
    private Integer paye ;
    private Integer reste;

    public Solvabilite() {

    }

    public Solvabilite(Eleve eleve,Integer paye, Integer reste) {
        this.eleve = eleve;
        this.reste = reste;
        this.paye = paye;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Integer getReste() {
        return reste;
    }

    public void setReste(Integer reste) {
        this.reste = reste;
    }

    public Integer getPaye() {
        return paye;
    }

    public void setPaye(Integer paye) {
        this.paye = paye;
    }
    
    

}
