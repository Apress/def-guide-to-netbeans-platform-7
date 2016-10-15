/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.galileo.netbeans.module;

import org.openide.windows.OutputEvent;
import org.openide.windows.OutputListener;

/**
 *
 * @author Heiko
 */
public class MyHyperlinkListener implements OutputListener {

   @Override
   public void outputLineSelected(OutputEvent ev) {
      // Aktion für Selektieren des Links / der Zeile
   }

   @Override
   public void outputLineAction(OutputEvent ev) {
      // Aktion für Klick auf den Link
   }

   @Override
   public void outputLineCleared(OutputEvent ev) {
      // Aktion beim Löschen des Links / der Zeile
   }
   
}
