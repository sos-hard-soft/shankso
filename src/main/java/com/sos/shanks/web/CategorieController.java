/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.shanks.web;

import com.sos.exception.CatchException;
import com.sos.shanks.domain.Categorie;
import com.sos.shanks.service.CategorieFacade;
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
public class CategorieController extends Controller implements Serializable {

    // ======================================
    // = Attributes =
    // ======================================
    @Inject
    private CategorieFacade categorieService;

    private Categorie current;
    private Categorie newCategorie;
    private List<Categorie> listCategorie;

    /**
     * Creates a new instance of CategorieController
     */
    public CategorieController() {
    }
    // ======================================
    // = Navigation Methods =
    // ======================================
    public String showList(){
        return "/catalogue/categorie/list?faces-redirect=true";
    }
    public String showCreate(){
        newCategorie = new Categorie();
        return "/catalogue/categorie/add?faces-redirect=true";
    }
    // ======================================
    // = Public Methods =
    // ======================================

    public List<Categorie> getAll() {
        return categorieService.findAll();
    }
    
    public String doCreate(){
        categorieService.create(newCategorie);
        return showList();
    }

    // ======================================
    // = Getters & setters =
    // ======================================
    public Categorie getCurrent() {
        return current;
    }

    public void setCurrent(Categorie current) {
        this.current = current;
    }

    public Categorie getNewCategorie() {
        return newCategorie;
    }

    public void setNewCategorie(Categorie newCategorie) {
        this.newCategorie = newCategorie;
    }
    
    // ======================================
    // = Converter =
    // ======================================
    public Categorie getCategorie(java.lang.Integer id) {
        return categorieService.find(id);
    }

    @FacesConverter(forClass = Categorie.class)
    public static class CategorieControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CategorieController controller = (CategorieController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "categorieController");
            return controller.getCategorie(getKey(value));
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
            if (object instanceof Categorie) {
                Categorie o = (Categorie) object;
                return getStringKey(o.getCategorieId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Categorie.class.getName());
            }
        }

    }
}
