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
@Table(name = "catanneeprix")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Catanneeprix.findAll", query = "SELECT c FROM Catanneeprix c"),
    @NamedQuery(name = "Catanneeprix.findById", query = "SELECT c FROM Catanneeprix c WHERE c.id = :id"),
    @NamedQuery(name = "Catanneeprix.findByPrix", query = "SELECT c FROM Catanneeprix c WHERE c.prix = :prix"),
    @NamedQuery(name = "Catanneeprix.findByNombretranche", query = "SELECT c FROM Catanneeprix c WHERE c.nombretranche = :nombretranche")})
public class Catanneeprix implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "prix")
    private Integer prix;
    @Column(name = "nombretranche")
    private Integer nombretranche;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idcategorie", referencedColumnName = "idcategorie")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Categorie idcategorie;

    public Catanneeprix() {
    }

    public Catanneeprix(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Integer getNombretranche() {
        return nombretranche;
    }

    public void setNombretranche(Integer nombretranche) {
        this.nombretranche = nombretranche;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Categorie getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Categorie idcategorie) {
        this.idcategorie = idcategorie;
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
        if (!(object instanceof Catanneeprix)) {
            return false;
        }
        Catanneeprix other = (Catanneeprix) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Catanneeprix[ id=" + id + " ]";
    }
    
}
