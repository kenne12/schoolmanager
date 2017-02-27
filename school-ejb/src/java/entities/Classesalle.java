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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "classesalle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classesalle.findAll", query = "SELECT c FROM Classesalle c"),
    @NamedQuery(name = "Classesalle.findById", query = "SELECT c FROM Classesalle c WHERE c.id = :id"),
    @NamedQuery(name = "Classesalle.findByEtat", query = "SELECT c FROM Classesalle c WHERE c.etat = :etat")})
public class Classesalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "etat")
    private Boolean etat;
    @JoinColumn(name = "idclasse", referencedColumnName = "idclasse")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Classe idclasse;
    @JoinColumn(name = "idsalle", referencedColumnName = "idsalle")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Salle idsalle;

    public Classesalle() {
    }

    public Classesalle(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Classe getIdclasse() {
        return idclasse;
    }

    public void setIdclasse(Classe idclasse) {
        this.idclasse = idclasse;
    }

    public Salle getIdsalle() {
        return idsalle;
    }

    public void setIdsalle(Salle idsalle) {
        this.idsalle = idsalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classesalle)) {
            return false;
        }
        Classesalle other = (Classesalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Classesalle[ id=" + id + " ]";
    }
    
}
