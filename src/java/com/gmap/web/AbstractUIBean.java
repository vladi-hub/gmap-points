/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmap.web;

import com.gmap.persistent.User;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Vladislav Vladimirov
 */
public class AbstractUIBean {
    
    private static final String user_key="USER_SESSION";
    
    protected static void invalidateInput(UIInput uii, String message) {

        uii.setValid(false);

        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, message, null);

        throw new ValidatorException(facesMessage);

    }

    public FacesContext getFacesContext(){
        return FacesContext.getCurrentInstance();
    }
    
    protected void setInfoMessage(String summary) {

        getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null));

    }



    protected void setWarnMessage(UIComponent component, String summary) {

        getFacesContext().addMessage(component.getClientId(getFacesContext()), new FacesMessage(FacesMessage.SEVERITY_WARN, summary, null));

    }
    
    protected String getMessage(String key,Locale loc){
        ResourceBundle res = ResourceBundle.getBundle(
            "Bundle",loc);
        return res.getString(key);
    }
    
    protected User getSessionUser(){
        FacesContext context = FacesContext.getCurrentInstance();  
        return (User)context.getExternalContext().getSessionMap().get(user_key);
    }
    
    protected void storeUserInSession(User u){
        FacesContext context = FacesContext.getCurrentInstance();  
        context.getExternalContext().getSessionMap().put(user_key, u);
    }
    
    protected void invalidateUser(){
       FacesContext context = FacesContext.getCurrentInstance();  
       context.getExternalContext().getSessionMap().remove(user_key); 
       context.getExternalContext().getSessionMap().clear();
    }
}
