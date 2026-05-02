package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.interfaces.Searchable;
import java.io.Serializable;

public abstract class Person implements Searchable<Person>, Serializable {
    private static final long serialVersionUID = 1L;

    protected String id;
    protected String name;
    protected int age;
    protected String contactNumber;

    public Person(String id, String name, int age, String contactNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public boolean match(String query) {
        if (query == null) return false;
        String q = query.toLowerCase();
        return (id != null && id.toLowerCase().contains(q)) ||
               (name != null && name.toLowerCase().contains(q)) ||
               (contactNumber != null && contactNumber.contains(q));
    }

    // Abstract method to be overridden by subclasses
    public abstract void displayDetails();
}
