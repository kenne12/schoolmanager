/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "type_etablissement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeEtablissement.findAll", query = "SELECT t FROM TypeEtablissement t"),
    @NamedQuery(name = "TypeEtablissement.findById", query = "SELECT t FROM TypeEtablissement t WHERE t.id = :id"),
    @NamedQuery(name = "TypeEtablissement.findByNom", query = "SELECT t FROM TypeEtablissement t WHERE t.nom = :nom")})
public class TypeEtablissement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "nom")
    private String nom;
    @OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
    private List<Etablissement> etablissementList;

    public TypeEtablissement() {
    }

    public TypeEtablissement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public List<Etablissement> getEtablissementList() {
        return etablissementList;
    }

    public void setEtablissementList(List<Etablissement> etablissementList) {
        this.etablissementList = etablissementList;
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
        if (!(object instanceof TypeEtablissement)) {
            return false;
        }
        TypeEtablissement other = (TypeEtablissement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TypeEtablissement[ id=" + id + " ]";
    }
    
}
