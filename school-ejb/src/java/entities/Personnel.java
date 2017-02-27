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
@Table(name = "personnel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personnel.findAll", query = "SELECT p FROM Personnel p"),
    @NamedQuery(name = "Personnel.findByMatriculepersonnel", query = "SELECT p FROM Personnel p WHERE p.matriculepersonnel = :matriculepersonnel"),
    @NamedQuery(name = "Personnel.findByNom", query = "SELECT p FROM Personnel p WHERE p.nom = :nom"),
    @NamedQuery(name = "Personnel.findByPrenom", query = "SELECT p FROM Personnel p WHERE p.prenom = :prenom"),
    @NamedQuery(name = "Personnel.findByAdresse", query = "SELECT p FROM Personnel p WHERE p.adresse = :adresse"),
    @NamedQuery(name = "Personnel.findByTelephone", query = "SELECT p FROM Personnel p WHERE p.telephone = :telephone"),
    @NamedQuery(name = "Personnel.findByEmail", query = "SELECT p FROM Personnel p WHERE p.email = :email"),
    @NamedQuery(name = "Personnel.findByDaterecrutement", query = "SELECT p FROM Personnel p WHERE p.daterecrutement = :daterecrutement"),
    @NamedQuery(name = "Personnel.findByEtatpersonnel", query = "SELECT p FROM Personnel p WHERE p.etatpersonnel = :etatpersonnel"),
    @NamedQuery(name = "Personnel.findByPhoto", query = "SELECT p FROM Personnel p WHERE p.photo = :photo"),
    @NamedQuery(name = "Personnel.findByAdmin", query = "SELECT p FROM Personnel p WHERE p.admin = :admin"),
    @NamedQuery(name = "Personnel.findByIdpersonnel", query = "SELECT p FROM Personnel p WHERE p.idpersonnel = :idpersonnel"),
    @NamedQuery(name = "Personnel.findByTheme", query = "SELECT p FROM Personnel p WHERE p.theme = :theme"),
    @NamedQuery(name = "Personnel.findByEnseignant", query = "SELECT p FROM Personnel p WHERE p.enseignant = :enseignant")})
