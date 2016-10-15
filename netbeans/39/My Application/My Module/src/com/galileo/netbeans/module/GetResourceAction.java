/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(category = "Tools",
id = "com.galileo.netbeans.module.GetResourceAction")
@ActionRegistration(displayName = "#CTL_GetResourceAction")
@ActionReferences({
   @ActionReference(path = "Menu/Tools", position = 0)
})
@Messages("CTL_GetResourceAction=Get Resource")
public final class GetResourceAction implements ActionListener {

   @Override
   public void actionPerformed(ActionEvent e) {
      FileObject res = Installer.getDefault().getModuleResource(
                                "resources/myprops.properties");
      Properties props = new Properties();
      try {
         props.load(res.getInputStream());
         System.out.println(props.getProperty("key"));
      } catch(Exception ex) {
         Exceptions.printStackTrace(ex);
      }
   }
}
