/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;

@ActionID(category = "Edit",
id = "com.galileo.netbeans.module.DisplayTrayMessageAction")
@ActionRegistration(displayName = "#CTL_DisplayTrayMessageAction")
@ActionReferences({
   @ActionReference(path = "Menu/Edit", position = 100, separatorAfter = 150)
})
public final class DisplayTrayMessageAction implements ActionListener {

   @Override
   public void actionPerformed(ActionEvent e) {
      Installer.displayTrayMessage("Caption", "Das ist meine erste System Tray Message", MessageType.INFO);
   }
}
