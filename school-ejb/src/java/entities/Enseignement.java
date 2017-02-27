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
@Table(name = "enseignement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enseignement.findAll", query = "SELECT e FROM Enseignement e"),
    @NamedQuery(name = "Enseignement.findByIdbranche", query = "SELECT e FROM Enseignement e WHERE e.idbranche = :idbranche"),
    @NamedQuery(name = "Enseignement.findByNom", query = "SELECT e FROM Enseignement e WHERE e.nom = :nom"),
    @NamedQuery(name = "Enseignement.findByEtat", query = "SELECT e FROM Enseignement e WHERE e.etat = :etat")})
public class Enseignement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbranche")
    private Integer idbranche;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Column(name = "etat")
    private Boolean etat;
    @JoinColumn(name = "etablissement", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etablissement etablissement;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idbranche", fetch = FetchType.LAZY)
    private List<Classe> classeList;

    public Enseignement() {
    }

    public Enseignement(Integer idbranche) {
        this.idbranche = idbranche;
    }

    public Integer getIdbranche() {
        return idbranche;
    }

    public void setIdbranche(Integer idbranche) {
        this.idbranche = idbranche;
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

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    @XmlTransient
    public List<Classe> getClasseList() {
        return classeList;
    }

    public void setClasseList(List<Classe> classeList) {
        this.classeList = classeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbranche != null ? idbranche.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Enseignement)) {
            return false;
        }
        Enseignement other = (Enseignement) object;
        if ((this.idbranche == null && other.idbranche != null) || (this.idbranche != null && !this.idbranche.equals(other.idbranche))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Enseignement[ idbranche=" + idbranche + " ]";
    }
    
}
