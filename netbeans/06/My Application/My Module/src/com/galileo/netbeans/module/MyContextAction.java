/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import org.netbeans.api.actions.Editable;

import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;

@ActionID(category = "Edit",
id = "com.galileo.netbeans.module.MyContextAction")
@ActionRegistration(iconBase = "com/galileo/netbeans/module/icon.png",
displayName = "#CTL_MyContextAction")
@ActionReferences({@ActionReference(path="Menu/Edit")})
public final class MyContextAction implements ActionListener {

    private final List<Editable> context;

    public MyContextAction(List<Editable> context) {
        this.context = context;
    }

    public void actionPerformed(ActionEvent ev) {
        for (Editable editable : context) {
            // TODO use editable
        }
    }
}
