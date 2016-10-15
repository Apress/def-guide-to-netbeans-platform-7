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

@ActionID(category = "TrayMenu",
id = "com.galileo.netbeans.module.FirstTrayAction")
@ActionRegistration(displayName = "#CTL_FirstTrayAction")
@ActionReferences({
   @ActionReference(path = "TrayMenu", position = 100)
})
public final class FirstTrayAction implements ActionListener {

   @Override
   public void actionPerformed(ActionEvent e) {
      System.out.println("My First Tray Action");
   }
}
