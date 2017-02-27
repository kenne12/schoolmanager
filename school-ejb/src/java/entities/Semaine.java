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
@Table(name = "semaine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Semaine.findAll", query = "SELECT s FROM Semaine s"),
    @NamedQuery(name = "Semaine.findByIdsemaine", query = "SELECT s FROM Semaine s WHERE s.idsemaine = :idsemaine"),
    @NamedQuery(name = "Semaine.findByNom", query = "SELECT s FROM Semaine s WHERE s.nom = :nom")})
public class Semaine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsemaine")
    private Integer idsemaine;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsemaine", fetch = FetchType.LAZY)
    private List<Semaineannee> semaineanneeList;
    @JoinColumn(name = "idsequence", referencedColumnName = "idsequence")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sequence idsequence;

    public Semaine() {
    }

    public Semaine(Integer idsemaine) {
        this.idsemaine = idsemaine;
    }

    public Integer getIdsemaine() {
        return idsemaine;
    }

    public void setIdsemaine(Integer idsemaine) {
        this.idsemaine = idsemaine;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public List<Semaineannee> getSemaineanneeList() {
        return semaineanneeList;
    }

    public void setSemaineanneeList(List<Semaineannee> semaineanneeList) {
        this.semaineanneeList = semaineanneeList;
    }

    public Sequence getIdsequence() {
        return idsequence;
    }

    public void setIdsequence(Sequence idsequence) {
        this.idsequence = idsequence;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsemaine != null ? idsemaine.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Semaine)) {
            return false;
        }
        Semaine other = (Semaine) object;
        if ((this.idsemaine == null && other.idsemaine != null) || (this.idsemaine != null && !this.idsemaine.equals(other.idsemaine))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Semaine[ idsemaine=" + idsemaine + " ]";
    }
    
}
