package com.fabrikam.directory.service;

import org.springframework.data.repository.CrudRepository;

import com.fabrikam.directory.Employee;

/**
 * Repository to perform CRUD operations on the Employee information.
 */
public interface DirectoryRepository extends CrudRepository<Employee, Long>{

}
