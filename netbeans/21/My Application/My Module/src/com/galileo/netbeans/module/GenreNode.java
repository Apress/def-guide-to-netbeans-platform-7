package com.galileo.netbeans.module;

import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;

public class GenreNode extends AbstractNode{

   public GenreNode(String genre) {
      super(Children.create(new AlbumNodeFactory(genre), false));
      this.setDisplayName(genre);
   }
}
