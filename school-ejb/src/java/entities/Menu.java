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
@Table(name = "menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"),
    @NamedQuery(name = "Menu.findByIdmenu", query = "SELECT m FROM Menu m WHERE m.idmenu = :idmenu"),
    @NamedQuery(name = "Menu.findByNom", query = "SELECT m FROM Menu m WHERE m.nom = :nom"),
    @NamedQuery(name = "Menu.findByRessource", query = "SELECT m FROM Menu m WHERE m.ressource = :ressource"),
    @NamedQuery(name = "Menu.findByEtat", query = "SELECT m FROM Menu m WHERE m.etat = :etat")})
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmenu")
    private Integer idmenu;
    @Size(max = 100)
    @Column(name = "nom")
    private String nom;
    @Size(max = 2147483647)
    @Column(name = "ressource")
    private String ressource;
    @Column(name = "etat")
    private Boolean etat;
    @OneToMany(mappedBy = "idmenu", fetch = FetchType.LAZY)
    private List<Privilege> privilegeList;
    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private List<Personnelmenu> personnelmenuList;

    public Menu() {
    }

    public Menu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public Integer getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getRessource() {
        return ressource;
    }

    public void setRessource(String ressource) {
        this.ressource = ressource;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    @XmlTransient
    public List<Privilege> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<Privilege> privilegeList) {
        this.privilegeList = privilegeList;
    }

    @XmlTransient
    public List<Personnelmenu> getPersonnelmenuList() {
        return personnelmenuList;
    }

    public void setPersonnelmenuList(List<Personnelmenu> personnelmenuList) {
        this.personnelmenuList = personnelmenuList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmenu != null ? idmenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.idmenu == null && other.idmenu != null) || (this.idmenu != null && !this.idmenu.equals(other.idmenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Menu[ idmenu=" + idmenu + " ]";
    }
    
}
