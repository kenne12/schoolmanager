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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "punition")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Punition.findAll", query = "SELECT p FROM Punition p"),
    @NamedQuery(name = "Punition.findById", query = "SELECT p FROM Punition p WHERE p.id = :id"),
    @NamedQuery(name = "Punition.findByDatepunition", query = "SELECT p FROM Punition p WHERE p.datepunition = :datepunition"),
    @NamedQuery(name = "Punition.findByObservation", query = "SELECT p FROM Punition p WHERE p.observation = :observation")})
public class Punition implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "datepunition")
    @Temporal(TemporalType.DATE)
    private Date datepunition;
    @Size(max = 500)
    @Column(name = "observation")
    private String observation;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "eleve", referencedColumnName = "ideleve")
    @ManyToOne(fetch = FetchType.LAZY)
    private Eleve eleve;
    @JoinColumn(name = "idmotif", referencedColumnName = "idmotif")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Motif idmotif;
    @JoinColumn(name = "personnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel personnel;
    @JoinColumn(name = "idsanction", referencedColumnName = "idsanction")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sanction idsanction;
    @JoinColumn(name = "sequence", referencedColumnName = "idsequencean")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sequenceannee sequence;

    public Punition() {
    }

    public Punition(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatepunition() {
        return datepunition;
    }

    public void setDatepunition(Date datepunition) {
        this.datepunition = datepunition;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
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

    public Motif getIdmotif() {
        return idmotif;
    }

    public void setIdmotif(Motif idmotif) {
        this.idmotif = idmotif;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public Sanction getIdsanction() {
        return idsanction;
    }

    public void setIdsanction(Sanction idsanction) {
        this.idsanction = idsanction;
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
        if (!(object instanceof Punition)) {
            return false;
        }
        Punition other = (Punition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Punition[ id=" + id + " ]";
    }
    
}
