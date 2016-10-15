/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.io.IOException;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.Node;
import org.openide.nodes.Children;
import org.openide.util.Lookup;

public class Mp3DataObject extends MultiDataObject {

   public Mp3DataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
      super(pf, loader);

   }

   @Override
   protected Node createNodeDelegate() {
      return new DataNode(this, Children.LEAF, getLookup());
   }

   @Override
   public Lookup getLookup() {
      return getCookieSet().getLookup();
   }
}
