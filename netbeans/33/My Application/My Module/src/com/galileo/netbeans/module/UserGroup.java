/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.galileo.netbeans.module;

import java.net.URL;

/**
 *
 * @author Heiko
 */
public enum UserGroup {
   ADMIN("Admin", "configs/admin.xml"),
   USER("User", "configs/user.xml");
   
   private String groupName;
   private String configPath;

   UserGroup(String groupName, String configPath) {
      this.groupName = groupName;
      this.configPath = configPath;
   }
   
   public URL getConfig() {
      return UserGroup.class.getResource(configPath);
   }
   
   public String getGroup() {
      return this.groupName;
   }
   
   public boolean equals(String groupName) {
      return this.groupName.equals(groupName);
   }
   
   @Override
   public String toString() {
      return this.groupName;
   }
   
   public static UserGroup get(String groupName) {
      for (UserGroup group : UserGroup.values()) {
         if (group.groupName.equals(groupName)) {
            return group;
         }
      }
      return null;
   }
}
