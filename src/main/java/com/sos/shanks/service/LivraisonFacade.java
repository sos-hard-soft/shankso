/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sos.shanks.service;

import com.sos.shanks.domain.Fournisseur;
import com.sos.shanks.domain.Livraison;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mab.salhi
 */
@Stateless
public class LivraisonFacade extends AbstractFacade<Livraison> {
    @PersistenceContext(unitName = "shanksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LivraisonFacade() {
        super(Livraison.class);
    }
    
    public List<Livraison> findByFournisseur(Fournisseur fournisseur){
        Query query = em.createNamedQuery("Livraison.findByFournisseur").setParameter("fournisseur", fournisseur);
        return query.getResultList() ;
    }
}
