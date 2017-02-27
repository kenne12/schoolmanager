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
@Table(name = "pension_cumulee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PensionCumulee.findAll", query = "SELECT p FROM PensionCumulee p"),
    @NamedQuery(name = "PensionCumulee.findById", query = "SELECT p FROM PensionCumulee p WHERE p.id = :id"),
    @NamedQuery(name = "PensionCumulee.findByMontantCumule", query = "SELECT p FROM PensionCumulee p WHERE p.montantCumule = :montantCumule"),
    @NamedQuery(name = "PensionCumulee.findByReste", query = "SELECT p FROM PensionCumulee p WHERE p.reste = :reste")})
public class PensionCumulee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Column(name = "montant_cumule")
    private Integer montantCumule;
    @Column(name = "reste")
    private Integer reste;
    @JoinColumn(name = "annee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee annee;
    @JoinColumn(name = "eleve", referencedColumnName = "ideleve")
    @ManyToOne(fetch = FetchType.LAZY)
    private Eleve eleve;

    public PensionCumulee() {
    }

    public PensionCumulee(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMontantCumule() {
        return montantCumule;
    }

    public void setMontantCumule(Integer montantCumule) {
        this.montantCumule = montantCumule;
    }

    public Integer getReste() {
        return reste;
    }

    public void setReste(Integer reste) {
        this.reste = reste;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
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
        if (!(object instanceof PensionCumulee)) {
            return false;
        }
        PensionCumulee other = (PensionCumulee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PensionCumulee[ id=" + id + " ]";
    }
    
}
