package com.tsmc.src.db_crud_restful_webservice.repository;

import com.tsmc.src.db_crud_restful_webservice.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    
}
