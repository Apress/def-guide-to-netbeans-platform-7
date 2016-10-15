/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hibernate;

import javassist.util.proxy.ProxyFactory;
import org.openide.modules.ModuleInstall;

/**
 * Manages a module's lifecycle. Remember that an installer is optional and
 * often not needed at all.
 */
public class Installer extends ModuleInstall {

   @Override
   public void restored() {
     ProxyFactory.classLoaderProvider = new ProxyFactory.ClassLoaderProvider() {
        @Override
        public ClassLoader get(ProxyFactory pf) {
           return Thread.currentThread().getContextClassLoader();
        }
     };
   }
}
