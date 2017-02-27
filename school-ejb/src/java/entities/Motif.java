/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "motif")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Motif.findAll", query = "SELECT m FROM Motif m"),
    @NamedQuery(name = "Motif.findByIdmotif", query = "SELECT m FROM Motif m WHERE m.idmotif = :idmotif"),
    @NamedQuery(name = "Motif.findByLibelle", query = "SELECT m FROM Motif m WHERE m.libelle = :libelle")})
public class Motif implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmotif")
    private Integer idmotif;
    @Size(max = 254)
    @Column(name = "libelle")
    private String libelle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmotif", fetch = FetchType.LAZY)
    private List<Punitionpersonnel> punitionpersonnelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmotif", fetch = FetchType.LAZY)
    private List<Punition> punitionList;

    public Motif() {
    }

    public Motif(Integer idmotif) {
        this.idmotif = idmotif;
    }

    public Integer getIdmotif() {
        return idmotif;
    }

    public void setIdmotif(Integer idmotif) {
        this.idmotif = idmotif;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @XmlTransient
    public List<Punitionpersonnel> getPunitionpersonnelList() {
        return punitionpersonnelList;
    }

    public void setPunitionpersonnelList(List<Punitionpersonnel> punitionpersonnelList) {
        this.punitionpersonnelList = punitionpersonnelList;
    }

    @XmlTransient
    public List<Punition> getPunitionList() {
        return punitionList;
    }

    public void setPunitionList(List<Punition> punitionList) {
        this.punitionList = punitionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmotif != null ? idmotif.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Motif)) {
            return false;
        }
        Motif other = (Motif) object;
        if ((this.idmotif == null && other.idmotif != null) || (this.idmotif != null && !this.idmotif.equals(other.idmotif))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Motif[ idmotif=" + idmotif + " ]";
    }
    
}
