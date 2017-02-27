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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "elevelivreemprunte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Elevelivreemprunte.findAll", query = "SELECT e FROM Elevelivreemprunte e"),
    @NamedQuery(name = "Elevelivreemprunte.findById", query = "SELECT e FROM Elevelivreemprunte e WHERE e.id = :id"),
    @NamedQuery(name = "Elevelivreemprunte.findByDateemprunt", query = "SELECT e FROM Elevelivreemprunte e WHERE e.dateemprunt = :dateemprunt"),
    @NamedQuery(name = "Elevelivreemprunte.findByEtatemprunt", query = "SELECT e FROM Elevelivreemprunte e WHERE e.etatemprunt = :etatemprunt"),
    @NamedQuery(name = "Elevelivreemprunte.findByDateremise", query = "SELECT e FROM Elevelivreemprunte e WHERE e.dateremise = :dateremise")})
public class Elevelivreemprunte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Column(name = "dateemprunt")
    @Temporal(TemporalType.DATE)
    private Date dateemprunt;
    @Column(name = "etatemprunt")
    private Boolean etatemprunt;
    @Column(name = "dateremise")
    @Temporal(TemporalType.DATE)
    private Date dateremise;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "eleve", referencedColumnName = "ideleve")
    @ManyToOne(fetch = FetchType.LAZY)
    private Eleve eleve;
    @JoinColumn(name = "livre", referencedColumnName = "idlivre")
    @ManyToOne(fetch = FetchType.LAZY)
    private Livre livre;

    public Elevelivreemprunte() {
    }

    public Elevelivreemprunte(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateemprunt() {
        return dateemprunt;
    }

    public void setDateemprunt(Date dateemprunt) {
        this.dateemprunt = dateemprunt;
    }

    public Boolean getEtatemprunt() {
        return etatemprunt;
    }

    public void setEtatemprunt(Boolean etatemprunt) {
        this.etatemprunt = etatemprunt;
    }

    public Date getDateremise() {
        return dateremise;
    }

    public void setDateremise(Date dateremise) {
        this.dateremise = dateremise;
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

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
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
        if (!(object instanceof Elevelivreemprunte)) {
            return false;
        }
        Elevelivreemprunte other = (Elevelivreemprunte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Elevelivreemprunte[ id=" + id + " ]";
    }
    
}
