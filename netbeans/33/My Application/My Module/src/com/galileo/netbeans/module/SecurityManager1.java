package com.galileo.netbeans.module;

import com.sun.security.auth.login.ConfigFile;
import java.io.IOException;
import java.net.URL;
import java.security.Principal;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginContext;
import org.openide.util.Exceptions;

public class SecurityManager1 implements CallbackHandler {

   private static SecurityManager1 inst = new SecurityManager1();
   
   private String user;
   private String password;

   private SecurityManager1() {
   }

   public static SecurityManager1 getDefault() {
      return inst;
   }

   public boolean login(String user, String password) {

      this.user = user;
      this.password = password;

      LoginContext loginContext = null;

      try {
         URL url = SecurityManager.class.
                 getClassLoader().getResource("config.jaas");
         loginContext = new LoginContext("LoginJaas", 
                 null, this, new ConfigFile(url.toURI()));
         loginContext.login();
         for (Principal p : loginContext.getSubject().getPrincipals()) {
             System.out.println("Principal: <" + p.getClass() + "> " + p.getName());
         }
         return true;
      } catch (Exception e) {
         Exceptions.printStackTrace(e);
         return false;
      }
   }
   
   
   @Override
   public void handle(Callback[] callbacks)
           throws IOException, UnsupportedCallbackException {
      for (Callback cb : callbacks) {
         if (cb instanceof NameCallback) {
            NameCallback nc = (NameCallback) cb;
            nc.setName(this.user);
         } else if (cb instanceof PasswordCallback) {
            PasswordCallback pc = (PasswordCallback) cb;
            pc.setPassword(this.password.toCharArray());
         }
      }
   }
}
