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
@Table(name = "auteurlivre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Auteurlivre.findAll", query = "SELECT a FROM Auteurlivre a"),
    @NamedQuery(name = "Auteurlivre.findById", query = "SELECT a FROM Auteurlivre a WHERE a.id = :id")})
public class Auteurlivre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "auteur", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Auteur auteur;
    @JoinColumn(name = "livre", referencedColumnName = "idlivre")
    @ManyToOne(fetch = FetchType.LAZY)
    private Livre livre;

    public Auteurlivre() {
    }

    public Auteurlivre(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
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
        if (!(object instanceof Auteurlivre)) {
            return false;
        }
        Auteurlivre other = (Auteurlivre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Auteurlivre[ id=" + id + " ]";
    }
    
}
