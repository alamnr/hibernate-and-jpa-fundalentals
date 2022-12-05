package com.example.hibernate.entity.association;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.hibernate.domain.Message;
import com.example.hibernate.entity.association.bidirectional.many_to_one.Student;
import com.example.hibernate.entity.association.bidirectional.one_to_many.Guide;
import com.example.hibernate.util.HibernateUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ManyToOneTest {

    private static final Logger log = LogManager.getLogger(ManyToOneTest.class);
    private static final Session session = HibernateUtil.getSessionFactory().openSession();

    @BeforeAll
    private static void setUp(){
        try {
            //saveStudentAndGuideWithoutCascade();
            persistStudentAndGuideWithCascade();
           // updateOnInverseEnd();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static void persistStudentAndGuideWithCascade() throws Exception{
        session.beginTransaction();
        Guide guide_1 = new Guide("123456", "Jhantu", 2000);
        Guide guide_2 = new Guide("12345", "Gandu", 2000);
        Student student_1 = new Student("Jane", guide_1);
        Student student_2 = new Student("Tarzan", guide_2);
        
       
        session.persist(student_1);
        session.persist(student_2);

       
        
        session.getTransaction().commit();

    }
   /*  private static void updateOnInverseEnd() {

        session.beginTransaction();
        Guide guide_1 = session.get( Guide.class,1L);
        Student student_2 = session.get(Student.class, 2L);
        guide_1.setSalary(3500);
        //guide_1.getStudents().add(student_2);
        guide_1.addstudent(student_2);
//        session.persist(guide_1);

        session.getTransaction().commit();

    } */

    @Test
    public void blankTest(){

    }

    @AfterAll
    private static void done(){
        if(session != null){
            session.close();
        }
    }

    @Test
    public void saveObjectGraphTwiceWithoutCascade(){
            session.beginTransaction();
            Student student = session.get(Student.class, 1L);
            session.getTransaction().commit();
            
            assertNotNull(student);
            assertNotNull(student.getGuide());
            assertEquals("Jane", student.getName());
            assertEquals("Gandu",student.getGuide().getName());
            System.out.println(student.toString());
    }

   /*  @Test
    public void retrieveListOfStudent(){
        session.beginTransaction();
        List<Student> students = session.createQuery("from Student").list();
        Guide guide = session.get(Guide.class, 1L);
        session.getTransaction().commit();
        for (Student student : students) {
            System.out.println(student.toString());
        }
        assertEquals(2, students.size()); 

        System.out.println(guide.toString());
        System.out.println(" Student size : " +guide.getStudents().size());
        guide.getStudents().forEach((e)-> System.out.println(e.toString()));
        assertEquals(2, guide.getStudents().size());
    }
 */
    @Test
    public void testJpaSetup(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello-world");
        EntityManager em = emf.createEntityManager();
        EntityTransaction txn = em.getTransaction();
        try {
            txn.begin();
            Message msg = new Message("Hello world with hibernate as JPA provider");
            em.persist(msg);

            txn.commit();
        } catch (Exception e) {
            if(txn!=null){
                txn.rollback();
            }
            e.printStackTrace();
        } finally{
            if(em != null){
                em.close();
            }
        }
    }

    private static void saveStudentAndGuideWithoutCascade() throws Exception{
        session.beginTransaction();
        Guide guidde = new Guide("123456", "Jhantu", 2000);
        Student student = new Student("Jane", guidde);
        
        session.save(guidde);
        session.save(student);
        
        session.getTransaction().commit();

    }

    
    
}
