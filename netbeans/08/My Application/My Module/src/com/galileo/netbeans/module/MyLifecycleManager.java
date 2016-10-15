package com.galileo.netbeans.module;

import org.openide.LifecycleManager;
import org.openide.util.Lookup;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = LifecycleManager.class, position=1)
public class MyLifecycleManager extends LifecycleManager{
   
   @Override
   public void saveAll() {
      for(LifecycleManager manager : Lookup.getDefault().lookupAll(LifecycleManager.class)) {
         if(manager != this) {
            manager.saveAll();
         }
      }
   }
   
   @Override
   public void exit() {
      System.out.println("Shutdown");
      for(LifecycleManager manager : Lookup.getDefault().lookupAll(LifecycleManager.class)) {
         if(manager != this) {
            manager.exit();
         }
      }
   }
}
