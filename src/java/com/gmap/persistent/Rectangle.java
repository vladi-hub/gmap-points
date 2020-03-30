/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmap.persistent;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Vladislav Vladimirov
 */
@Entity
@Table(name="rectangle")
public class Rectangle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;
    
    @Column(name="listdate", nullable=true) 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date listdate;
    
    @ManyToOne
    @JoinColumn (name="userId")
    private User user;
    
    @Column(name="coordx", nullable=true)
    private double coordx = 0.0;
    
    @Column(name="coordy", nullable=true)
    private double coordy = 0.0;
    
    @Column(name="coordxx", nullable=true)
    private double coordxx = 0.0;
    
    @Column(name="coordyy", nullable=true)
    private double coordyy = 0.0;
    
    @Column(name="description", nullable=true)
    private String description;

    @Column(name="shareit", nullable=true)
    private boolean shareit;

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
     * @return the listdate
     */
    public Date getListdate() {
        return listdate;
    }

    /**
     * @param listdate the listdate to set
     */
    public void setListdate(Date listdate) {
        this.listdate = listdate;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the coordx
     */
    public double getCoordx() {
        return coordx;
    }

    /**
     * @param coordx the coordx to set
     */
    public void setCoordx(double coordx) {
        this.coordx = coordx;
    }

    /**
     * @return the coordy
     */
    public double getCoordy() {
        return coordy;
    }

    /**
     * @param coordy the coordy to set
     */
    public void setCoordy(double coordy) {
        this.coordy = coordy;
    }

    /**
     * @return the coordxx
     */
    public double getCoordxx() {
        return coordxx;
    }

    /**
     * @param coordxx the coordxx to set
     */
    public void setCoordxx(double coordxx) {
        this.coordxx = coordxx;
    }

    /**
     * @return the coordyy
     */
    public double getCoordyy() {
        return coordyy;
    }

    /**
     * @param coordyy the coordyy to set
     */
    public void setCoordyy(double coordyy) {
        this.coordyy = coordyy;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the shareit
     */
    public boolean isShareit() {
        return shareit;
    }

    /**
     * @param shareit the shareit to set
     */
    public void setShareit(boolean shareit) {
        this.shareit = shareit;
    }
    
    
}
