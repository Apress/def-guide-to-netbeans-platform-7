package com.galileo.netbeans.module;

import java.awt.Desktop;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.awt.HtmlBrowser;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider (service = HtmlBrowser.URLDisplayer.class, position = 0)
public class ExternalURLDisplayer extends HtmlBrowser.URLDisplayer{

   @Override
   public void showURL(URL url) {
      try {
         Desktop.getDesktop().browse(url.toURI());
      } catch(Exception ex) {
         Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, null, ex);
      }
   }
}
