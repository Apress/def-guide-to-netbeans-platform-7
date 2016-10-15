/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author Heiko
 */
public class Math {
   public int add(int a, int b) {
      return a + b;
   }
   public int multiply(String aName, String bName) {
      FileObject folder = FileUtil.getConfigFile("MyFolder2");
      FileObject file = folder.getFileObject("MyFile");
      Integer aVal = (Integer)file.getAttribute(aName);
      Integer bVal = (Integer)file.getAttribute(bName);

      return aVal * bVal;
   }
}
