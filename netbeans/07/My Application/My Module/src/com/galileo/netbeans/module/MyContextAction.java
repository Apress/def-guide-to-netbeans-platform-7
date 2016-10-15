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
id = "com.galileo.netbeans.module.MyContextAction")
@ActionRegistration(displayName = "#CTL_MyContextAction")
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1300)
})
public final class MyContextAction implements ActionListener {

    private final Mp3DataObject context;

    public MyContextAction(Mp3DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        PlayInterface p = context.getLookup().lookup(PlayInterface.class);
        p.play();
    }
}
