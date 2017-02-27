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
@Table(name = "travauxdirige")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Travauxdirige.findAll", query = "SELECT t FROM Travauxdirige t"),
    @NamedQuery(name = "Travauxdirige.findById", query = "SELECT t FROM Travauxdirige t WHERE t.id = :id"),
    @NamedQuery(name = "Travauxdirige.findByDate", query = "SELECT t FROM Travauxdirige t WHERE t.date = :date"),
    @NamedQuery(name = "Travauxdirige.findByNombreheure", query = "SELECT t FROM Travauxdirige t WHERE t.nombreheure = :nombreheure")})
public class Travauxdirige implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "nombreheure")
    private Integer nombreheure;
    @JoinColumn(name = "personnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel personnel;

    public Travauxdirige() {
    }

    public Travauxdirige(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getNombreheure() {
        return nombreheure;
    }

    public void setNombreheure(Integer nombreheure) {
        this.nombreheure = nombreheure;
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
        if (!(object instanceof Travauxdirige)) {
            return false;
        }
        Travauxdirige other = (Travauxdirige) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Travauxdirige[ id=" + id + " ]";
    }
    
}
