package com.example.hibernate.entity.composition;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "street" , column = @Column(name="home_street")),
        @AttributeOverride(name = "zip", column = @Column(name="home_zip")),
        @AttributeOverride(name = "city", column = @Column(name="home_city"))
    })
    private Address homeAddress;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "street" , column = @Column(name="billing_strret")),
        @AttributeOverride(name = "zip" , column = @Column(name="billing_zip")),
        @AttributeOverride(name = "city", column = @Column(name="billing_city"))
    })
    private Address billingAddress;

    public Person(){

    }

    public Person( String name, Address homeAddress, Address billingAddress) {
        this.name = name;
        this.homeAddress = homeAddress;
        this.billingAddress = billingAddress;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", homeAddress=" + homeAddress + ", billingAddress="
                + billingAddress + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

    
    
}
