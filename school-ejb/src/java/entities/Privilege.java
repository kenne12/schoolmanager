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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "privilege")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privilege.findAll", query = "SELECT p FROM Privilege p"),
    @NamedQuery(name = "Privilege.findByIdprivilege", query = "SELECT p FROM Privilege p WHERE p.idprivilege = :idprivilege"),
    @NamedQuery(name = "Privilege.findByEtat", query = "SELECT p FROM Privilege p WHERE p.etat = :etat")})
public class Privilege implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idprivilege")
    private Long idprivilege;
    @Column(name = "etat")
    private Boolean etat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idprivilege", fetch = FetchType.LAZY)
    private List<Personnelprivilege> personnelprivilegeList;
    @JoinColumn(name = "idcompte", referencedColumnName = "idcompte")
    @ManyToOne(fetch = FetchType.LAZY)
    private CompteUtilisateur idcompte;
    @JoinColumn(name = "idmenu", referencedColumnName = "idmenu")
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu idmenu;

    public Privilege() {
    }

    public Privilege(Long idprivilege) {
        this.idprivilege = idprivilege;
    }

    public Long getIdprivilege() {
        return idprivilege;
    }

    public void setIdprivilege(Long idprivilege) {
        this.idprivilege = idprivilege;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    @XmlTransient
    public List<Personnelprivilege> getPersonnelprivilegeList() {
        return personnelprivilegeList;
    }

    public void setPersonnelprivilegeList(List<Personnelprivilege> personnelprivilegeList) {
        this.personnelprivilegeList = personnelprivilegeList;
    }

    public CompteUtilisateur getIdcompte() {
        return idcompte;
    }

    public void setIdcompte(CompteUtilisateur idcompte) {
        this.idcompte = idcompte;
    }

    public Menu getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Menu idmenu) {
        this.idmenu = idmenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprivilege != null ? idprivilege.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privilege)) {
            return false;
        }
        Privilege other = (Privilege) object;
        if ((this.idprivilege == null && other.idprivilege != null) || (this.idprivilege != null && !this.idprivilege.equals(other.idprivilege))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Privilege[ idprivilege=" + idprivilege + " ]";
    }
    
}
