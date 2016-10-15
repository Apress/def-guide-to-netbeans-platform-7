/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.galileo.netbeans.mp3searcher;

import com.galileo.netbeans.mp3object.Mp3FileObject;
import java.util.Collections;
import java.util.List;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Heiko
 */
@ServiceProvider(
   service = Mp3Finder.class,
   path = "Mp3FinderServices",
   position = 10,
   supersedes = {"com.galileo.netbeans.module.DefaultMp3Finder"})
public class Mp3DatabaseFinder implements Mp3Finder {

   @Override
   public List<Mp3FileObject> find(String what) {
      return Collections.EMPTY_LIST;
   }
}
