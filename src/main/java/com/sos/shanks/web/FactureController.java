/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.shanks.web;

import com.sos.exception.CatchException;
import com.sos.shanks.domain.Facture;
import com.sos.shanks.domain.Livraison;
import com.sos.shanks.service.FactureFacade;
import com.sos.shanks.service.LivraisonFacade;
import com.sos.util.Loggable;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author mab.salhi
 */
@Named
@SessionScoped
@Loggable
@CatchException
public class FactureController extends Controller implements Serializable {

    // ======================================
    // = Attributes =
    // ======================================
    @Inject
    private FactureFacade factureService;

    private Facture current;
    private Facture newFacture;
    private Facture editedFacture;
    private List<Facture> listFacture;
    
    @Inject
    private LivraisonFacade livraisonService;

    /**
     * Creates a new instance of FactureController
     */
    public FactureController() {
    }
    // ======================================
    // = Navigation Methods =
    // ======================================
    public String showList(){
        return "/facture/list?faces-redirect=true";
    }
    public String showCreate(){
        newFacture = new Facture();
        return "/facture/add?faces-redirect=true";
    }
    public String showSelectLivraison(){
        return "/facture/selectLivraison?faces-redirect=true";
    }
    public String showEdit(Facture facture){
        current = facture;
        return "/facture/edit?faces-redirect=true";
    }
    // ======================================
    // = Public Methods =
    // ======================================

    public List<Facture> getAll() {
        return factureService.findAll();
    }
    
    public String doCreate(){
        factureService.create(newFacture);
        return showEdit(newFacture);
    }
    
    public List<Livraison> getLivraisonByFournisseur(){
        return livraisonService.findByFournisseur(current.getFournisseur());
    }
    
    public String addLivraisonToFacture(Livraison livraison){
        current.getLivraisonList().add(livraison);
        editedFacture = factureService.find(current.getFactureId());
        return showEdit(editedFacture);
    }
    // ======================================
    // = Getters & setters =
    // ======================================
    public Facture getCurrent() {
        return current;
    }

    public void setCurrent(Facture current) {
        this.current = current;
    }

    public Facture getNewFacture() {
        return newFacture;
    }

    public void setNewFacture(Facture newFacture) {
        this.newFacture = newFacture;
    }
    
    // ======================================
    // = Converter =
    // ======================================
    public Facture getFacture(java.lang.Integer id) {
        return factureService.find(id);
    }

    @FacesConverter(forClass = Facture.class)
    public static class FactureControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FactureController controller = (FactureController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "factureController");
            return controller.getFacture(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Facture) {
                Facture o = (Facture) object;
                return getStringKey(o.getFactureId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Facture.class.getName());
            }
        }

    }
}
