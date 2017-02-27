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
@Table(name = "personnelprivilege")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personnelprivilege.findAll", query = "SELECT p FROM Personnelprivilege p"),
    @NamedQuery(name = "Personnelprivilege.findById", query = "SELECT p FROM Personnelprivilege p WHERE p.id = :id"),
    @NamedQuery(name = "Personnelprivilege.findByEtat", query = "SELECT p FROM Personnelprivilege p WHERE p.etat = :etat")})
public class Personnelprivilege implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "etat")
    private Boolean etat;
    @JoinColumn(name = "personnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel personnel;
    @JoinColumn(name = "idprivilege", referencedColumnName = "idprivilege")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Privilege idprivilege;

    public Personnelprivilege() {
    }

    public Personnelprivilege(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Privilege getIdprivilege() {
        return idprivilege;
    }

    public void setIdprivilege(Privilege idprivilege) {
        this.idprivilege = idprivilege;
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
        if (!(object instanceof Personnelprivilege)) {
            return false;
        }
        Personnelprivilege other = (Personnelprivilege) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Personnelprivilege[ id=" + id + " ]";
    }
    
}
