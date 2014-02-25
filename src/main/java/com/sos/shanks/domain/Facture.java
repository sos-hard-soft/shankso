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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "t_facture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facture.findAll", query = "SELECT f FROM Facture f"),
    @NamedQuery(name = "Facture.findByFactureId", query = "SELECT f FROM Facture f WHERE f.factureId = :factureId"),
    @NamedQuery(name = "Facture.findByNumeroFacture", query = "SELECT f FROM Facture f WHERE f.numeroFacture = :numeroFacture"),
    @NamedQuery(name = "Facture.findByDateFacture", query = "SELECT f FROM Facture f WHERE f.dateFacture = :dateFacture"),
    @NamedQuery(name = "Facture.findByStatus", query = "SELECT f FROM Facture f WHERE f.status = :status")})
public class Facture implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "facture_id")
    private Integer factureId;
    @Size(max = 250)
    @Column(name = "numero_facture")
    private String numeroFacture;
    @Column(name = "date_facture")
    @Temporal(TemporalType.DATE)
    private Date dateFacture;
    @Size(max = 150)
    @Column(name = "status")
    private String status;
    @JoinTable(name = "t_facture_livraison", joinColumns = {
        @JoinColumn(name = "facture", referencedColumnName = "facture_id")}, inverseJoinColumns = {
        @JoinColumn(name = "livraison", referencedColumnName = "livraison_id")})
    @ManyToMany
    private List<Livraison> livraisonList;
    @JoinColumn(name = "fournisseur", referencedColumnName = "fournisseur_id")
    @ManyToOne
    private Fournisseur fournisseur;

    public Facture() {
    }

    public Facture(Integer factureId) {
        this.factureId = factureId;
    }

    public Integer getFactureId() {
        return factureId;
    }

    public void setFactureId(Integer factureId) {
        this.factureId = factureId;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public Date getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(Date dateFacture) {
        this.dateFacture = dateFacture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public List<Livraison> getLivraisonList() {
        return livraisonList;
    }

    public void setLivraisonList(List<Livraison> livraisonList) {
        this.livraisonList = livraisonList;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (factureId != null ? factureId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facture)) {
            return false;
        }
        Facture other = (Facture) object;
        if ((this.factureId == null && other.factureId != null) || (this.factureId != null && !this.factureId.equals(other.factureId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sos.shanks.domain.Facture[ factureId=" + factureId + " ]";
    }
    
}
