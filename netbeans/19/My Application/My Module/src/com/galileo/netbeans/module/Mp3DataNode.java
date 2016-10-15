package com.galileo.netbeans.module;

import org.openide.loaders.DataNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;

public class Mp3DataNode extends DataNode {
   
   private static final String IMAGE_ICON_BASE = "com/galileo/netbeans/module/mp3.png";
   
   public Mp3DataNode(Mp3DataObject obj) {
      super(obj, Children.LEAF);
      setIconBaseWithExtension(IMAGE_ICON_BASE);
   }
   
   public Mp3DataNode(Mp3DataObject obj, Lookup lookup) {
      super(obj, Children.LEAF, lookup);
      setIconBaseWithExtension(IMAGE_ICON_BASE);
   }

   @Override
   protected Sheet createSheet() {
      System.out.println("create sheet");
      Sheet s = super.createSheet();
      Sheet.Set id3v1 = Sheet.createPropertiesSet();
      Sheet.Set id3v2 = Sheet.createPropertiesSet();
      
      id3v1.setDisplayName("ID3v1");
      id3v1.setName("ID3v1");
      id3v2.setDisplayName("ID3v2");
      id3v2.setName("ID3v2");
      
      Mp3DataObject mp3 = getLookup().lookup(Mp3DataObject.class);
      
      try {
         Property artistProp = new PropertySupport.Reflection<String>(mp3, String.class, "artist");
         Property titleProp  = new PropertySupport.Reflection<String>(mp3, String.class, "title");
         Property trackProp  = new PropertySupport.Reflection<Integer>(mp3, Integer.class, "getTrack", null);
         PropertySupport.Reflection<String> genreProp  = new PropertySupport.Reflection<String>(mp3, String.class, "genre");

         genreProp.setPropertyEditorClass(GenrePropertyEditor.class);

         artistProp.setName("Artist");
         titleProp.setName("Title");
         genreProp.setName("Genre");
         trackProp.setName("Track");

         id3v1.put(artistProp);
         id3v1.put(titleProp);
         id3v1.put(genreProp);
         id3v2.put(trackProp);
      } catch (NoSuchMethodException ex) {
         Exceptions.printStackTrace(ex);
      }

      s.put(id3v1);
      s.put(id3v2);

      return(s);
   }
}
