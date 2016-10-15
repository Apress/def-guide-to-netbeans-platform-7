package com.galileo.netbeans.beans;

import com.galileo.netbeans.entities.Product;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;

@Stateful
@LocalBean
public class CartBean {
   
    private List<Product> products = new ArrayList<Product>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }
    
    public Double getSum() {
        Double sum = 0.0;
        for (Product p : products) {
            sum += p.getPrice();
        }
        
        return sum;
    }
    
    public Integer getAmount() {
        return products.size();
    }
}
