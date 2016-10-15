package com.galileo.netbeans.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.LifecycleManager;
import org.openide.util.Exceptions;

public class LoginHandler implements ActionListener {
   private static LoginHandler instance = new LoginHandler();

   private static final String MOD2DISABLE = "Modules2Disable";
   private static final String MOD2ENABLE = "Modules2Enable";

   private LoginPanel panel = new LoginPanel();
   private DialogDescriptor dialog = null;
   
   private LoginHandler() {
   }

   public static LoginHandler getDefault() {
      return instance;
   }

   public void showLoginDialog() {
      panel.reset();
      dialog = new DialogDescriptor(panel, "Login", true, this);
      dialog.setClosingOptions(new Object[]{});
      dialog.addPropertyChangeListener(new PropertyChangeListener() {
         @Override
         public void propertyChange(PropertyChangeEvent evt) {
            if(evt.getPropertyName().equals(DialogDescriptor.PROP_VALUE)
               && evt.getNewValue().equals(DialogDescriptor.CLOSED_OPTION)) {
               LifecycleManager.getDefault().exit();
            }
         }
      });

      DialogDisplayer.getDefault().notifyLater(dialog);
      
//      WindowManager.getDefault().invokeWhenUIReady(new Runnable(){
//         public void run() {
//            DialogDisplayer.getDefault().notify(d);
//         }
//      });
   }

   @Override
   public void actionPerformed(ActionEvent evt) {
      if(evt.getSource() == DialogDescriptor.CANCEL_OPTION) {
         LifecycleManager.getDefault().exit();
      } else {
         login();
      }
   }

   private void login() {
      if(!SecurityManager.getDefault().login(
              panel.getUsername(), panel.getPassword())) {
         panel.setInfo("Benutzername oder Passwort falsch");
      } else {
         List<UserGroup> groups = 
            SecurityManager.getDefault().getUserGroups();

         if (groups.isEmpty()) {
            panel.setInfo("Sie haben keine Berechtigung");
         } else {
            try {
               UserGroupFileSystem.getDefault().setUserGroups(groups);
               UserGroupModuleSystem.handleModules(
                       MOD2DISABLE, false);
               UserGroupModuleSystem.handleModules(
                       MOD2ENABLE, true);
               dialog.setClosingOptions(null);
            } catch (Exception ex) {
               Exceptions.printStackTrace(ex);
            }
         }
      }
   }

}
