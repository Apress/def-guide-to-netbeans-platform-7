package com.galileo.netbeans.module;

import com.galileo.netbeans.myentities.Genre;
import com.galileo.netbeans.myentities.Album;
import java.util.List;
import javax.persistence.Query;

public class DataModel {

   @SuppressWarnings("unchecked")
   public static List<Album> getAlbums() {
      Installer.EM.getTransaction().begin();
      Query q = Installer.EM.createQuery("SELECT a FROM Album a");
      List<Album> list = q.getResultList();
      Installer.EM.getTransaction().commit();

      return list;
   }

   @SuppressWarnings("unchecked")
   public static List<Genre> getGenres() {
      Installer.EM.getTransaction().begin();
      Query q = Installer.EM.createQuery("SELECT g FROM Genre g");
      List<Genre> list = q.getResultList();
      Installer.EM.getTransaction().commit();

      return list;
   }

   public static void updateAlbum(Album album) {
      Installer.EM.getTransaction().begin();
      Installer.EM.persist(album);
      Installer.EM.getTransaction().commit();
   }

   public static void insertAlbum(Album album) {
      updateAlbum(album);
   }

   public static void deleteAlbum(Album album) {
      Installer.EM.getTransaction().begin();
      Installer.EM.remove(album);
      Installer.EM.getTransaction().commit();
   }
}
