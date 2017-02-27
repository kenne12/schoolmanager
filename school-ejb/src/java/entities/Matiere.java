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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "matiere")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matiere.findAll", query = "SELECT m FROM Matiere m"),
    @NamedQuery(name = "Matiere.findByIdmatiere", query = "SELECT m FROM Matiere m WHERE m.idmatiere = :idmatiere"),
    @NamedQuery(name = "Matiere.findByLibelle", query = "SELECT m FROM Matiere m WHERE m.libelle = :libelle"),
    @NamedQuery(name = "Matiere.findByCoefficient", query = "SELECT m FROM Matiere m WHERE m.coefficient = :coefficient"),
    @NamedQuery(name = "Matiere.findByEnseigne", query = "SELECT m FROM Matiere m WHERE m.enseigne = :enseigne")})
public class Matiere implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmatiere")
    private Integer idmatiere;
    @Size(max = 254)
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "coefficient")
    private Integer coefficient;
    @Column(name = "enseigne")
    private Boolean enseigne;
    @JoinColumn(name = "idnaturematiere", referencedColumnName = "idnaturematiere")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Naturematiere idnaturematiere;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtypematiere", fetch = FetchType.LAZY)
    private List<Personmatiereclasseanneedate> personmatiereclasseanneedateList;
    @OneToMany(mappedBy = "idmatiere", fetch = FetchType.LAZY)
    private List<Personnelmatiereclasseannee> personnelmatiereclasseanneeList;
    @OneToMany(mappedBy = "matiere", fetch = FetchType.LAZY)
    private List<Qualification> qualificationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "matiere", fetch = FetchType.LAZY)
    private List<ElementEvaluation> elementEvaluationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idmatiere", fetch = FetchType.LAZY)
    private List<Classematiere> classematiereList;

    public Matiere() {
    }

    public Matiere(Integer idmatiere) {
        this.idmatiere = idmatiere;
    }

    public Integer getIdmatiere() {
        return idmatiere;
    }

    public void setIdmatiere(Integer idmatiere) {
        this.idmatiere = idmatiere;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Integer coefficient) {
        this.coefficient = coefficient;
    }

    public Boolean getEnseigne() {
        return enseigne;
    }

    public void setEnseigne(Boolean enseigne) {
        this.enseigne = enseigne;
    }

    public Naturematiere getIdnaturematiere() {
        return idnaturematiere;
    }

    public void setIdnaturematiere(Naturematiere idnaturematiere) {
        this.idnaturematiere = idnaturematiere;
    }

    @XmlTransient
    public List<Personmatiereclasseanneedate> getPersonmatiereclasseanneedateList() {
        return personmatiereclasseanneedateList;
    }

    public void setPersonmatiereclasseanneedateList(List<Personmatiereclasseanneedate> personmatiereclasseanneedateList) {
        this.personmatiereclasseanneedateList = personmatiereclasseanneedateList;
    }

    @XmlTransient
    public List<Personnelmatiereclasseannee> getPersonnelmatiereclasseanneeList() {
        return personnelmatiereclasseanneeList;
    }

    public void setPersonnelmatiereclasseanneeList(List<Personnelmatiereclasseannee> personnelmatiereclasseanneeList) {
        this.personnelmatiereclasseanneeList = personnelmatiereclasseanneeList;
    }

    @XmlTransient
    public List<Qualification> getQualificationList() {
        return qualificationList;
    }

    public void setQualificationList(List<Qualification> qualificationList) {
        this.qualificationList = qualificationList;
    }

    @XmlTransient
    public List<ElementEvaluation> getElementEvaluationList() {
        return elementEvaluationList;
    }

    public void setElementEvaluationList(List<ElementEvaluation> elementEvaluationList) {
        this.elementEvaluationList = elementEvaluationList;
    }

    @XmlTransient
    public List<Classematiere> getClassematiereList() {
        return classematiereList;
    }

    public void setClassematiereList(List<Classematiere> classematiereList) {
        this.classematiereList = classematiereList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmatiere != null ? idmatiere.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matiere)) {
            return false;
        }
        Matiere other = (Matiere) object;
        if ((this.idmatiere == null && other.idmatiere != null) || (this.idmatiere != null && !this.idmatiere.equals(other.idmatiere))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Matiere[ idmatiere=" + idmatiere + " ]";
    }
    
}
