/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "anneedepense")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anneedepense.findAll", query = "SELECT a FROM Anneedepense a"),
    @NamedQuery(name = "Anneedepense.findById", query = "SELECT a FROM Anneedepense a WHERE a.id = :id"),
    @NamedQuery(name = "Anneedepense.findByMatriculepersonnel", query = "SELECT a FROM Anneedepense a WHERE a.matriculepersonnel = :matriculepersonnel"),
    @NamedQuery(name = "Anneedepense.findByMontant", query = "SELECT a FROM Anneedepense a WHERE a.montant = :montant"),
    @NamedQuery(name = "Anneedepense.findByDatedepense", query = "SELECT a FROM Anneedepense a WHERE a.datedepense = :datedepense")})
public class Anneedepense implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "matriculepersonnel")
    private String matriculepersonnel;
    @Column(name = "montant")
    private BigInteger montant;
    @Column(name = "datedepense")
    @Temporal(TemporalType.DATE)
    private Date datedepense;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "iddepense", referencedColumnName = "iddepense")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Depense iddepense;

    public Anneedepense() {
    }

    public Anneedepense(Integer id) {
        this.id = id;
    }

    public Anneedepense(Integer id, String matriculepersonnel) {
        this.id = id;
        this.matriculepersonnel = matriculepersonnel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatriculepersonnel() {
        return matriculepersonnel;
    }

    public void setMatriculepersonnel(String matriculepersonnel) {
        this.matriculepersonnel = matriculepersonnel;
    }

    public BigInteger getMontant() {
        return montant;
    }

    public void setMontant(BigInteger montant) {
        this.montant = montant;
    }

    public Date getDatedepense() {
        return datedepense;
    }

    public void setDatedepense(Date datedepense) {
        this.datedepense = datedepense;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Depense getIddepense() {
        return iddepense;
    }

    public void setIddepense(Depense iddepense) {
        this.iddepense = iddepense;
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
        if (!(object instanceof Anneedepense)) {
            return false;
        }
        Anneedepense other = (Anneedepense) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Anneedepense[ id=" + id + " ]";
    }
    
}
