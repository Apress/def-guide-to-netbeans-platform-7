/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;

@ActionID(category = "Edit",
id = "com.galileo.netbeans.module.MyFirstAction")
@ActionRegistration(iconBase = "com/galileo/netbeans/module/icon.png",
displayName = "#CTL_MyFirstAction")
@ActionReferences({
    @ActionReference(path = "Menu/Edit", position = 100, separatorAfter = 150),
    @ActionReference(path = "Shortcuts", name = "D-M"),
    @ActionReference(path = "NotificationActions", name = "D-M")
})
public final class MyFirstAction implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        System.out.println("My First Action");
    }
}
