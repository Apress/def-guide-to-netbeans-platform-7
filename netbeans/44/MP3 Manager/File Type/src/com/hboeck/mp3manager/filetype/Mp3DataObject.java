/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hboeck.mp3manager.filetype;

import com.hboeck.mp3manager.id3.v1.ID3v1Tag;
import com.hboeck.mp3manager.id3.v2.ID3v2Tag;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.nodes.Node;
import org.openide.util.Lookup;

public class Mp3DataObject extends MultiDataObject {

   private ID3v1Tag id3v1 = null;
   private ID3v2Tag id3v2 = null;
   
   public Mp3DataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
      super(pf, loader);

   }

   public ID3v1Tag getID3v1Tag() {
      if(id3v1 == null) {
         id3v1 = new ID3v1Tag(FileUtil.toFile(getPrimaryFile()));
      }
      return id3v1;
   }
   public ID3v2Tag getID3v2Tag() {
      if(id3v2 == null) {
         id3v2 = new ID3v2Tag(FileUtil.toFile(getPrimaryFile()));
      }
      return id3v2;
   }

   
   @Override
   protected Node createNodeDelegate() {
      return new Mp3DataNode(this, getLookup());
   }

   @Override
   public Lookup getLookup() {
      return getCookieSet().getLookup();
   }
   
   public static Mp3DataObject find(File file) throws DataObjectNotFoundException, FileNotFoundException {

      FileObject fo = FileUtil.toFileObject(file);
      if(fo == null) {
          System.out.println(file.getAbsolutePath());
         throw new FileNotFoundException();
      }
      DataObject data = find(fo);
      if(data instanceof Mp3DataObject) {
         return((Mp3DataObject)data);
      } else {
         throw new DataObjectNotFoundException(fo);
      }
   }

   public static Mp3DataObject find(String path) throws DataObjectNotFoundException, FileNotFoundException {
      return(find(new File(path)));
   }
}
