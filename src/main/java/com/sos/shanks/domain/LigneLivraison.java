/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sos.shanks.domain;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mab.salhi
 */
@Entity
@Table(name = "t_ligne_livraison")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LigneLivraison.findAll", query = "SELECT l FROM LigneLivraison l"),
    @NamedQuery(name = "LigneLivraison.findById", query = "SELECT l FROM LigneLivraison l WHERE l.id = :id"),
    @NamedQuery(name = "LigneLivraison.findByQuantite", query = "SELECT l FROM LigneLivraison l WHERE l.quantite = :quantite")})
public class LigneLivraison implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "quantite")
    private Integer quantite;
    @JoinColumn(name = "livraison", referencedColumnName = "livraison_id")
    @ManyToOne
    private Livraison livraison;
    @JoinColumn(name = "article", referencedColumnName = "article_id")
    @ManyToOne
    private Article article;

    public LigneLivraison() {
    }

    public LigneLivraison(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Livraison getLivraison() {
        return livraison;
    }

    public void setLivraison(Livraison livraison) {
        this.livraison = livraison;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
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
        if (!(object instanceof LigneLivraison)) {
            return false;
        }
        LigneLivraison other = (LigneLivraison) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sos.shanks.domain.LigneLivraison[ id=" + id + " ]";
    }
    
}
