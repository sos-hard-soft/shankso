/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.shanks.web;

import com.sos.exception.CatchException;
import com.sos.shanks.domain.Fournisseur;
import com.sos.shanks.service.FournisseurFacade;
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
public class FournisseurController extends Controller implements Serializable {

    // ======================================
    // = Attributes =
    // ======================================
    @Inject
    private FournisseurFacade fournisseurService;

    private Fournisseur current;
    private Fournisseur newFournisseur;
    private List<Fournisseur> listFournisseur;

    /**
     * Creates a new instance of FournisseurController
     */
    public FournisseurController() {
    }
    // ======================================
    // = Navigation Methods =
    // ======================================
    public String showList(){
        return "/fournisseur/list?faces-redirect=true";
    }
    public String showCreate(){
        newFournisseur = new Fournisseur();
        return "/fournisseur/add?faces-redirect=true";
    }
    // ======================================
    // = Public Methods =
    // ======================================

    public List<Fournisseur> getAll() {
        return fournisseurService.findAll();
    }
    
    public String doCreate(){
        fournisseurService.create(newFournisseur);
        return showList();
    }

    // ======================================
    // = Getters & setters =
    // ======================================
    public Fournisseur getCurrent() {
        return current;
    }

    public void setCurrent(Fournisseur current) {
        this.current = current;
    }

    public Fournisseur getNewFournisseur() {
        return newFournisseur;
    }

    public void setNewFournisseur(Fournisseur newFournisseur) {
        this.newFournisseur = newFournisseur;
    }
    
    // ======================================
    // = Converter =
    // ======================================
    public Fournisseur getFournisseur(java.lang.Integer id) {
        return fournisseurService.find(id);
    }

    @FacesConverter(forClass = Fournisseur.class)
    public static class FournisseurControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FournisseurController controller = (FournisseurController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fournisseurController");
            return controller.getFournisseur(getKey(value));
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
            if (object instanceof Fournisseur) {
                Fournisseur o = (Fournisseur) object;
                return getStringKey(o.getFournisseurId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Fournisseur.class.getName());
            }
        }

    }
}
