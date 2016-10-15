package com.galileo.netbeans.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "File",
id = "com.galileo.netbeans.module.SwitchUser")
@ActionRegistration(displayName = "#CTL_SwitchUser")
@ActionReferences({
   @ActionReference(path = "Menu/File", position = 1300)
})
@Messages("CTL_SwitchUser=Switch User")
public final class SwitchUser implements ActionListener {

   @Override
   public void actionPerformed(ActionEvent e) {
      LoginHandler.getDefault().showLoginDialog();
   }
}
