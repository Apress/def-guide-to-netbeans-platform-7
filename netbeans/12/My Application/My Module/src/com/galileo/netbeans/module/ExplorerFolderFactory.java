/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.galileo.netbeans.module;

import java.util.Arrays;
import java.util.List;
import org.openide.filesystems.FileObject;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Children;
import org.openide.nodes.Node;

/**
 *
 * @author Heiko
 */
public class ExplorerFolderFactory extends ChildFactory<FileObject> {

   private FileObject folder = null;

   public ExplorerFolderFactory(FileObject folder) {
      this.folder = folder;
   }

   @Override
   protected boolean createKeys(List<FileObject> toPopulate) {
      System.out.println("createKeys:folder");
      toPopulate.addAll(Arrays.asList(folder.getChildren()));

      return true;
   }

   @Override
   protected Node createNodeForKey(FileObject key) {
      System.out.println("createNodeForKey:folder");
      return new ExplorerFolderNode(key, Children.create(new ExplorerChildFactory(key), false));
   }
}
