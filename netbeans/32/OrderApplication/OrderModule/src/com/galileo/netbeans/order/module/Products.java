/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.order.module;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Heiko
 */
@XmlRootElement
public class Products {
    
    private List<Product> product = new ArrayList<Product>();

    public List<Product> getProduct() {
        return product;
    }
    
    public void setProduct(List<Product> product) {
        this.product = product;
    }    
}
