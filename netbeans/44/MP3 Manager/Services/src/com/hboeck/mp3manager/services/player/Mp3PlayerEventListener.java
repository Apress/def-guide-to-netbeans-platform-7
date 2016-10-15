package com.hboeck.mp3manager.services.player;

import com.hboeck.mp3manager.filetype.Mp3DataObject;
import java.util.EventListener;

public interface Mp3PlayerEventListener extends EventListener{
   public void playing(Mp3DataObject mp3);
   public void stopped();
}
