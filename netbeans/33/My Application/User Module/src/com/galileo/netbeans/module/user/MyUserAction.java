/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module.user;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "User",
id = "com.galileo.netbeans.module.user.MyUserAction")
@ActionRegistration(displayName = "#CTL_MyUserAction")
@ActionReferences({
   @ActionReference(path = "Menu/User", position = 100)
})
@Messages("CTL_MyUserAction=My User Action")
public final class MyUserAction implements ActionListener {

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO implement action body
   }
}
