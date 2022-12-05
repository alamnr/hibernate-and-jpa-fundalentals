package com.example.hibernate.client;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.hibernate.entity.Message;
import com.example.hibernate.entity.association.Guide;
import com.example.hibernate.entity.association.Student;
import com.example.hibernate.entity.composition.Address;
import com.example.hibernate.entity.composition.Person;
import com.example.hibernate.util.HibernateUtil;



public class HelloWorldClient {

    private static final Logger log = LogManager.getLogger(HelloWorldClient.class);
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

         Guide guide = new Guide("staff-1", "tarjan", 2000);
         Student student = new Student("Jane", guide);

         session.save(guide);
         session.save(student);

         session.getTransaction().commit();
         session.close();
        
        /* Message message = new Message("Hello world with hibernate");

        session.persist(message);
        session.getTransaction().commit();
        session.close();

       

        session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Message msg = session.get(Message.class, 1L);
            msg.setText("Hello Automatic dirty checking");
            session.delete(msg);
            transaction.commit();
        } catch (Exception e) {
            if(transaction!=null){
                transaction.rollback();
            }
            log.error(e.getMessage());            
        } finally {
            if(session != null){
                session.close();
            }
        }

        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List result = session.createQuery("from Message").list();
        for (Object object : result) {
            System.out.println(((Message)object).toString());
        }
        session.getTransaction().commit();
        session.close();    */

        /*
        Address homeAddress = new Address("Duk", "1234", "Duke street");
        Address billingAddress = new Address("Pune", "4321", "pune street");
        Person person = new Person("Mac",  homeAddress, billingAddress);

        session.save(person);
        session.getTransaction().commit();
        session.close(); */


        
    } 


}
