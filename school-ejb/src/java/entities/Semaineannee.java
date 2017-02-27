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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "semaineannee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Semaineannee.findAll", query = "SELECT s FROM Semaineannee s"),
    @NamedQuery(name = "Semaineannee.findById", query = "SELECT s FROM Semaineannee s WHERE s.id = :id"),
    @NamedQuery(name = "Semaineannee.findByDatedebut", query = "SELECT s FROM Semaineannee s WHERE s.datedebut = :datedebut"),
    @NamedQuery(name = "Semaineannee.findByDatefin", query = "SELECT s FROM Semaineannee s WHERE s.datefin = :datefin"),
    @NamedQuery(name = "Semaineannee.findByEtat", query = "SELECT s FROM Semaineannee s WHERE s.etat = :etat")})
public class Semaineannee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "datedebut")
    @Temporal(TemporalType.DATE)
    private Date datedebut;
    @Column(name = "datefin")
    @Temporal(TemporalType.DATE)
    private Date datefin;
    @Column(name = "etat")
    private Integer etat;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idsemaine", referencedColumnName = "idsemaine")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Semaine idsemaine;

    public Semaineannee() {
    }

    public Semaineannee(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Semaine getIdsemaine() {
        return idsemaine;
    }

    public void setIdsemaine(Semaine idsemaine) {
        this.idsemaine = idsemaine;
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
        if (!(object instanceof Semaineannee)) {
            return false;
        }
        Semaineannee other = (Semaineannee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Semaineannee[ id=" + id + " ]";
    }
    
}
