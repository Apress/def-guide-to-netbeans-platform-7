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
import org.openide.cookies.InstanceCookie;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;

@ActionID(category = "Edit",
id = "com.galileo.netbeans.module.CreateMyClassAction")
@ActionRegistration(displayName = "#CTL_CreateMyClassAction")
@ActionReferences({
    @ActionReference(path = "Menu/Edit", position = 100)
})
public final class CreateMyClassAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
       try {
           FileObject o = FileUtil.getConfigFile("MyClass.instance");
           DataObject d = DataObject.find(o);
           InstanceCookie c = d.getLookup().lookup(InstanceCookie.class);
           MyClass mc = (MyClass)c.instanceCreate();
           mc.doSomething();
       } catch (Exception ex) {
           System.out.println(ex);
       }
    }
}
