package com.example.hibernate.client;

import com.example.hibernate.entity.Message;
import com.example.hibernate.entity.association.bidirectional.many_to_many.Actor;
import com.example.hibernate.entity.association.bidirectional.many_to_many.Movie;
import com.example.hibernate.entity.association.bidirectional.many_to_one.Student;
import com.example.hibernate.entity.association.bidirectional.one_to_many.Guide;
import com.example.hibernate.entity.association.bidirectional.one_to_one.Customer;
import com.example.hibernate.entity.association.bidirectional.one_to_one.Passport;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaClient {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello-world");
        EntityManager em = emf.createEntityManager();
        EntityTransaction txn = em.getTransaction();

        try {
            txn.begin();

            Message msg = new Message("This msg saved by using JPA persistence.xml config");
            em.persist(msg);

            txn.commit();

            txn.begin();

            Guide guide_1 = new Guide("staff-1", "Guide-1", 2000);
            Guide guide_2 = new Guide("staff-2", "Guide-2", 2500);

            Student student_1 = new Student("Student-1", guide_1);
            Student student_2 = new Student("Student-2", guide_1);
          


            guide_1.getStudents().add(student_1);
            guide_1.getStudents().add(student_2);

            em.persist(guide_1);
            em.persist(guide_2);
            
            //Student student = em.find(Student.class, 2L);
             //em.remove(student);

            txn.commit();

            txn.begin();

            guide_2 = em.find(Guide.class, 2L);
            student_2 = em.find(Student.class, 2L);

            // student_2.setGuide(guide_2);
            // guide_2.setSalary(4000);
            // guide_2.getStudents().add(student_2);
            guide_2.addStudent(student_2);
            // System.out.println(student_1.toString());

            txn.commit();

            txn.begin();

            guide_1 = em.find(Guide.class, 1L);
            System.out.println(guide_1.getStudents().size());

            txn.commit();

            for (Student student : guide_1.getStudents()) {
                System.out.println(student.getName());
            }

            txn.begin();

            Passport passport = new Passport("925076473");
            Customer customer = new Customer("Jhantu", passport);

            em.persist(customer);

            txn.commit();

            txn.begin();

            Movie movie_1 = new Movie("Tarzan");
            Movie movie_2 = new Movie("Oh! Yes");

            Actor actor_1 = new Actor("Tim");
            Actor actor_2 = new Actor("Jane");

            movie_1.getActors().add(actor_1);
            movie_2.getActors().add(actor_2);

            movie_2.getActors().add(actor_1);

            em.persist(movie_1);
            em.persist(movie_2);

            txn.commit();

        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }
}
