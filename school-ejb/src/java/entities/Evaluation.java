/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "evaluation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evaluation.findAll", query = "SELECT e FROM Evaluation e"),
    @NamedQuery(name = "Evaluation.findByIdeval", query = "SELECT e FROM Evaluation e WHERE e.ideval = :ideval"),
    @NamedQuery(name = "Evaluation.findByObservation", query = "SELECT e FROM Evaluation e WHERE e.observation = :observation"),
    @NamedQuery(name = "Evaluation.findByNote", query = "SELECT e FROM Evaluation e WHERE e.note = :note"),
    @NamedQuery(name = "Evaluation.findByValide", query = "SELECT e FROM Evaluation e WHERE e.valide = :valide")})
public class Evaluation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ideval")
    private Long ideval;
    @Size(max = 254)
    @Column(name = "observation")
    private String observation;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "note")
    private Double note;
    @Column(name = "valide")
    private Boolean valide;
    @JoinColumn(name = "eleve", referencedColumnName = "ideleve")
    @ManyToOne(fetch = FetchType.LAZY)
    private Eleve eleve;
    @JoinColumn(name = "personnel", referencedColumnName = "idpersonnel")
    @ManyToOne(fetch = FetchType.LAZY)
    private Personnel personnel;
    @JoinColumn(name = "planning_evaluation", referencedColumnName = "idplanning")
    @ManyToOne(fetch = FetchType.LAZY)
    private PlanningEvaluation planningEvaluation;

    public Evaluation() {
    }

    public Evaluation(Long ideval) {
        this.ideval = ideval;
    }

    public Long getIdeval() {
        return ideval;
    }

    public void setIdeval(Long ideval) {
        this.ideval = ideval;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Double getNote() {
        return note;
    }

    public void setNote(Double note) {
        this.note = note;
    }

    public Boolean getValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public Personnel getPersonnel() {
        return personnel;
    }

    public void setPersonnel(Personnel personnel) {
        this.personnel = personnel;
    }

    public PlanningEvaluation getPlanningEvaluation() {
        return planningEvaluation;
    }

    public void setPlanningEvaluation(PlanningEvaluation planningEvaluation) {
        this.planningEvaluation = planningEvaluation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ideval != null ? ideval.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evaluation)) {
            return false;
        }
        Evaluation other = (Evaluation) object;
        if ((this.ideval == null && other.ideval != null) || (this.ideval != null && !this.ideval.equals(other.ideval))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Evaluation[ ideval=" + ideval + " ]";
    }
    
}
