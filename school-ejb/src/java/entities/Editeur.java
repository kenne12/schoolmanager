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
@Table(name = "editeur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Editeur.findAll", query = "SELECT e FROM Editeur e"),
    @NamedQuery(name = "Editeur.findByIdediteur", query = "SELECT e FROM Editeur e WHERE e.idediteur = :idediteur"),
    @NamedQuery(name = "Editeur.findByNom", query = "SELECT e FROM Editeur e WHERE e.nom = :nom"),
    @NamedQuery(name = "Editeur.findByPays", query = "SELECT e FROM Editeur e WHERE e.pays = :pays")})
public class Editeur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idediteur")
    private Integer idediteur;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Size(max = 254)
    @Column(name = "pays")
    private String pays;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idediteur", fetch = FetchType.LAZY)
    private List<Livre> livreList;

    public Editeur() {
    }

    public Editeur(Integer idediteur) {
        this.idediteur = idediteur;
    }

    public Integer getIdediteur() {
        return idediteur;
    }

    public void setIdediteur(Integer idediteur) {
        this.idediteur = idediteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    @XmlTransient
    public List<Livre> getLivreList() {
        return livreList;
    }

    public void setLivreList(List<Livre> livreList) {
        this.livreList = livreList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idediteur != null ? idediteur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Editeur)) {
            return false;
        }
        Editeur other = (Editeur) object;
        if ((this.idediteur == null && other.idediteur != null) || (this.idediteur != null && !this.idediteur.equals(other.idediteur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Editeur[ idediteur=" + idediteur + " ]";
    }
    
}
