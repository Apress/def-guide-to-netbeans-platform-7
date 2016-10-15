package com.hboeck.mp3manager.services.player;

import com.hboeck.mp3manager.filetype.Mp3DataObject;
import java.util.ListIterator;
import javax.swing.event.EventListenerList;
import org.openide.util.Lookup;

public abstract class Mp3Player {

   public static Mp3Player getDefault() {
      Mp3Player player = Lookup.getDefault().lookup(Mp3Player.class);
      if (player == null) {
         player = new DefaultMp3Player();
      }

      return player;
   }
   
   private final EventListenerList listeners = new EventListenerList();

   public void addEventListener(Mp3PlayerEventListener l) {
      listeners.add(Mp3PlayerEventListener.class, l);
   }

   public void removeEventListener(Mp3PlayerEventListener l) {
      listeners.remove(Mp3PlayerEventListener.class, l);
   }

   protected final void firePlayEvent(Mp3DataObject mp3) {
      for(Mp3PlayerEventListener listener : listeners.getListeners(Mp3PlayerEventListener.class)) {
         listener.playing(mp3);
      }
   }

   protected final void fireStopEvent() {
      for(Mp3PlayerEventListener listener : listeners.getListeners(Mp3PlayerEventListener.class)) {
         listener.stopped();
      }
   }

   private static final class DefaultMp3Player extends Mp3Player {

      @Override
      public void play(Mp3DataObject mp3) {
         throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public void pause() {
         throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public void stop() {
         throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public void setMute(boolean mute) {
         throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public void setVolume(int volume) {
         throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public int getDuration() {
         throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public int getMediaTime() {
         throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public void setMediaTime(int seconds) {
         throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public void play(ListIterator<Mp3DataObject> mp3s) {
         throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public void previous() {
         throw new UnsupportedOperationException("Not supported yet.");
      }

      @Override
      public void next() {
         throw new UnsupportedOperationException("Not supported yet.");
      }
   }

   public abstract void play(Mp3DataObject mp3);

   public abstract void play(ListIterator<Mp3DataObject> mp3s);

   public abstract void pause();

   public abstract void stop();

   public abstract void previous();

   public abstract void next();

   public abstract void setMute(boolean mute);

   public abstract void setVolume(int volume);

   public abstract int getDuration();

   public abstract int getMediaTime();

   public abstract void setMediaTime(int seconds);
}
