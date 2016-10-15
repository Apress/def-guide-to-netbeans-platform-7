package com.galileo.netbeans.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class SecurityManager {

   private static SecurityManager inst = new SecurityManager();
   
   private String user;
   private List<UserGroup> groups = new ArrayList<UserGroup>();

   private SecurityManager() {
   }

   public static SecurityManager getDefault() {
      return inst;
   }

   public boolean login(String user, String password) {
      
      this.user = "";
      this.groups.clear();

      Properties props = new Properties();
      props.put(DirContext.INITIAL_CONTEXT_FACTORY, 
              "com.sun.jndi.ldap.LdapCtxFactory");
      props.put(DirContext.PROVIDER_URL, 
              "ldap://localhost:10389/ou=system");
      props.put(DirContext.SECURITY_AUTHENTICATION, "simple");
      props.put(DirContext.SECURITY_PRINCIPAL, 
              "uid=" + user + ", ou=users, ou=system");
      props.put(DirContext.SECURITY_CREDENTIALS, password);

      try {
         InitialDirContext ctx = new InitialDirContext(props);

         this.user = user;

         Attributes attr = 
                 ctx.getAttributes("uid=" + user + ", ou=users");

         Attribute a = attr.get("employeeType");
         
         if (a != null) {
            for (String groupName : Collections.list(
                    (Enumeration<String>) a.getAll())) {
               UserGroup group = UserGroup.get(groupName);
               if (group != null) {
                  groups.add(group);
               }
            }
         }

         return true;
      } catch (NamingException ex) {
         return false;
      }
   }
   
   public List<UserGroup> getUserGroups() {
      return this.groups;
   }
}
