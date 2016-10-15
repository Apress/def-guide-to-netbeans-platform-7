/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;

@ActionID(category = "File",
id = "com.galileo.netbeans.module.MyDropDownAction2")
@ActionRegistration(iconBase = "com/galileo/netbeans/module/icon.png",
displayName = "#CTL_MyDropDownAction2")
@ActionReferences({
    @ActionReference(path = "MyDropDownActions", position = 1400)
})
public final class MyDropDownAction2 implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }
}
