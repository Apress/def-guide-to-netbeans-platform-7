/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hboeck.mp3manager.player;

import com.hboeck.mp3manager.filetype.Mp3DataObject;
import com.hboeck.mp3manager.services.player.Mp3Player;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;

@ActionID(
        category = "File",
        id = "com.hboeck.mp3manager.player.PlayAction")
@ActionRegistration(
        displayName = "#CTL_PlayAction", 
        iconBase="com/hboeck/mp3manager/player/gui/icons/play16.png")
@ActionReferences({
   @ActionReference(path = "Menu/File", position = 0),
   @ActionReference(path = "Loaders/audio/mpeg/Actions", position = 0)
})
public final class PlayAction implements ActionListener {

   private final Mp3DataObject context;

   public PlayAction(Mp3DataObject context) {
      this.context = context;
   }

   @Override
   public void actionPerformed(ActionEvent ev) {
      Mp3Player.getDefault().play(context);
   }
}
