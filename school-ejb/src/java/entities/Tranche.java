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
@Table(name = "tranche")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tranche.findAll", query = "SELECT t FROM Tranche t"),
    @NamedQuery(name = "Tranche.findByIdtranche", query = "SELECT t FROM Tranche t WHERE t.idtranche = :idtranche"),
    @NamedQuery(name = "Tranche.findByNom", query = "SELECT t FROM Tranche t WHERE t.nom = :nom"),
    @NamedQuery(name = "Tranche.findByDatedebut", query = "SELECT t FROM Tranche t WHERE t.datedebut = :datedebut"),
    @NamedQuery(name = "Tranche.findByDatefin", query = "SELECT t FROM Tranche t WHERE t.datefin = :datefin"),
    @NamedQuery(name = "Tranche.findByPrix", query = "SELECT t FROM Tranche t WHERE t.prix = :prix"),
    @NamedQuery(name = "Tranche.findByEtat", query = "SELECT t FROM Tranche t WHERE t.etat = :etat")})
public class Tranche implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtranche")
    private Integer idtranche;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Column(name = "datedebut")
    @Temporal(TemporalType.DATE)
    private Date datedebut;
    @Column(name = "datefin")
    @Temporal(TemporalType.DATE)
    private Date datefin;
    @Column(name = "prix")
    private Integer prix;
    @Column(name = "etat")
    private Boolean etat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtranche", fetch = FetchType.LAZY)
    private List<Pension> pensionList;
    @JoinColumn(name = "annee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee annee;
    @JoinColumn(name = "idcategorie", referencedColumnName = "idcategorie")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Categorie idcategorie;
    @JoinColumn(name = "idtypetranche", referencedColumnName = "idtypetranche")
    @ManyToOne(fetch = FetchType.LAZY)
    private Typetranche idtypetranche;

    public Tranche() {
    }

    public Tranche(Integer idtranche) {
        this.idtranche = idtranche;
    }

    public Integer getIdtranche() {
        return idtranche;
    }

    public void setIdtranche(Integer idtranche) {
        this.idtranche = idtranche;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
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

    public Categorie getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(Categorie idcategorie) {
        this.idcategorie = idcategorie;
    }

    public Typetranche getIdtypetranche() {
        return idtypetranche;
    }

    public void setIdtypetranche(Typetranche idtypetranche) {
        this.idtypetranche = idtypetranche;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtranche != null ? idtranche.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tranche)) {
            return false;
        }
        Tranche other = (Tranche) object;
        if ((this.idtranche == null && other.idtranche != null) || (this.idtranche != null && !this.idtranche.equals(other.idtranche))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tranche[ idtranche=" + idtranche + " ]";
    }
    
}
