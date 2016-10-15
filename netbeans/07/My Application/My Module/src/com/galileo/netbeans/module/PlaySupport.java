package com.galileo.netbeans.module;

public class PlaySupport implements PlayInterface {
   
   private Mp3DataObject mp3 = null;

   public PlaySupport(Mp3DataObject mp3) {
      this.mp3 = mp3;
   }

    @Override
    public void play() {
       System.out.println("Play: " + mp3.getPrimaryFile().getName());
       mp3.playing(true);
    }
}
