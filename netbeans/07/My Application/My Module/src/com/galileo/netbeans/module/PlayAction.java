/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;

@ActionID(category = "File",
id = "com.galileo.netbeans.module.PlayAction")
@ActionRegistration(iconBase = "com/galileo/netbeans/module/play.png",
displayName = "#CTL_PlayAction")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1200),
    @ActionReference(path = "Toolbars/File", position = 300),
    @ActionReference(path = "Loaders/audio/mpeg/Actions", position = 50, separatorAfter=60)
})
public final class PlayAction implements ActionListener {

    private final PlayInterface context;

    public PlayAction(PlayInterface context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        context.play();
    }
}
