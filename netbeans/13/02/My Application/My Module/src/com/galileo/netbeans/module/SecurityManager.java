package com.galileo.netbeans.module;

public class SecurityManager {
   
   public static boolean login(String username, String password) {
      if(username.equals("admin") && password.equals("password")) {
         return true;
      } else {
         return false;
      }
   }
}
