/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sos.shanks.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mab.salhi
 */
@Entity
@Table(name = "t_article")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
    @NamedQuery(name = "Article.findByArticleId", query = "SELECT a FROM Article a WHERE a.articleId = :articleId"),
    @NamedQuery(name = "Article.findByDesignation", query = "SELECT a FROM Article a WHERE a.designation = :designation"),
    @NamedQuery(name = "Article.findByDescription", query = "SELECT a FROM Article a WHERE a.description = :description"),
    @NamedQuery(name = "Article.findByPrixUnitaire", query = "SELECT a FROM Article a WHERE a.prixUnitaire = :prixUnitaire"),
    @NamedQuery(name = "Article.findByUniteMesure", query = "SELECT a FROM Article a WHERE a.uniteMesure = :uniteMesure")})
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "article_id")
    private Integer articleId;
    @Size(max = 150)
    @Column(name = "designation")
    private String designation;
    @Size(max = 350)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prix_unitaire")
    private Double prixUnitaire;
    @Size(max = 50)
    @Column(name = "unite_mesure")
    private String uniteMesure;
    @OneToMany(mappedBy = "article")
    private List<LigneCommande> ligneCommandeList;
    @JoinColumn(name = "categorie", referencedColumnName = "categorie_id")
    @ManyToOne
    private Categorie categorie;
    @OneToMany(mappedBy = "article")
    private List<LigneLivraison> ligneLivraisonList;

    public Article() {
    }

    public Article(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getUniteMesure() {
        return uniteMesure;
    }

    public void setUniteMesure(String uniteMesure) {
        this.uniteMesure = uniteMesure;
    }

    @XmlTransient
    public List<LigneCommande> getLigneCommandeList() {
        return ligneCommandeList;
    }

    public void setLigneCommandeList(List<LigneCommande> ligneCommandeList) {
        this.ligneCommandeList = ligneCommandeList;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @XmlTransient
    public List<LigneLivraison> getLigneLivraisonList() {
        return ligneLivraisonList;
    }

    public void setLigneLivraisonList(List<LigneLivraison> ligneLivraisonList) {
        this.ligneLivraisonList = ligneLivraisonList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articleId != null ? articleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.articleId == null && other.articleId != null) || (this.articleId != null && !this.articleId.equals(other.articleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sos.shanks.domain.Article[ articleId=" + articleId + " ]";
    }
    
}
