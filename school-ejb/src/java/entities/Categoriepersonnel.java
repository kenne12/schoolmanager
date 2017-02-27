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
@Table(name = "categoriepersonnel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoriepersonnel.findAll", query = "SELECT c FROM Categoriepersonnel c"),
    @NamedQuery(name = "Categoriepersonnel.findByIdcatpersonnel", query = "SELECT c FROM Categoriepersonnel c WHERE c.idcatpersonnel = :idcatpersonnel"),
    @NamedQuery(name = "Categoriepersonnel.findByCodecategorie", query = "SELECT c FROM Categoriepersonnel c WHERE c.codecategorie = :codecategorie"),
    @NamedQuery(name = "Categoriepersonnel.findByPrixHeure", query = "SELECT c FROM Categoriepersonnel c WHERE c.prixHeure = :prixHeure"),
    @NamedQuery(name = "Categoriepersonnel.findByEtat", query = "SELECT c FROM Categoriepersonnel c WHERE c.etat = :etat")})
public class Categoriepersonnel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcatpersonnel")
    private Integer idcatpersonnel;
    @Size(max = 254)
    @Column(name = "codecategorie")
    private String codecategorie;
    @Column(name = "prix_heure")
    private Integer prixHeure;
    @Column(name = "etat")
    private Boolean etat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idcatpersonnel", fetch = FetchType.LAZY)
    private List<Personnelanneecatfonct> personnelanneecatfonctList;

    public Categoriepersonnel() {
    }

    public Categoriepersonnel(Integer idcatpersonnel) {
        this.idcatpersonnel = idcatpersonnel;
    }

    public Integer getIdcatpersonnel() {
        return idcatpersonnel;
    }

    public void setIdcatpersonnel(Integer idcatpersonnel) {
        this.idcatpersonnel = idcatpersonnel;
    }

    public String getCodecategorie() {
        return codecategorie;
    }

    public void setCodecategorie(String codecategorie) {
        this.codecategorie = codecategorie;
    }

    public Integer getPrixHeure() {
        return prixHeure;
    }

    public void setPrixHeure(Integer prixHeure) {
        this.prixHeure = prixHeure;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    @XmlTransient
    public List<Personnelanneecatfonct> getPersonnelanneecatfonctList() {
        return personnelanneecatfonctList;
    }

    public void setPersonnelanneecatfonctList(List<Personnelanneecatfonct> personnelanneecatfonctList) {
        this.personnelanneecatfonctList = personnelanneecatfonctList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcatpersonnel != null ? idcatpersonnel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categoriepersonnel)) {
            return false;
        }
        Categoriepersonnel other = (Categoriepersonnel) object;
        if ((this.idcatpersonnel == null && other.idcatpersonnel != null) || (this.idcatpersonnel != null && !this.idcatpersonnel.equals(other.idcatpersonnel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Categoriepersonnel[ idcatpersonnel=" + idcatpersonnel + " ]";
    }
    
}
