/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "eleveanneeclasse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eleveanneeclasse.findAll", query = "SELECT e FROM Eleveanneeclasse e"),
    @NamedQuery(name = "Eleveanneeclasse.findById", query = "SELECT e FROM Eleveanneeclasse e WHERE e.id = :id"),
    @NamedQuery(name = "Eleveanneeclasse.findByRedoublant", query = "SELECT e FROM Eleveanneeclasse e WHERE e.redoublant = :redoublant"),
    @NamedQuery(name = "Eleveanneeclasse.findByMoyenne", query = "SELECT e FROM Eleveanneeclasse e WHERE e.moyenne = :moyenne")})
public class Eleveanneeclasse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Column(name = "redoublant")
    private Boolean redoublant;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "moyenne")
    private Double moyenne;
    @OneToMany(mappedBy = "eleve", fetch = FetchType.LAZY)
    private List<Appreciationannuelle> appreciationannuelleList;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idclasse", referencedColumnName = "idclasse")
    @ManyToOne(fetch = FetchType.LAZY)
    private Classe idclasse;
    @JoinColumn(name = "eleve", referencedColumnName = "ideleve")
    @ManyToOne(fetch = FetchType.LAZY)
    private Eleve eleve;
    @OneToMany(mappedBy = "eleve", fetch = FetchType.LAZY)
    private List<Appreciationtrimestrielle> appreciationtrimestrielleList;

    public Eleveanneeclasse() {
    }

    public Eleveanneeclasse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getRedoublant() {
        return redoublant;
    }

    public void setRedoublant(Boolean redoublant) {
        this.redoublant = redoublant;
    }

    public Double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(Double moyenne) {
        this.moyenne = moyenne;
    }

    @XmlTransient
    public List<Appreciationannuelle> getAppreciationannuelleList() {
        return appreciationannuelleList;
    }

    public void setAppreciationannuelleList(List<Appreciationannuelle> appreciationannuelleList) {
        this.appreciationannuelleList = appreciationannuelleList;
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

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    @XmlTransient
    public List<Appreciationtrimestrielle> getAppreciationtrimestrielleList() {
        return appreciationtrimestrielleList;
    }

    public void setAppreciationtrimestrielleList(List<Appreciationtrimestrielle> appreciationtrimestrielleList) {
        this.appreciationtrimestrielleList = appreciationtrimestrielleList;
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
        if (!(object instanceof Eleveanneeclasse)) {
            return false;
        }
        Eleveanneeclasse other = (Eleveanneeclasse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Eleveanneeclasse[ id=" + id + " ]";
    }
    
}
