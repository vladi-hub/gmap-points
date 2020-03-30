/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmap.web;

import com.gmap.persistent.GeoDAO;
import com.gmap.persistent.User;
import java.io.Serializable;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Vladislav Vladimirov
 */
@ManagedBean
@SessionScoped
public class SignUpBean extends AbstractUIBean implements Serializable{
    
    private String email;
    private String password;
    private String password2;

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the password2
     */
    public String getPassword2() {
        return password2;
    }

    /**
     * @param password2 the password2 to set
     */
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    public String signUp(){
        GeoDAO dao = new GeoDAO();
        if(!getPassword().equals(getPassword2())){
           //Put error Message 
           //getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Passwords must match!", null));  
           getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,getMessage("PASSES_MUST_MATCH_ERR",Locale.US),null));
           return "SIGNUP_FAIL"; 
        }
        User u = new User();
        u.setEmail(getEmail());
        u.setPassword(getPassword());
        Long uId = dao.addUser(u);
        if(uId < 0) {
            //put db error message
            return "SIGNUP_FAIL";
        }
        return "SIGNUP_OK";
    }
    
    public String login(){
        GeoDAO dao = new GeoDAO();
        
        User u = new User();
        u.setEmail(getEmail());
        u.setPassword(getPassword());
        User logUser = dao.findUserByCredentials(u);
        storeUserInSession(logUser);
        if(logUser == null) {
            getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,getMessage("PASSES_MUST_MATCH_ERR",Locale.US),null));
            return "LOGIN_FAIL";
        }
        return "LOGIN_OK";
    }
    
    public String logout(){
        invalidateUser();
        return "LOGOUT_OK";
    }
}
