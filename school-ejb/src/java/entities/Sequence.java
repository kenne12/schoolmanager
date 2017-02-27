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
@Table(name = "sequence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sequence.findAll", query = "SELECT s FROM Sequence s"),
    @NamedQuery(name = "Sequence.findByIdsequence", query = "SELECT s FROM Sequence s WHERE s.idsequence = :idsequence"),
    @NamedQuery(name = "Sequence.findByNom", query = "SELECT s FROM Sequence s WHERE s.nom = :nom"),
    @NamedQuery(name = "Sequence.findByNumero", query = "SELECT s FROM Sequence s WHERE s.numero = :numero")})
public class Sequence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsequence")
    private Integer idsequence;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Column(name = "numero")
    private Integer numero;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsequence", fetch = FetchType.LAZY)
    private List<Sequenceannee> sequenceanneeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsequence", fetch = FetchType.LAZY)
    private List<Semaine> semaineList;

    public Sequence() {
    }

    public Sequence(Integer idsequence) {
        this.idsequence = idsequence;
    }

    public Integer getIdsequence() {
        return idsequence;
    }

    public void setIdsequence(Integer idsequence) {
        this.idsequence = idsequence;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @XmlTransient
    public List<Sequenceannee> getSequenceanneeList() {
        return sequenceanneeList;
    }

    public void setSequenceanneeList(List<Sequenceannee> sequenceanneeList) {
        this.sequenceanneeList = sequenceanneeList;
    }

    @XmlTransient
    public List<Semaine> getSemaineList() {
        return semaineList;
    }

    public void setSemaineList(List<Semaine> semaineList) {
        this.semaineList = semaineList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsequence != null ? idsequence.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sequence)) {
            return false;
        }
        Sequence other = (Sequence) object;
        if ((this.idsequence == null && other.idsequence != null) || (this.idsequence != null && !this.idsequence.equals(other.idsequence))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sequence[ idsequence=" + idsequence + " ]";
    }
    
}
