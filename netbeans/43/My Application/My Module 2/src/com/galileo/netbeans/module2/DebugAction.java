/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "Edit",
id = "com.galileo.netbeans.module2.DebugAction")
@ActionRegistration(displayName = "#CTL_DebugAction")
@ActionReferences({
   @ActionReference(path = "Menu/File", position = 1200)
})
@Messages("CTL_DebugAction=Debug Action")
public final class DebugAction implements ActionListener {

   public void actionPerformed(ActionEvent e) {
      // TODO implement action body
   }
}
