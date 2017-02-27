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
@Table(name = "etablissement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etablissement.findAll", query = "SELECT e FROM Etablissement e"),
    @NamedQuery(name = "Etablissement.findById", query = "SELECT e FROM Etablissement e WHERE e.id = :id"),
    @NamedQuery(name = "Etablissement.findByNom", query = "SELECT e FROM Etablissement e WHERE e.nom = :nom"),
    @NamedQuery(name = "Etablissement.findByLogo", query = "SELECT e FROM Etablissement e WHERE e.logo = :logo"),
    @NamedQuery(name = "Etablissement.findByDatecreation", query = "SELECT e FROM Etablissement e WHERE e.datecreation = :datecreation"),
    @NamedQuery(name = "Etablissement.findByDevise", query = "SELECT e FROM Etablissement e WHERE e.devise = :devise")})
public class Etablissement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 2147483647)
    @Column(name = "nom")
    private String nom;
    @Size(max = 2147483647)
    @Column(name = "logo")
    private String logo;
    @Column(name = "datecreation")
    @Temporal(TemporalType.DATE)
    private Date datecreation;
    @Size(max = 2147483647)
    @Column(name = "devise")
    private String devise;
    @OneToMany(mappedBy = "etablissement", fetch = FetchType.LAZY)
    private List<Eleve> eleveList;
    @OneToMany(mappedBy = "etablissement", fetch = FetchType.LAZY)
    private List<Batiment> batimentList;
    @OneToMany(mappedBy = "idetablissement", fetch = FetchType.LAZY)
    private List<Typetranche> typetrancheList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idetablissement", fetch = FetchType.LAZY)
    private List<Observationnote> observationnoteList;
    @OneToMany(mappedBy = "etablissement", fetch = FetchType.LAZY)
    private List<Enseignement> enseignementList;
    @JoinColumn(name = "adresse", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Adresse adresse;
    @JoinColumn(name = "type", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TypeEtablissement type;
    @OneToMany(mappedBy = "etablissement", fetch = FetchType.LAZY)
    private List<Livre> livreList;
    @OneToMany(mappedBy = "etablissement", fetch = FetchType.LAZY)
    private List<Qualification> qualificationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etablissement", fetch = FetchType.LAZY)
    private List<EtablissementPersonnel> etablissementPersonnelList;
    @OneToMany(mappedBy = "idetablissement", fetch = FetchType.LAZY)
    private List<Fonction> fonctionList;
    @OneToMany(mappedBy = "etablissement", fetch = FetchType.LAZY)
    private List<Annee> anneeList;
    @OneToMany(mappedBy = "idetablissement", fetch = FetchType.LAZY)
    private List<Typecompte> typecompteList;
    @OneToMany(mappedBy = "etablissement", fetch = FetchType.LAZY)
    private List<CompteUtilisateur> compteUtilisateurList;
    @OneToMany(mappedBy = "etablissement", fetch = FetchType.LAZY)
    private List<Classe> classeList;
    @OneToMany(mappedBy = "etablissement", fetch = FetchType.LAZY)
    private List<Categorie> categorieList;
    @OneToMany(mappedBy = "idetablissement", fetch = FetchType.LAZY)
    private List<Depense> depenseList;
    @OneToMany(mappedBy = "idetablissement", fetch = FetchType.LAZY)
    private List<Recette> recetteList;
    @OneToMany(mappedBy = "idetablissement", fetch = FetchType.LAZY)
    private List<Operation> operationList;
    @OneToMany(mappedBy = "idetablissement", fetch = FetchType.LAZY)
    private List<Compte> compteList;
    @OneToMany(mappedBy = "etablissement", fetch = FetchType.LAZY)
    private List<PhotoPersonnel> photoPersonnelList;

    public Etablissement() {
    }

    public Etablissement(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    @XmlTransient
    public List<Eleve> getEleveList() {
        return eleveList;
    }

    public void setEleveList(List<Eleve> eleveList) {
        this.eleveList = eleveList;
    }

    @XmlTransient
    public List<Batiment> getBatimentList() {
        return batimentList;
    }

    public void setBatimentList(List<Batiment> batimentList) {
        this.batimentList = batimentList;
    }

    @XmlTransient
    public List<Typetranche> getTypetrancheList() {
        return typetrancheList;
    }

    public void setTypetrancheList(List<Typetranche> typetrancheList) {
        this.typetrancheList = typetrancheList;
    }

    @XmlTransient
    public List<Observationnote> getObservationnoteList() {
        return observationnoteList;
    }

    public void setObservationnoteList(List<Observationnote> observationnoteList) {
        this.observationnoteList = observationnoteList;
    }

    @XmlTransient
    public List<Enseignement> getEnseignementList() {
        return enseignementList;
    }

    public void setEnseignementList(List<Enseignement> enseignementList) {
        this.enseignementList = enseignementList;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public TypeEtablissement getType() {
        return type;
    }

    public void setType(TypeEtablissement type) {
        this.type = type;
    }

    @XmlTransient
    public List<Livre> getLivreList() {
        return livreList;
    }

    public void setLivreList(List<Livre> livreList) {
        this.livreList = livreList;
    }

    @XmlTransient
    public List<Qualification> getQualificationList() {
        return qualificationList;
    }

    public void setQualificationList(List<Qualification> qualificationList) {
        this.qualificationList = qualificationList;
    }

    @XmlTransient
    public List<EtablissementPersonnel> getEtablissementPersonnelList() {
        return etablissementPersonnelList;
    }

    public void setEtablissementPersonnelList(List<EtablissementPersonnel> etablissementPersonnelList) {
        this.etablissementPersonnelList = etablissementPersonnelList;
    }

    @XmlTransient
    public List<Fonction> getFonctionList() {
        return fonctionList;
    }

    public void setFonctionList(List<Fonction> fonctionList) {
        this.fonctionList = fonctionList;
    }

    @XmlTransient
    public List<Annee> getAnneeList() {
        return anneeList;
    }

    public void setAnneeList(List<Annee> anneeList) {
        this.anneeList = anneeList;
    }

    @XmlTransient
    public List<Typecompte> getTypecompteList() {
        return typecompteList;
    }

    public void setTypecompteList(List<Typecompte> typecompteList) {
        this.typecompteList = typecompteList;
    }

    @XmlTransient
    public List<CompteUtilisateur> getCompteUtilisateurList() {
        return compteUtilisateurList;
    }

    public void setCompteUtilisateurList(List<CompteUtilisateur> compteUtilisateurList) {
        this.compteUtilisateurList = compteUtilisateurList;
    }

    @XmlTransient
    public List<Classe> getClasseList() {
        return classeList;
    }

    public void setClasseList(List<Classe> classeList) {
        this.classeList = classeList;
    }

    @XmlTransient
    public List<Categorie> getCategorieList() {
        return categorieList;
    }

    public void setCategorieList(List<Categorie> categorieList) {
        this.categorieList = categorieList;
    }

    @XmlTransient
    public List<Depense> getDepenseList() {
        return depenseList;
    }

    public void setDepenseList(List<Depense> depenseList) {
        this.depenseList = depenseList;
    }

    @XmlTransient
    public List<Recette> getRecetteList() {
        return recetteList;
    }

    public void setRecetteList(List<Recette> recetteList) {
        this.recetteList = recetteList;
    }

    @XmlTransient
    public List<Operation> getOperationList() {
        return operationList;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    @XmlTransient
    public List<Compte> getCompteList() {
        return compteList;
    }

    public void setCompteList(List<Compte> compteList) {
        this.compteList = compteList;
    }

    @XmlTransient
    public List<PhotoPersonnel> getPhotoPersonnelList() {
        return photoPersonnelList;
    }

    public void setPhotoPersonnelList(List<PhotoPersonnel> photoPersonnelList) {
        this.photoPersonnelList = photoPersonnelList;
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
        if (!(object instanceof Etablissement)) {
            return false;
        }
        Etablissement other = (Etablissement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Etablissement[ id=" + id + " ]";
    }
    
}
