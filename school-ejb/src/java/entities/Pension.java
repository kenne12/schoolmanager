/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "pension")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pension.findAll", query = "SELECT p FROM Pension p"),
    @NamedQuery(name = "Pension.findByIdpension", query = "SELECT p FROM Pension p WHERE p.idpension = :idpension"),
    @NamedQuery(name = "Pension.findByMontant", query = "SELECT p FROM Pension p WHERE p.montant = :montant"),
    @NamedQuery(name = "Pension.findByDatepayement", query = "SELECT p FROM Pension p WHERE p.datepayement = :datepayement"),
    @NamedQuery(name = "Pension.findByReste", query = "SELECT p FROM Pension p WHERE p.reste = :reste"),
    @NamedQuery(name = "Pension.findByEtat", query = "SELECT p FROM Pension p WHERE p.etat = :etat"),
    @NamedQuery(name = "Pension.findByMontantPaye", query = "SELECT p FROM Pension p WHERE p.montantPaye = :montantPaye")})
public class Pension implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpension")
    private Long idpension;
    @Column(name = "montant")
    private Integer montant;
    @Column(name = "datepayement")
    @Temporal(TemporalType.DATE)
    private Date datepayement;
    @Column(name = "reste")
    private Integer reste;
    @Column(name = "etat")
    private Boolean etat;
    @Column(name = "montant_paye")
    private Integer montantPaye;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "eleve", referencedColumnName = "ideleve")
    @ManyToOne(fetch = FetchType.LAZY)
    private Eleve eleve;
    @JoinColumn(name = "pensionsave", referencedColumnName = "idpensionsave")
    @ManyToOne(fetch = FetchType.LAZY)
    private PensionSave pensionsave;
    @JoinColumn(name = "idtranche", referencedColumnName = "idtranche")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Tranche idtranche;

    public Pension() {
    }

    public Pension(Long idpension) {
        this.idpension = idpension;
    }

    public Long getIdpension() {
        return idpension;
    }

    public void setIdpension(Long idpension) {
        this.idpension = idpension;
    }

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public Date getDatepayement() {
        return datepayement;
    }

    public void setDatepayement(Date datepayement) {
        this.datepayement = datepayement;
    }

    public Integer getReste() {
        return reste;
    }

    public void setReste(Integer reste) {
        this.reste = reste;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Integer getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(Integer montantPaye) {
        this.montantPaye = montantPaye;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public PensionSave getPensionsave() {
        return pensionsave;
    }

    public void setPensionsave(PensionSave pensionsave) {
        this.pensionsave = pensionsave;
    }

    public Tranche getIdtranche() {
        return idtranche;
    }

    public void setIdtranche(Tranche idtranche) {
        this.idtranche = idtranche;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpension != null ? idpension.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pension)) {
            return false;
        }
        Pension other = (Pension) object;
        if ((this.idpension == null && other.idpension != null) || (this.idpension != null && !this.idpension.equals(other.idpension))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pension[ idpension=" + idpension + " ]";
    }
    
}
