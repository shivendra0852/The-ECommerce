package com.ecommerce.module;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Address;
import com.ecommerce.entity.City;
import com.ecommerce.entity.Employee;
import com.ecommerce.entity.State;
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
	
	public List<Employee> updateSalaryOfEmployeesByPerformanceScore(Double performanceScore, Double salary){
		
		return employeeRepository.updateSalaryOfEmployees(performanceScore, salary);
	}

    public void deleteAddressesOfEmployees(List<Integer> employeeIds) {
        employeeRepository.deleteAddressOfEmployees(employeeIds);
    }

   
    public Employee registerEmployee(String name, Integer age, Date joiningDate) {
        return employeeRepository.registerEmployeeQuery(name, age, joiningDate);
    }

    public List<Integer> getAgesOfEmployeesByConditions(String nameEndsWith, Integer age, List<String> cities) {
        return employeeRepository.fetchAgesOfEmployees(nameEndsWith, age, cities);
    }

    public List<Address> getAddressesOfEmployeesByConditions(String state, List<String> pincodes, Double performanceScore, Double annualPackage, String startDate, String endDate) {
        return employeeRepository.fetchAddressesOfEmployees(state, pincodes, performanceScore, annualPackage, startDate, endDate);
    }
    
    public List<Object[]> getEmployeeInfoByAgeAndSalary(Integer age, List<Integer> salaries){
    	return employeeRepository.fetchEmployeeInfoByAgeAndSalary(age, salaries);
    }
    
    public List<Employee> getEmployeeByConditions(Date startDate, Date endDate, Integer initialAge, Integer finalAge, List<String> stateNames, List<String> cityNames){
    	return employeeRepository.fetchEmployeeByConditions(startDate, endDate, initialAge, finalAge, stateNames, cityNames);
    }
}
