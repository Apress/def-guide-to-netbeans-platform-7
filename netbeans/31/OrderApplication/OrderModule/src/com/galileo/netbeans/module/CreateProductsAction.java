/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import com.galileo.netbeans.client.OrderService;
import com.galileo.netbeans.client.OrderService_Service;
import com.galileo.netbeans.client.Product;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "Tools",
id = "com.galileo.netbeans.module.CreateProductsAction")
@ActionRegistration(displayName = "#CTL_CreateProductsAction")
@ActionReferences({
    @ActionReference(path = "Menu/Tools", position = 0)
})
@Messages("CTL_CreateProductsAction=Create Products")
public final class CreateProductsAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        OrderService_Service service = new OrderService_Service();
        OrderService port = service.getOrderServicePort();

        Product p1 = new Product();
        p1.setName("Product 1");
        p1.setOrderId("P1");
        p1.setPrice(2.99);
        
        Product p2 = new Product();
        p2.setName("Product 2");
        p2.setOrderId("P2");
        p2.setPrice(3.99);
        
        Product p3 = new Product();
        p3.setName("Product 3");
        p3.setOrderId("P3");
        p3.setPrice(4.99);
        
        port.addProduct(p1);
        port.addProduct(p2);
        port.addProduct(p3);
    }
}
