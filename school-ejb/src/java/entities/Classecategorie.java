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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "classecategorie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classecategorie.findAll", query = "SELECT c FROM Classecategorie c"),
    @NamedQuery(name = "Classecategorie.findById", query = "SELECT c FROM Classecategorie c WHERE c.id = :id")})
public class Classecategorie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "idcategorie", referencedColumnName = "idcategorie")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Categorie idcategorie;
    @JoinColumn(name = "idclasse", referencedColumnName = "idclasse")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Classe idclasse;

    public Classecategorie() {
    }

    public Classecategorie(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Categorie getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Categorie idcategorie) {
        this.idcategorie = idcategorie;
    }

    public Classe getIdclasse() {
        return idclasse;
    }

    public void setIdclasse(Classe idclasse) {
        this.idclasse = idclasse;
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
        if (!(object instanceof Classecategorie)) {
            return false;
        }
        Classecategorie other = (Classecategorie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Classecategorie[ id=" + id + " ]";
    }
    
}
