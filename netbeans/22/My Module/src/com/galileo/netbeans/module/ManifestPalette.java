package com.galileo.netbeans.module;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.netbeans.spi.palette.PaletteActions;
import org.netbeans.spi.palette.PaletteController;
import org.netbeans.spi.palette.PaletteFactory;
import org.openide.util.Lookup;

public class ManifestPalette {

   private static PaletteController palette;
   private static final String MANIFEST_PALETTE = "ManifestPalette";

   public static PaletteController createPalette() {
      try {
         if (palette == null) {
            palette = PaletteFactory.createPalette(MANIFEST_PALETTE, new MyPaletteActions());
         }
         return palette;
      } catch (Exception ex) {
         Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, null, ex);
      }
      return null;
   }

   private static final class MyPaletteActions extends PaletteActions {

      @Override
      public Action[] getImportActions() {
         return new Action[]{};
      }

      @Override
      public Action[] getCustomPaletteActions() {
         return new Action[]{};
      }

      @Override
      public Action[] getCustomCategoryActions(Lookup arg0) {
         return new Action[]{};
      }

      @Override
      public Action[] getCustomItemActions(Lookup arg0) {
         return new Action[]{};
      }

      @Override
      public Action getPreferredAction(Lookup arg0) {
         return new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
               throw new UnsupportedOperationException("Not supported yet.");
            }
         };
      }
   }
}
