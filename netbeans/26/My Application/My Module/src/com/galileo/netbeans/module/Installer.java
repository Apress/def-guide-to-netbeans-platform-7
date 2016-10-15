package com.galileo.netbeans.module;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.openide.modules.ModuleInstall;
import org.openide.util.Exceptions;

public class Installer extends ModuleInstall {
   
   private static Connection conn = null;
   
   @Override
   public void restored() {
      System.setProperty("derby.system.home", System.getProperty("netbeans.user", System.getProperty("user.home")) +  "/databases");
      initTables();
}

   @Override
   public void close() {
      try {
         conn.close();
         DriverManager.getConnection("jdbc:derby:;shutdown=true");
      } catch (SQLException ex) {
      }
   }

   public static Connection getConnection() throws SQLException{
      if(conn == null || conn.isClosed()) {
         conn = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDB;create=true;", "user", "password");
         //conn = DriverManager.getConnection("jdbc:derby:MyDB;create=true", "user", "password");
      }
      
      return conn;
   }

   private void initTables() {
      try {
         Statement stmt = getConnection().createStatement();
         stmt.executeQuery("SELECT id FROM genres");
         stmt.close();
      } catch(SQLException e) {
         try {
            Statement stmt = getConnection().createStatement();
            stmt.execute("CREATE TABLE genres (id INTEGER GENERATED ALWAYS AS IDENTITY, genre VARCHAR(100), PRIMARY KEY(id))");
            stmt.execute("CREATE TABLE albums (id INTEGER GENERATED ALWAYS AS IDENTITY, title VARCHAR(100), tracks VARCHAR(10), cds VARCHAR(10), years VARCHAR(10), genre INTEGER, PRIMARY KEY(id), FOREIGN KEY(genre) REFERENCES genres (id))");
            stmt.execute("INSERT INTO genres (genre) VALUES('Techno, Trance & Dance')");
            stmt.execute("INSERT INTO genres (genre) VALUES('Rock & Pop')");
            stmt.execute("INSERT INTO genres (genre) VALUES('Country & Classic')");
            stmt.close();
         } catch(SQLException ex) {
            Exceptions.printStackTrace(ex);
         }
      }
   }
}
