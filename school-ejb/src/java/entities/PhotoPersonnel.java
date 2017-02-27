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
@Table(name = "photo_personnel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PhotoPersonnel.findAll", query = "SELECT p FROM PhotoPersonnel p"),
    @NamedQuery(name = "PhotoPersonnel.findByIdphoto", query = "SELECT p FROM PhotoPersonnel p WHERE p.idphoto = :idphoto"),
    @NamedQuery(name = "PhotoPersonnel.findByChemin", query = "SELECT p FROM PhotoPersonnel p WHERE p.chemin = :chemin"),
    @NamedQuery(name = "PhotoPersonnel.findByNom", query = "SELECT p FROM PhotoPersonnel p WHERE p.nom = :nom"),
    @NamedQuery(name = "PhotoPersonnel.findByEtat", query = "SELECT p FROM PhotoPersonnel p WHERE p.etat = :etat")})
public class PhotoPersonnel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idphoto")
    private Integer idphoto;
    @Size(max = 2147483647)
    @Column(name = "chemin")
    private String chemin;
    @Size(max = 2147483647)
    @Column(name = "nom")
    private String nom;
    @Column(name = "etat")
    private Boolean etat;
    @JoinColumn(name = "etablissement", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etablissement etablissement;
    @JoinColumn(name = "personnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel personnel;

    public PhotoPersonnel() {
    }

    public PhotoPersonnel(Integer idphoto) {
        this.idphoto = idphoto;
    }

    public Integer getIdphoto() {
        return idphoto;
    }

    public void setIdphoto(Integer idphoto) {
        this.idphoto = idphoto;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
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
        hash += (idphoto != null ? idphoto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PhotoPersonnel)) {
            return false;
        }
        PhotoPersonnel other = (PhotoPersonnel) object;
        if ((this.idphoto == null && other.idphoto != null) || (this.idphoto != null && !this.idphoto.equals(other.idphoto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PhotoPersonnel[ idphoto=" + idphoto + " ]";
    }
    
}
