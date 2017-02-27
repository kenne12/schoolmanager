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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "appreciationtrimestrielle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appreciationtrimestrielle.findAll", query = "SELECT a FROM Appreciationtrimestrielle a"),
    @NamedQuery(name = "Appreciationtrimestrielle.findById", query = "SELECT a FROM Appreciationtrimestrielle a WHERE a.id = :id"),
    @NamedQuery(name = "Appreciationtrimestrielle.findByNom", query = "SELECT a FROM Appreciationtrimestrielle a WHERE a.nom = :nom")})
public class Appreciationtrimestrielle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "nom")
    private String nom;
    @JoinColumn(name = "matiere", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ClasseElementevaluation matiere;
    @JoinColumn(name = "eleve", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Eleveanneeclasse eleve;
    @JoinColumn(name = "trimestre", referencedColumnName = "idtrimestrean")
    @ManyToOne(fetch = FetchType.LAZY)
    private Trimesteannee trimestre;

    public Appreciationtrimestrielle() {
    }

    public Appreciationtrimestrielle(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ClasseElementevaluation getMatiere() {
        return matiere;
    }

    public void setMatiere(ClasseElementevaluation matiere) {
        this.matiere = matiere;
    }

    public Eleveanneeclasse getEleve() {
        return eleve;
    }

    public void setEleve(Eleveanneeclasse eleve) {
        this.eleve = eleve;
    }

    public Trimesteannee getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Trimesteannee trimestre) {
        this.trimestre = trimestre;
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
        if (!(object instanceof Appreciationtrimestrielle)) {
            return false;
        }
        Appreciationtrimestrielle other = (Appreciationtrimestrielle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Appreciationtrimestrielle[ id=" + id + " ]";
    }
    
}
