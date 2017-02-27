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
@Table(name = "livre")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Livre.findAll", query = "SELECT l FROM Livre l"),
    @NamedQuery(name = "Livre.findByCodeisbn", query = "SELECT l FROM Livre l WHERE l.codeisbn = :codeisbn"),
    @NamedQuery(name = "Livre.findByTitre", query = "SELECT l FROM Livre l WHERE l.titre = :titre"),
    @NamedQuery(name = "Livre.findByDatepublication", query = "SELECT l FROM Livre l WHERE l.datepublication = :datepublication"),
    @NamedQuery(name = "Livre.findByEmpruntable", query = "SELECT l FROM Livre l WHERE l.empruntable = :empruntable"),
    @NamedQuery(name = "Livre.findByIdlivre", query = "SELECT l FROM Livre l WHERE l.idlivre = :idlivre")})
public class Livre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "codeisbn")
    private String codeisbn;
    @Size(max = 254)
    @Column(name = "titre")
    private String titre;
    @Column(name = "datepublication")
    @Temporal(TemporalType.DATE)
    private Date datepublication;
    @Column(name = "empruntable")
    private Boolean empruntable;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idlivre")
    private Long idlivre;
    @JoinColumn(name = "idediteur", referencedColumnName = "idediteur")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Editeur idediteur;
    @JoinColumn(name = "etablissement", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Etablissement etablissement;
    @OneToMany(mappedBy = "livre", fetch = FetchType.LAZY)
    private List<Auteurlivre> auteurlivreList;
    @OneToMany(mappedBy = "livre", fetch = FetchType.LAZY)
    private List<Elevelivreemprunte> elevelivreemprunteList;

    public Livre() {
    }

    public Livre(Long idlivre) {
        this.idlivre = idlivre;
    }

    public Livre(Long idlivre, String codeisbn) {
        this.idlivre = idlivre;
        this.codeisbn = codeisbn;
    }

    public String getCodeisbn() {
        return codeisbn;
    }

    public void setCodeisbn(String codeisbn) {
        this.codeisbn = codeisbn;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Date getDatepublication() {
        return datepublication;
    }

    public void setDatepublication(Date datepublication) {
        this.datepublication = datepublication;
    }

    public Boolean getEmpruntable() {
        return empruntable;
    }

    public void setEmpruntable(Boolean empruntable) {
        this.empruntable = empruntable;
    }

    public Long getIdlivre() {
        return idlivre;
    }

    public void setIdlivre(Long idlivre) {
        this.idlivre = idlivre;
    }

    public Editeur getIdediteur() {
        return idediteur;
    }

    public void setIdediteur(Editeur idediteur) {
        this.idediteur = idediteur;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    @XmlTransient
    public List<Auteurlivre> getAuteurlivreList() {
        return auteurlivreList;
    }

    public void setAuteurlivreList(List<Auteurlivre> auteurlivreList) {
        this.auteurlivreList = auteurlivreList;
    }

    @XmlTransient
    public List<Elevelivreemprunte> getElevelivreemprunteList() {
        return elevelivreemprunteList;
    }

    public void setElevelivreemprunteList(List<Elevelivreemprunte> elevelivreemprunteList) {
        this.elevelivreemprunteList = elevelivreemprunteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idlivre != null ? idlivre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livre)) {
            return false;
        }
        Livre other = (Livre) object;
        if ((this.idlivre == null && other.idlivre != null) || (this.idlivre != null && !this.idlivre.equals(other.idlivre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Livre[ idlivre=" + idlivre + " ]";
    }
    
}
