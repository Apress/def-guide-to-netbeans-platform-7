/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Action;
import org.openide.modules.ModuleInstall;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.Lookups;

/**
 * Manages a module's lifecycle. Remember that an installer is optional and
 * often not needed at all.
 */
public class Installer extends ModuleInstall {
   
   private static TrayIcon trayIcon;
   
   public static void displayTrayMessage(String caption, String text,  TrayIcon.MessageType messageType) {
      if (trayIcon != null) {
         trayIcon.displayMessage(caption, text, messageType);
      }
   }

   @Override
   public void restored() {
      if (SystemTray.isSupported()) {
         SystemTray tray = SystemTray.getSystemTray();
         
         PopupMenu popup = new PopupMenu();

         for(Action a : Lookups.forPath("TrayMenu").lookupAll(Action.class)) {
            MenuItem item = new MenuItem((String)a.getValue(Action.NAME));
            item.addActionListener(a);
            popup.add(item);
         }
         
         Image image = ImageUtilities.loadImage("com/galileo/netbeans/module/icon.gif");
         trayIcon = new TrayIcon(image, "My Tray Menu", popup);
         
         trayIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               System.out.println("double click");
            }
         });
         
         try {
            tray.add(trayIcon);
         } catch (AWTException e) {
            System.err.println(e);
         }

      }
   }
}
