package com.galileo.netbeans.module;

import java.awt.Image;
import javax.swing.Action;
import javax.swing.ImageIcon;
import org.openide.awt.Actions;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

public class ExplorerLeafNode extends AbstractNode {
   
   private Action action = null;

   public ExplorerLeafNode(Action action) {
      super(Children.LEAF);
      this.action = action;
      setDisplayName(Actions.cutAmpersand((String)action.getValue(Action.NAME)));
   }
   
   @Override
   public Action getPreferredAction() {
      return action;
   }
   
   @Override
   public Image getIcon(int type) {
      ImageIcon img = (ImageIcon) action.getValue(Action.SMALL_ICON);
      
      if(img != null) {
         return img.getImage();
      } else {
         return null;
      }
   }
}
