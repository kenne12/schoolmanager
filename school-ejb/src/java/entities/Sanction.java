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
@Table(name = "sanction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sanction.findAll", query = "SELECT s FROM Sanction s"),
    @NamedQuery(name = "Sanction.findByIdsanction", query = "SELECT s FROM Sanction s WHERE s.idsanction = :idsanction"),
    @NamedQuery(name = "Sanction.findByLibelle", query = "SELECT s FROM Sanction s WHERE s.libelle = :libelle")})
public class Sanction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsanction")
    private Integer idsanction;
    @Size(max = 254)
    @Column(name = "libelle")
    private String libelle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsanction", fetch = FetchType.LAZY)
    private List<Punitionpersonnel> punitionpersonnelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsanction", fetch = FetchType.LAZY)
    private List<Punition> punitionList;

    public Sanction() {
    }

    public Sanction(Integer idsanction) {
        this.idsanction = idsanction;
    }

    public Integer getIdsanction() {
        return idsanction;
    }

    public void setIdsanction(Integer idsanction) {
        this.idsanction = idsanction;
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
        hash += (idsanction != null ? idsanction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sanction)) {
            return false;
        }
        Sanction other = (Sanction) object;
        if ((this.idsanction == null && other.idsanction != null) || (this.idsanction != null && !this.idsanction.equals(other.idsanction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sanction[ idsanction=" + idsanction + " ]";
    }
    
}
