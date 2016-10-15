package com.galileo.netbeans.module;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.openide.WizardDescriptor;
import org.openide.util.NbBundle;

public class PlaylistWizardDescriptor extends WizardDescriptor{
   
   private PlaylistWizardPanel1 panel1 = new PlaylistWizardPanel1();
   private PlaylistWizardPanel2 panel2 = new PlaylistWizardPanel2();

   public PlaylistWizardDescriptor() {
      List<Panel<WizardDescriptor>> panels = new ArrayList<Panel<WizardDescriptor>>();
      panels.add(panel1);
      panels.add(panel2);
      this.setPanelsAndSettings(new ArrayIterator<WizardDescriptor>(panels), this);

      // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName()
      this.setTitleFormat(new MessageFormat("{0}"));
      this.setTitle(NbBundle.getBundle(PlaylistWizardDescriptor.class).getString("Wizard.Name"));

      putProperty(WizardDescriptor.PROP_CONTENT_DATA, new String[]{panel1.getName(), panel2.getName()});
      putProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, Boolean.TRUE);
      putProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, Boolean.TRUE);
      putProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, Boolean.TRUE);
   }
}
