package net.javaguides.springboot.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	
	
	//1. build create employee REST API
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee)
	{
		return new ResponseEntity<Employee>(employeeService.saveemployee(employee),HttpStatus.CREATED);
	}
	
	
	
	//2. build get all employees REST API
	@GetMapping()
	public List<Employee> getAllEmployees()
	{
		return employeeService.getAllEmployees();
		
	}
	
	
	
	//3.  build get employee by id REST API
	// http://localhost:8180/api/employees/1
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId)
	{
			return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
	}
	
	
	
	
	//4. build update employee REST API
	// http://localhost:8180/api/employees/4
	@PutMapping("{id}")
	public ResponseEntity<Employee>  updateEmployee(@PathVariable("id") long id,@RequestBody Employee employee)
	{
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id),HttpStatus.OK);
	}
	
	
	
	//5. build delete employee REST API
	//http://localhost:8180/api/employees/4
	@DeleteMapping("{id}")
	public ResponseEntity<String> deletEmployee(@PathVariable("id") long id)
	{
		//delete employee from database
		employeeService.deleteEmployee(id);
		
		return new ResponseEntity<String>("Employee Deleted successfully",HttpStatus.OK);
	}
	
	
		
	
}
