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
@Table(name = "classe_elementevaluation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClasseElementevaluation.findAll", query = "SELECT c FROM ClasseElementevaluation c"),
    @NamedQuery(name = "ClasseElementevaluation.findById", query = "SELECT c FROM ClasseElementevaluation c WHERE c.id = :id"),
    @NamedQuery(name = "ClasseElementevaluation.findByCoefficient", query = "SELECT c FROM ClasseElementevaluation c WHERE c.coefficient = :coefficient")})
public class ClasseElementevaluation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Column(name = "coefficient")
    private Integer coefficient;
    @OneToMany(mappedBy = "matiere", fetch = FetchType.LAZY)
    private List<Appreciationannuelle> appreciationannuelleList;
    @JoinColumn(name = "classe", referencedColumnName = "idclasse")
    @ManyToOne(fetch = FetchType.LAZY)
    private Classe classe;
    @JoinColumn(name = "elementevaluation", referencedColumnName = "idelement")
    @ManyToOne(fetch = FetchType.LAZY)
    private ElementEvaluation elementevaluation;
    @OneToMany(mappedBy = "elementEvaluation", fetch = FetchType.LAZY)
    private List<PlanningEvaluation> planningEvaluationList;
    @OneToMany(mappedBy = "matiere", fetch = FetchType.LAZY)
    private List<Appreciationtrimestrielle> appreciationtrimestrielleList;

    public ClasseElementevaluation() {
    }

    public ClasseElementevaluation(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Integer coefficient) {
        this.coefficient = coefficient;
    }

    @XmlTransient
    public List<Appreciationannuelle> getAppreciationannuelleList() {
        return appreciationannuelleList;
    }

    public void setAppreciationannuelleList(List<Appreciationannuelle> appreciationannuelleList) {
        this.appreciationannuelleList = appreciationannuelleList;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public ElementEvaluation getElementevaluation() {
        return elementevaluation;
    }

    public void setElementevaluation(ElementEvaluation elementevaluation) {
        this.elementevaluation = elementevaluation;
    }

    @XmlTransient
    public List<PlanningEvaluation> getPlanningEvaluationList() {
        return planningEvaluationList;
    }

    public void setPlanningEvaluationList(List<PlanningEvaluation> planningEvaluationList) {
        this.planningEvaluationList = planningEvaluationList;
    }

    @XmlTransient
    public List<Appreciationtrimestrielle> getAppreciationtrimestrielleList() {
        return appreciationtrimestrielleList;
    }

    public void setAppreciationtrimestrielleList(List<Appreciationtrimestrielle> appreciationtrimestrielleList) {
        this.appreciationtrimestrielleList = appreciationtrimestrielleList;
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
        if (!(object instanceof ClasseElementevaluation)) {
            return false;
        }
        ClasseElementevaluation other = (ClasseElementevaluation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ClasseElementevaluation[ id=" + id + " ]";
    }
    
}
