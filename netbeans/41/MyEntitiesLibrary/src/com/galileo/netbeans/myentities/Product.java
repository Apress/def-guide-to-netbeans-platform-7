/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.myentities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Heiko
 */
@Entity
@Table(name = "PRODUCT")
@NamedQueries({
   @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p"),
   @NamedQuery(name = "Product.findById", query = "SELECT p FROM Product p WHERE p.id = :id"),
   @NamedQuery(name = "Product.findByTitle", query = "SELECT p FROM Product p WHERE p.title = :title"),
   @NamedQuery(name = "Product.findByOrderid", query = "SELECT p FROM Product p WHERE p.orderid = :orderid"),
   @NamedQuery(name = "Product.findByPrice", query = "SELECT p FROM Product p WHERE p.price = :price")})
public class Product implements Serializable {

   private static final long serialVersionUID = 1L;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Basic(optional = false)
   @Column(name = "ID")
   private Integer id;
   @Basic(optional = false)
   @Column(name = "TITLE")
   private String title;
   @Basic(optional = false)
   @Column(name = "ORDERID")
   private String orderid;
   @Basic(optional = false)
   @Column(name = "PRICE")
   private double price;

   public Product() {
   }

   public Product(Integer id) {
      this.id = id;
   }

   public Product(Integer id, String title, String orderid, double price) {
      this.id = id;
      this.title = title;
      this.orderid = orderid;
      this.price = price;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      Integer oldId = this.id;
      this.id = id;
      changeSupport.firePropertyChange("id", oldId, id);
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      String oldTitle = this.title;
      this.title = title;
      changeSupport.firePropertyChange("title", oldTitle, title);
   }

   public String getOrderid() {
      return orderid;
   }

   public void setOrderid(String orderid) {
      String oldOrderid = this.orderid;
      this.orderid = orderid;
      changeSupport.firePropertyChange("orderid", oldOrderid, orderid);
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      double oldPrice = this.price;
      this.price = price;
      changeSupport.firePropertyChange("price", oldPrice, price);
   }

   @Override
   public int hashCode() {
      int hash = 0;
      hash += (id != null ? id.hashCode() : 0);
      return hash;
   }

   @Override
   public boolean equals(Object object) {
      // TODO: Warning - this method won't work in the case the id fields are not set
      if (!(object instanceof Product)) {
         return false;
      }
      Product other = (Product) object;
      if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
         return false;
      }
      return true;
   }

   @Override
   public String toString() {
      return "com.galileo.netbeans.myentities.Product[ id=" + id + " ]";
   }
   private transient PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

   public void addPropertyChangeListener(PropertyChangeListener listener) {
      changeSupport.addPropertyChangeListener(listener);
   }

   public void removePropertyChangeListener(PropertyChangeListener listener) {
      changeSupport.removePropertyChangeListener(listener);
   }
}
