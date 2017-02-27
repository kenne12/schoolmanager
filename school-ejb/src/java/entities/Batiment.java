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
@Table(name = "batiment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Batiment.findAll", query = "SELECT b FROM Batiment b"),
    @NamedQuery(name = "Batiment.findByIdbatiment", query = "SELECT b FROM Batiment b WHERE b.idbatiment = :idbatiment"),
    @NamedQuery(name = "Batiment.findByNom", query = "SELECT b FROM Batiment b WHERE b.nom = :nom"),
    @NamedQuery(name = "Batiment.findByCode", query = "SELECT b FROM Batiment b WHERE b.code = :code")})
public class Batiment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbatiment")
    private Integer idbatiment;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Size(max = 254)
    @Column(name = "code")
    private String code;
    @JoinColumn(name = "etablissement", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etablissement etablissement;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbatiment", fetch = FetchType.LAZY)
    private List<Salle> salleList;

    public Batiment() {
    }

    public Batiment(Integer idbatiment) {
        this.idbatiment = idbatiment;
    }

    public Integer getIdbatiment() {
        return idbatiment;
    }

    public void setIdbatiment(Integer idbatiment) {
        this.idbatiment = idbatiment;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    @XmlTransient
    public List<Salle> getSalleList() {
        return salleList;
    }

    public void setSalleList(List<Salle> salleList) {
        this.salleList = salleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbatiment != null ? idbatiment.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Batiment)) {
            return false;
        }
        Batiment other = (Batiment) object;
        if ((this.idbatiment == null && other.idbatiment != null) || (this.idbatiment != null && !this.idbatiment.equals(other.idbatiment))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Batiment[ idbatiment=" + idbatiment + " ]";
    }
    
}
