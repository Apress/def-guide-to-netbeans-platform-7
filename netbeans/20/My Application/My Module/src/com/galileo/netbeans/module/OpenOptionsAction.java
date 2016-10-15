/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.netbeans.api.options.OptionsDisplayer;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;

@ActionID(category = "Tools",
id = "com.galileo.netbeans.module.OpenOptionsAction")
@ActionRegistration(displayName = "#CTL_OpenOptionsAction")
@ActionReferences({
   @ActionReference(path = "Menu/Tools", position = 0, separatorAfter = 50)
})
public final class OpenOptionsAction implements ActionListener {

   @Override
   public void actionPerformed(ActionEvent e) {
      OptionsDisplayer.getDefault().open(ModuleOptions2PanelController.ID);
   }
}
