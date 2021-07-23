package com.example.userdetailsviewingapp.Model;

public class Address {
    Geo geo;
    String street,city,zipcode;

    public Address(Geo geo, String street, String city, String zipcode) {
        this.geo = geo;
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
    }

    public Geo getGeo() {
        return geo;
    }



    public String getStreet() {
        return street;
    }



    public String getCity() {
        return city;
    }


    public String getZipcode() {
        return zipcode;
    }


}
