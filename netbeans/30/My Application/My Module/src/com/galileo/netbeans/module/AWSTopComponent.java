/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import com.amazon.advertising.api.AWSECommerceService;
import com.amazon.advertising.api.AWSECommerceServicePortType;
import com.amazon.advertising.api.ImageSet;
import com.amazon.advertising.api.Item;
import com.amazon.advertising.api.ItemLookup;
import com.amazon.advertising.api.ItemLookupRequest;
import com.amazon.advertising.api.ItemLookupResponse;
import java.awt.FlowLayout;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingWorker;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//com.galileo.netbeans.module//AWS//EN",
autostore = false)
@TopComponent.Description(preferredID = "AWSTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "explorer", openAtStartup = true)
@ActionID(category = "Window", id = "com.galileo.netbeans.module.AWSTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_AWSAction",
preferredID = "AWSTopComponent")
public final class AWSTopComponent extends TopComponent {
   
   private static final String AWS_KEY = "1FE60E2PTDEN6TA8R6G2";
   private static final String SEC_KEY = "tPjJZUkRUwn/sJpPVV4TFXBOlJbYBlIA6wXR52z/";

   public AWSTopComponent() {
      initComponents();
      setName(NbBundle.getMessage(AWSTopComponent.class, "CTL_AWSTopComponent"));
      setToolTipText(NbBundle.getMessage(AWSTopComponent.class, "HINT_AWSTopComponent"));

   }

   private final class ImageLookupByASIN 
           extends SwingWorker<String, Object> {

      private String asin = new String();

      public ImageLookupByASIN(String asin) {
         this.asin = asin;
      }

      @Override
      public String doInBackground() {
         String url = new String();

         try {
            AWSECommerceService service = new AWSECommerceService();
            service.setHandlerResolver(new AWSHandlerResolver(SEC_KEY));
            AWSECommerceServicePortType port = service.getAWSECommerceServicePort();

            ItemLookupRequest request = new ItemLookupRequest();
            request.setIdType("ASIN");
            request.getItemId().add(asin);
            request.getResponseGroup().add("Images");

            ItemLookup il = new ItemLookup();
            il.setAWSAccessKeyId(AWS_KEY);
            il.getRequest().add(request);

            ItemLookupResponse response = port.itemLookup(il);

            if(response.getItems().get(0).getItem().size() > 0) {
               Item i = response.getItems().get(0).getItem().get(0);
               if(i.getImageSets().get(0).getImageSet().size() > 0) {
                  ImageSet is = i.getImageSets().get(0).getImageSet().get(0);
                  url = is.getThumbnailImage().getURL();
               } else {
                  System.out.println("no image set found for product: " + asin);
               }
            } else {
               System.out.println("no product found for ASIN: " + asin);
            }
         } catch (Exception e) {
            // TODO handle custom exceptions here
            Exceptions.printStackTrace(e);
         }

         return url;
      }

      @Override
      protected void done() {
         try {
            cover.add(new JLabel(new ImageIcon(new URL(get()))), FlowLayout.LEFT);
            cover.updateUI();
         } catch (Exception e) {
            Exceptions.printStackTrace(e);
         }
      }
   }
   
   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
      // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      search = new javax.swing.JButton();
      jSeparator1 = new javax.swing.JSeparator();
      cover = new javax.swing.JPanel();
      asin = new javax.swing.JTextField();
      jLabel1 = new javax.swing.JLabel();

      org.openide.awt.Mnemonics.setLocalizedText(search, org.openide.util.NbBundle.getMessage(AWSTopComponent.class, "AWSTopComponent.search.text")); // NOI18N
      search.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            searchActionPerformed(evt);
         }
      });

      cover.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(AWSTopComponent.class, "AWSTopComponent.cover.border.title"))); // NOI18N

      org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(AWSTopComponent.class, "AWSTopComponent.jLabel1.text")); // NOI18N

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 400, Short.MAX_VALUE)
         .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(cover, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
               .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
               .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                  .addComponent(jLabel1)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(asin, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(search)))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGap(0, 300, Short.MAX_VALUE)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel1)
               .addComponent(search)
               .addComponent(asin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(cover, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
            .addContainerGap())
      );
   }// </editor-fold>//GEN-END:initComponents

   private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
      new ImageLookupByASIN(asin.getText()).execute();
}//GEN-LAST:event_searchActionPerformed

      // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JTextField asin;
   private javax.swing.JPanel cover;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JSeparator jSeparator1;
   private javax.swing.JButton search;
   // End of variables declaration//GEN-END:variables
   @Override
   public void componentOpened() {
      // TODO add custom code on component opening
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
}
