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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "fonction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fonction.findAll", query = "SELECT f FROM Fonction f"),
    @NamedQuery(name = "Fonction.findByIdfonction", query = "SELECT f FROM Fonction f WHERE f.idfonction = :idfonction"),
    @NamedQuery(name = "Fonction.findByNom", query = "SELECT f FROM Fonction f WHERE f.nom = :nom"),
    @NamedQuery(name = "Fonction.findByEtat", query = "SELECT f FROM Fonction f WHERE f.etat = :etat")})
public class Fonction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idfonction")
    private Integer idfonction;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Column(name = "etat")
    private Boolean etat;
    @JoinColumn(name = "idetablissement", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etablissement idetablissement;
    @OneToMany(mappedBy = "idfonction", fetch = FetchType.LAZY)
    private List<Personnelanneecatfonct> personnelanneecatfonctList;
    @OneToMany(mappedBy = "fonction", fetch = FetchType.LAZY)
    private List<Personnel> personnelList;

    public Fonction() {
    }

    public Fonction(Integer idfonction) {
        this.idfonction = idfonction;
    }

    public Integer getIdfonction() {
        return idfonction;
    }

    public void setIdfonction(Integer idfonction) {
        this.idfonction = idfonction;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Etablissement getIdetablissement() {
        return idetablissement;
    }

    public void setIdetablissement(Etablissement idetablissement) {
        this.idetablissement = idetablissement;
    }

    @XmlTransient
    public List<Personnelanneecatfonct> getPersonnelanneecatfonctList() {
        return personnelanneecatfonctList;
    }

    public void setPersonnelanneecatfonctList(List<Personnelanneecatfonct> personnelanneecatfonctList) {
        this.personnelanneecatfonctList = personnelanneecatfonctList;
    }

    @XmlTransient
    public List<Personnel> getPersonnelList() {
        return personnelList;
    }

    public void setPersonnelList(List<Personnel> personnelList) {
        this.personnelList = personnelList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfonction != null ? idfonction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fonction)) {
            return false;
        }
        Fonction other = (Fonction) object;
        if ((this.idfonction == null && other.idfonction != null) || (this.idfonction != null && !this.idfonction.equals(other.idfonction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Fonction[ idfonction=" + idfonction + " ]";
    }
    
}
