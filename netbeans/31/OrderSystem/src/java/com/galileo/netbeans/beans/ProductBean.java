package com.galileo.netbeans.beans;

import com.galileo.netbeans.entities.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@LocalBean
public class ProductBean {

    @PersistenceContext(unitName = "OrderSystemPU")
    private EntityManager em;
    
    public void addProduct(Product product) {
        em.persist(product);
    }

    public List<Product> getProducts() {
        return em.createQuery(em.getCriteriaBuilder().createQuery(Product.class)).getResultList();
    }
}
