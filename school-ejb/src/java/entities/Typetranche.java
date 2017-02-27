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
@Table(name = "typetranche")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typetranche.findAll", query = "SELECT t FROM Typetranche t"),
    @NamedQuery(name = "Typetranche.findByIdtypetranche", query = "SELECT t FROM Typetranche t WHERE t.idtypetranche = :idtypetranche"),
    @NamedQuery(name = "Typetranche.findByNom", query = "SELECT t FROM Typetranche t WHERE t.nom = :nom"),
    @NamedQuery(name = "Typetranche.findByMontantdefault", query = "SELECT t FROM Typetranche t WHERE t.montantdefault = :montantdefault")})
public class Typetranche implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtypetranche")
    private Integer idtypetranche;
    @Size(max = 2147483647)
    @Column(name = "nom")
    private String nom;
    @Column(name = "montantdefault")
    private Integer montantdefault;
    @JoinColumn(name = "idetablissement", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etablissement idetablissement;
    @OneToMany(mappedBy = "idtypetranche", fetch = FetchType.LAZY)
    private List<Tranche> trancheList;

    public Typetranche() {
    }

    public Typetranche(Integer idtypetranche) {
        this.idtypetranche = idtypetranche;
    }

    public Integer getIdtypetranche() {
        return idtypetranche;
    }

    public void setIdtypetranche(Integer idtypetranche) {
        this.idtypetranche = idtypetranche;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getMontantdefault() {
        return montantdefault;
    }

    public void setMontantdefault(Integer montantdefault) {
        this.montantdefault = montantdefault;
    }

    public Etablissement getIdetablissement() {
        return idetablissement;
    }

    public void setIdetablissement(Etablissement idetablissement) {
        this.idetablissement = idetablissement;
    }

    @XmlTransient
    public List<Tranche> getTrancheList() {
        return trancheList;
    }

    public void setTrancheList(List<Tranche> trancheList) {
        this.trancheList = trancheList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtypetranche != null ? idtypetranche.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typetranche)) {
            return false;
        }
        Typetranche other = (Typetranche) object;
        if ((this.idtypetranche == null && other.idtypetranche != null) || (this.idtypetranche != null && !this.idtypetranche.equals(other.idtypetranche))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Typetranche[ idtypetranche=" + idtypetranche + " ]";
    }
    
}
