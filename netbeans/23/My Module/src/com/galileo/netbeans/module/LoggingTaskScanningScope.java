/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.galileo.netbeans.module;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.netbeans.spi.tasklist.TaskScanningScope;
import org.openide.filesystems.FileObject;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Heiko
 */
public class LoggingTaskScanningScope extends TaskScanningScope {
   
   Callback cb = null;
   
   List<FileObject> files = new ArrayList<FileObject>();
   
   public LoggingTaskScanningScope() {
      super ("Display Name", "Description", ImageUtilities.loadImage("com/galileo/netbeans/module/unknown.gif"));
   }

   @Override
   public boolean isInScope(FileObject resource) {
      System.out.println(resource.getName());
      return true;
   }

   @Override
   public void attach(Callback callback) {
      cb = callback;
   }

   @Override
   public Lookup getLookup() {
      return Lookups.singleton(cb);
   }

   @Override
   public Iterator<FileObject> iterator() {
      return files.iterator();
   }

}
