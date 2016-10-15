package com.galileo.netbeans.module;

import org.openide.modules.ModuleInstall;

public class Installer extends ModuleInstall {

   @Override
   public void restored() {
      LoginHandler.getDefault().showLoginDialog();
   }
}
