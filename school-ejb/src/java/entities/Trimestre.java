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
@Table(name = "trimestre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trimestre.findAll", query = "SELECT t FROM Trimestre t"),
    @NamedQuery(name = "Trimestre.findByIdtrimestre", query = "SELECT t FROM Trimestre t WHERE t.idtrimestre = :idtrimestre"),
    @NamedQuery(name = "Trimestre.findByNom", query = "SELECT t FROM Trimestre t WHERE t.nom = :nom")})
public class Trimestre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtrimestre")
    private Integer idtrimestre;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtrimestre", fetch = FetchType.LAZY)
    private List<Trimesteannee> trimesteanneeList;

    public Trimestre() {
    }

    public Trimestre(Integer idtrimestre) {
        this.idtrimestre = idtrimestre;
    }

    public Integer getIdtrimestre() {
        return idtrimestre;
    }

    public void setIdtrimestre(Integer idtrimestre) {
        this.idtrimestre = idtrimestre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public List<Trimesteannee> getTrimesteanneeList() {
        return trimesteanneeList;
    }

    public void setTrimesteanneeList(List<Trimesteannee> trimesteanneeList) {
        this.trimesteanneeList = trimesteanneeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtrimestre != null ? idtrimestre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trimestre)) {
            return false;
        }
        Trimestre other = (Trimestre) object;
        if ((this.idtrimestre == null && other.idtrimestre != null) || (this.idtrimestre != null && !this.idtrimestre.equals(other.idtrimestre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Trimestre[ idtrimestre=" + idtrimestre + " ]";
    }
    
}
