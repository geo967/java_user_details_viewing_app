package com.example.userdetailsviewingapp.Model;

public class MainModel {
    String name,username,email,phone;
    Address address;
    Company company;

    public MainModel(String name, String username, String email, String phone, Address address, Company company) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address=address;
        this.company=company;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }


    public String getPhone() {
        return phone;
    }


    public Address getAddress() {
        return address;
    }



    public Company getCompany() {
        return company;
    }


}
