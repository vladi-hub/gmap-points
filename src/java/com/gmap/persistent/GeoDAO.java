/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmap.persistent;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Vladislav Vladimirov
 */
public class GeoDAO {
    
    public Long addUser(User user){  
        Long result = -1L;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        result = (Long)session.save(user);
        session.getTransaction().commit();
        session.close();         
        return result;
    }
    
    public boolean updateUser(User user){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();         
        return true;
    }
    

    
    public Long addMarkers(Markers sl,User u){
        Long result = -1L;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user =(User) session.get(u.getClass(), u.getId());
        user.getsLists().add(sl);
        session.save(user);
        result = (Long)session.save(sl);
        session.getTransaction().commit();
        session.close();         
        return result;
    }
    
    public Long addRectangle(Rectangle sl,User u){
        Long result = -1L;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User user =(User) session.get(u.getClass(), u.getId());
        user.getrLists().add(sl);
        session.save(user);
        result = (Long)session.save(sl);
        session.getTransaction().commit();
        session.close();         
        return result;
    }
    
    public boolean updateMarkers(Markers sl){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(sl);
        session.getTransaction().commit();
        session.close();         
        return true;
    }
    
    
    
    public User getUser(User u){
        User result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        result = (User)session.get(u.getClass(), u.getId());
        if(result == null){
            return result;
        }
        result.getsLists().size();//inititalize for lazy loading
        session.getTransaction().commit();
        session.close();         
        return result;
    }
    
    public User findUserByCredentials(User u){
        User result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query q = session.createQuery("from User where email = :field1 and password = :field2");
        q.setParameter("field1", u.getEmail());
        q.setParameter("field2", u.getPassword());
        Object o = q.uniqueResult();
        result = (User) o;   
        result.getsLists().size();//inititalize for lazy loading
        session.getTransaction().commit();
        session.close();         
        return result;
    }
    
    public Markers getMarkers(Markers s){
        Markers result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        result = (Markers)session.get(s.getClass(), s.getId());
        session.getTransaction().commit();
        session.close();         
        return result;
    }
    
    public List<Markers> getMarkersList(User u){
        List<Markers> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        result = ((User)session.get(u.getClass(), u.getId())).getsLists();
        result.size();
        session.getTransaction().commit();
        session.close();         
        return result;
    }
    
  
    public static void main(String[] args){
        GeoDAO dao = new GeoDAO();
        /*
        SlUser s = new SlUser();
        s.setEmail("a@a.com");
        s.setPassword("123");
              
        Vendor v = new Vendor();
        v.setName("BILLA");
        Long vId = dao.addVendor(v);
        v.setId(vId);
        List<ShopList> sList = new ArrayList<ShopList>();
        ShopList sl = new ShopList();
        sl.setListdate(new Date());
        
        
        List<Item> items = new ArrayList<Item>();
        Item i1 = new Item();
        i1.setName("Item 2");
        i1.setPrice(6.50f);
        i1.setSerialNum("NB-1323-543");
        i1.setPic("url-pic");
        i1.setVendor(v);
        Long itemId1 = dao.addItem(i1);
        i1.setId(itemId1);
        items.add(i1);
        Item i2 = new Item();
        i2.setName("Item 7");
        i2.setPrice(9.34f);
        i2.setSerialNum("NB-1323-543");
        i2.setPic("url-pic2");
        Long itemId2 = dao.addItem(i2);
        i2.setId(itemId2);
        items.add(i2);
        
        sl.setItems(items);
        sList.add(sl);
        
        List<ShopList> itmz = new ArrayList<ShopList>();
        s.setsLists(itmz);
        s.getsLists().addAll(sList);
        
        Long uId = dao.addUser(s);
        s.setId(uId); 
        SlUser u = dao.getUser(s);
        sl.setSluser(u);
        Long slId = dao.addShopList(sl,s);
                                      
        System.out.println("u.getLists.size = " + u.getsLists().size());
        */
        User uu = new User();
        uu.setEmail("a@a.com");
        uu.setPassword("123");
        User nu = dao.findUserByCredentials(uu);
        System.out.println("nu.id = " + nu.getId());
        System.out.println("nu.markers.size = " + nu.getsLists().size());
        Markers sl = nu.getsLists().get(0);
        System.out.println("sl.id = " + sl.getId());
        Markers newSl = dao.getMarkers(sl);        
        System.out.println("items.size = " + newSl.getCoordx());
    }
}
