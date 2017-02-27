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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "personnelmatiereclasseannee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personnelmatiereclasseannee.findAll", query = "SELECT p FROM Personnelmatiereclasseannee p"),
    @NamedQuery(name = "Personnelmatiereclasseannee.findById", query = "SELECT p FROM Personnelmatiereclasseannee p WHERE p.id = :id"),
    @NamedQuery(name = "Personnelmatiereclasseannee.findByEtat", query = "SELECT p FROM Personnelmatiereclasseannee p WHERE p.etat = :etat")})
public class Personnelmatiereclasseannee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "etat")
    private Boolean etat;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idclasse", referencedColumnName = "idclasse")
    @ManyToOne(fetch = FetchType.LAZY)
    private Classe idclasse;
    @JoinColumn(name = "idmatiere", referencedColumnName = "idmatiere")
    @ManyToOne(fetch = FetchType.LAZY)
    private Matiere idmatiere;
    @JoinColumn(name = "personnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel personnel;

    public Personnelmatiereclasseannee() {
    }

    public Personnelmatiereclasseannee(Integer id) {
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

    public Classe getIdclasse() {
        return idclasse;
    }

    public void setIdclasse(Classe idclasse) {
        this.idclasse = idclasse;
    }

    public Matiere getIdmatiere() {
        return idmatiere;
    }

    public void setIdmatiere(Matiere idmatiere) {
        this.idmatiere = idmatiere;
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
        if (!(object instanceof Personnelmatiereclasseannee)) {
            return false;
        }
        Personnelmatiereclasseannee other = (Personnelmatiereclasseannee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Personnelmatiereclasseannee[ id=" + id + " ]";
    }
    
}
