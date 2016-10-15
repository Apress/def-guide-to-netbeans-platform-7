/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.LifecycleManager;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;

@ActionID(category = "File",
id = "com.galileo.netbeans.module.RestartAction")
@ActionRegistration(displayName = "#CTL_RestartAction")
@ActionReferences({
   @ActionReference(path = "Menu/File", position = 1300, separatorAfter = 1350)
})
public final class RestartAction implements ActionListener {

   @Override
   public void actionPerformed(ActionEvent e) {
      LifecycleManager.getDefault().markForRestart();
   }
}
