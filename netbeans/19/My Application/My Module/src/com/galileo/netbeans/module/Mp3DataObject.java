/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.io.IOException;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

public class Mp3DataObject extends MultiDataObject {

   private String artist = "artist";
   private String title  = "title";
   private String genre  = "genre";
   private int    track  = 1;

   public Mp3DataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
      super(pf, loader);

   }

   @Override
   protected Node createNodeDelegate() {
      return new Mp3DataNode(this, getLookup());
   }

   @Override
   public Lookup getLookup() {
      return getCookieSet().getLookup();
   }
   
   
   public String getArtist() {
      return this.artist;
   }
   
   public void setArtist(String artist) {
      this.artist = artist;
   }
   
   public String getTitle() {
      return this.title;
   }
   
   public void setTitle(String title) {
      this.title = title;
   }
   
   public String getGenre() {
      return this.genre;
   }
   
   public void setGenre(String genre) {
      this.genre = genre;
   }
   
   public int getTrack() {
      return this.track;
   }
}
