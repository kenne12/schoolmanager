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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "eleve")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eleve.findAll", query = "SELECT e FROM Eleve e"),
    @NamedQuery(name = "Eleve.findByMatricule", query = "SELECT e FROM Eleve e WHERE e.matricule = :matricule"),
    @NamedQuery(name = "Eleve.findByNom", query = "SELECT e FROM Eleve e WHERE e.nom = :nom"),
    @NamedQuery(name = "Eleve.findByPrenom", query = "SELECT e FROM Eleve e WHERE e.prenom = :prenom"),
    @NamedQuery(name = "Eleve.findByDatenaissance", query = "SELECT e FROM Eleve e WHERE e.datenaissance = :datenaissance"),
    @NamedQuery(name = "Eleve.findByLieunaissance", query = "SELECT e FROM Eleve e WHERE e.lieunaissance = :lieunaissance"),
    @NamedQuery(name = "Eleve.findBySexe", query = "SELECT e FROM Eleve e WHERE e.sexe = :sexe"),
    @NamedQuery(name = "Eleve.findByPhoto", query = "SELECT e FROM Eleve e WHERE e.photo = :photo"),
    @NamedQuery(name = "Eleve.findByAnneeadmission", query = "SELECT e FROM Eleve e WHERE e.anneeadmission = :anneeadmission"),
    @NamedQuery(name = "Eleve.findByEtateleve", query = "SELECT e FROM Eleve e WHERE e.etateleve = :etateleve"),
    @NamedQuery(name = "Eleve.findByIdeleve", query = "SELECT e FROM Eleve e WHERE e.ideleve = :ideleve")})
public class Eleve implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "matricule")
    private String matricule;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Size(max = 254)
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "datenaissance")
    @Temporal(TemporalType.DATE)
    private Date datenaissance;
    @Size(max = 254)
    @Column(name = "lieunaissance")
    private String lieunaissance;
    @Size(max = 254)
    @Column(name = "sexe")
    private String sexe;
    @Size(max = 254)
    @Column(name = "photo")
    private String photo;
    @Column(name = "anneeadmission")
    @Temporal(TemporalType.DATE)
    private Date anneeadmission;
    @Column(name = "etateleve")
    private Boolean etateleve;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ideleve")
    private Integer ideleve;
    @JoinColumn(name = "etablissement", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etablissement etablissement;
    @JoinColumn(name = "parent", referencedColumnName = "idparent")
    @ManyToOne(fetch = FetchType.LAZY)
    private Parent parent;
    @OneToMany(mappedBy = "eleve", fetch = FetchType.LAZY)
    private List<Absenceeleve> absenceeleveList;
    @OneToMany(mappedBy = "eleve", fetch = FetchType.LAZY)
    private List<Evaluation> evaluationList;
    @OneToMany(mappedBy = "eleve", fetch = FetchType.LAZY)
    private List<Pension> pensionList;
    @OneToMany(mappedBy = "eleve", fetch = FetchType.LAZY)
    private List<PensionCumulee> pensionCumuleeList;
    @OneToMany(mappedBy = "eleve", fetch = FetchType.LAZY)
    private List<PensionSave> pensionSaveList;
    @OneToMany(mappedBy = "eleve", fetch = FetchType.LAZY)
    private List<Eleveanneeclasse> eleveanneeclasseList;
    @OneToMany(mappedBy = "eleve", fetch = FetchType.LAZY)
    private List<Elevelivreemprunte> elevelivreemprunteList;
    @OneToMany(mappedBy = "eleve", fetch = FetchType.LAZY)
    private List<Punition> punitionList;

    public Eleve() {
    }

    public Eleve(Integer ideleve) {
        this.ideleve = ideleve;
    }

    public Eleve(Integer ideleve, String matricule) {
        this.ideleve = ideleve;
        this.matricule = matricule;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public String getLieunaissance() {
        return lieunaissance;
    }

    public void setLieunaissance(String lieunaissance) {
        this.lieunaissance = lieunaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getAnneeadmission() {
        return anneeadmission;
    }

    public void setAnneeadmission(Date anneeadmission) {
        this.anneeadmission = anneeadmission;
    }

    public Boolean getEtateleve() {
        return etateleve;
    }

    public void setEtateleve(Boolean etateleve) {
        this.etateleve = etateleve;
    }

    public Integer getIdeleve() {
        return ideleve;
    }

    public void setIdeleve(Integer ideleve) {
        this.ideleve = ideleve;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    @XmlTransient
    public List<Absenceeleve> getAbsenceeleveList() {
        return absenceeleveList;
    }

    public void setAbsenceeleveList(List<Absenceeleve> absenceeleveList) {
        this.absenceeleveList = absenceeleveList;
    }

    @XmlTransient
    public List<Evaluation> getEvaluationList() {
        return evaluationList;
    }

    public void setEvaluationList(List<Evaluation> evaluationList) {
        this.evaluationList = evaluationList;
    }

    @XmlTransient
    public List<Pension> getPensionList() {
        return pensionList;
    }

    public void setPensionList(List<Pension> pensionList) {
        this.pensionList = pensionList;
    }

    @XmlTransient
    public List<PensionCumulee> getPensionCumuleeList() {
        return pensionCumuleeList;
    }

    public void setPensionCumuleeList(List<PensionCumulee> pensionCumuleeList) {
        this.pensionCumuleeList = pensionCumuleeList;
    }

    @XmlTransient
    public List<PensionSave> getPensionSaveList() {
        return pensionSaveList;
    }

    public void setPensionSaveList(List<PensionSave> pensionSaveList) {
        this.pensionSaveList = pensionSaveList;
    }

    @XmlTransient
    public List<Eleveanneeclasse> getEleveanneeclasseList() {
        return eleveanneeclasseList;
    }

    public void setEleveanneeclasseList(List<Eleveanneeclasse> eleveanneeclasseList) {
        this.eleveanneeclasseList = eleveanneeclasseList;
    }

    @XmlTransient
    public List<Elevelivreemprunte> getElevelivreemprunteList() {
        return elevelivreemprunteList;
    }

    public void setElevelivreemprunteList(List<Elevelivreemprunte> elevelivreemprunteList) {
        this.elevelivreemprunteList = elevelivreemprunteList;
    }

    @XmlTransient
    public List<Punition> getPunitionList() {
        return punitionList;
    }

    public void setPunitionList(List<Punition> punitionList) {
        this.punitionList = punitionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideleve != null ? ideleve.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eleve)) {
            return false;
        }
        Eleve other = (Eleve) object;
        if ((this.ideleve == null && other.ideleve != null) || (this.ideleve != null && !this.ideleve.equals(other.ideleve))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Eleve[ ideleve=" + ideleve + " ]";
    }
    
}
