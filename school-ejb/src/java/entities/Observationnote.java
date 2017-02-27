/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "observationnote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Observationnote.findAll", query = "SELECT o FROM Observationnote o"),
    @NamedQuery(name = "Observationnote.findByIdobservationnote", query = "SELECT o FROM Observationnote o WHERE o.idobservationnote = :idobservationnote"),
    @NamedQuery(name = "Observationnote.findByBorneinferieur", query = "SELECT o FROM Observationnote o WHERE o.borneinferieur = :borneinferieur"),
    @NamedQuery(name = "Observationnote.findByBornesuperieur", query = "SELECT o FROM Observationnote o WHERE o.bornesuperieur = :bornesuperieur"),
    @NamedQuery(name = "Observationnote.findByAvis", query = "SELECT o FROM Observationnote o WHERE o.avis = :avis")})
public class Observationnote implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idobservationnote")
    private Integer idobservationnote;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "borneinferieur")
    private Double borneinferieur;
    @Column(name = "bornesuperieur")
    private Double bornesuperieur;
    @Size(max = 2147483647)
    @Column(name = "avis")
    private String avis;
    @JoinColumn(name = "idetablissement", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Etablissement idetablissement;

    public Observationnote() {
    }

    public Observationnote(Integer idobservationnote) {
        this.idobservationnote = idobservationnote;
    }

    public Integer getIdobservationnote() {
        return idobservationnote;
    }

    public void setIdobservationnote(Integer idobservationnote) {
        this.idobservationnote = idobservationnote;
    }

    public Double getBorneinferieur() {
        return borneinferieur;
    }

    public void setBorneinferieur(Double borneinferieur) {
        this.borneinferieur = borneinferieur;
    }

    public Double getBornesuperieur() {
        return bornesuperieur;
    }

    public void setBornesuperieur(Double bornesuperieur) {
        this.bornesuperieur = bornesuperieur;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public Etablissement getIdetablissement() {
        return idetablissement;
    }

    public void setIdetablissement(Etablissement idetablissement) {
        this.idetablissement = idetablissement;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idobservationnote != null ? idobservationnote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Observationnote)) {
            return false;
        }
        Observationnote other = (Observationnote) object;
        if ((this.idobservationnote == null && other.idobservationnote != null) || (this.idobservationnote != null && !this.idobservationnote.equals(other.idobservationnote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Observationnote[ idobservationnote=" + idobservationnote + " ]";
    }
    
}
