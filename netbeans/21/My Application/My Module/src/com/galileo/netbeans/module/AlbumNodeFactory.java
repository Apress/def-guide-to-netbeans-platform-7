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
public class AlbumNodeFactory extends ChildFactory<String> {
   
   String genre;
   
   public AlbumNodeFactory(String genre) {
      this.genre = genre;
   }

   @Override
   protected boolean createKeys(List<String> toPopulate) {
      toPopulate.add(genre);
      return true;
   }

   @Override
   protected Node[] createNodesForKey(String key) {
      return new Node[] {new AlbumNode(new Album("Tunnel Trance Force 39", "42", "2", "2007", "com/galileo/netbeans/module/resources/cover1_small.jpg", "com/galileo/netbeans/module/resources/cover1_big.jpg")),
                         new AlbumNode(new Album("Dream Dance 43", "39", "3", "2007", "com/galileo/netbeans/module/resources/cover2_small.jpg", "com/galileo/netbeans/module/resources/cover2_big.jpg")),
                         new AlbumNode(new Album("DJ Networx 31", "45", "2", "2006", "com/galileo/netbeans/module/resources/cover3_small.jpg", "com/galileo/netbeans/module/resources/cover3_big.jpg"))};
   }
}
