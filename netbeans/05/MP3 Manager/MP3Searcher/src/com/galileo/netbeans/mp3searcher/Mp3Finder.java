/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.galileo.netbeans.mp3searcher;

import com.galileo.netbeans.mp3object.Mp3FileObject;
import java.util.List;

/**
 *
 * @author Heiko
 */
public interface Mp3Finder {
    
    public List<Mp3FileObject> find(String what);

}
