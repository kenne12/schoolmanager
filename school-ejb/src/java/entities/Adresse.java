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
@Table(name = "adresse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adresse.findAll", query = "SELECT a FROM Adresse a"),
    @NamedQuery(name = "Adresse.findById", query = "SELECT a FROM Adresse a WHERE a.id = :id"),
    @NamedQuery(name = "Adresse.findByBp", query = "SELECT a FROM Adresse a WHERE a.bp = :bp"),
    @NamedQuery(name = "Adresse.findByContact", query = "SELECT a FROM Adresse a WHERE a.contact = :contact"),
    @NamedQuery(name = "Adresse.findBySiteweb", query = "SELECT a FROM Adresse a WHERE a.siteweb = :siteweb"),
    @NamedQuery(name = "Adresse.findByEmail", query = "SELECT a FROM Adresse a WHERE a.email = :email"),
    @NamedQuery(name = "Adresse.findByAdresse", query = "SELECT a FROM Adresse a WHERE a.adresse = :adresse"),
    @NamedQuery(name = "Adresse.findByContact2", query = "SELECT a FROM Adresse a WHERE a.contact2 = :contact2"),
    @NamedQuery(name = "Adresse.findByFaxe", query = "SELECT a FROM Adresse a WHERE a.faxe = :faxe")})
public class Adresse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "bp")
    private String bp;
    @Size(max = 2147483647)
    @Column(name = "contact")
    private String contact;
    @Size(max = 2147483647)
    @Column(name = "siteweb")
    private String siteweb;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 2147483647)
    @Column(name = "email")
    private String email;
    @Size(max = 2147483647)
    @Column(name = "adresse")
    private String adresse;
    @Size(max = 2147483647)
    @Column(name = "contact2")
    private String contact2;
    @Size(max = 2147483647)
    @Column(name = "faxe")
    private String faxe;
    @OneToMany(mappedBy = "adresse", fetch = FetchType.LAZY)
    private List<Etablissement> etablissementList;

    public Adresse() {
    }

    public Adresse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBp() {
        return bp;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getSiteweb() {
        return siteweb;
    }

    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getContact2() {
        return contact2;
    }

    public void setContact2(String contact2) {
        this.contact2 = contact2;
    }

    public String getFaxe() {
        return faxe;
    }

    public void setFaxe(String faxe) {
        this.faxe = faxe;
    }

    @XmlTransient
    public List<Etablissement> getEtablissementList() {
        return etablissementList;
    }

    public void setEtablissementList(List<Etablissement> etablissementList) {
        this.etablissementList = etablissementList;
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
        if (!(object instanceof Adresse)) {
            return false;
        }
        Adresse other = (Adresse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Adresse[ id=" + id + " ]";
    }
    
}
