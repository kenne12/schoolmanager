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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "personnelmenu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personnelmenu.findAll", query = "SELECT p FROM Personnelmenu p"),
    @NamedQuery(name = "Personnelmenu.findById", query = "SELECT p FROM Personnelmenu p WHERE p.id = :id"),
    @NamedQuery(name = "Personnelmenu.findByEtat", query = "SELECT p FROM Personnelmenu p WHERE p.etat = :etat")})
public class Personnelmenu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "etat")
    private Boolean etat;
    @JoinColumn(name = "menu", referencedColumnName = "idmenu")
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;
    @JoinColumn(name = "personnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel personnel;

    public Personnelmenu() {
    }

    public Personnelmenu(Integer id) {
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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
        if (!(object instanceof Personnelmenu)) {
            return false;
        }
        Personnelmenu other = (Personnelmenu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Personnelmenu[ id=" + id + " ]";
    }
    
}
