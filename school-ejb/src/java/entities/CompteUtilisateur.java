/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "compte_utilisateur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompteUtilisateur.findAll", query = "SELECT c FROM CompteUtilisateur c"),
    @NamedQuery(name = "CompteUtilisateur.findByIdcompte", query = "SELECT c FROM CompteUtilisateur c WHERE c.idcompte = :idcompte"),
    @NamedQuery(name = "CompteUtilisateur.findByLogin", query = "SELECT c FROM CompteUtilisateur c WHERE c.login = :login"),
    @NamedQuery(name = "CompteUtilisateur.findByPassword", query = "SELECT c FROM CompteUtilisateur c WHERE c.password = :password"),
    @NamedQuery(name = "CompteUtilisateur.findByDatecreation", query = "SELECT c FROM CompteUtilisateur c WHERE c.datecreation = :datecreation"),
    @NamedQuery(name = "CompteUtilisateur.findByEtat", query = "SELECT c FROM CompteUtilisateur c WHERE c.etat = :etat"),
    @NamedQuery(name = "CompteUtilisateur.findByPrincipale", query = "SELECT c FROM CompteUtilisateur c WHERE c.principale = :principale")})
public class CompteUtilisateur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcompte")
    private Integer idcompte;
    @Size(max = 2147483647)
    @Column(name = "login")
    private String login;
    @Size(max = 2147483647)
    @Column(name = "password")
    private String password;
    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;
    @Column(name = "etat")
    private Boolean etat;
    @Column(name = "principale")
    private Boolean principale;
    @OneToMany(mappedBy = "compteutilisateur", fetch = FetchType.LAZY)
    private List<Traceur> traceurList;
    @OneToMany(mappedBy = "idcompte", fetch = FetchType.LAZY)
    private List<Privilege> privilegeList;
    @JoinColumn(name = "etablissement", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etablissement etablissement;
    @JoinColumn(name = "personnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel personnel;

    public CompteUtilisateur() {
    }

    public CompteUtilisateur(Integer idcompte) {
        this.idcompte = idcompte;
    }

    public Integer getIdcompte() {
        return idcompte;
    }

    public void setIdcompte(Integer idcompte) {
        this.idcompte = idcompte;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Boolean getPrincipale() {
        return principale;
    }

    public void setPrincipale(Boolean principale) {
        this.principale = principale;
    }

    @XmlTransient
    public List<Traceur> getTraceurList() {
        return traceurList;
    }

    public void setTraceurList(List<Traceur> traceurList) {
        this.traceurList = traceurList;
    }

    @XmlTransient
    public List<Privilege> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<Privilege> privilegeList) {
        this.privilegeList = privilegeList;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
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
        hash += (idcompte != null ? idcompte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompteUtilisateur)) {
            return false;
        }
        CompteUtilisateur other = (CompteUtilisateur) object;
        if ((this.idcompte == null && other.idcompte != null) || (this.idcompte != null && !this.idcompte.equals(other.idcompte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CompteUtilisateur[ idcompte=" + idcompte + " ]";
    }
    
}
