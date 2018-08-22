package com.fabrikam.directory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import com.fabrikam.directory.Employee;

/**
 * Implementation of DirectoryService.
 */
public class DirectoryServiceImpl implements DirectoryService {

    @Autowired
    DirectoryRepository directoryRepository;
    
    /**
     * Saves Employee object in the backend database and also creates an entry in the cache.
     */
    @Override
    @CachePut(value="EmployeeCache", key="#id")
    public Employee createEmployee(long id, String firstName, String lastName, String address) {
        Employee emp = new Employee(id, firstName, lastName, address);
        return directoryRepository.save(emp);
    }
    
    /**
     * Fetches Employee information by id from backend for the first time and creates an entry in the cache, which will be used from next time with out executing the method.
     */
    @Override
    @Cacheable(value="EmployeeCache", key="#id")
    public Employee getById(long id) {
        return directoryRepository.findOne(id);
    }

    /**
     * Removes the Employee information by id from backend and evicts the same from cache.
     */
    @Override
    @CacheEvict(value="EmployeeCache", key="#id")
    public void removeEmployee(long id) {
        directoryRepository.delete(id);
    }

}
