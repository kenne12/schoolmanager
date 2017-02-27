/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "categorie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorie.findAll", query = "SELECT c FROM Categorie c"),
    @NamedQuery(name = "Categorie.findByIdcategorie", query = "SELECT c FROM Categorie c WHERE c.idcategorie = :idcategorie"),
    @NamedQuery(name = "Categorie.findByNom", query = "SELECT c FROM Categorie c WHERE c.nom = :nom"),
    @NamedQuery(name = "Categorie.findByEtat", query = "SELECT c FROM Categorie c WHERE c.etat = :etat")})
public class Categorie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcategorie")
    private Integer idcategorie;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Column(name = "etat")
    private Boolean etat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcategorie", fetch = FetchType.LAZY)
    private List<Catanneeprix> catanneeprixList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcategorie", fetch = FetchType.LAZY)
    private List<Classecategorie> classecategorieList;
    @JoinColumn(name = "etablissement", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etablissement etablissement;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcategorie", fetch = FetchType.LAZY)
    private List<Tranche> trancheList;

    public Categorie() {
    }

    public Categorie(Integer idcategorie) {
        this.idcategorie = idcategorie;
    }

    public Integer getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Integer idcategorie) {
        this.idcategorie = idcategorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    @XmlTransient
    public List<Catanneeprix> getCatanneeprixList() {
        return catanneeprixList;
    }

    public void setCatanneeprixList(List<Catanneeprix> catanneeprixList) {
        this.catanneeprixList = catanneeprixList;
    }

    @XmlTransient
    public List<Classecategorie> getClassecategorieList() {
        return classecategorieList;
    }

    public void setClassecategorieList(List<Classecategorie> classecategorieList) {
        this.classecategorieList = classecategorieList;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    @XmlTransient
    public List<Tranche> getTrancheList() {
        return trancheList;
    }

    public void setTrancheList(List<Tranche> trancheList) {
        this.trancheList = trancheList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcategorie != null ? idcategorie.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorie)) {
            return false;
        }
        Categorie other = (Categorie) object;
        if ((this.idcategorie == null && other.idcategorie != null) || (this.idcategorie != null && !this.idcategorie.equals(other.idcategorie))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Categorie[ idcategorie=" + idcategorie + " ]";
    }
    
}
