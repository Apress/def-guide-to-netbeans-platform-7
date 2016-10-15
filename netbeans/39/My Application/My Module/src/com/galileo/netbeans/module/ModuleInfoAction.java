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

@ActionID(category = "Tools",
id = "com.galileo.netbeans.module.ModuleInfoAction")
@ActionRegistration(displayName = "#CTL_ModuleInfoAction")
@ActionReferences({
   @ActionReference(path = "Menu/Tools", position = 0)
})
@Messages("CTL_ModuleInfoAction=Module Info")
public final class ModuleInfoAction implements ActionListener {

   @Override
   public void actionPerformed(ActionEvent e) {
      System.out.println("Module Version: " + Installer.getModuleInfo().getSpecificationVersion());
   }
}
