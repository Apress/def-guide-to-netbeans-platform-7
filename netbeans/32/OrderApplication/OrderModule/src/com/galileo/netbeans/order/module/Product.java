/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.order.module;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Heiko
 */
@XmlRootElement
public class Product {

    private Long id;
    protected String orderId;
    protected String name;
    protected Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "com.galileo.netbeans.order.entities.Product[ id=" + id + " ]";
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }    
}
