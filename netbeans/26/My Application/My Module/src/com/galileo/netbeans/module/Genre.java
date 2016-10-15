package com.galileo.netbeans.module;

public class Genre {
   
   private Integer id;
   private String  genre;

   public Genre(Integer id, String genre) {
      this.id    = id;
      this.genre = genre;
   }
   
   public Integer getId() {
      return id;
   }
   
   public String getGenre() {
      return genre;
   }
   
   @Override
   public String toString() {
      return genre;
   }
   
   @Override
   public boolean equals(Object obj) {
      if(obj instanceof Genre) {
         if(((Genre)obj).getId() == id) {
            return true;
         }
      }
      return false;
   }

   @Override
   public int hashCode() {
      int hash = 7;
      hash = 97 * hash + this.id;
      return hash;
   }
}
