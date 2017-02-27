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
@Table(name = "classe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classe.findAll", query = "SELECT c FROM Classe c"),
    @NamedQuery(name = "Classe.findByIdclasse", query = "SELECT c FROM Classe c WHERE c.idclasse = :idclasse"),
    @NamedQuery(name = "Classe.findByNom", query = "SELECT c FROM Classe c WHERE c.nom = :nom"),
    @NamedQuery(name = "Classe.findByEtat", query = "SELECT c FROM Classe c WHERE c.etat = :etat"),
    @NamedQuery(name = "Classe.findByNiveau", query = "SELECT c FROM Classe c WHERE c.niveau = :niveau"),
    @NamedQuery(name = "Classe.findByFictive", query = "SELECT c FROM Classe c WHERE c.fictive = :fictive")})
public class Classe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idclasse")
    private Integer idclasse;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Column(name = "etat")
    private Boolean etat;
    @Column(name = "niveau")
    private Integer niveau;
    @Column(name = "fictive")
    private Boolean fictive;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idclasse", fetch = FetchType.LAZY)
    private List<Classecategorie> classecategorieList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idclasse", fetch = FetchType.LAZY)
    private List<Personmatiereclasseanneedate> personmatiereclasseanneedateList;
    @OneToMany(mappedBy = "idclasse", fetch = FetchType.LAZY)
    private List<Personnelmatiereclasseannee> personnelmatiereclasseanneeList;
    @OneToMany(mappedBy = "classe", fetch = FetchType.LAZY)
    private List<ClasseElementevaluation> classeElementevaluationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idclasse", fetch = FetchType.LAZY)
    private List<Classesalle> classesalleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idclasse", fetch = FetchType.LAZY)
    private List<Classematiere> classematiereList;
    @JoinColumn(name = "idcycle", referencedColumnName = "idcycle")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cycle idcycle;
    @JoinColumn(name = "idbranche", referencedColumnName = "idbranche")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Enseignement idbranche;
    @JoinColumn(name = "etablissement", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etablissement etablissement;
    @OneToMany(mappedBy = "idclasse", fetch = FetchType.LAZY)
    private List<Eleveanneeclasse> eleveanneeclasseList;

    public Classe() {
    }

    public Classe(Integer idclasse) {
        this.idclasse = idclasse;
    }

    public Integer getIdclasse() {
        return idclasse;
    }

    public void setIdclasse(Integer idclasse) {
        this.idclasse = idclasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Integer getNiveau() {
        return niveau;
    }

    public void setNiveau(Integer niveau) {
        this.niveau = niveau;
    }

    public Boolean getFictive() {
        return fictive;
    }

    public void setFictive(Boolean fictive) {
        this.fictive = fictive;
    }

    @XmlTransient
    public List<Classecategorie> getClassecategorieList() {
        return classecategorieList;
    }

    public void setClassecategorieList(List<Classecategorie> classecategorieList) {
        this.classecategorieList = classecategorieList;
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
    public List<ClasseElementevaluation> getClasseElementevaluationList() {
        return classeElementevaluationList;
    }

    public void setClasseElementevaluationList(List<ClasseElementevaluation> classeElementevaluationList) {
        this.classeElementevaluationList = classeElementevaluationList;
    }

    @XmlTransient
    public List<Classesalle> getClassesalleList() {
        return classesalleList;
    }

    public void setClassesalleList(List<Classesalle> classesalleList) {
        this.classesalleList = classesalleList;
    }

    @XmlTransient
    public List<Classematiere> getClassematiereList() {
        return classematiereList;
    }

    public void setClassematiereList(List<Classematiere> classematiereList) {
        this.classematiereList = classematiereList;
    }

    public Cycle getIdcycle() {
        return idcycle;
    }

    public void setIdcycle(Cycle idcycle) {
        this.idcycle = idcycle;
    }

    public Enseignement getIdbranche() {
        return idbranche;
    }

    public void setIdbranche(Enseignement idbranche) {
        this.idbranche = idbranche;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    @XmlTransient
    public List<Eleveanneeclasse> getEleveanneeclasseList() {
        return eleveanneeclasseList;
    }

    public void setEleveanneeclasseList(List<Eleveanneeclasse> eleveanneeclasseList) {
        this.eleveanneeclasseList = eleveanneeclasseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idclasse != null ? idclasse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classe)) {
            return false;
        }
        Classe other = (Classe) object;
        if ((this.idclasse == null && other.idclasse != null) || (this.idclasse != null && !this.idclasse.equals(other.idclasse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Classe[ idclasse=" + idclasse + " ]";
    }
    
}
