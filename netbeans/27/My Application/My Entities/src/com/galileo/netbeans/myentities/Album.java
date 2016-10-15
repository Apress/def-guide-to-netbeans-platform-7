package com.galileo.netbeans.myentities;

public class Album {
   
   private Integer id;
   private String  title;
   private String  tracks;
   private String  cds;
   private String  year;
   private Genre   genre;
   
   public Album() {
      
   }

   public Album(Integer id, String title, String tracks, String cds, String year) {
      this.id     = id;
      this.title  = title;
      this.tracks = tracks;
      this.cds    = cds;
      this.year   = year;

   }
   
   public Integer getId() {
      return id;
   }
   
   public void setId(Integer id) {
      this.id = id;
   }
   
   public String getTitle() {
      return title;
   }
   
   public String getTracks() {
      return tracks;
   }
   
   public String getCds() {
      return cds;
   }
   
   public String getYear() {
      return year;
   }
   
   public Genre getGenre() {
      return genre;
   }
   
   public void setTitle(String title) {
      this.title = title;
   }
   
   public void setTracks(String tracks) {
      this.tracks = tracks;
   }
   
   public void setCds(String cds) {
      this.cds = cds;
   }
   
   public void setYear(String year) {
      this.year = year;
   }
   
   public void setGenre(Genre genre) {
      this.genre = genre;
   }
}
