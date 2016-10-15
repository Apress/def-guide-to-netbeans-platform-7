/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionID;

@ActionID(category = "Edit",
id = "com.galileo.netbeans.module.PlaylistAction")
@ActionRegistration(displayName = "#CTL_PlaylistAction", iconBase = "com/galileo/netbeans/module/playlists.png")
public final class PlaylistAction implements ActionListener {

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO implement action body
   }
}
