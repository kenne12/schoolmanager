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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "remuneration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Remuneration.findAll", query = "SELECT r FROM Remuneration r"),
    @NamedQuery(name = "Remuneration.findByIdrenum", query = "SELECT r FROM Remuneration r WHERE r.idrenum = :idrenum"),
    @NamedQuery(name = "Remuneration.findByMontant", query = "SELECT r FROM Remuneration r WHERE r.montant = :montant"),
    @NamedQuery(name = "Remuneration.findByDatepaiement", query = "SELECT r FROM Remuneration r WHERE r.datepaiement = :datepaiement")})
public class Remuneration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrenum")
    private Integer idrenum;
    @Column(name = "montant")
    private BigInteger montant;
    @Column(name = "datepaiement")
    @Temporal(TemporalType.DATE)
    private Date datepaiement;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "personnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel personnel;

    public Remuneration() {
    }

    public Remuneration(Integer idrenum) {
        this.idrenum = idrenum;
    }

    public Integer getIdrenum() {
        return idrenum;
    }

    public void setIdrenum(Integer idrenum) {
        this.idrenum = idrenum;
    }

    public BigInteger getMontant() {
        return montant;
    }

    public void setMontant(BigInteger montant) {
        this.montant = montant;
    }

    public Date getDatepaiement() {
        return datepaiement;
    }

    public void setDatepaiement(Date datepaiement) {
        this.datepaiement = datepaiement;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrenum != null ? idrenum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Remuneration)) {
            return false;
        }
        Remuneration other = (Remuneration) object;
        if ((this.idrenum == null && other.idrenum != null) || (this.idrenum != null && !this.idrenum.equals(other.idrenum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Remuneration[ idrenum=" + idrenum + " ]";
    }
    
}
