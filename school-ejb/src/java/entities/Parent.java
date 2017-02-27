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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "parent")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parent.findAll", query = "SELECT p FROM Parent p"),
    @NamedQuery(name = "Parent.findByNom", query = "SELECT p FROM Parent p WHERE p.nom = :nom"),
    @NamedQuery(name = "Parent.findByPrenom", query = "SELECT p FROM Parent p WHERE p.prenom = :prenom"),
    @NamedQuery(name = "Parent.findByAdresse", query = "SELECT p FROM Parent p WHERE p.adresse = :adresse"),
    @NamedQuery(name = "Parent.findByTelephone", query = "SELECT p FROM Parent p WHERE p.telephone = :telephone"),
    @NamedQuery(name = "Parent.findByEmail", query = "SELECT p FROM Parent p WHERE p.email = :email"),
    @NamedQuery(name = "Parent.findByIdparent", query = "SELECT p FROM Parent p WHERE p.idparent = :idparent")})
public class Parent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Size(max = 254)
    @Column(name = "prenom")
    private String prenom;
    @Size(max = 254)
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "telephone")
    private Integer telephone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 254)
    @Column(name = "email")
    private String email;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idparent")
    private Integer idparent;
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private List<Eleve> eleveList;

    public Parent() {
    }

    public Parent(Integer idparent) {
        this.idparent = idparent;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdparent() {
        return idparent;
    }

    public void setIdparent(Integer idparent) {
        this.idparent = idparent;
    }

    @XmlTransient
    public List<Eleve> getEleveList() {
        return eleveList;
    }

    public void setEleveList(List<Eleve> eleveList) {
        this.eleveList = eleveList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idparent != null ? idparent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parent)) {
            return false;
        }
        Parent other = (Parent) object;
        if ((this.idparent == null && other.idparent != null) || (this.idparent != null && !this.idparent.equals(other.idparent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Parent[ idparent=" + idparent + " ]";
    }
    
}
