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
@Table(name="markers")
public class Markers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    private long id;
    
    @Column(name="listdate", nullable=true) 
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date listdate;
    
    @ManyToOne(optional = false)
    //@JoinColumn (name="userId")
    private User user;
    
    @Column(name="coordx", nullable=true)
    private double coordx = 0.0;
    
    @Column(name="coordy", nullable=true)
    private double coordy = 0.0;
    
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
     * @return the desc
     */
    public String getDesc() {
        return description;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.description = desc;
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
