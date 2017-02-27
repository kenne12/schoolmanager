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
@Table(name = "salle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salle.findAll", query = "SELECT s FROM Salle s"),
    @NamedQuery(name = "Salle.findByIdsalle", query = "SELECT s FROM Salle s WHERE s.idsalle = :idsalle"),
    @NamedQuery(name = "Salle.findByCode", query = "SELECT s FROM Salle s WHERE s.code = :code"),
    @NamedQuery(name = "Salle.findByNombreplace", query = "SELECT s FROM Salle s WHERE s.nombreplace = :nombreplace"),
    @NamedQuery(name = "Salle.findByNombrebanc", query = "SELECT s FROM Salle s WHERE s.nombrebanc = :nombrebanc")})
public class Salle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsalle")
    private Integer idsalle;
    @Size(max = 254)
    @Column(name = "code")
    private String code;
    @Column(name = "nombreplace")
    private Integer nombreplace;
    @Column(name = "nombrebanc")
    private Integer nombrebanc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsalle", fetch = FetchType.LAZY)
    private List<Classesalle> classesalleList;
    @JoinColumn(name = "idbatiment", referencedColumnName = "idbatiment")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Batiment idbatiment;

    public Salle() {
    }

    public Salle(Integer idsalle) {
        this.idsalle = idsalle;
    }

    public Integer getIdsalle() {
        return idsalle;
    }

    public void setIdsalle(Integer idsalle) {
        this.idsalle = idsalle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNombreplace() {
        return nombreplace;
    }

    public void setNombreplace(Integer nombreplace) {
        this.nombreplace = nombreplace;
    }

    public Integer getNombrebanc() {
        return nombrebanc;
    }

    public void setNombrebanc(Integer nombrebanc) {
        this.nombrebanc = nombrebanc;
    }

    @XmlTransient
    public List<Classesalle> getClassesalleList() {
        return classesalleList;
    }

    public void setClassesalleList(List<Classesalle> classesalleList) {
        this.classesalleList = classesalleList;
    }

    public Batiment getIdbatiment() {
        return idbatiment;
    }

    public void setIdbatiment(Batiment idbatiment) {
        this.idbatiment = idbatiment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsalle != null ? idsalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salle)) {
            return false;
        }
        Salle other = (Salle) object;
        if ((this.idsalle == null && other.idsalle != null) || (this.idsalle != null && !this.idsalle.equals(other.idsalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Salle[ idsalle=" + idsalle + " ]";
    }
    
}
