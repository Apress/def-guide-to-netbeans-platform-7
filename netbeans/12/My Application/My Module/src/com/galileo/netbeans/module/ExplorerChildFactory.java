/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.galileo.netbeans.module;

import java.util.List;
import javax.swing.Action;
import org.openide.filesystems.FileObject;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Heiko
 */
public class ExplorerChildFactory extends ChildFactory<Action> {

   private FileObject folder = null;

   public ExplorerChildFactory(FileObject folder) {
      this.folder = folder;
   }

   @Override
   protected boolean createKeys(List<Action> toPopulate) {
      System.out.println("createKeys:child");
      for(Action action : Lookups.forPath(folder.getPath()).lookupAll(Action.class)) {
         toPopulate.add(action);
      }

      return true;
   }

   @Override
   protected Node createNodeForKey(Action key) {
      System.out.println("createNodeForKey:child");
      return new ExplorerLeafNode(key);
   }
}
