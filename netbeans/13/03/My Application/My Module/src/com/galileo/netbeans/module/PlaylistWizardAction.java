/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;

@ActionID(category = "Tools",
id = "com.galileo.netbeans.module.PlaylistWizardAction")
@ActionRegistration(
   displayName = "#CTL_PlaylistWizardAction",
   iconBase = "com/galileo/netbeans/module/wizard.png")
@ActionReferences({
   @ActionReference(path = "Menu/Tools", position = 1200, separatorAfter = 1250)
})
public final class PlaylistWizardAction implements ActionListener {

   @Override
   public void actionPerformed(ActionEvent e) {
      PlaylistWizardDescriptor descriptor =
         new PlaylistWizardDescriptor();
      Dialog dialog =
         DialogDisplayer.getDefault().createDialog(descriptor);
      dialog.setVisible(true);
      dialog.toFront();
      if(descriptor.getValue()==WizardDescriptor.FINISH_OPTION) {
         Map<String, Object> props = descriptor.getProperties();
         //Create the playlist with the data stored in props
      }
   }
}
