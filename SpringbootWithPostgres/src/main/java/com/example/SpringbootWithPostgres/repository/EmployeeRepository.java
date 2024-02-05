package com.example.SpringbootWithPostgres.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringbootWithPostgres.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	

}
