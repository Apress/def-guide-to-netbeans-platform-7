/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

public class PlaylistWizardPanel2 implements WizardDescriptor.Panel<WizardDescriptor> {

   private PlaylistVisualPanel2 view = null;

   @Override
   public PlaylistVisualPanel2 getComponent() {
      if(view == null) {
         view = new PlaylistVisualPanel2();
         view.putClientProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, new Integer(1));
         view.putClientProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, Boolean.TRUE);
         view.putClientProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, Boolean.TRUE);
         view.putClientProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, Boolean.TRUE);
      }

      return view;
   }

   public String getName() {
      return NbBundle.getBundle(PlaylistWizardPanel2.class).getString("Panel2.Name");
   }
   
   @Override
   public HelpCtx getHelp() {
      return HelpCtx.DEFAULT_HELP;
   }
   
   @Override
   public boolean isValid() {
      return true;
   }

   @Override
   public final void addChangeListener(ChangeListener l) {}
   @Override
   public final void removeChangeListener(ChangeListener l) {}

   @Override
   public void readSettings(WizardDescriptor model) {
   }

   @Override
   public void storeSettings(WizardDescriptor model) {
      model.putProperty(PlaylistVisualPanel2.PROP_TRACKS, getComponent().getTracks());
   }
}
