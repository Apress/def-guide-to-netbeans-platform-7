/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionID;
import org.openide.awt.Notification;
import org.openide.awt.NotificationDisplayer;
import org.openide.awt.StatusDisplayer;
import org.openide.util.ImageUtilities;
import org.openide.util.lookup.Lookups;

@ActionID(category = "File",
id = "com.galileo.netbeans.module.MyDropDownAction1")
@ActionRegistration(displayName = "#CTL_MyDropDownAction1")
@ActionReference(path = "MyDropDownActions", position = 1300)
public final class MyDropDownAction1 implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        StatusDisplayer.Message msg = StatusDisplayer.getDefault().setStatusText("MyDropDownAction1", 100);
        msg.clear(5000);

        Notification noti = NotificationDisplayer.getDefault().notify(
                "Meine erste Benachrichtigung", 
                ImageUtilities.loadImageIcon("com/galileo/netbeans/module/info16.png", true), 
                "... die nach ein paar Sekunden wieder verschwindet",
                Lookups.forPath("NotificationActions").lookup(ActionListener.class),
                NotificationDisplayer.Priority.SILENT);
    }
}
