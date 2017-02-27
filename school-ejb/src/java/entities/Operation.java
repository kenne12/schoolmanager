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
@Table(name = "operation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operation.findAll", query = "SELECT o FROM Operation o"),
    @NamedQuery(name = "Operation.findByIdoperation", query = "SELECT o FROM Operation o WHERE o.idoperation = :idoperation"),
    @NamedQuery(name = "Operation.findByDebit", query = "SELECT o FROM Operation o WHERE o.debit = :debit"),
    @NamedQuery(name = "Operation.findByDateoperation", query = "SELECT o FROM Operation o WHERE o.dateoperation = :dateoperation"),
    @NamedQuery(name = "Operation.findByVersement", query = "SELECT o FROM Operation o WHERE o.versement = :versement"),
    @NamedQuery(name = "Operation.findByLibelle", query = "SELECT o FROM Operation o WHERE o.libelle = :libelle"),
    @NamedQuery(name = "Operation.findByCredit", query = "SELECT o FROM Operation o WHERE o.credit = :credit"),
    @NamedQuery(name = "Operation.findByHeure", query = "SELECT o FROM Operation o WHERE o.heure = :heure")})
public class Operation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idoperation")
    private Long idoperation;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "debit")
    private Double debit;
    @Column(name = "dateoperation")
    @Temporal(TemporalType.DATE)
    private Date dateoperation;
    @Column(name = "versement")
    private Boolean versement;
    @Size(max = 2147483647)
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "credit")
    private Double credit;
    @Column(name = "heure")
    @Temporal(TemporalType.TIME)
    private Date heure;
    @OneToMany(mappedBy = "idoperation", fetch = FetchType.LAZY)
    private List<PensionSave> pensionSaveList;
    @OneToMany(mappedBy = "idoperation", fetch = FetchType.LAZY)
    private List<Depense> depenseList;
    @OneToMany(mappedBy = "idoperation", fetch = FetchType.LAZY)
    private List<Recette> recetteList;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idcompte", referencedColumnName = "idcompte")
    @ManyToOne(fetch = FetchType.LAZY)
    private Compte idcompte;
    @JoinColumn(name = "idetablissement", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etablissement idetablissement;
    @JoinColumn(name = "idtypeoperation", referencedColumnName = "idtypeoperation")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typeoperation idtypeoperation;

    public Operation() {
    }

    public Operation(Long idoperation) {
        this.idoperation = idoperation;
    }

    public Long getIdoperation() {
        return idoperation;
    }

    public void setIdoperation(Long idoperation) {
        this.idoperation = idoperation;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Date getDateoperation() {
        return dateoperation;
    }

    public void setDateoperation(Date dateoperation) {
        this.dateoperation = dateoperation;
    }

    public Boolean getVersement() {
        return versement;
    }

    public void setVersement(Boolean versement) {
        this.versement = versement;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Date getHeure() {
        return heure;
    }

    public void setHeure(Date heure) {
        this.heure = heure;
    }

    @XmlTransient
    public List<PensionSave> getPensionSaveList() {
        return pensionSaveList;
    }

    public void setPensionSaveList(List<PensionSave> pensionSaveList) {
        this.pensionSaveList = pensionSaveList;
    }

    @XmlTransient
    public List<Depense> getDepenseList() {
        return depenseList;
    }

    public void setDepenseList(List<Depense> depenseList) {
        this.depenseList = depenseList;
    }

    @XmlTransient
    public List<Recette> getRecetteList() {
        return recetteList;
    }

    public void setRecetteList(List<Recette> recetteList) {
        this.recetteList = recetteList;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Compte getIdcompte() {
        return idcompte;
    }

    public void setIdcompte(Compte idcompte) {
        this.idcompte = idcompte;
    }

    public Etablissement getIdetablissement() {
        return idetablissement;
    }

    public void setIdetablissement(Etablissement idetablissement) {
        this.idetablissement = idetablissement;
    }

    public Typeoperation getIdtypeoperation() {
        return idtypeoperation;
    }

    public void setIdtypeoperation(Typeoperation idtypeoperation) {
        this.idtypeoperation = idtypeoperation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idoperation != null ? idoperation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operation)) {
            return false;
        }
        Operation other = (Operation) object;
        if ((this.idoperation == null && other.idoperation != null) || (this.idoperation != null && !this.idoperation.equals(other.idoperation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Operation[ idoperation=" + idoperation + " ]";
    }
    
}
