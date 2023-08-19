package net.javaguides.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}


	@Override
	public Employee saveemployee(Employee employee) {
		return employeeRepository.save(employee);
	}


	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll() ;
	}


	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}
		else 
		{
			throw new ResourceNotFoundException("Employee", "Id", id);
	    }
		//here we also can use lambda Expression
		
	}


	@Override
	public Employee updateEmployee(Employee employee, long id) 
	{
		//we need to check employee with given id exists database or not
		Employee existingEmployee=employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee","Id", id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		//save existing employee to database
		employeeRepository.save(existingEmployee);
		return existingEmployee;
			
	}


	@Override
	public void deleteEmployee(long id)
	{
		//check whether a employee exist in a database or not
		employeeRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Employee","Id",id));
		
		employeeRepository.deleteById(id);
		
	}
		
		
	
	

	
}
