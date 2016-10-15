package com.galileo.netbeans.service;

import com.galileo.netbeans.beans.CartBean;
import com.galileo.netbeans.beans.ProductBean;
import com.galileo.netbeans.entities.Product;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

@WebService(serviceName = "OrderService")
@Stateless
public class OrderService {

    @EJB
    private ProductBean productBean;

    @EJB
    private CartBean cartBean;

    @WebMethod(operationName = "addProductToCart")
    @Oneway
    public void addProductToCart2(
            @WebParam(name = "product") Product product) {
        cartBean.addProduct(product);
    }

    @WebMethod(operationName = "getSumOfCart")
    public Double getSumOfCart() {
        return cartBean.getSum();
    }
    
    @WebMethod(operationName = "getAmountOfCart")
    public Integer getAmountOfCart() {
        return cartBean.getAmount();
    }

    @WebMethod(operationName = "getProducts")
    public List<Product> getProducts() {
        return productBean.getProducts();
    }

    @WebMethod(operationName = "addProduct")
    @Oneway
    public void addProduct(
            @WebParam(name = "product") Product product) {
        productBean.addProduct(product);
    }
}
