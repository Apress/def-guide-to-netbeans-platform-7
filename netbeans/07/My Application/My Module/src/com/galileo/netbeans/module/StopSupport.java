/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.galileo.netbeans.module;

/**
 *
 * @author Heiko
 */
public class StopSupport implements StopInterface {

    private Mp3DataObject mp3 = null;

   public StopSupport(Mp3DataObject mp3) {
      this.mp3 = mp3;
   }

   public void stop() {
      System.out.println("stop");
      mp3.playing(false);
  }
}

