package com.example.SpringbootWithPostgres.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringbootWithPostgres.exception.ResourceNotFoundException;
import com.example.SpringbootWithPostgres.model.Employee;
import com.example.SpringbootWithPostgres.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	//get employee
	private EmployeeRepository employeeRepository;
	
	@GetMapping("employees")
	public List<Employee> getAllEmployee() {
		return this.employeeRepository.findAll();
	}
	
	//get employee by id
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
	      throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(()->new ResourceNotFoundException("Employee not found by this id ::" + employeeId));
		return ResponseEntity.ok().body(employee);
		
		
		
	}
	//save employee
	@PostMapping("employees")
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return this.employeeRepository.save(employee);
	}
	//update employee
	
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Validated @RequestBody Employee employeeDetails) throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(()->new ResourceNotFoundException("Employee not found by this id ::" + employeeId));
		employee.setEmail(employeeDetails.getEmail());
		employee.setFirstname(employeeDetails.getFirstname());
		employee.setLastname(employeeDetails.getLastname());
		
		return ResponseEntity.ok(this.employeeRepository.save(employee));
		
		
	}
	//delete employee
	@DeleteMapping("employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException{
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(()->new ResourceNotFoundException("Employee not found by this id ::" + employeeId));
		this.employeeRepository.delete(employee);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
		

	}
	

}
