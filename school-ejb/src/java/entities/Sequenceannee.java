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
@Table(name = "sequenceannee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sequenceannee.findAll", query = "SELECT s FROM Sequenceannee s"),
    @NamedQuery(name = "Sequenceannee.findByIdsequencean", query = "SELECT s FROM Sequenceannee s WHERE s.idsequencean = :idsequencean"),
    @NamedQuery(name = "Sequenceannee.findByEtat", query = "SELECT s FROM Sequenceannee s WHERE s.etat = :etat")})
public class Sequenceannee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsequencean")
    private Integer idsequencean;
    @Column(name = "etat")
    private Boolean etat;
    @JoinColumn(name = "idannee", referencedColumnName = "idannee")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Annee idannee;
    @JoinColumn(name = "idsequence", referencedColumnName = "idsequence")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sequence idsequence;
    @JoinColumn(name = "trimestre", referencedColumnName = "idtrimestrean")
    @ManyToOne(fetch = FetchType.LAZY)
    private Trimesteannee trimestre;
    @OneToMany(mappedBy = "sequence", fetch = FetchType.LAZY)
    private List<Absenceeleve> absenceeleveList;
    @OneToMany(mappedBy = "sequence", fetch = FetchType.LAZY)
    private List<PlanningEvaluation> planningEvaluationList;
    @OneToMany(mappedBy = "sequence", fetch = FetchType.LAZY)
    private List<Punition> punitionList;

    public Sequenceannee() {
    }

    public Sequenceannee(Integer idsequencean) {
        this.idsequencean = idsequencean;
    }

    public Integer getIdsequencean() {
        return idsequencean;
    }

    public void setIdsequencean(Integer idsequencean) {
        this.idsequencean = idsequencean;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public Annee getIdannee() {
        return idannee;
    }

    public void setIdannee(Annee idannee) {
        this.idannee = idannee;
    }

    public Sequence getIdsequence() {
        return idsequence;
    }

    public void setIdsequence(Sequence idsequence) {
        this.idsequence = idsequence;
    }

    public Trimesteannee getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Trimesteannee trimestre) {
        this.trimestre = trimestre;
    }

    @XmlTransient
    public List<Absenceeleve> getAbsenceeleveList() {
        return absenceeleveList;
    }

    public void setAbsenceeleveList(List<Absenceeleve> absenceeleveList) {
        this.absenceeleveList = absenceeleveList;
    }

    @XmlTransient
    public List<PlanningEvaluation> getPlanningEvaluationList() {
        return planningEvaluationList;
    }

    public void setPlanningEvaluationList(List<PlanningEvaluation> planningEvaluationList) {
        this.planningEvaluationList = planningEvaluationList;
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
        hash += (idsequencean != null ? idsequencean.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sequenceannee)) {
            return false;
        }
        Sequenceannee other = (Sequenceannee) object;
        if ((this.idsequencean == null && other.idsequencean != null) || (this.idsequencean != null && !this.idsequencean.equals(other.idsequencean))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Sequenceannee[ idsequencean=" + idsequencean + " ]";
    }
    
}
