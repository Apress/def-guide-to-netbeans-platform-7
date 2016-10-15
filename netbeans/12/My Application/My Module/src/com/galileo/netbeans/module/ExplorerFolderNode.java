package com.galileo.netbeans.module;

import org.openide.filesystems.FileObject;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

public class ExplorerFolderNode extends AbstractNode {
   
   public ExplorerFolderNode(FileObject folder, Children children) {
      super(children);

      setDisplayName(folder.getName());

      String iconBase = (String) folder.getAttribute("icon");
      if(iconBase != null) {
         setIconBaseWithExtension(iconBase);
      }
   }
}
