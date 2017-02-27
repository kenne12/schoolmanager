/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "naturematiere")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Naturematiere.findAll", query = "SELECT n FROM Naturematiere n"),
    @NamedQuery(name = "Naturematiere.findByIdnaturematiere", query = "SELECT n FROM Naturematiere n WHERE n.idnaturematiere = :idnaturematiere"),
    @NamedQuery(name = "Naturematiere.findByLibelle", query = "SELECT n FROM Naturematiere n WHERE n.libelle = :libelle")})
public class Naturematiere implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idnaturematiere")
    private Integer idnaturematiere;
    @Size(max = 254)
    @Column(name = "libelle")
    private String libelle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idnaturematiere", fetch = FetchType.LAZY)
    private List<Matiere> matiereList;

    public Naturematiere() {
    }

    public Naturematiere(Integer idnaturematiere) {
        this.idnaturematiere = idnaturematiere;
    }

    public Integer getIdnaturematiere() {
        return idnaturematiere;
    }

    public void setIdnaturematiere(Integer idnaturematiere) {
        this.idnaturematiere = idnaturematiere;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @XmlTransient
    public List<Matiere> getMatiereList() {
        return matiereList;
    }

    public void setMatiereList(List<Matiere> matiereList) {
        this.matiereList = matiereList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnaturematiere != null ? idnaturematiere.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Naturematiere)) {
            return false;
        }
        Naturematiere other = (Naturematiere) object;
        if ((this.idnaturematiere == null && other.idnaturematiere != null) || (this.idnaturematiere != null && !this.idnaturematiere.equals(other.idnaturematiere))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Naturematiere[ idnaturematiere=" + idnaturematiere + " ]";
    }
    
}
