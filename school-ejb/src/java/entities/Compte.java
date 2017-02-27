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
@Table(name = "compte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compte.findAll", query = "SELECT c FROM Compte c"),
    @NamedQuery(name = "Compte.findByIdcompte", query = "SELECT c FROM Compte c WHERE c.idcompte = :idcompte"),
    @NamedQuery(name = "Compte.findByLibelle", query = "SELECT c FROM Compte c WHERE c.libelle = :libelle"),
    @NamedQuery(name = "Compte.findByDatecreation", query = "SELECT c FROM Compte c WHERE c.datecreation = :datecreation"),
    @NamedQuery(name = "Compte.findByDebit", query = "SELECT c FROM Compte c WHERE c.debit = :debit"),
    @NamedQuery(name = "Compte.findByCredit", query = "SELECT c FROM Compte c WHERE c.credit = :credit"),
    @NamedQuery(name = "Compte.findByClasse", query = "SELECT c FROM Compte c WHERE c.classe = :classe")})
public class Compte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcompte")
    private Long idcompte;
    @Size(max = 2147483647)
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "datecreation")
    @Temporal(TemporalType.DATE)
    private Date datecreation;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "debit")
    private Double debit;
    @Column(name = "credit")
    private Double credit;
    @Column(name = "classe")
    private Integer classe;
    @OneToMany(mappedBy = "idcompte", fetch = FetchType.LAZY)
    private List<Operation> operationList;
    @JoinColumn(name = "idetablissement", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etablissement idetablissement;
    @JoinColumn(name = "idtypecompte", referencedColumnName = "idtypecompte")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typecompte idtypecompte;

    public Compte() {
    }

    public Compte(Long idcompte) {
        this.idcompte = idcompte;
    }

    public Long getIdcompte() {
        return idcompte;
    }

    public void setIdcompte(Long idcompte) {
        this.idcompte = idcompte;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Integer getClasse() {
        return classe;
    }

    public void setClasse(Integer classe) {
        this.classe = classe;
    }

    @XmlTransient
    public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    public Etablissement getIdetablissement() {
        return idetablissement;
    }

    public void setIdetablissement(Etablissement idetablissement) {
        this.idetablissement = idetablissement;
    }

    public Typecompte getIdtypecompte() {
        return idtypecompte;
    }

    public void setIdtypecompte(Typecompte idtypecompte) {
        this.idtypecompte = idtypecompte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcompte != null ? idcompte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compte)) {
            return false;
        }
        Compte other = (Compte) object;
        if ((this.idcompte == null && other.idcompte != null) || (this.idcompte != null && !this.idcompte.equals(other.idcompte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Compte[ idcompte=" + idcompte + " ]";
    }
    
}