public class Personnel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "matriculepersonnel")
    private String matriculepersonnel;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Size(max = 254)
    @Column(name = "prenom")
    private String prenom;
    @Size(max = 254)
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "telephone")
    private Integer telephone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 254)
    @Column(name = "email")
    private String email;
    @Column(name = "daterecrutement")
    @Temporal(TemporalType.DATE)
    private Date daterecrutement;
    @Column(name = "etatpersonnel")
    private Boolean etatpersonnel;
    @Size(max = 254)
    @Column(name = "photo")
    private String photo;
    @Column(name = "admin")
    private Boolean admin;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpersonnel")
    private Integer idpersonnel;
    @Size(max = 2147483647)
    @Column(name = "theme")
    private String theme;
    @Column(name = "enseignant")
    private Boolean enseignant;
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    private List<Personnelmodule> personnelmoduleList;
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    private List<Absenceeleve> absenceeleveList;
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    private List<Evaluation> evaluationList;
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    private List<Travauxdirige> travauxdirigeList;
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    private List<Personnelmatiereclasseannee> personnelmatiereclasseanneeList;
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    private List<Activite> activiteList;
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    private List<Qualification> qualificationList;
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    private List<EtablissementPersonnel> etablissementPersonnelList;
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    private List<Traceur> traceurList;
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    private List<Personnelprivilege> personnelprivilegeList;
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    private List<Personnelanneecatfonct> personnelanneecatfonctList;
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    private List<CompteUtilisateur> compteUtilisateurList;
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    private List<Remuneration> remunerationList;
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    private List<Personnelmenu> personnelmenuList;
    @JoinColumn(name = "fonction", referencedColumnName = "idfonction")
    @ManyToOne(fetch = FetchType.LAZY)
    private Fonction fonction;
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    private List<Punition> punitionList;
    @OneToMany(mappedBy = "personnel", fetch = FetchType.LAZY)
    private List<PhotoPersonnel> photoPersonnelList;

    public Personnel() {
    }

    public Personnel(Integer idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    public Personnel(Integer idpersonnel, String matriculepersonnel) {
        this.idpersonnel = idpersonnel;
        this.matriculepersonnel = matriculepersonnel;
    }

    public String getMatriculepersonnel() {
        return matriculepersonnel;
    }

    public void setMatriculepersonnel(String matriculepersonnel) {
        this.matriculepersonnel = matriculepersonnel;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDaterecrutement() {
        return daterecrutement;
    }

    public void setDaterecrutement(Date daterecrutement) {
        this.daterecrutement = daterecrutement;
    }

    public Boolean getEtatpersonnel() {
        return etatpersonnel;
    }

    public void setEtatpersonnel(Boolean etatpersonnel) {
        this.etatpersonnel = etatpersonnel;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Integer getIdpersonnel() {
        return idpersonnel;
    }

    public void setIdpersonnel(Integer idpersonnel) {
        this.idpersonnel = idpersonnel;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Boolean getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Boolean enseignant) {
        this.enseignant = enseignant;
    }

    @XmlTransient
    public List<Personnelmodule> getPersonnelmoduleList() {
        return personnelmoduleList;
    }

    public void setPersonnelmoduleList(List<Personnelmodule> personnelmoduleList) {
        this.personnelmoduleList = personnelmoduleList;
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
    public List<Travauxdirige> getTravauxdirigeList() {
        return travauxdirigeList;
    }

    public void setTravauxdirigeList(List<Travauxdirige> travauxdirigeList) {
        this.travauxdirigeList = travauxdirigeList;
    }

    @XmlTransient
    public List<Personnelmatiereclasseannee> getPersonnelmatiereclasseanneeList() {
        return personnelmatiereclasseanneeList;
    }

    public void setPersonnelmatiereclasseanneeList(List<Personnelmatiereclasseannee> personnelmatiereclasseanneeList) {
        this.personnelmatiereclasseanneeList = personnelmatiereclasseanneeList;
    }

    @XmlTransient
    public List<Activite> getActiviteList() {
        return activiteList;
    }

    public void setActiviteList(List<Activite> activiteList) {
        this.activiteList = activiteList;
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
    public List<Traceur> getTraceurList() {
        return traceurList;
    }

    public void setTraceurList(List<Traceur> traceurList) {
        this.traceurList = traceurList;
    }

    @XmlTransient
    public List<Personnelprivilege> getPersonnelprivilegeList() {
        return personnelprivilegeList;
    }

    public void setPersonnelprivilegeList(List<Personnelprivilege> personnelprivilegeList) {
        this.personnelprivilegeList = personnelprivilegeList;
    }

    @XmlTransient
    public List<Personnelanneecatfonct> getPersonnelanneecatfonctList() {
        return personnelanneecatfonctList;
    }

    public void setPersonnelanneecatfonctList(List<Personnelanneecatfonct> personnelanneecatfonctList) {
        this.personnelanneecatfonctList = personnelanneecatfonctList;
    }

    @XmlTransient
    public List<CompteUtilisateur> getCompteUtilisateurList() {
        return compteUtilisateurList;
    }

    public void setCompteUtilisateurList(List<CompteUtilisateur> compteUtilisateurList) {
        this.compteUtilisateurList = compteUtilisateurList;
    }

    @XmlTransient
    public List<Remuneration> getRemunerationList() {
        return remunerationList;
    }

    public void setRemunerationList(List<Remuneration> remunerationList) {
        this.remunerationList = remunerationList;
    }

    @XmlTransient
    public List<Personnelmenu> getPersonnelmenuList() {
        return personnelmenuList;
    }

    public void setPersonnelmenuList(List<Personnelmenu> personnelmenuList) {
        this.personnelmenuList = personnelmenuList;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }

    @XmlTransient
    public List<Punition> getPunitionList() {
        return punitionList;
    }

    public void setPunitionList(List<Punition> punitionList) {
        this.punitionList = punitionList;
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
        hash += (idpersonnel != null ? idpersonnel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personnel)) {
            return false;
        }
        Personnel other = (Personnel) object;
        if ((this.idpersonnel == null && other.idpersonnel != null) || (this.idpersonnel != null && !this.idpersonnel.equals(other.idpersonnel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Personnel[ idpersonnel=" + idpersonnel + " ]";
    }
    
}
