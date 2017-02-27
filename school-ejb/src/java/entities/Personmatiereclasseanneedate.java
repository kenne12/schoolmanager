/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "personmatiereclasseanneedate")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personmatiereclasseanneedate.findAll", query = "SELECT p FROM Personmatiereclasseanneedate p"),
    @NamedQuery(name = "Personmatiereclasseanneedate.findById", query = "SELECT p FROM Personmatiereclasseanneedate p WHERE p.id = :id"),
    @NamedQuery(name = "Personmatiereclasseanneedate.findByMatriculepersonnel", query = "SELECT p FROM Personmatiereclasseanneedate p WHERE p.matriculepersonnel = :matriculepersonnel"),
    @NamedQuery(name = "Personmatiereclasseanneedate.findByDatecours", query = "SELECT p FROM Personmatiereclasseanneedate p WHERE p.datecours = :datecours"),
    @NamedQuery(name = "Personmatiereclasseanneedate.findByNombreheure", query = "SELECT p FROM Personmatiereclasseanneedate p WHERE p.nombreheure = :nombreheure"),
    @NamedQuery(name = "Personmatiereclasseanneedate.findByPaye", query = "SELECT p FROM Personmatiereclasseanneedate p WHERE p.paye = :paye")})
public class Personmatiereclasseanneedate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "matriculepersonnel")
    private String matriculepersonnel;
    @Column(name = "datecours")
    @Temporal(TemporalType.DATE)
    private Date datecours;
    @Column(name = "nombreheure")
    private Integer nombreheure;
    @Column(name = "paye")
    private Boolean paye;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idclasse", referencedColumnName = "idclasse")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Classe idclasse;
    @JoinColumn(name = "idtypematiere", referencedColumnName = "idmatiere")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Matiere idtypematiere;

    public Personmatiereclasseanneedate() {
    }

    public Personmatiereclasseanneedate(Integer id) {
        this.id = id;
    }

    public Personmatiereclasseanneedate(Integer id, String matriculepersonnel) {
        this.id = id;
        this.matriculepersonnel = matriculepersonnel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatriculepersonnel() {
        return matriculepersonnel;
    }

    public void setMatriculepersonnel(String matriculepersonnel) {
        this.matriculepersonnel = matriculepersonnel;
    }

    public Date getDatecours() {
        return datecours;
    }

    public void setDatecours(Date datecours) {
        this.datecours = datecours;
    }

    public Integer getNombreheure() {
        return nombreheure;
    }

    public void setNombreheure(Integer nombreheure) {
        this.nombreheure = nombreheure;
    }

    public Boolean getPaye() {
        return paye;
    }

    public void setPaye(Boolean paye) {
        this.paye = paye;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Classe getIdclasse() {
        return idclasse;
    }

    public void setIdclasse(Classe idclasse) {
        this.idclasse = idclasse;
    }

    public Matiere getIdtypematiere() {
        return idtypematiere;
    }

    public void setIdtypematiere(Matiere idtypematiere) {
        this.idtypematiere = idtypematiere;
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
        if (!(object instanceof Personmatiereclasseanneedate)) {
            return false;
        }
        Personmatiereclasseanneedate other = (Personmatiereclasseanneedate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Personmatiereclasseanneedate[ id=" + id + " ]";
    }
    
}
