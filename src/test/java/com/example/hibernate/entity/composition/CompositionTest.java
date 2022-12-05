package com.example.hibernate.entity.composition;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.hibernate.util.HibernateUtil;

public class CompositionTest {
    private static Logger log = LogManager.getLogger(CompositionTest.class);
    private static final Session session = HibernateUtil.getSessionFactory().openSession();

    @BeforeAll
    static void setUp(){
        insertPerson();
    }

    @AfterAll
    static void done(){
        if(session!=null){
            session.close();
        }
    }

    @Test
    public void testPersonSave(){
        session.beginTransaction();
        Person person = session.get(Person.class, 1L);
        session.getTransaction().commit();
        System.out.println(person.toString());
        assertEquals(1L, person.getId());
        assertEquals("Mac", person.getName());
        assertEquals("Duk", person.getHomeAddress().getCity());
        assertEquals("Pune", person.getBillingAddress().getCity());
    }

    private static void insertPerson() {
        session.beginTransaction();
        Address homeAddress = new Address("Duk", "1234", "Duke street");
        Address billingAddress = new Address("Pune", "4321", "pune street");
        Person person = new Person("Mac",  homeAddress, billingAddress);
        session.save(person);
        session.getTransaction().commit();
    }
}
