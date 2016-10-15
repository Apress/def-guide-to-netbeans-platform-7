/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.order.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "Tools",
id = "com.galileo.netbeans.order.module.TestRESTAction")
@ActionRegistration(displayName = "#CTL_TestRESTAction")
@ActionReferences({
    @ActionReference(path = "Menu/Tools", position = 0)
})
@Messages("CTL_TestRESTAction=Test REST")
public final class TestRESTAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        OrderServiceClient client = new OrderServiceClient();
        Product p = new Product();
        p.setName("Test Product");
        p.setOrderId("P1");
        p.setPrice(3.99);
        client.create(p);
        
        Product p1 = client.find(Product.class, "123");
        System.out.println("P1: " + p1);
        
        Products pro = client.findAll(Products.class);

        System.out.println("Products: " + pro.getProduct().size());
        System.out.println("Product: " + pro.getProduct().get(1).getId());
    }
}
