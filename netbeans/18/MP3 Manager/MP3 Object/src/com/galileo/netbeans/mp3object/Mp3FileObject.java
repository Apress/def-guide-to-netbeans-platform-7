package com.galileo.netbeans.mp3object;

public class Mp3FileObject {
    
    private String artist = new String();
    private String title  = new String();
    private String year   = new String();
    
    public Mp3FileObject(String artist, String title, String year) {
        this.artist = artist;
        this.title  = title;
        this.year   = year;
    }
    
    public String getArtist() {
        return this.artist;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getYear() {
        return this.year;
    }
}
