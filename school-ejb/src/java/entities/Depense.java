/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "depense")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Depense.findAll", query = "SELECT d FROM Depense d"),
    @NamedQuery(name = "Depense.findByIddepense", query = "SELECT d FROM Depense d WHERE d.iddepense = :iddepense"),
    @NamedQuery(name = "Depense.findByLibelle", query = "SELECT d FROM Depense d WHERE d.libelle = :libelle"),
    @NamedQuery(name = "Depense.findByDatedepense", query = "SELECT d FROM Depense d WHERE d.datedepense = :datedepense"),
    @NamedQuery(name = "Depense.findByMontant", query = "SELECT d FROM Depense d WHERE d.montant = :montant"),
    @NamedQuery(name = "Depense.findByOperation2", query = "SELECT d FROM Depense d WHERE d.operation2 = :operation2")})
public class Depense implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "iddepense")
    private Long iddepense;
    @Size(max = 254)
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "datedepense")
    @Temporal(TemporalType.DATE)
    private Date datedepense;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montant")
    private Double montant;
    @Column(name = "operation2")
    private Long operation2;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idetablissement", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etablissement idetablissement;
    @JoinColumn(name = "idoperation", referencedColumnName = "idoperation")
    @ManyToOne(fetch = FetchType.LAZY)
    private Operation idoperation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iddepense", fetch = FetchType.LAZY)
    private List<Anneedepense> anneedepenseList;

    public Depense() {
    }

    public Depense(Long iddepense) {
        this.iddepense = iddepense;
    }

    public Long getIddepense() {
        return iddepense;
    }

    public void setIddepense(Long iddepense) {
        this.iddepense = iddepense;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getDatedepense() {
        return datedepense;
    }

    public void setDatedepense(Date datedepense) {
        this.datedepense = datedepense;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Long getOperation2() {
        return operation2;
    }

    public void setOperation2(Long operation2) {
        this.operation2 = operation2;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Etablissement getIdetablissement() {
        return idetablissement;
    }

    public void setIdetablissement(Etablissement idetablissement) {
        this.idetablissement = idetablissement;
    }

    public Operation getIdoperation() {
        return idoperation;
    }

    public void setIdoperation(Operation idoperation) {
        this.idoperation = idoperation;
    }

    @XmlTransient
    public List<Anneedepense> getAnneedepenseList() {
        return anneedepenseList;
    }

    public void setAnneedepenseList(List<Anneedepense> anneedepenseList) {
        this.anneedepenseList = anneedepenseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddepense != null ? iddepense.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Depense)) {
            return false;
        }
        Depense other = (Depense) object;
        if ((this.iddepense == null && other.iddepense != null) || (this.iddepense != null && !this.iddepense.equals(other.iddepense))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Depense[ iddepense=" + iddepense + " ]";
    }

}
