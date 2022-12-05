package com.example.hibernate.entity.composition;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    @Column(name="city")
    private String city;
    @Column(name="zip")
    private String zip;
    @Column(name="street")
    private String street;

    public Address(){

    }

    public Address(String city, String zip, String street) {
        this.city = city;
        this.zip = zip;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Address [city=" + city + ", zip=" + zip + ", street=" + street + "]";
    }
    
    
}
