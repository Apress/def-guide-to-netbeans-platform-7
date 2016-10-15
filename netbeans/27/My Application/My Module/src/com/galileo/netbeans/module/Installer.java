package com.galileo.netbeans.module;

import com.galileo.netbeans.myentities.Genre;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.openide.modules.ModuleInstall;

public class Installer extends ModuleInstall {

   private static final SessionFactory sessionFactory;
   
   static {
      try {
         // Create the SessionFactory from hibernate.cfg.xml
         System.setProperty("derby.system.home", System.getProperty("netbeans.user"));
         sessionFactory = new Configuration().configure().buildSessionFactory();
      } catch (Throwable ex) {
         // Make sure you log the exception, as it might be swallowed
         System.err.println("Initial SessionFactory creation failed." + ex);
         throw new ExceptionInInitializerError(ex);
      }
   }

   public static Session createSession() {
      return sessionFactory.openSession();
   }
   
   public static Session currentSession() {
      return sessionFactory.getCurrentSession();
   }

   @Override
   public void close() {
      sessionFactory.close();
   }
   
   @Override
   public void restored() {
      Session s = currentSession();
      Transaction t = s.beginTransaction();
      s.merge(new Genre(1, "Techno, Trance & Dance"));
      s.merge(new Genre(2, "Rock & Pop"));
      s.merge(new Genre(3, "Country & Classic"));
      t.commit();
   }
}
