/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sos.shanks.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mab.salhi
 */
@Entity
@Table(name = "t_livraison")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Livraison.findAll", query = "SELECT l FROM Livraison l"),
    @NamedQuery(name = "Livraison.findByLivraisonId", query = "SELECT l FROM Livraison l WHERE l.livraisonId = :livraisonId"),
    @NamedQuery(name = "Livraison.findByNumeroLivraison", query = "SELECT l FROM Livraison l WHERE l.numeroLivraison = :numeroLivraison"),
    @NamedQuery(name = "Livraison.findByDateLivraison", query = "SELECT l FROM Livraison l WHERE l.dateLivraison = :dateLivraison"),
    @NamedQuery(name = "Livraison.findByDetails", query = "SELECT l FROM Livraison l WHERE l.details = :details"),
    @NamedQuery(name = "Livraison.findByFournisseur", query = "SELECT l FROM Livraison l WHERE l.fournisseur = :fournisseur")})
public class Livraison implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "livraison_id")
    private Integer livraisonId;
    @Size(max = 250)
    @Column(name = "numero_livraison")
    private String numeroLivraison;
    @Column(name = "date_livraison")
    @Temporal(TemporalType.DATE)
    private Date dateLivraison;
    @Size(max = 350)
    @Column(name = "details")
    private String details;
    @ManyToMany(mappedBy = "livraisonList")
    private List<Commande> commandeList;
    @ManyToMany(mappedBy = "livraisonList")
    private List<Facture> factureList;
    @JoinColumn(name = "fournisseur", referencedColumnName = "fournisseur_id")
    @ManyToOne
    private Fournisseur fournisseur;
    @OneToMany(mappedBy = "livraison")
    private List<LigneLivraison> ligneLivraisonList;

    public Livraison() {
    }

    public Livraison(Integer livraisonId) {
        this.livraisonId = livraisonId;
    }

    public Integer getLivraisonId() {
        return livraisonId;
    }

    public void setLivraisonId(Integer livraisonId) {
        this.livraisonId = livraisonId;
    }

    public String getNumeroLivraison() {
        return numeroLivraison;
    }

    public void setNumeroLivraison(String numeroLivraison) {
        this.numeroLivraison = numeroLivraison;
    }

    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @XmlTransient
    public List<Commande> getCommandeList() {
        return commandeList;
    }

    public void setCommandeList(List<Commande> commandeList) {
        this.commandeList = commandeList;
    }

    @XmlTransient
    public List<Facture> getFactureList() {
        return factureList;
    }

    public void setFactureList(List<Facture> factureList) {
        this.factureList = factureList;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
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
        hash += (livraisonId != null ? livraisonId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Livraison)) {
            return false;
        }
        Livraison other = (Livraison) object;
        if ((this.livraisonId == null && other.livraisonId != null) || (this.livraisonId != null && !this.livraisonId.equals(other.livraisonId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sos.shanks.domain.Livraison[ livraisonId=" + livraisonId + " ]";
    }
    
}
