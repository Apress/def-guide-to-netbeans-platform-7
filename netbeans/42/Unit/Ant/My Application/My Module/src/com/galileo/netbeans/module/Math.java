package com.galileo.netbeans.module;

import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;


public class Math {
   
   public int add(int a, int b) {
      return a + b;
   }
   
   public int subtract(int a, int b) {
      return a - b;
   }
   
   public int multiply(String aName, String bName) {
      FileObject folder = FileUtil.getConfigFile("MyFolder");
      FileObject file = folder.getFileObject("MyFile");
      int aVal = (int)file.getAttribute(aName);
      int bVal = (int)file.getAttribute(bName);

      return aVal * bVal;
   }
}
