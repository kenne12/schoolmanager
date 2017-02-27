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
@Table(name = "cycle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cycle.findAll", query = "SELECT c FROM Cycle c"),
    @NamedQuery(name = "Cycle.findByIdcycle", query = "SELECT c FROM Cycle c WHERE c.idcycle = :idcycle"),
    @NamedQuery(name = "Cycle.findByNom", query = "SELECT c FROM Cycle c WHERE c.nom = :nom"),
    @NamedQuery(name = "Cycle.findByEtat", query = "SELECT c FROM Cycle c WHERE c.etat = :etat")})
public class Cycle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcycle")
    private Integer idcycle;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Column(name = "etat")
    private Boolean etat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcycle", fetch = FetchType.LAZY)
    private List<Classe> classeList;

    public Cycle() {
    }

    public Cycle(Integer idcycle) {
        this.idcycle = idcycle;
    }

    public Integer getIdcycle() {
        return idcycle;
    }

    public void setIdcycle(Integer idcycle) {
        this.idcycle = idcycle;
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
    public List<Classe> getClasseList() {
        return classeList;
    }

    public void setClasseList(List<Classe> classeList) {
        this.classeList = classeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcycle != null ? idcycle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cycle)) {
            return false;
        }
        Cycle other = (Cycle) object;
        if ((this.idcycle == null && other.idcycle != null) || (this.idcycle != null && !this.idcycle.equals(other.idcycle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cycle[ idcycle=" + idcycle + " ]";
    }
    
}
