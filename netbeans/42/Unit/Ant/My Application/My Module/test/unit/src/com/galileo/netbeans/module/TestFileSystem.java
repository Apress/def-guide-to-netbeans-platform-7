/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import org.openide.filesystems.FileSystem;
import org.openide.filesystems.MultiFileSystem;
import org.openide.filesystems.XMLFileSystem;
import org.openide.util.Exceptions;
import org.openide.util.lookup.ServiceProvider;


@ServiceProvider(service=FileSystem.class)
public class TestFileSystem extends MultiFileSystem {
   
   public TestFileSystem() {
      try {
         setDelegates(new XMLFileSystem(
                 TestFileSystem.class.getResource("test.xml")));
      } catch (Exception ex) {
         Exceptions.printStackTrace(ex);
      }
   }
   
}
