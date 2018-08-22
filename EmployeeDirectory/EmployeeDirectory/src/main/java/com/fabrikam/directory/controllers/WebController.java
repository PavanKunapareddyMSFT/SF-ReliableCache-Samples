package com.fabrikam.directory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fabrikam.directory.Employee;
import com.fabrikam.directory.service.DirectoryService;

/**
 * Web controller to serve CURD operations on the DirectoryService
 */
@Controller
public class WebController {

    @Autowired
    DirectoryService dirService;
    
    @RequestMapping("/add")
    public String add(ModelMap modelMap, 
            @RequestParam("id") long id, 
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("address") String address) {
        
        Employee emp = dirService.createEmployee(id, firstName, lastName, address);
        modelMap.addAttribute("value", emp);
        modelMap.addAttribute("status", "Entry added");
        return "dummy-page";
    }
    
    @RequestMapping("/remove")
    public String remove(ModelMap modelMap, 
            @RequestParam("id") long id) {
        
        dirService.removeEmployee(id);
        modelMap.addAttribute("status", "Entry removed");
        return "dummy-page";
    }
    
    @RequestMapping("/find")
    public String find(ModelMap modelMap, 
            @RequestParam("id") long id) {
        
        Employee emp = dirService.getById(id);
        modelMap.addAttribute("value", emp);
        modelMap.addAttribute("status", "Entry fetched");
        return "dummy-page";
    }
}