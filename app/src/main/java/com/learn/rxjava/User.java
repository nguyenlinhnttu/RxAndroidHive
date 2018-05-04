package com.learn.rxjava;


/**
 * Created by nguyenvanlinh on 5/4/18.
 * Project: RxAndroidHive
 * Web: www.androdcoban.com
 */
public class User {
    String name;
    String email;
    String gender;
    Address  address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
