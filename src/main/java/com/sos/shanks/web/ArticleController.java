/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.shanks.web;

import com.sos.exception.CatchException;
import com.sos.shanks.domain.Article;
import com.sos.shanks.service.ArticleFacade;
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
public class ArticleController extends Controller implements Serializable {

    // ======================================
    // = Attributes =
    // ======================================
    @Inject
    private ArticleFacade articleService;

    private Article current;
    private Article newArticle;
    private List<Article> listArticle;

    /**
     * Creates a new instance of ArticleController
     */
    public ArticleController() {
    }
    // ======================================
    // = Navigation Methods =
    // ======================================
    public String showList(){
        return "/catalogue/article/list?faces-redirect=true";
    }
    public String showCreate(){
        newArticle = new Article();
        return "/catalogue/article/add?faces-redirect=true";
    }
    // ======================================
    // = Public Methods =
    // ======================================

    public List<Article> getAll() {
        return articleService.findAll();
    }
    
    public String doCreate(){
        articleService.create(newArticle);
        return showList();
    }

    // ======================================
    // = Getters & setters =
    // ======================================
    public Article getCurrent() {
        return current;
    }

    public void setCurrent(Article current) {
        this.current = current;
    }

    public Article getNewArticle() {
        return newArticle;
    }

    public void setNewArticle(Article newArticle) {
        this.newArticle = newArticle;
    }
    
    // ======================================
    // = Converter =
    // ======================================
    public Article getArticle(java.lang.Integer id) {
        return articleService.find(id);
    }

    @FacesConverter(forClass = Article.class)
    public static class ArticleControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ArticleController controller = (ArticleController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "articleController");
            return controller.getArticle(getKey(value));
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
            if (object instanceof Article) {
                Article o = (Article) object;
                return getStringKey(o.getArticleId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Article.class.getName());
            }
        }

    }
}
