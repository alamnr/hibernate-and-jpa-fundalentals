package com.example.hibernate.client;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.hibernate.entity.Message;
import com.example.hibernate.entity.association.bidirectional.many_to_many.Actor;
import com.example.hibernate.entity.association.bidirectional.many_to_many.Movie;
import com.example.hibernate.entity.association.bidirectional.many_to_one.Student;
import com.example.hibernate.entity.association.bidirectional.one_to_many.Guide;
import com.example.hibernate.entity.association.bidirectional.one_to_one.Customer;
import com.example.hibernate.entity.association.bidirectional.one_to_one.Passport;
import com.example.hibernate.entity.composition.Address;
import com.example.hibernate.entity.composition.Person;
import com.example.hibernate.util.HibernateUtil;
import com.mysql.cj.xdevapi.SessionImpl;



public class HelloWorldClient {

    private static final Logger log = LogManager.getLogger(HelloWorldClient.class);
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

         Guide guide_1 = new Guide("staff-1", "Guide-1", 2000);
         Guide guide_2 = new Guide("staff-2", "Guide-2", 2500);

         Student student_1 = new Student("Student-1", guide_1);        
         Student student_2 = new Student("Student-2", guide_1);

         //session.save(guide);
         //session.save(student);

         guide_1.getStudents().add(student_1);
         guide_1.getStudents().add(student_2);

         session.persist(guide_1);
         session.persist(guide_2);
         //Student student = session.get(Student.class, 2L);
         //session.delete(student); 



         session.getTransaction().commit();

         session.beginTransaction();

         guide_2 = session.get(Guide.class, 2L);
         student_2 = session.get(Student.class, 2L);
         
         //student_2.setGuide(guide_2);
         //guide_2.setSalary(4000);
         //guide_2.getStudents().add(student_2);
         guide_2.addStudent(student_2);
         //System.out.println(student_1.toString()); 
        

         session.getTransaction().commit(); 


         session.beginTransaction();

         guide_1 = session.get(Guide.class, 1L);
         System.out.println(guide_1.getStudents().size());

         session.getTransaction().commit();

         for (Student student : guide_1.getStudents()) {
            System.out.println(student.getName());
         }

         session.beginTransaction();

         Passport passport = new Passport("925076473");
         Customer customer = new Customer("Jhantu", passport);

         session.persist(customer);

         session.getTransaction().commit();

         session.beginTransaction();

         Movie movie_1 = new Movie("Tarzan");
         Movie movie_2 = new Movie("Oh! Yes");

         Actor actor_1 = new Actor("Tim");
         Actor actor_2 = new Actor("Jane");

         movie_1.getActors().add(actor_1);
         movie_2.getActors().add(actor_2);

         movie_2.getActors().add(actor_1);

         session.persist(movie_1);
         session.persist(movie_2);

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
