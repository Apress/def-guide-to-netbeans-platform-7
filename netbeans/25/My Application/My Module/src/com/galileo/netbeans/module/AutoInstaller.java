/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.galileo.netbeans.module;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import org.netbeans.api.autoupdate.InstallSupport;
import org.netbeans.api.autoupdate.InstallSupport.Installer;
import org.netbeans.api.autoupdate.InstallSupport.Validator;
import org.netbeans.api.autoupdate.OperationContainer;
import org.netbeans.api.autoupdate.OperationContainer.OperationInfo;
import org.netbeans.api.autoupdate.OperationException;
import org.netbeans.api.autoupdate.OperationSupport;
import org.netbeans.api.autoupdate.OperationSupport.Restarter;
import org.netbeans.api.autoupdate.UpdateElement;
import org.netbeans.api.autoupdate.UpdateManager;
import org.netbeans.api.autoupdate.UpdateUnit;
import org.netbeans.api.autoupdate.UpdateUnitProvider;
import org.netbeans.api.autoupdate.UpdateUnitProviderFactory;
import org.openide.awt.NotificationDisplayer;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.RequestProcessor;

/**
 *
 * @author Heiko
 */
public class AutoInstaller implements Runnable {

   private static final Logger LOG = 
           Logger.getLogger(AutoInstaller.class.getName());
   
   @Override
   public void run() {
      RequestProcessor.getDefault().post(new AutoInstallerImpl(), 1000);
   }
   
   private static final class AutoInstallerImpl implements Runnable {
      
      private static final String UC_NAME = "com_galileo_netbeans_module_update_center";

      private List<UpdateElement> install = new ArrayList<UpdateElement>();
      private List<UpdateElement> update = new ArrayList<UpdateElement>();
      private boolean isRestartRequested = false;
   
      @Override
      public void run() {
         /*
         searchNewAndUpdatedModules();

         OperationContainer<InstallSupport> installContainer = 
                 addToContainer(OperationContainer.createForInstall(), install);
         installModules(installContainer);

         OperationContainer<InstallSupport> updateContainer = 
                 addToContainer(OperationContainer.createForUpdate(), update);
         installModules(updateContainer);
          * 
          */
         
         disableModules(Collections.singletonList("com.galileo.netbeans.module3"));
      }
     
      public OperationContainer<InstallSupport> addToContainer(
              OperationContainer<InstallSupport> container, 
              List<UpdateElement> modules) {

         for (UpdateElement element : modules) {
            if (container.canBeAdded (element.getUpdateUnit (), element)) {
               OperationInfo<InstallSupport> operationInfo = container.add (element);
               if (operationInfo != null) {
                  container.add (operationInfo.getRequiredElements ());
               }
            }
         }

         return container;
      }
      
      public void disableModules(List<String> modules) {
         OperationContainer<OperationSupport> container = OperationContainer.createForDirectDisable();

         for (UpdateUnit unit : UpdateManager.getDefault().getUpdateUnits(UpdateManager.TYPE.MODULE)) {
            if (unit.getInstalled() != null) {
               UpdateElement element = unit.getInstalled();
               if (element.isEnabled()) {
                  if (modules.contains(element.getCodeName())) {
                     if (container.canBeAdded (unit, element)) {
                        OperationInfo<OperationSupport> operationInfo = container.add(element);
                        if (operationInfo != null) {
                           container.add(operationInfo.getRequiredElements());
                        }
                     }
                  }
               }
            }
         }

         if (!container.listAll().isEmpty()) {
            try {
               Restarter restarter = container.getSupport().doOperation(null);
            } catch (OperationException ex) {
               LOG.severe(ex.getMessage());
            }
         }
      }

      public void installModules(OperationContainer<InstallSupport> container) {

         try {
            InstallSupport support = container.getSupport();
            
            if (support != null) {
               
               Validator validator = support.doDownload (null, true);
               Installer installer = support.doValidate(validator, null);
               Restarter restarter = support.doInstall(installer, null);

               if (restarter != null) {
                  support.doRestartLater(restarter);

                  if (!isRestartRequested) {
                     NotificationDisplayer.getDefault().notify(
                             "Die Anwendung wurde aktualisiert", ImageUtilities.loadImageIcon("com/galileo/netbeans/module/info16.png", false), 
                             "Klicken Sie hier um den notwendigen Neustart durchzuführen", 
                             new RestartAction(support, restarter));
                     isRestartRequested = true;
                  }
               }
            }
         } catch (OperationException ex) {
            LOG.severe(ex.getMessage());
         }
      }

      public void searchNewAndUpdatedModules() {
         
         for (UpdateUnitProvider provider : UpdateUnitProviderFactory.getDefault().getUpdateUnitProviders(false)) {
            try {
               provider.refresh(null, true);
            } catch (IOException ex) {
               LOG.severe(ex.getMessage());
            }
         }
         
         for (UpdateUnit unit : UpdateManager.getDefault().getUpdateUnits()) {
            if (!unit.getAvailableUpdates().isEmpty()) {
               if (unit.getInstalled() == null) {
                  install.add(unit.getAvailableUpdates().get(0));
               } else {
                  update.add(unit.getAvailableUpdates().get(0));
               }
            }
         }
      }
      
      public void searchNewAndUpdatedModulesInDedicatedUC() {
         
         for (UpdateUnitProvider provider : UpdateUnitProviderFactory.getDefault().getUpdateUnitProviders(false)) {
            try {
               if (provider.getName().equals(UC_NAME)) {
                  provider.refresh(null, true);
                  
                  for (UpdateUnit unit : provider.getUpdateUnits()) {
                     if (!unit.getAvailableUpdates().isEmpty()) {
                        if (unit.getInstalled() == null) {
                           install.add(unit.getAvailableUpdates().get(0));
                        } else {
                           update.add(unit.getAvailableUpdates().get(0));
                        }
                     }
                  }
               }
            } catch (IOException ex) {
               LOG.severe(ex.getMessage());
            }
         }
      }
   }
   
   private static final class RestartAction implements ActionListener {
      
      private InstallSupport support;
      private OperationSupport.Restarter restarter;
      
      public RestartAction(InstallSupport support, OperationSupport.Restarter restarter) {
         this.support = support;
         this.restarter = restarter;
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         try {
            support.doRestart(restarter, null);
         } catch (OperationException ex) {
            LOG.severe(ex.getMessage());
         }
      }
      
   }
}
