package com.galileo.netbeans.module.items;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import org.openide.text.ActiveEditorDrop;

public abstract class ManifestItem implements ActiveEditorDrop {

   public abstract String getItem();

   public boolean handleTransfer(JTextComponent editor) {
      try {
         Document doc = editor.getDocument();
         doc.insertString(editor.getCaretPosition(), getItem() + "\n", null);
      } catch (BadLocationException ex) {
         Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, null, ex);
      }
      return true;
   }
}
