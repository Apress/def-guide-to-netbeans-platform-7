package com.hboeck.mp3manager.filetype;

import com.hboeck.mp3manager.filetype.properties.GenrePropertyEditor;
import com.hboeck.mp3manager.id3.v1.ID3v1Tag;
import com.hboeck.mp3manager.id3.v2.ID3v2Tag;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.util.logging.Logger;
import org.openide.loaders.DataNode;
import org.openide.nodes.Children;
import org.openide.nodes.PropertySupport;
import org.openide.nodes.Sheet;
import org.openide.util.Lookup;

public class Mp3DataNode extends DataNode implements Transferable {

   public static final DataFlavor DATA_FLAVOR = new DataFlavor(Mp3DataNode.class, "Mp3DataNode");

   public Mp3DataNode(Mp3DataObject obj) {
      super(obj, Children.LEAF);
   }

   public Mp3DataNode(Mp3DataObject obj, Lookup lookup) {
      super(obj, Children.LEAF, lookup);
   }
   
   @Override
   protected Sheet createSheet() {
      Sheet sheet = super.createSheet();
      Sheet.Set set1 = Sheet.createPropertiesSet();
      Sheet.Set set2 = Sheet.createPropertiesSet();

      set1.setName("id3v1");
      set1.setDisplayName("ID3 V1");
      set2.setName("id3v2");
      set2.setDisplayName("ID3 V2");

      Mp3DataObject mp3 = getLookup().lookup(Mp3DataObject.class);
      ID3v1Tag id3v1 = mp3.getID3v1Tag();
      ID3v2Tag id3v2 = mp3.getID3v2Tag();

      try {
         Property title1   = new PropertySupport.Reflection<String>(id3v1, String.class, "title");
         Property artist1  = new PropertySupport.Reflection<String>(id3v1, String.class, "artist");
         Property album1   = new PropertySupport.Reflection<String>(id3v1, String.class, "album");
         Property year1    = new PropertySupport.Reflection<String>(id3v1, String.class, "year");
         Property comment1 = new PropertySupport.Reflection<String>(id3v1, String.class, "comment");
         Property track1   = new PropertySupport.Reflection<String>(id3v1, String.class, "track");
         PropertySupport.Reflection<String> genre1 = new PropertySupport.Reflection<String>(id3v1, String.class, "genre");

         track1.setName("Nr");
         track1.setValue("suppressCustomEditor", Boolean.TRUE);
         set1.put(track1);
         title1.setName("Title");
         set1.put(title1);
         artist1.setName("Artist");
         set1.put(artist1);
         album1.setName("Album");
         set1.put(album1);
         year1.setName("Year");
         year1.setValue("suppressCustomEditor", Boolean.TRUE);
         set1.put(year1);
         comment1.setName("Comment");
         set1.put(comment1);
         genre1.setName("Genre");
         genre1.setValue("suppressCustomEditor", Boolean.TRUE);
         genre1.setPropertyEditorClass(GenrePropertyEditor.class);
         set1.put(genre1);

         Property album2  = new PropertySupport.Reflection<String>(id3v2, String.class, "getAlbum", null);
         Property artist2 = new PropertySupport.Reflection<String>(id3v2, String.class, "getArtist", null);
         Property group2  = new PropertySupport.Reflection<String>(id3v2, String.class, "getGroup", null);
         Property track2  = new PropertySupport.Reflection<String>(id3v2, String.class, "getTrack", null);

         album2.setName("Album");
         set2.put(album2);
         artist2.setName("Artist");
         set2.put(artist2);
         group2.setName("Group");
         set2.put(group2);
         track2.setName("Track");
         set2.put(track2);

      } catch (Exception e) {
         Logger.getLogger(Mp3DataNode.class.getName()).severe(e.toString());
      }

      sheet.put(set1);
      sheet.put(set2);

      return sheet;
   }

   @Override
   public Transferable drag() {
      return this;
   }

   @Override
   public DataFlavor[] getTransferDataFlavors() {
      return new DataFlavor[]{DATA_FLAVOR};
   }

   @Override
   public boolean isDataFlavorSupported(DataFlavor flavor) {
      return flavor == DATA_FLAVOR;
   }

   @Override
   public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
      if(flavor == DATA_FLAVOR) {
         return this;
      } else {
         throw new UnsupportedFlavorException(flavor);
      }
   }
}
