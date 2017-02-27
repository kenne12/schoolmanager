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
@Table(name = "traceur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Traceur.findAll", query = "SELECT t FROM Traceur t"),
    @NamedQuery(name = "Traceur.findById", query = "SELECT t FROM Traceur t WHERE t.id = :id"),
    @NamedQuery(name = "Traceur.findByDateaction", query = "SELECT t FROM Traceur t WHERE t.dateaction = :dateaction"),
    @NamedQuery(name = "Traceur.findByAction", query = "SELECT t FROM Traceur t WHERE t.action = :action"),
    @NamedQuery(name = "Traceur.findByHeure", query = "SELECT t FROM Traceur t WHERE t.heure = :heure")})
public class Traceur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "dateaction")
    @Temporal(TemporalType.DATE)
    private Date dateaction;
    @Size(max = 150)
    @Column(name = "action")
    private String action;
    @Column(name = "heure")
    @Temporal(TemporalType.TIME)
    private Date heure;
    @JoinColumn(name = "annee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee annee;
    @JoinColumn(name = "compteutilisateur", referencedColumnName = "idcompte")
    @ManyToOne(fetch = FetchType.LAZY)
    private CompteUtilisateur compteutilisateur;
    @JoinColumn(name = "personnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel personnel;

    public Traceur() {
    }

    public Traceur(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateaction() {
        return dateaction;
    }

    public void setDateaction(Date dateaction) {
        this.dateaction = dateaction;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getHeure() {
        return heure;
    }

    public void setHeure(Date heure) {
        this.heure = heure;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public CompteUtilisateur getCompteutilisateur() {
        return compteutilisateur;
    }

    public void setCompteutilisateur(CompteUtilisateur compteutilisateur) {
        this.compteutilisateur = compteutilisateur;
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
        if (!(object instanceof Traceur)) {
            return false;
        }
        Traceur other = (Traceur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Traceur[ id=" + id + " ]";
    }
    
}
