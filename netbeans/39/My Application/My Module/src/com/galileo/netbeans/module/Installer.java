/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.net.URL;
import java.util.Collection;
import org.netbeans.api.autoupdate.UpdateElement;
import org.netbeans.api.autoupdate.UpdateManager;
import org.netbeans.api.autoupdate.UpdateUnit;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.URLMapper;
import org.openide.modules.ModuleInfo;
import org.openide.modules.ModuleInstall;
import org.openide.util.Lookup;

/**
 * Manages a module's lifecycle. Remember that an installer is optional and
 * often not needed at all.
 */
public class Installer extends ModuleInstall {
   
   public static final String MODULE_ID = "com.galileo.netbeans.module";
   
   private static UpdateElement moduleInfo;
   //private static ModuleInfo moduleInfo;

   @Override
   public void restored() {
      // By default, do nothing.
      // Put your startup code here.
   }
   
   public static Installer getDefault() {
      return findObject(Installer.class, true);
   }
   
   public FileObject getModuleResource(String path) {
      URL url = getClass().getClassLoader().getResource(path);
      FileObject resource = URLMapper.findFileObject(url);
      return resource;
   }

   public static UpdateElement getModuleInfo() {
      if (moduleInfo == null) {
         for (UpdateUnit unit : UpdateManager.getDefault().getUpdateUnits(UpdateManager.TYPE.MODULE)) {
            if (unit.getInstalled() != null && unit.getInstalled().getCodeName().equals(MODULE_ID)) {
               moduleInfo = unit.getInstalled();
               break;
            }
         }
      }

      return moduleInfo;
   }
/*
   public ModuleInfo getModuleInfo2() {
      if(moduleInfo == null) {
         Collection<? extends ModuleInfo> all = 
            Lookup.getDefault().lookupAll(ModuleInfo.class);
         for(ModuleInfo m : all) {
            if(m.getCodeNameBase().equals(MODULE_ID)) {
               moduleInfo = m; 
               break;
            }
         }
      }

      return moduleInfo;
   }
    * 
    */
}
