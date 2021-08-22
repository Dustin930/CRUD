package com.tsmc.src.db_crud_restful_webservice.controller;

import java.util.List;

import com.tsmc.src.db_crud_restful_webservice.model.Employee;
import com.tsmc.src.db_crud_restful_webservice.repository.EmployeeRepository;
import com.tsmc.src.db_crud_restful_webservice.exception.*;
import javax.validation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    // create get all employee api
    @GetMapping("/employees")
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    };


    // create Employee
    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee){
        return employeeRepository.save(employee);
    } 

    // get employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") long employeeId) throws ResourceNotFoundException{
        Employee employee = employeeRepository.findById(employeeId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        return ResponseEntity.ok().body(employee);
    }

    // update employee
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") long employeeId
            , @RequestBody Employee employeeDatail) throws ResourceNotFoundException{
        Employee employee = employeeRepository.findById(employeeId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setFirstname(employeeDatail.getFirstname());
        employee.setLastname(employeeDatail.getLastname());
        employee.setEmailid(employeeDatail.getEmailid());
        employeeRepository.save(employee);
        return ResponseEntity.ok().body(employee);

    }
    // delete employee by id
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") long employeeId) throws ResourceNotFoundException{
        employeeRepository.findById(employeeId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.deleteById(employeeId);
        return ResponseEntity.ok().build();


    }
}
