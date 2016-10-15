/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPopupMenu;
import org.openide.awt.ActionRegistration;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionID;
import org.openide.awt.DropDownButtonFactory;
import org.openide.util.ImageUtilities;
import org.openide.util.Utilities;
import org.openide.util.actions.Presenter;

@ActionID(category = "File",
id = "com.galileo.netbeans.module.MyDropDownButton")
@ActionRegistration(iconBase = "com/galileo/netbeans/module/icon.png",
displayName = "#CTL_MyDropDownButton")
@ActionReferences({
    @ActionReference(path = "Toolbars/File", position = 300)
})
public final class MyDropDownButton extends AbstractAction implements Presenter.Toolbar {
    
    final String EXTENSION_POINT = "MyDropDownActions";
    JPopupMenu popup = new JPopupMenu();

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
    }

    @Override
    public Component getToolbarPresenter() {
        for (Action a : Utilities.actionsForPath(EXTENSION_POINT))
            popup.add(a);

        return DropDownButtonFactory.createDropDownButton(ImageUtilities.loadImageIcon("com/galileo/netbeans/module/icon24.png", false), popup);
    }
}
