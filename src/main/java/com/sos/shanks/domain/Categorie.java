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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "t_categorie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categorie.findAll", query = "SELECT c FROM Categorie c"),
    @NamedQuery(name = "Categorie.findByCategorieId", query = "SELECT c FROM Categorie c WHERE c.categorieId = :categorieId"),
    @NamedQuery(name = "Categorie.findByDesignation", query = "SELECT c FROM Categorie c WHERE c.designation = :designation"),
    @NamedQuery(name = "Categorie.findByDescription", query = "SELECT c FROM Categorie c WHERE c.description = :description")})
public class Categorie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "categorie_id")
    private Integer categorieId;
    @Size(max = 150)
    @Column(name = "designation")
    private String designation;
    @Size(max = 350)
    @Column(name = "description")
    private String description;
    @JoinTable(name = "t_fournisseur_categorie", joinColumns = {
        @JoinColumn(name = "categorie", referencedColumnName = "categorie_id")}, inverseJoinColumns = {
        @JoinColumn(name = "fournisseur", referencedColumnName = "fournisseur_id")})
    @ManyToMany
    private List<Fournisseur> fournisseurList;
    @OneToMany(mappedBy = "categorie")
    private List<Article> articleList;

    public Categorie() {
    }

    public Categorie(Integer categorieId) {
        this.categorieId = categorieId;
    }

    public Integer getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Integer categorieId) {
        this.categorieId = categorieId;
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

    @XmlTransient
    public List<Fournisseur> getFournisseurList() {
        return fournisseurList;
    }

    public void setFournisseurList(List<Fournisseur> fournisseurList) {
        this.fournisseurList = fournisseurList;
    }

    @XmlTransient
    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categorieId != null ? categorieId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Categorie)) {
            return false;
        }
        Categorie other = (Categorie) object;
        if ((this.categorieId == null && other.categorieId != null) || (this.categorieId != null && !this.categorieId.equals(other.categorieId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sos.shanks.domain.Categorie[ categorieId=" + categorieId + " ]";
    }
    
}
