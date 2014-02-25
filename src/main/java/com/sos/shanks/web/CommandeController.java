/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.shanks.web;

import com.sos.exception.CatchException;
import com.sos.shanks.domain.Commande;
import com.sos.shanks.domain.LigneCommande;
import com.sos.shanks.service.CommandeFacade;
import com.sos.shanks.service.LigneCommandeFacade;
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
public class CommandeController extends Controller implements Serializable {

    // ======================================
    // = Attributes =
    // ======================================
    @Inject
    private CommandeFacade commandeService;

    private Commande current;
    private Commande editedCommande;
    private Commande newCommande;
    private List<Commande> listCommande;

    @Inject
    private LigneCommandeFacade lcService;
    
    private LigneCommande newLC;
    private LigneCommande currentLC;
    private List<LigneCommande> lignesCommande;
    
    /**
     * Creates a new instance of CommandeController
     */
    public CommandeController() {
    }
    // ======================================
    // = Navigation Methods =
    // ======================================
    public String showList(){
        return "/commande/list?faces-redirect=true";
    }
    public String showCreate(){
        newCommande = new Commande();
        return "/commande/add?faces-redirect=true";
    }
    public String showEdit(Commande commande){
        commandeService.clearCache();
        current = commande;
        return "/commande/edit?faces-redirect=true";
    }
    public String showAddLigne(){
        newLC = new LigneCommande();
        return "/commande/addLigneCommande?faces-redirect=true";
    }
    // ======================================
    // = Public Methods =
    // ======================================

    public List<Commande> getAll() {
        return commandeService.findAll();
    }
    
    public String doCreate(){
        commandeService.create(newCommande);
        return showList();
    }
    
    public String doAddLC(){
        newLC.setCommande(current);
        lcService.create(newLC);
        commandeService.clearCache();
        editedCommande = commandeService.find(current.getCommandeId());
        return showEdit(editedCommande);
    }

    // ======================================
    // = Getters & setters =
    // ======================================
    public Commande getCurrent() {
        return current;
    }

    public void setCurrent(Commande current) {
        this.current = current;
    }

    public Commande getNewCommande() {
        return newCommande;
    }

    public void setNewCommande(Commande newCommande) {
        this.newCommande = newCommande;
    }

    public LigneCommande getNewLC() {
        return newLC;
    }

    public void setNewLC(LigneCommande newLC) {
        this.newLC = newLC;
    }

    public LigneCommande getCurrentLC() {
        return currentLC;
    }

    public void setCurrentLC(LigneCommande currentLC) {
        this.currentLC = currentLC;
    }
    
    // ======================================
    // = Converter =
    // ======================================
    public Commande getCommande(java.lang.Integer id) {
        return commandeService.find(id);
    }

    @FacesConverter(forClass = Commande.class)
    public static class CommandeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CommandeController controller = (CommandeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "commandeController");
            return controller.getCommande(getKey(value));
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
            if (object instanceof Commande) {
                Commande o = (Commande) object;
                return getStringKey(o.getCommandeId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Commande.class.getName());
            }
        }

    }
}
