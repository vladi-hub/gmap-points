/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmap.persistent;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Vladislav Vladimirov
 */
@Entity
@Table(name="user")
public class User implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;
    
    @Column(name="email", nullable=false)
    private String email;
    
    @Column(name="password", nullable=false)
    private String password;
    
    @OneToMany(fetch = FetchType.LAZY)
    //@JoinTable(name="shoplist", 
    //            joinColumns={@JoinColumn(name="userId")}, 
   //             inverseJoinColumns={@JoinColumn(name="id")})
    private List<Markers> sLists;
    
    @OneToMany(mappedBy="id", fetch = FetchType.LAZY)
    //@JoinTable(name="shoplist", 
    //            joinColumns={@JoinColumn(name="userId")}, 
   //             inverseJoinColumns={@JoinColumn(name="id")})
    private List<Rectangle> rLists;

    /**
     * @return the id
     */
    
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

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
     * @return the sLists
     */
    
    public List<Markers> getsLists() {
        return sLists;
    }

    /**
     * @param sLists the sLists to set
     */
    public void setsLists(List<Markers> sLists) {
        this.sLists = sLists;
    }

    /**
     * @return the rLists
     */
    public List<Rectangle> getrLists() {
        return rLists;
    }

    /**
     * @param rLists the rLists to set
     */
    public void setrLists(List<Rectangle> rLists) {
        this.rLists = rLists;
    }
    
    
    
}
