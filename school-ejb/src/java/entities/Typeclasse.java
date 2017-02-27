/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "typeclasse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typeclasse.findAll", query = "SELECT t FROM Typeclasse t"),
    @NamedQuery(name = "Typeclasse.findByIdtypeclasse", query = "SELECT t FROM Typeclasse t WHERE t.idtypeclasse = :idtypeclasse"),
    @NamedQuery(name = "Typeclasse.findByNom", query = "SELECT t FROM Typeclasse t WHERE t.nom = :nom"),
    @NamedQuery(name = "Typeclasse.findByNiveau", query = "SELECT t FROM Typeclasse t WHERE t.niveau = :niveau")})
public class Typeclasse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtypeclasse")
    private Integer idtypeclasse;
    @Size(max = 2147483647)
    @Column(name = "nom")
    private String nom;
    @Column(name = "niveau")
    private Integer niveau;

    public Typeclasse() {
    }

    public Typeclasse(Integer idtypeclasse) {
        this.idtypeclasse = idtypeclasse;
    }

    public Integer getIdtypeclasse() {
        return idtypeclasse;
    }

    public void setIdtypeclasse(Integer idtypeclasse) {
        this.idtypeclasse = idtypeclasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtypeclasse != null ? idtypeclasse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typeclasse)) {
            return false;
        }
        Typeclasse other = (Typeclasse) object;
        if ((this.idtypeclasse == null && other.idtypeclasse != null) || (this.idtypeclasse != null && !this.idtypeclasse.equals(other.idtypeclasse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Typeclasse[ idtypeclasse=" + idtypeclasse + " ]";
    }
    
}
