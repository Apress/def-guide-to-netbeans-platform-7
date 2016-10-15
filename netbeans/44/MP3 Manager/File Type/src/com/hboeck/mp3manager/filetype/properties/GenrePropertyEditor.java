package com.hboeck.mp3manager.filetype.properties;

import com.hboeck.mp3manager.id3.v1.Genres;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import org.openide.explorer.propertysheet.ExPropertyEditor;
import org.openide.explorer.propertysheet.InplaceEditor;
import org.openide.explorer.propertysheet.PropertyEnv;
import org.openide.explorer.propertysheet.PropertyModel;

public class GenrePropertyEditor extends PropertyEditorSupport implements ExPropertyEditor, InplaceEditor.Factory
{
   private InplaceEditor ed = null;

   @Override
   public void attachEnv(PropertyEnv propertyEnv) {
      propertyEnv.registerInplaceEditorFactory(this);
   }

   @Override
   public InplaceEditor getInplaceEditor() {
      if(ed == null)
         ed = new Inplace();
      return ed;
   }
   
   private static class Inplace implements InplaceEditor
   {
      private       PropertyEditor editor = null;
      private       PropertyModel  model  = null;
      private final JComboBox      genres = new JComboBox(Genres.getGenres());

      @Override
      public void connect(PropertyEditor propertyEditor, PropertyEnv propertyEnv) {
         this.editor = propertyEditor;
         reset();
      }

      @Override
      public JComponent getComponent() {
         return this.genres;
      }

      @Override
      public void clear() {
         this.editor = null;
         this.model  = null;
      }

      @Override
      public Object getValue() {
         return this.genres.getSelectedItem();
      }

      @Override
      public void setValue(Object object) {
         this.genres.setSelectedItem(object);
      }

      @Override
      public boolean supportsTextEntry() {
         return true;
      }

      @Override
      public void reset() {
         String genre = (String) editor.getValue();
         if(genre != null)
            this.genres.setSelectedItem(genre);
      }

      @Override
      public void addActionListener(ActionListener actionListener) {
         //not needed
      }

      @Override
      public void removeActionListener(ActionListener actionListener) {
         //not needed
      }

      @Override
      public KeyStroke[] getKeyStrokes() {
         return new KeyStroke[0];
      }

      @Override
      public PropertyEditor getPropertyEditor() {
         return this.editor;
      }

      @Override
      public PropertyModel getPropertyModel() {
         return this.model;
      }

      @Override
      public void setPropertyModel(PropertyModel propertyModel) {
         this.model = propertyModel;
      }

      @Override
      public boolean isKnownComponent(Component component) {
         return component == this.genres || this.genres.isAncestorOf(component);
      }
   }
}
