/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sos.shanks.service;

import com.sos.shanks.domain.LigneCommande;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mab.salhi
 */
@Stateless
public class LigneCommandeFacade extends AbstractFacade<LigneCommande> {
    @PersistenceContext(unitName = "shanksPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneCommandeFacade() {
        super(LigneCommande.class);
    }
    
}
