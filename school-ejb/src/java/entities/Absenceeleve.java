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
@Table(name = "absenceeleve")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Absenceeleve.findAll", query = "SELECT a FROM Absenceeleve a"),
    @NamedQuery(name = "Absenceeleve.findById", query = "SELECT a FROM Absenceeleve a WHERE a.id = :id"),
    @NamedQuery(name = "Absenceeleve.findByNombreheure", query = "SELECT a FROM Absenceeleve a WHERE a.nombreheure = :nombreheure"),
    @NamedQuery(name = "Absenceeleve.findByDatejour", query = "SELECT a FROM Absenceeleve a WHERE a.datejour = :datejour")})
public class Absenceeleve implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombreheure")
    private Integer nombreheure;
    @Column(name = "datejour")
    @Temporal(TemporalType.DATE)
    private Date datejour;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "eleve", referencedColumnName = "ideleve")
    @ManyToOne(fetch = FetchType.LAZY)
    private Eleve eleve;
    @JoinColumn(name = "personnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel personnel;
    @JoinColumn(name = "sequence", referencedColumnName = "idsequencean")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sequenceannee sequence;

    public Absenceeleve() {
    }

    public Absenceeleve(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNombreheure() {
        return nombreheure;
    }

    public void setNombreheure(Integer nombreheure) {
        this.nombreheure = nombreheure;
    }

    public Date getDatejour() {
        return datejour;
    }

    public void setDatejour(Date datejour) {
        this.datejour = datejour;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Sequenceannee getSequence() {
        return sequence;
    }

    public void setSequence(Sequenceannee sequence) {
        this.sequence = sequence;
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
        if (!(object instanceof Absenceeleve)) {
            return false;
        }
        Absenceeleve other = (Absenceeleve) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Absenceeleve[ id=" + id + " ]";
    }
    
}
