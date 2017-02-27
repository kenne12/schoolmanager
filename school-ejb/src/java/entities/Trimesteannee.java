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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "trimesteannee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trimesteannee.findAll", query = "SELECT t FROM Trimesteannee t"),
    @NamedQuery(name = "Trimesteannee.findByIdtrimestrean", query = "SELECT t FROM Trimesteannee t WHERE t.idtrimestrean = :idtrimestrean"),
    @NamedQuery(name = "Trimesteannee.findByEtat", query = "SELECT t FROM Trimesteannee t WHERE t.etat = :etat")})
public class Trimesteannee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtrimestrean")
    private Integer idtrimestrean;
    @Column(name = "etat")
    private Boolean etat;
    @OneToMany(mappedBy = "trimestre", fetch = FetchType.LAZY)
    private List<Sequenceannee> sequenceanneeList;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idtrimestre", referencedColumnName = "idtrimestre")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Trimestre idtrimestre;
    @OneToMany(mappedBy = "trimestre", fetch = FetchType.LAZY)
    private List<Appreciationtrimestrielle> appreciationtrimestrielleList;

    public Trimesteannee() {
    }

    public Trimesteannee(Integer idtrimestrean) {
        this.idtrimestrean = idtrimestrean;
    }

    public Integer getIdtrimestrean() {
        return idtrimestrean;
    }

    public void setIdtrimestrean(Integer idtrimestrean) {
        this.idtrimestrean = idtrimestrean;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    @XmlTransient
    public List<Sequenceannee> getSequenceanneeList() {
        return sequenceanneeList;
    }

    public void setSequenceanneeList(List<Sequenceannee> sequenceanneeList) {
        this.sequenceanneeList = sequenceanneeList;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Trimestre getIdtrimestre() {
        return idtrimestre;
    }

    public void setIdtrimestre(Trimestre idtrimestre) {
        this.idtrimestre = idtrimestre;
    }

    @XmlTransient
    public List<Appreciationtrimestrielle> getAppreciationtrimestrielleList() {
        return appreciationtrimestrielleList;
    }

    public void setAppreciationtrimestrielleList(List<Appreciationtrimestrielle> appreciationtrimestrielleList) {
        this.appreciationtrimestrielleList = appreciationtrimestrielleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtrimestrean != null ? idtrimestrean.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trimesteannee)) {
            return false;
        }
        Trimesteannee other = (Trimesteannee) object;
        if ((this.idtrimestrean == null && other.idtrimestrean != null) || (this.idtrimestrean != null && !this.idtrimestrean.equals(other.idtrimestrean))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Trimesteannee[ idtrimestrean=" + idtrimestrean + " ]";
    }
    
}
