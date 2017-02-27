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
@Table(name = "annee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Annee.findAll", query = "SELECT a FROM Annee a"),
    @NamedQuery(name = "Annee.findByIdannee", query = "SELECT a FROM Annee a WHERE a.idannee = :idannee"),
    @NamedQuery(name = "Annee.findByCode", query = "SELECT a FROM Annee a WHERE a.code = :code"),
    @NamedQuery(name = "Annee.findByTheme", query = "SELECT a FROM Annee a WHERE a.theme = :theme"),
    @NamedQuery(name = "Annee.findByEtatannee", query = "SELECT a FROM Annee a WHERE a.etatannee = :etatannee"),
    @NamedQuery(name = "Annee.findByCodefin", query = "SELECT a FROM Annee a WHERE a.codefin = :codefin"),
    @NamedQuery(name = "Annee.findByPrincipal", query = "SELECT a FROM Annee a WHERE a.principal = :principal")})
public class Annee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idannee")
    private Integer idannee;
    @Column(name = "code")
    private Integer code;
    @Size(max = 254)
    @Column(name = "theme")
    private String theme;
    @Column(name = "etatannee")
    private Boolean etatannee;
    @Column(name = "codefin")
    private Integer codefin;
    @Column(name = "principal")
    private Boolean principal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Catanneeprix> catanneeprixList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Sequenceannee> sequenceanneeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Punitionpersonnel> punitionpersonnelList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Absenceeleve> absenceeleveList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Activiteannee> activiteanneeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Pension> pensionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Semaineannee> semaineanneeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Personmatiereclasseanneedate> personmatiereclasseanneedateList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Personnelmatiereclasseannee> personnelmatiereclasseanneeList;
    @OneToMany(mappedBy = "annee", fetch = FetchType.LAZY)
    private List<Appreciationannuelle> appreciationannuelleList;
    @OneToMany(mappedBy = "annee", fetch = FetchType.LAZY)
    private List<Traceur> traceurList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Trimesteannee> trimesteanneeList;
    @JoinColumn(name = "etablissement", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etablissement etablissement;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Personnelanneecatfonct> personnelanneecatfonctList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Remuneration> remunerationList;
    @OneToMany(mappedBy = "annee", fetch = FetchType.LAZY)
    private List<PensionCumulee> pensionCumuleeList;
    @OneToMany(mappedBy = "annee", fetch = FetchType.LAZY)
    private List<PensionSave> pensionSaveList;
    @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Depense> depenseList;
    @OneToMany(mappedBy = "annee", fetch = FetchType.LAZY)
    private List<Tranche> trancheList;
    @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Recette> recetteList;
    @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Eleveanneeclasse> eleveanneeclasseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Anneedepense> anneedepenseList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Elevelivreemprunte> elevelivreemprunteList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Punition> punitionList;
    @OneToMany(mappedBy = "idannee", fetch = FetchType.LAZY)
    private List<Operation> operationList;

    public Annee() {
    }

    public Annee(Integer idannee) {
        this.idannee = idannee;
    }

    public Integer getIdannee() {
        return idannee;
    }

    public void setIdannee(Integer idannee) {
        this.idannee = idannee;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Boolean getEtatannee() {
        return etatannee;
    }

    public void setEtatannee(Boolean etatannee) {
        this.etatannee = etatannee;
    }

    public Integer getCodefin() {
        return codefin;
    }

    public void setCodefin(Integer codefin) {
        this.codefin = codefin;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    @XmlTransient
    public List<Catanneeprix> getCatanneeprixList() {
        return catanneeprixList;
    }

    public void setCatanneeprixList(List<Catanneeprix> catanneeprixList) {
        this.catanneeprixList = catanneeprixList;
    }

    @XmlTransient
    public List<Sequenceannee> getSequenceanneeList() {
        return sequenceanneeList;
    }

    public void setSequenceanneeList(List<Sequenceannee> sequenceanneeList) {
        this.sequenceanneeList = sequenceanneeList;
    }

    @XmlTransient
    public List<Punitionpersonnel> getPunitionpersonnelList() {
        return punitionpersonnelList;
    }

    public void setPunitionpersonnelList(List<Punitionpersonnel> punitionpersonnelList) {
        this.punitionpersonnelList = punitionpersonnelList;
    }

    @XmlTransient
    public List<Absenceeleve> getAbsenceeleveList() {
        return absenceeleveList;
    }

    public void setAbsenceeleveList(List<Absenceeleve> absenceeleveList) {
        this.absenceeleveList = absenceeleveList;
    }

    @XmlTransient
    public List<Activiteannee> getActiviteanneeList() {
        return activiteanneeList;
    }

    public void setActiviteanneeList(List<Activiteannee> activiteanneeList) {
        this.activiteanneeList = activiteanneeList;
    }

    @XmlTransient
    public List<Pension> getPensionList() {
        return pensionList;
    }

    public void setPensionList(List<Pension> pensionList) {
        this.pensionList = pensionList;
    }

    @XmlTransient
    public List<Semaineannee> getSemaineanneeList() {
        return semaineanneeList;
    }

    public void setSemaineanneeList(List<Semaineannee> semaineanneeList) {
        this.semaineanneeList = semaineanneeList;
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
    public List<Appreciationannuelle> getAppreciationannuelleList() {
        return appreciationannuelleList;
    }

    public void setAppreciationannuelleList(List<Appreciationannuelle> appreciationannuelleList) {
        this.appreciationannuelleList = appreciationannuelleList;
    }

    @XmlTransient
    public List<Traceur> getTraceurList() {
        return traceurList;
    }

    public void setTraceurList(List<Traceur> traceurList) {
        this.traceurList = traceurList;
    }

    @XmlTransient
    public List<Trimesteannee> getTrimesteanneeList() {
        return trimesteanneeList;
    }

    public void setTrimesteanneeList(List<Trimesteannee> trimesteanneeList) {
        this.trimesteanneeList = trimesteanneeList;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    @XmlTransient
    public List<Personnelanneecatfonct> getPersonnelanneecatfonctList() {
        return personnelanneecatfonctList;
    }

    public void setPersonnelanneecatfonctList(List<Personnelanneecatfonct> personnelanneecatfonctList) {
        this.personnelanneecatfonctList = personnelanneecatfonctList;
    }

    @XmlTransient
    public List<Remuneration> getRemunerationList() {
        return remunerationList;
    }

    public void setRemunerationList(List<Remuneration> remunerationList) {
        this.remunerationList = remunerationList;
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
    public List<Depense> getDepenseList() {
        return depenseList;
    }

    public void setDepenseList(List<Depense> depenseList) {
        this.depenseList = depenseList;
    }

    @XmlTransient
    public List<Tranche> getTrancheList() {
        return trancheList;
    }

    public void setTrancheList(List<Tranche> trancheList) {
        this.trancheList = trancheList;
    }

    @XmlTransient
    public List<Recette> getRecetteList() {
        return recetteList;
    }

    public void setRecetteList(List<Recette> recetteList) {
        this.recetteList = recetteList;
    }

    @XmlTransient
    public List<Eleveanneeclasse> getEleveanneeclasseList() {
        return eleveanneeclasseList;
    }

    public void setEleveanneeclasseList(List<Eleveanneeclasse> eleveanneeclasseList) {
        this.eleveanneeclasseList = eleveanneeclasseList;
    }

    @XmlTransient
    public List<Anneedepense> getAnneedepenseList() {
        return anneedepenseList;
    }

    public void setAnneedepenseList(List<Anneedepense> anneedepenseList) {
        this.anneedepenseList = anneedepenseList;
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
        hash += (idannee != null ? idannee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Annee)) {
            return false;
        }
        Annee other = (Annee) object;
        if ((this.idannee == null && other.idannee != null) || (this.idannee != null && !this.idannee.equals(other.idannee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Annee[ idannee=" + idannee + " ]";
    }
    
}
