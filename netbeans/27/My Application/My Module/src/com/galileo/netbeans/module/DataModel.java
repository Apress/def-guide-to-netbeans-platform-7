package com.galileo.netbeans.module;

import com.galileo.netbeans.myentities.Genre;
import com.galileo.netbeans.myentities.Album;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DataModel {

   @SuppressWarnings("unchecked")
   public static List<Album> getAlbums() {
      Session s = Installer.currentSession();
      Transaction t = s.beginTransaction();
      List<Album> list = (List<Album>)s.createCriteria(Album.class).list();
      t.commit();

      return list;
   }
   
   @SuppressWarnings("unchecked")
   public static List<Genre> getGenres() {
      Session s = Installer.currentSession();
      Transaction t = s.beginTransaction();
      List<Genre> list = (List<Genre>)s.createCriteria(Genre.class).list();
      t.commit();
      
      return list;
   }

   public static void updateAlbum(Album album) {
      Session s = Installer.currentSession();
      Transaction t = s.beginTransaction();
      s.update(album);
      t.commit();
   }

   public static void insertAlbum(Album album) {
      Session s = Installer.currentSession();
      Transaction t = s.beginTransaction();
      s.save(album);
      t.commit();
   }

   public static void deleteAlbum(Album album) {
      Session s = Installer.currentSession();
      Transaction t = s.beginTransaction();
      s.delete(album);
      t.commit();
   }
}
