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
@Table(name = "pension_save")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PensionSave.findAll", query = "SELECT p FROM PensionSave p"),
    @NamedQuery(name = "PensionSave.findByIdpensionsave", query = "SELECT p FROM PensionSave p WHERE p.idpensionsave = :idpensionsave"),
    @NamedQuery(name = "PensionSave.findByMontant", query = "SELECT p FROM PensionSave p WHERE p.montant = :montant"),
    @NamedQuery(name = "PensionSave.findByCode", query = "SELECT p FROM PensionSave p WHERE p.code = :code"),
    @NamedQuery(name = "PensionSave.findByDatepayement", query = "SELECT p FROM PensionSave p WHERE p.datepayement = :datepayement")})
public class PensionSave implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpensionsave")
    private Long idpensionsave;
    @Column(name = "montant")
    private Integer montant;
    @Size(max = 2147483647)
    @Column(name = "code")
    private String code;
    @Column(name = "datepayement")
    @Temporal(TemporalType.DATE)
    private Date datepayement;
    @OneToMany(mappedBy = "pensionsave", fetch = FetchType.LAZY)
    private List<Pension> pensionList;
    @JoinColumn(name = "annee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee annee;
    @JoinColumn(name = "eleve", referencedColumnName = "ideleve")
    @ManyToOne(fetch = FetchType.LAZY)
    private Eleve eleve;
    @JoinColumn(name = "idoperation", referencedColumnName = "idoperation")
    @ManyToOne(fetch = FetchType.LAZY)
    private Operation idoperation;

    public PensionSave() {
    }

    public PensionSave(Long idpensionsave) {
        this.idpensionsave = idpensionsave;
    }

    public Long getIdpensionsave() {
        return idpensionsave;
    }

    public void setIdpensionsave(Long idpensionsave) {
        this.idpensionsave = idpensionsave;
    }

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDatepayement() {
        return datepayement;
    }

    public void setDatepayement(Date datepayement) {
        this.datepayement = datepayement;
    }

    @XmlTransient
    public List<Pension> getPensionList() {
        return pensionList;
    }

    public void setPensionList(List<Pension> pensionList) {
        this.pensionList = pensionList;
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

    public Operation getIdoperation() {
        return idoperation;
    }

    public void setIdoperation(Operation idoperation) {
        this.idoperation = idoperation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpensionsave != null ? idpensionsave.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PensionSave)) {
            return false;
        }
        PensionSave other = (PensionSave) object;
        if ((this.idpensionsave == null && other.idpensionsave != null) || (this.idpensionsave != null && !this.idpensionsave.equals(other.idpensionsave))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PensionSave[ idpensionsave=" + idpensionsave + " ]";
    }
    
}
