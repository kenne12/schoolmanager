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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "recette")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recette.findAll", query = "SELECT r FROM Recette r"),
    @NamedQuery(name = "Recette.findByIdrecette", query = "SELECT r FROM Recette r WHERE r.idrecette = :idrecette"),
    @NamedQuery(name = "Recette.findByCode", query = "SELECT r FROM Recette r WHERE r.code = :code"),
    @NamedQuery(name = "Recette.findByMontant", query = "SELECT r FROM Recette r WHERE r.montant = :montant"),
    @NamedQuery(name = "Recette.findByDaterecette", query = "SELECT r FROM Recette r WHERE r.daterecette = :daterecette"),
    @NamedQuery(name = "Recette.findByLibelle", query = "SELECT r FROM Recette r WHERE r.libelle = :libelle")})
public class Recette implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idrecette")
    private Long idrecette;
    @Size(max = 2147483647)
    @Column(name = "code")
    private String code;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montant")
    private Double montant;
    @Column(name = "daterecette")
    @Temporal(TemporalType.DATE)
    private Date daterecette;
    @Size(max = 2147483647)
    @Column(name = "libelle")
    private String libelle;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idetablissement", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etablissement idetablissement;
    @JoinColumn(name = "idoperation", referencedColumnName = "idoperation")
    @ManyToOne(fetch = FetchType.LAZY)
    private Operation idoperation;

    public Recette() {
    }

    public Recette(Long idrecette) {
        this.idrecette = idrecette;
    }

    public Long getIdrecette() {
        return idrecette;
    }

    public void setIdrecette(Long idrecette) {
        this.idrecette = idrecette;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Date getDaterecette() {
        return daterecette;
    }

    public void setDaterecette(Date daterecette) {
        this.daterecette = daterecette;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrecette != null ? idrecette.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recette)) {
            return false;
        }
        Recette other = (Recette) object;
        if ((this.idrecette == null && other.idrecette != null) || (this.idrecette != null && !this.idrecette.equals(other.idrecette))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Recette[ idrecette=" + idrecette + " ]";
    }
    
}
