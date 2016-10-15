/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.galileo.netbeans.module;

import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;

/**
 *
 * @author Heiko
 */
public class GenreNodeFactory extends ChildFactory<String> {

   @Override
   protected boolean createKeys(List<String> toPopulate) {
      toPopulate.add("root");
      return true;
   }

   @Override
   protected Node[] createNodesForKey(String key) {
      return new Node[]{new GenreNode("Techno, Trance and Dance"),
                        new GenreNode("Rock and Pop"),
                        new GenreNode("Country and Classic")};
   }
}
