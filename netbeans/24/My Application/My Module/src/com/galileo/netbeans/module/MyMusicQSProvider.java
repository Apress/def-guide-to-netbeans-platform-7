/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.KeyStroke;
import org.netbeans.spi.quicksearch.SearchProvider;
import org.netbeans.spi.quicksearch.SearchRequest;
import org.netbeans.spi.quicksearch.SearchResponse;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.loaders.DataShadow;
import org.openide.util.Exceptions;

public class MyMusicQSProvider implements SearchProvider {

   private DataFolder f;
   
   public MyMusicQSProvider() {
      FileObject fo = FileUtil.getConfigFile("Favorites");
      f = DataFolder.findFolder(fo);
   }

   /**
    * Method is called by infrastructure when search operation was requested.
    * Implementors should evaluate given request and fill response object with
    * apropriate results
    *
    * @param request Search request object that contains information what to search for
    * @param response Search response object that stores search results. Note that it's important to react to return value of SearchResponse.addResult(...) method and stop computation if false value is returned.
    */
   @Override
   public void evaluate(SearchRequest request, SearchResponse response) {
      for (DataObject data : f.getChildren()) {

         if (data instanceof DataShadow) {
            DataShadow obj = (DataShadow) data;

            for (final FileObject child : obj.getOriginal().getPrimaryFile().getChildren()) {
               if (child.getName().toLowerCase().contains(request.getText().toLowerCase()) && child.getExt().toLowerCase().equals("mp3")) {
                  if (!response.addResult(new AddToPlaylist(child), child.getName())) {
                     return;
                  }
               }
            }
         }
      }
   }
   
   private static final class AddToPlaylist implements Runnable {
      
      private FileObject fo;
   
      public AddToPlaylist(FileObject fo) {
         this.fo = fo;
      }

      @Override
      public void run() {
         try {
            PlaylistTopComponent.addFile(DataObject.find(fo).getNodeDelegate());
         } catch (DataObjectNotFoundException ex) {
            Exceptions.printStackTrace(ex);
         }
      }
   }
}
