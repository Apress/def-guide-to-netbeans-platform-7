package com.galileo.netbeans.module;

import java.util.ArrayList;
import java.util.List;
import org.netbeans.api.autoupdate.OperationContainer;
import org.netbeans.api.autoupdate.OperationSupport;
import org.netbeans.api.autoupdate.UpdateManager;
import org.netbeans.api.autoupdate.UpdateUnit;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;


public class UserGroupModuleSystem {

   public static void handleModules(String folder, boolean en) {
      FileObject fo = FileUtil.getConfigFile(folder);
      List<String> modules = new ArrayList<String>();

      for (FileObject fi : fo.getChildren()) {
         modules.add((String)fi.getAttribute("codeNameBase"));
      }

      try {
         OperationContainer<OperationSupport> cont;

         if (en) {
            cont = OperationContainer.createForEnable();
         } else {
            cont = OperationContainer.createForDirectDisable();
         }

         for (UpdateUnit uu : 
                 UpdateManager.getDefault().getUpdateUnits()) {
            if (uu.getInstalled() != null 
             && modules.contains(uu.getInstalled().getCodeName())
               && uu.getInstalled().isEnabled() == !en) {
               cont.add(uu.getInstalled());
            }
         }

         if (!cont.listAll().isEmpty()) {
            cont.getSupport().doOperation(null);
         }
      } catch (Exception ex) {
         Exceptions.printStackTrace(ex);
      }
   }
}
