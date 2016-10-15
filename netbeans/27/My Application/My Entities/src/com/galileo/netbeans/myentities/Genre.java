package com.galileo.netbeans.myentities;

public class Genre {
   
   private Integer id;
   private String  genre;
   
   public Genre() {
   }

   public Genre(String genre) {
      this.genre = genre;
   }
   
   public Genre(Integer id, String genre) {
      this.id    = id;
      this.genre = genre;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }
   
   public String getGenre() {
      return genre;
   }
   
   public void setGenre(String genre) {
      this.genre = genre;
   }

   @Override
   public String toString() {
      return genre;
   }

   @Override
   public boolean equals(Object obj) {
      if(obj instanceof Genre) {
         if(((Genre)obj).getGenre().equals(genre)) {
            return true;
         }
      }
      return false;
   }

   @Override
   public int hashCode() {
      int hash = 3;
      hash = 37 * hash + this.genre != null ? this.genre.hashCode() : 0;
      return hash;
   }
}
