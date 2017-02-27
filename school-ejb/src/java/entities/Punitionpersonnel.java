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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "punitionpersonnel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Punitionpersonnel.findAll", query = "SELECT p FROM Punitionpersonnel p"),
    @NamedQuery(name = "Punitionpersonnel.findById", query = "SELECT p FROM Punitionpersonnel p WHERE p.id = :id")})
public class Punitionpersonnel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idmotif", referencedColumnName = "idmotif")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Motif idmotif;
    @JoinColumn(name = "idsanction", referencedColumnName = "idsanction")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sanction idsanction;

    public Punitionpersonnel() {
    }

    public Punitionpersonnel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Motif getIdmotif() {
        return idmotif;
    }

    public void setIdmotif(Motif idmotif) {
        this.idmotif = idmotif;
    }

    public Sanction getIdsanction() {
        return idsanction;
    }

    public void setIdsanction(Sanction idsanction) {
        this.idsanction = idsanction;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Punitionpersonnel)) {
            return false;
        }
        Punitionpersonnel other = (Punitionpersonnel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Punitionpersonnel[ id=" + id + " ]";
    }
    
}
