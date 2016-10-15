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

@OptionsPanelController.SubRegistration(location = "Advanced",
displayName = "#AdvancedOption_DisplayName_ModuleOptions1",
keywords = "#AdvancedOption_Keywords_ModuleOptions1",
keywordsCategory = "Advanced/ModuleOptions1")
public final class ModuleOptions1PanelController extends OptionsPanelController {

   private ModuleOptions1Panel panel;
   private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
   private boolean changed;

   @Override
   public void update() {
      getPanel().load();
      changed = false;
   }

   @Override
   public void applyChanges() {
      getPanel().store();
      changed = false;
   }

   @Override
   public void cancel() {
      // need not do anything special, if no changes have been persisted yet
   }

   @Override
   public boolean isValid() {
      return getPanel().valid();
   }

   @Override
   public boolean isChanged() {
      return changed;
   }

   @Override
   public HelpCtx getHelpCtx() {
      return null; // new HelpCtx("...ID") if you have a help set
   }

   @Override
   public JComponent getComponent(Lookup masterLookup) {
      return getPanel();
   }

   @Override
   public void addPropertyChangeListener(PropertyChangeListener l) {
      pcs.addPropertyChangeListener(l);
   }

   @Override
   public void removePropertyChangeListener(PropertyChangeListener l) {
      pcs.removePropertyChangeListener(l);
   }

   private ModuleOptions1Panel getPanel() {
      if (panel == null) {
         panel = new ModuleOptions1Panel(this);
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
