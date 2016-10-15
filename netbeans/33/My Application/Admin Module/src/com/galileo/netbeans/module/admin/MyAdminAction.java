/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "Admin",
id = "com.galileo.netbeans.module2.MyAdminAction")
@ActionRegistration(displayName = "#CTL_MyAdminAction")
@ActionReferences({
   @ActionReference(path = "Menu/Admin", position = 100)
})
@Messages("CTL_MyAdminAction=My Admin Action")
public final class MyAdminAction implements ActionListener {

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO implement action body
   }
}
