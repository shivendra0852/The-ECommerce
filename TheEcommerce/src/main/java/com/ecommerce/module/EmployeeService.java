package com.ecommerce.module;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Employee;
import com.ecommerce.exception.EmployeeException;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
    private EmailService emailService;
	
	@Autowired
	private EmployeeCriteriaRepository employeeCriteriaRepository;
	
	public Employee registerEmployee(Employee employee) throws EmployeeException{
		
		Optional<Employee> existingEmployee = employeeRepository.findByEmail(employee.getEmail());
		
		if(existingEmployee.isPresent()) {
			throw new EmployeeException("Employee already registered!");
		}
		
		String verificationToken = UUID.randomUUID().toString();
        employee.setVerificationToken(verificationToken);

        // Save employee and send verification email
        Employee savedEmployee = employeeRepository.save(employee);
        emailService.sendVerificationEmail(savedEmployee.getEmail(), verificationToken);

        return savedEmployee;
	}
	
	public List<Employee> getEmployeeByFilters(Integer itemPerPage, Integer pageNumber, String sort, String sortBy, String search, String searchBy) throws EmployeeException {
	    
	    List<Employee> employees = employeeCriteriaRepository.findEmployeeByFilters(itemPerPage, pageNumber, sort, sortBy, search, searchBy);

	    if (employees.size() == 0) {
	        throw new EmployeeException("No employee exists!");
	    }

	    return employees;
	}

}
