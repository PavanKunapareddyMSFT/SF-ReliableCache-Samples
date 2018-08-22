package com.fabrikam.directory.service;

import com.fabrikam.directory.Employee;

/**
 * Directory service to perform CURD operations in the employee directory.
 */
public interface DirectoryService {

    Employee createEmployee(long id, String firstName, String lastName, String address);
    
    Employee getById(long id);
    
    void removeEmployee(long id);
}
