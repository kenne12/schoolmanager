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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "typecompte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typecompte.findAll", query = "SELECT t FROM Typecompte t"),
    @NamedQuery(name = "Typecompte.findByIdtypecompte", query = "SELECT t FROM Typecompte t WHERE t.idtypecompte = :idtypecompte"),
    @NamedQuery(name = "Typecompte.findByLibelle", query = "SELECT t FROM Typecompte t WHERE t.libelle = :libelle"),
    @NamedQuery(name = "Typecompte.findByClasse", query = "SELECT t FROM Typecompte t WHERE t.classe = :classe"),
    @NamedQuery(name = "Typecompte.findByDebit", query = "SELECT t FROM Typecompte t WHERE t.debit = :debit"),
    @NamedQuery(name = "Typecompte.findByCredit", query = "SELECT t FROM Typecompte t WHERE t.credit = :credit")})
public class Typecompte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtypecompte")
    private Integer idtypecompte;
    @Size(max = 2147483647)
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "classe")
    private Integer classe;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "debit")
    private Double debit;
    @Column(name = "credit")
    private Double credit;
    @JoinColumn(name = "idetablissement", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etablissement idetablissement;
    @OneToMany(mappedBy = "idtypecompte", fetch = FetchType.LAZY)
    private List<Compte> compteList;

    public Typecompte() {
    }

    public Typecompte(Integer idtypecompte) {
        this.idtypecompte = idtypecompte;
    }

    public Integer getIdtypecompte() {
        return idtypecompte;
    }

    public void setIdtypecompte(Integer idtypecompte) {
        this.idtypecompte = idtypecompte;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getClasse() {
        return classe;
    }

    public void setClasse(Integer classe) {
        this.classe = classe;
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

    public Etablissement getIdetablissement() {
        return idetablissement;
    }

    public void setIdetablissement(Etablissement idetablissement) {
        this.idetablissement = idetablissement;
    }

    @XmlTransient
    public List<Compte> getCompteList() {
        return compteList;
    }

    public void setCompteList(List<Compte> compteList) {
        this.compteList = compteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtypecompte != null ? idtypecompte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typecompte)) {
            return false;
        }
        Typecompte other = (Typecompte) object;
        if ((this.idtypecompte == null && other.idtypecompte != null) || (this.idtypecompte != null && !this.idtypecompte.equals(other.idtypecompte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Typecompte[ idtypecompte=" + idtypecompte + " ]";
    }
    
}
