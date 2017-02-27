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
@Table(name = "personnelanneecatfonct")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personnelanneecatfonct.findAll", query = "SELECT p FROM Personnelanneecatfonct p"),
    @NamedQuery(name = "Personnelanneecatfonct.findById", query = "SELECT p FROM Personnelanneecatfonct p WHERE p.id = :id"),
    @NamedQuery(name = "Personnelanneecatfonct.findByEtat", query = "SELECT p FROM Personnelanneecatfonct p WHERE p.etat = :etat")})
public class Personnelanneecatfonct implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "etat")
    private Boolean etat;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idcatpersonnel", referencedColumnName = "idcatpersonnel")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Categoriepersonnel idcatpersonnel;
    @JoinColumn(name = "idfonction", referencedColumnName = "idfonction")
    @ManyToOne(fetch = FetchType.LAZY)
    private Fonction idfonction;
    @JoinColumn(name = "personnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel personnel;

    public Personnelanneecatfonct() {
    }

    public Personnelanneecatfonct(Integer id) {
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

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Categoriepersonnel getIdcatpersonnel() {
        return idcatpersonnel;
    }

    public void setIdcatpersonnel(Categoriepersonnel idcatpersonnel) {
        this.idcatpersonnel = idcatpersonnel;
    }

    public Fonction getIdfonction() {
        return idfonction;
    }

    public void setIdfonction(Fonction idfonction) {
        this.idfonction = idfonction;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personnelanneecatfonct)) {
            return false;
        }
        Personnelanneecatfonct other = (Personnelanneecatfonct) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Personnelanneecatfonct[ id=" + id + " ]";
    }
    
}
