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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "planning_evaluation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanningEvaluation.findAll", query = "SELECT p FROM PlanningEvaluation p"),
    @NamedQuery(name = "PlanningEvaluation.findByIdplanning", query = "SELECT p FROM PlanningEvaluation p WHERE p.idplanning = :idplanning"),
    @NamedQuery(name = "PlanningEvaluation.findByDateEvaluation", query = "SELECT p FROM PlanningEvaluation p WHERE p.dateEvaluation = :dateEvaluation"),
    @NamedQuery(name = "PlanningEvaluation.findByHeureDebut", query = "SELECT p FROM PlanningEvaluation p WHERE p.heureDebut = :heureDebut"),
    @NamedQuery(name = "PlanningEvaluation.findByHeureFin", query = "SELECT p FROM PlanningEvaluation p WHERE p.heureFin = :heureFin")})
public class PlanningEvaluation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idplanning")
    private Long idplanning;
    @Column(name = "date_evaluation")
    @Temporal(TemporalType.DATE)
    private Date dateEvaluation;
    @Column(name = "heure_debut")
    @Temporal(TemporalType.TIME)
    private Date heureDebut;
    @Column(name = "heure_fin")
    @Temporal(TemporalType.TIME)
    private Date heureFin;
    @OneToMany(mappedBy = "planningEvaluation", fetch = FetchType.LAZY)
    private List<Evaluation> evaluationList;
    @JoinColumn(name = "element_evaluation", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ClasseElementevaluation elementEvaluation;
    @JoinColumn(name = "sequence", referencedColumnName = "idsequencean")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sequenceannee sequence;

    public PlanningEvaluation() {
    }

    public PlanningEvaluation(Long idplanning) {
        this.idplanning = idplanning;
    }

    public Long getIdplanning() {
        return idplanning;
    }

    public void setIdplanning(Long idplanning) {
        this.idplanning = idplanning;
    }

    public Date getDateEvaluation() {
        return dateEvaluation;
    }

    public void setDateEvaluation(Date dateEvaluation) {
        this.dateEvaluation = dateEvaluation;
    }

    public Date getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Date getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    @XmlTransient
    public List<Evaluation> getEvaluationList() {
        return evaluationList;
    }

    public void setEvaluationList(List<Evaluation> evaluationList) {
        this.evaluationList = evaluationList;
    }

    public ClasseElementevaluation getElementEvaluation() {
        return elementEvaluation;
    }

    public void setElementEvaluation(ClasseElementevaluation elementEvaluation) {
        this.elementEvaluation = elementEvaluation;
    }

    public Sequenceannee getSequence() {
        return sequence;
    }

    public void setSequence(Sequenceannee sequence) {
        this.sequence = sequence;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idplanning != null ? idplanning.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanningEvaluation)) {
            return false;
        }
        PlanningEvaluation other = (PlanningEvaluation) object;
        if ((this.idplanning == null && other.idplanning != null) || (this.idplanning != null && !this.idplanning.equals(other.idplanning))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PlanningEvaluation[ idplanning=" + idplanning + " ]";
    }
    
}
