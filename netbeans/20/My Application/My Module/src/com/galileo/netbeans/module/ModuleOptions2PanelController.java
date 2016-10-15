/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JComponent;
import org.netbeans.spi.options.OptionsPanelController;
import org.openide.util.HelpCtx;
import org.openide.util.Lookup;

@OptionsPanelController.TopLevelRegistration(categoryName = "#OptionsCategory_Name_ModuleOptions2",
iconBase = "com/galileo/netbeans/module/icon.png",
keywords = "#OptionsCategory_Keywords_ModuleOptions2",
keywordsCategory = "ModuleOptions2",
id = ModuleOptions2PanelController.ID)
public final class ModuleOptions2PanelController extends OptionsPanelController {
   public static final String ID = "ModuleOptions2";

   private ModuleOptions2Panel panel;
   private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
   private boolean changed;

   public void update() {
      getPanel().load();
      changed = false;
   }

   public void applyChanges() {
      getPanel().store();
      changed = false;
   }

   public void cancel() {
      // need not do anything special, if no changes have been persisted yet
   }

   public boolean isValid() {
      return getPanel().valid();
   }

   public boolean isChanged() {
      return changed;
   }

   public HelpCtx getHelpCtx() {
      return null; // new HelpCtx("...ID") if you have a help set
   }

   public JComponent getComponent(Lookup masterLookup) {
      return getPanel();
   }

   public void addPropertyChangeListener(PropertyChangeListener l) {
      pcs.addPropertyChangeListener(l);
   }

   public void removePropertyChangeListener(PropertyChangeListener l) {
      pcs.removePropertyChangeListener(l);
   }

   private ModuleOptions2Panel getPanel() {
      if (panel == null) {
         panel = new ModuleOptions2Panel(this);
      }
      return panel;
   }

   void changed() {
      if (!changed) {
         changed = true;
         pcs.firePropertyChange(OptionsPanelController.PROP_CHANGED, false, true);
      }
      pcs.firePropertyChange(OptionsPanelController.PROP_VALID, null, null);
   }
}
