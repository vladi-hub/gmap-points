/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmap.web;

import com.gmap.persistent.GeoDAO;
import com.gmap.persistent.Markers;
import com.gmap.persistent.User;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Rectangle;

/**
 *
 * @author Vladislav Vladimirov
 */
public class GmapBean extends AbstractUIBean implements Serializable {
    
    private MapModel emptyModel;  
    
    private MapModel simpleModel;
    
    private MapModel rectangleModel;
      
    private String title;  
      
    private double lat;  
      
    private double lng; 
    
    private double lat2;  
      
    private double lng2;
    
    private Marker marker; 
    
    private Rectangle rectangle;
    
    public GmapBean(){
        simpleModel = new DefaultMapModel(); 
        emptyModel = new DefaultMapModel();  
        rectangleModel = new DefaultMapModel();  
        User u = getSessionUser();
        if(u != null){
            GeoDAO dao = new GeoDAO();
            List<Markers> markersList = dao.getMarkersList(u);

            for (Markers m : markersList){
                LatLng coord = new LatLng(m.getCoordx(), m.getCoordy());
                simpleModel.addOverlay(new Marker(coord, m.getDesc()));
            }
        }
    }
    
    public MapModel getEmptyModel() {  
        return emptyModel;  
    }  
      
    public void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
      
    public String getTitle() {  
        return title;  
    }  
  
    public void setTitle(String title) {  
        this.title = title;  
    }  
  
    public double getLat() {  
        return lat;  
    }  
  
    public void setLat(double lat) {  
        this.lat = lat;  
    }  
  
    public double getLng() {  
        return lng;  
    }  
  
    public void setLng(double lng) {  
        this.lng = lng;  
    }  
      
    public void addMarker(ActionEvent actionEvent) {  
        Marker marker = new Marker(new LatLng(lat, lng), title);  
        emptyModel.addOverlay(marker);  
          
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));  
        
        User u = getSessionUser();
        GeoDAO dao = new GeoDAO();
        Markers m = new Markers();
        m.setCoordx(lat);
        m.setCoordy(lng);
        m.setDesc(title);
        m.setShareit(true);
        m.setUser(u);
        Long mId = dao.addMarkers(m, u);
    }
    
    public void addRectangle(ActionEvent actionEvent) {  
        Rectangle rectangle = new Rectangle(new LatLngBounds(new LatLng(lat, lng), new LatLng(getLat2(), getLng2()))); 
        rectangle.setStrokeColor("#d93c3c");  
        rectangle.setFillColor("#d93c3c");  
        rectangle.setFillOpacity(0.5); 
        rectangleModel.addOverlay(rectangle);  
          
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "rectangle Added", "Lat:" + lat + ", Lng:" + lng + " Lat2:" + lat2 + " Lng2:" + lng2));  
        
        User u = getSessionUser();
        GeoDAO dao = new GeoDAO();
        com.gmap.persistent.Rectangle m = new com.gmap.persistent.Rectangle();
        m.setCoordx(lat);
        m.setCoordy(lng);
        m.setCoordxx(lat2);
        m.setCoordyy(lng2);        
        m.setShareit(true);
        m.setDescription(title);
        m.setUser(u);
        Long mId = dao.addRectangle(m, u);
    }
    
    public void onMarkerSelect(OverlaySelectEvent event) {  
        setMarker((Marker) event.getOverlay());  
          
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", getMarker().getTitle()));  
    } 
    
    public void loadMarker(ActionEvent actionEvent){
        User u = getSessionUser();
        GeoDAO dao = new GeoDAO();
        List<Markers> markersList = dao.getMarkersList(u);
        
        for (Markers m : markersList){
            LatLng coord = new LatLng(m.getCoordx(), m.getCoordy());
            emptyModel.addOverlay(new Marker(coord, m.getDesc()));
        }                
    }

    /**
     * @return the simpleModel
     */
    public MapModel getSimpleModel() {
        return simpleModel;
    }

    /**
     * @param simpleModel the simpleModel to set
     */
    public void setSimpleModel(MapModel simpleModel) {
        this.simpleModel = simpleModel;
    }
    
    public void onRectangleSelect(OverlaySelectEvent event) {  
            addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Rectangle Selected", null));  
    }

    /**
     * @return the rectangleModel
     */
    public MapModel getRectangleModel() {
        return rectangleModel;
    }

    /**
     * @param rectangleModel the rectangleModel to set
     */
    public void setRectangleModel(MapModel rectangleModel) {
        this.rectangleModel = rectangleModel;
    }

    /**
     * @return the lat2
     */
    public double getLat2() {
        return lat2;
    }

    /**
     * @param lat2 the lat2 to set
     */
    public void setLat2(double lat2) {
        this.lat2 = lat2;
    }

    /**
     * @return the lng2
     */
    public double getLng2() {
        return lng2;
    }

    /**
     * @param lng2 the lng2 to set
     */
    public void setLng2(double lng2) {
        this.lng2 = lng2;
    }

    /**
     * @return the marker
     */
    public Marker getMarker() {
        return marker;
    }

    /**
     * @param marker the marker to set
     */
    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    /**
     * @return the rectangle
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * @param rectangle the rectangle to set
     */
    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    
    
}
