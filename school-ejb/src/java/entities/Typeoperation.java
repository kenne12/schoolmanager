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
@Table(name = "typeoperation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Typeoperation.findAll", query = "SELECT t FROM Typeoperation t"),
    @NamedQuery(name = "Typeoperation.findByIdtypeoperation", query = "SELECT t FROM Typeoperation t WHERE t.idtypeoperation = :idtypeoperation"),
    @NamedQuery(name = "Typeoperation.findByNom", query = "SELECT t FROM Typeoperation t WHERE t.nom = :nom")})
public class Typeoperation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtypeoperation")
    private Integer idtypeoperation;
    @Size(max = 2147483647)
    @Column(name = "nom")
    private String nom;
    @OneToMany(mappedBy = "idtypeoperation", fetch = FetchType.LAZY)
    private List<Operation> operationList;

    public Typeoperation() {
    }

    public Typeoperation(Integer idtypeoperation) {
        this.idtypeoperation = idtypeoperation;
    }

    public Integer getIdtypeoperation() {
        return idtypeoperation;
    }

    public void setIdtypeoperation(Integer idtypeoperation) {
        this.idtypeoperation = idtypeoperation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlTransient
    public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtypeoperation != null ? idtypeoperation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typeoperation)) {
            return false;
        }
        Typeoperation other = (Typeoperation) object;
        if ((this.idtypeoperation == null && other.idtypeoperation != null) || (this.idtypeoperation != null && !this.idtypeoperation.equals(other.idtypeoperation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Typeoperation[ idtypeoperation=" + idtypeoperation + " ]";
    }
    
}
