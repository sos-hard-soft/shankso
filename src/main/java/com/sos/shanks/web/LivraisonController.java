/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.shanks.web;

import com.sos.exception.CatchException;
import com.sos.shanks.domain.Commande;
import com.sos.shanks.domain.LigneCommande;
import com.sos.shanks.domain.LigneLivraison;
import com.sos.shanks.domain.Livraison;
import com.sos.shanks.service.LigneLivraisonFacade;
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
public class LivraisonController extends Controller implements Serializable {

    // ======================================
    // = Attributes =
    // ======================================
    @Inject
    private LivraisonFacade livraisonService;

    private Livraison current;
    private Livraison editedLivraison;
    private Livraison newLivraison;
    private List<Livraison> listLivraison;

    @Inject
    private LigneLivraisonFacade llService;
    
    private LigneLivraison newLL;
    private LigneLivraison currentLL;
    private List<LigneLivraison> LignesLivraison;
    /**
     * Creates a new instance of LivraisonController
     */
    public LivraisonController() {
    }
    // ======================================
    // = Navigation Methods =
    // ======================================
    public String showList(){
        return "/livraison/list?faces-redirect=true";
    }
    public String showCreate(){
        newLivraison = new Livraison();
        return "/livraison/add?faces-redirect=true";
    }
    public String showEdit(Livraison livraison){
        livraisonService.clearCache();
        current = livraison;
        return "/livraison/edit?faces-redirect=true";
    }
    public String showAddLigne(){
        newLL = new LigneLivraison();
        return "/livraison/addLigneLivraison?faces-redirect=true";
    }
    // ======================================
    // = Public Methods =
    // ======================================

    public List<Livraison> getAll() {
        return livraisonService.findAll();
    }
    
    public String doCreate(){
        livraisonService.create(newLivraison);
        return showList();
    }
public String doAddLL(){
        newLL.setLivraison(current);
        llService.create(newLL);
        livraisonService.clearCache();
        editedLivraison = livraisonService.find(current.getLivraisonId());
        return showEdit(editedLivraison);
    }
    // ======================================
    // = Getters & setters =
    // ======================================
    public Livraison getCurrent() {
        return current;
    }

    public void setCurrent(Livraison current) {
        this.current = current;
    }

    public Livraison getNewLivraison() {
        return newLivraison;
    }

    public void setNewLivraison(Livraison newLivraison) {
        this.newLivraison = newLivraison;
    }

    public LigneLivraison getNewLL() {
        return newLL;
    }

    public void setNewLL(LigneLivraison newLL) {
        this.newLL = newLL;
    }

    public LigneLivraison getCurrentLL() {
        return currentLL;
    }

    public void setCurrentLL(LigneLivraison currentLL) {
        this.currentLL = currentLL;
    }
    
    // ======================================
    // = Converter =
    // ======================================
    public Livraison getLivraison(java.lang.Integer id) {
        return livraisonService.find(id);
    }

    @FacesConverter(forClass = Livraison.class)
    public static class LivraisonControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LivraisonController controller = (LivraisonController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "livraisonController");
            return controller.getLivraison(getKey(value));
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
            if (object instanceof Livraison) {
                Livraison o = (Livraison) object;
                return getStringKey(o.getLivraisonId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Livraison.class.getName());
            }
        }

    }
}
