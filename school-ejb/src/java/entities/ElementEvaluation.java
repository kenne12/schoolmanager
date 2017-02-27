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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kenne
 */
@Entity
@Table(name = "element_evaluation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ElementEvaluation.findAll", query = "SELECT e FROM ElementEvaluation e"),
    @NamedQuery(name = "ElementEvaluation.findByIdelement", query = "SELECT e FROM ElementEvaluation e WHERE e.idelement = :idelement"),
    @NamedQuery(name = "ElementEvaluation.findByNom", query = "SELECT e FROM ElementEvaluation e WHERE e.nom = :nom"),
    @NamedQuery(name = "ElementEvaluation.findByCoeficient", query = "SELECT e FROM ElementEvaluation e WHERE e.coeficient = :coeficient"),
    @NamedQuery(name = "ElementEvaluation.findByTrimestriel", query = "SELECT e FROM ElementEvaluation e WHERE e.trimestriel = :trimestriel")})
public class ElementEvaluation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idelement")
    private Integer idelement;
    @Size(max = 254)
    @Column(name = "nom")
    private String nom;
    @Column(name = "coeficient")
    private Integer coeficient;
    @Column(name = "trimestriel")
    private Boolean trimestriel;
    @OneToMany(mappedBy = "elementevaluation", fetch = FetchType.LAZY)
    private List<ClasseElementevaluation> classeElementevaluationList;
    @JoinColumn(name = "matiere", referencedColumnName = "idmatiere")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Matiere matiere;

    public ElementEvaluation() {
    }

    public ElementEvaluation(Integer idelement) {
        this.idelement = idelement;
    }

    public Integer getIdelement() {
        return idelement;
    }

    public void setIdelement(Integer idelement) {
        this.idelement = idelement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getCoeficient() {
        return coeficient;
    }

    public void setCoeficient(Integer coeficient) {
        this.coeficient = coeficient;
    }

    public Boolean getTrimestriel() {
        return trimestriel;
    }

    public void setTrimestriel(Boolean trimestriel) {
        this.trimestriel = trimestriel;
    }

    @XmlTransient
    public List<ClasseElementevaluation> getClasseElementevaluationList() {
        return classeElementevaluationList;
    }

    public void setClasseElementevaluationList(List<ClasseElementevaluation> classeElementevaluationList) {
        this.classeElementevaluationList = classeElementevaluationList;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idelement != null ? idelement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElementEvaluation)) {
            return false;
        }
        ElementEvaluation other = (ElementEvaluation) object;
        if ((this.idelement == null && other.idelement != null) || (this.idelement != null && !this.idelement.equals(other.idelement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ElementEvaluation[ idelement=" + idelement + " ]";
    }
    
}
