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
import org.openide.util.NbBundle.Messages;

@ActionID(category = "File",
id = "com.galileo.netbeans.module.TestAction")
@ActionRegistration(displayName = "#CTL_TestAction")
@ActionReferences({
   @ActionReference(path = "Menu/File", position = 1300)
})
@Messages("CTL_TestAction=Test Action")
public final class TestAction implements ActionListener {
   private int value = 0;

   public void actionPerformed(ActionEvent e) {
      System.out.println("Start test");
      
      String tempValue = "temp";
      
      String newValue = hello("World") + bye("World");
      
      for (int i = 0; i < 20; i++) {
         value++;
      }
   }
   
   public String hello(String name) {
      return "Hello " + name;
   }
   
   public String bye(String name) {
      return "Bye " + name;
   }
}
