/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import com.galileo.netbeans.myentities.Album;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//com.galileo.netbeans.module//Albums//EN",
autostore = false)
@TopComponent.Description(preferredID = "AlbumsTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "explorer", openAtStartup = true)
@ActionID(category = "Window", id = "com.galileo.netbeans.module.AlbumsTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_AlbumsAction",
preferredID = "AlbumsTopComponent")
public final class AlbumsTopComponent extends TopComponent {
   
   private AlbumTableModel model = new AlbumTableModel();

   public AlbumsTopComponent() {
      initComponents();
      setName(NbBundle.getMessage(AlbumsTopComponent.class, "CTL_AlbumsTopComponent"));
      setToolTipText(NbBundle.getMessage(AlbumsTopComponent.class, "HINT_AlbumsTopComponent"));

      albums.setModel(model);
      albums.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent event) {
            if(event.getClickCount() == 2) {
               editAlbumActionPerformed(null);
            }
         }
      });
   }

   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
      // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      scrollPane = new javax.swing.JScrollPane();
      albums = new javax.swing.JTable();
      editAlbum = new javax.swing.JButton();
      newAlbum = new javax.swing.JButton();
      deleteAlbum = new javax.swing.JButton();

      albums.setAutoCreateRowSorter(true);
      scrollPane.setViewportView(albums);

      org.openide.awt.Mnemonics.setLocalizedText(editAlbum, org.openide.util.NbBundle.getMessage(AlbumsTopComponent.class, "AlbumsTopComponent.editAlbum.text")); // NOI18N
      editAlbum.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            editAlbumActionPerformed(evt);
         }
      });

      org.openide.awt.Mnemonics.setLocalizedText(newAlbum, org.openide.util.NbBundle.getMessage(AlbumsTopComponent.class, "AlbumsTopComponent.newAlbum.text")); // NOI18N
      newAlbum.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            newAlbumActionPerformed(evt);
         }
      });

      org.openide.awt.Mnemonics.setLocalizedText(deleteAlbum, org.openide.util.NbBundle.getMessage(AlbumsTopComponent.class, "AlbumsTopComponent.deleteAlbum.text")); // NOI18N
      deleteAlbum.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            deleteAlbumActionPerformed(evt);
         }
      });

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 400, Short.MAX_VALUE)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(deleteAlbum)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                  .addComponent(newAlbum)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(editAlbum)))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 300, Short.MAX_VALUE)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(editAlbum)
               .addComponent(newAlbum)
               .addComponent(deleteAlbum))
            .addContainerGap())
      );
   }// </editor-fold>//GEN-END:initComponents

   private void editAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editAlbumActionPerformed
      if (albums.getSelectedRowCount() > 0) {
         Album album = AlbumEditDialog.editAlbum(model.getRow(albums.getSelectedRow()));
         if(album != null) {
            DataModel.updateAlbum(album);
            model.fireTableDataChanged();
         }
      }
}//GEN-LAST:event_editAlbumActionPerformed

   private void newAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newAlbumActionPerformed
      Album album = AlbumEditDialog.newAlbum();
      if(album != null) {
            DataModel.insertAlbum(album);
            model.getData().add(album);
            model.fireTableDataChanged();
      }
}//GEN-LAST:event_newAlbumActionPerformed

   private void deleteAlbumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAlbumActionPerformed
      if (albums.getSelectedRowCount() > 0) {
         Album album = model.getRow(albums.getSelectedRow());
         NotifyDescriptor d = new NotifyDescriptor.Confirmation(
                 "Are you sure you want delete the album " + album.getTitle(),
                 "Confirm Album Deletion");
         if(DialogDisplayer.getDefault().notify(d) == NotifyDescriptor.YES_OPTION) {
            DataModel.deleteAlbum(album);
            model.getData().remove(album);
            model.fireTableDataChanged();
         }
      }
}//GEN-LAST:event_deleteAlbumActionPerformed

      // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JTable albums;
   private javax.swing.JButton deleteAlbum;
   private javax.swing.JButton editAlbum;
   private javax.swing.JButton newAlbum;
   private javax.swing.JScrollPane scrollPane;
   // End of variables declaration//GEN-END:variables

   @Override
   public void componentOpened() {
      model.getData().clear();
      model.getData().addAll(DataModel.getAlbums());
      model.fireTableDataChanged();
   }

   @Override
   public void componentClosed() {
      // TODO add custom code on component closing
   }

   void writeProperties(java.util.Properties p) {
      // better to version settings since initial version as advocated at
      // http://wiki.apidesign.org/wiki/PropertyFiles
      p.setProperty("version", "1.0");
      // TODO store your settings
   }

   void readProperties(java.util.Properties p) {
      String version = p.getProperty("version");
      // TODO read your settings according to their version
   }
   
   private static final class AlbumTableModel extends AbstractTableModel {
      private String[] columns = {"Title", "Tracks", "CDs", "Year"};
      private List<Album> data = new ArrayList<Album>();

      public Album getRow(int row) {
         return data.get(row);
      }
      
      @Override
      public int getRowCount() {
         return data.size();
      }
      
      @Override
      public int getColumnCount() {
         return columns.length;
      }
      
      @Override
      public String getColumnName(int col) {
         return columns[col];
      }
      
      @Override
      public Object getValueAt(int row, int col) {
         Album album = data.get(row);
         
         switch(col) {
            case 0: return album.getTitle();
            case 1: return album.getTracks();
            case 2: return album.getCds();
            case 3: return album.getYear();
         }
         return "";
      }
      
      public List<Album> getData() {
         return data;
      }
   }
}
