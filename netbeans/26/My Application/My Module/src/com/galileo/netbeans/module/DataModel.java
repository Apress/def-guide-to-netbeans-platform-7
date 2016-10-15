package com.galileo.netbeans.module;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.openide.util.Exceptions;

public class DataModel {

   public static List<Album> getAlbums() {
      List<Album> albums = new ArrayList<Album>();

      try {
         Statement stmt = Installer.getConnection().createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM albums INNER JOIN genres ON albums.genre = genres.id");
         while(rs.next()) {
            Album album = new Album(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            album.setGenre(new Genre(rs.getInt(7), rs.getString(8)));
            albums.add(album);
         }
         rs.close();
         stmt.close();
      } catch(SQLException e) {
         Exceptions.printStackTrace(e);
      }
      
      return albums;
   }
   
   public static List<Genre> getGenres() {
      List<Genre> genres = new ArrayList<Genre>();

      try {
         Statement stmt = Installer.getConnection().createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM genres");
         while(rs.next()) {
            genres.add(new Genre(rs.getInt(1), rs.getString(2)));
         }
         rs.close();
         stmt.close();
      } catch(Exception e) {
         Exceptions.printStackTrace(e);
      }
      
      return genres;
   }

   public static void updateAlbum(Album album) throws SQLException {
      PreparedStatement stmt = Installer.getConnection().prepareStatement("UPDATE albums SET title=?, tracks=?, cds=?, years=?, genre=? WHERE id=?");
      stmt.setString(1, album.getTitle());
      stmt.setString(2, album.getTracks());
      stmt.setString(3, album.getCds());
      stmt.setString(4, album.getYear());
      stmt.setInt(5, album.getGenre().getId());
      stmt.setInt(6, album.getId());
      stmt.execute();
   }
   
   public static void insertAlbum(Album album) throws SQLException {
      PreparedStatement stmt = Installer.getConnection().prepareStatement("INSERT INTO albums (title, tracks, cds, years, genre) VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

      stmt.setString(1, album.getTitle());
      stmt.setString(2, album.getTracks());
      stmt.setString(3, album.getCds());
      stmt.setString(4, album.getYear());
      stmt.setInt(5, album.getGenre().getId());
      stmt.execute();
      
      ResultSet rs = stmt.getGeneratedKeys(); rs.next();
      album.setId(rs.getInt(1));
   }
   
   public static void deleteAlbum(Album album) throws SQLException {
      PreparedStatement stmt = Installer.getConnection().prepareStatement("DELETE FROM albums WHERE id = ?");
      stmt.setInt(1, album.getId());
      stmt.execute();
   }
}
