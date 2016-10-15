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
id = "com.galileo.netbeans.module.StopAction")
@ActionRegistration(iconBase = "com/galileo/netbeans/module/stop.png", displayName = "#CTL_StopAction")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1200),
    @ActionReference(path = "Toolbars/File", position = 350)
})
public final class StopAction implements ActionListener {

    private final StopInterface context;

    public StopAction(StopInterface context) {
        this.context = context;
    }

    public void actionPerformed(ActionEvent ev) {
        context.stop();
    }
}
