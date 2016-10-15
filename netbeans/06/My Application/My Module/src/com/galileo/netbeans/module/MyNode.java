/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.galileo.netbeans.module;

import org.netbeans.api.actions.Editable;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;


public class MyNode extends AbstractNode implements Editable {
   public MyNode() {
     super(Children.LEAF);
   }
   public void edit() {
      // edit something depend on the data, this node represents
   }
}

