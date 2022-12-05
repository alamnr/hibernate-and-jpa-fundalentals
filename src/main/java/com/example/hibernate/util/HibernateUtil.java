package com.example.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

  private static StandardServiceRegistry standardServiceRegistry;
  private static SessionFactory sessionFactory;
 
  static{
	    if (sessionFactory == null) {
	      try {
	       // create session factory from hibernate.cfg.xml
          // A SessionFactory is set up once for an application!
            standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        
	        sessionFactory = new MetadataSources(standardServiceRegistry).buildMetadata().buildSessionFactory();
	      } catch (Exception e) {
	        e.printStackTrace();
	        if (standardServiceRegistry != null) {
	          StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
	        }
	      }
	    }
  }
 
  private HibernateUtil(){}
  //Utility method to return SessionFactory
  public static SessionFactory getSessionFactory() {
	  return sessionFactory;
  }
    
}
