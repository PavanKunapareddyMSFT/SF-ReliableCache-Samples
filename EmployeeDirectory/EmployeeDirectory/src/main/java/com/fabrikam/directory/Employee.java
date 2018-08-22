package com.fabrikam.directory;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

/**
 * Class to create employee objects.
 */
public class Employee implements Serializable{

    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String address;
    
    public Employee(long id, String firstName, String lastName, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }
    
    
}
