package com.hboeck.mp3manager.player;

import com.hboeck.mp3manager.filetype.Mp3DataObject;
import com.hboeck.mp3manager.services.player.Mp3Player;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.GainControl;
import javax.media.Manager;
import javax.media.Player;
import javax.media.RealizeCompleteEvent;
import javax.media.Time;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service=Mp3Player.class)
public class Mp3PlayerImpl extends Mp3Player implements ControllerListener {

   private static final Logger LOG = Logger.getLogger(Mp3PlayerImpl.class.getName());

   private Player      player        = null;
   private GainControl volumeControl = null;

   private int     volume = 20;
   private boolean mute   = false;

   private Mp3DataObject               mp3  = null;
   private ListIterator<Mp3DataObject> list = null;

   public Mp3PlayerImpl() {
   }

   @Override
   public void play(Mp3DataObject mp3) {
      try {
         this.mp3 = mp3;
         if (player != null) {
            player.stop();
            player.close();
         }
         player = Manager.createPlayer(mp3.getPrimaryFile().getURL());
         player.addControllerListener(this);
         player.start();
      } catch (Exception e) {
         LOG.log(Level.SEVERE, e.getMessage(), e);
      }
   }

   @Override
   public void play(ListIterator<Mp3DataObject> mp3s) {
      list = mp3s;
      if (list.hasNext()) {
         play(list.next());
      }
   }

   @Override
   public void pause() {
      if (player != null) {
         player.stop();
      }
   }

   @Override
   public void stop() {
      if (player != null) {
         fireStopEvent();
         player.stop();
         player.setMediaTime(new Time(0));
         player.close();
      }
   }

   @Override
   public void controllerUpdate(ControllerEvent evt) {
      if (evt instanceof RealizeCompleteEvent) {
         LOG.info("Realized");
         firePlayEvent(mp3);
         volumeControl = player.getGainControl();
         setVolume(volume);
         setMute(mute);
      } else if (evt instanceof EndOfMediaEvent) {
         LOG.info("End of Media");
         stop();

         if (list != null && list.hasNext()) {
            play(list.next());
         } else {
            list = null;
         }
      }
   }

   @Override
   public void setVolume(int volume) {
      this.volume = volume;
      if (volumeControl != null) {
         volumeControl.setLevel((float) (volume/100.0));
      }
   }

   @Override
   public void setMute(boolean mute) {
      this.mute = mute;
      if (volumeControl != null) {
         volumeControl.setMute(mute);
      }
   }

   @Override
   public int getDuration() {
      return (int) player.getDuration().getSeconds();
   }

   @Override
   public int getMediaTime() {
      return (int) player.getMediaTime().getSeconds();
   }

   @Override
   public void setMediaTime(int seconds) {
      player.setMediaTime(new Time((double) seconds));
   }

   @Override
   public void previous() {
      if (list != null && list.hasPrevious()) {
         play(list.previous());
      }
   }

   @Override
   public void next() {
      if (list != null && list.hasNext()) {
         play(list.next());
      }
   }
}
