package com.ecommerce.module;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Address;
import com.ecommerce.entity.Employee;
import com.ecommerce.exception.AddressException;
import com.ecommerce.exception.EmployeeException;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmailService emailService;
	
	public Address registerAddress(Integer id, Address address) throws AddressException{
		
		address.setEmployee(employeeRepository.findById(id).get());
		return addressRepository.save(address);
	}
	
	
	public String giveSalaryHikeAndSendMail(Double salary, Integer age, String organization) {
		
	
	
		// Calculate the date 2 years ago from the current date
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, -2);
		Date twoYearsAgo = calendar.getTime();
	
		// Use the twoYearsAgo date in your query
	
	    	
	    	List<Object[]> eligibleEmployees = addressRepository.findCityAndAgeByOrganizationAndOtherConditions(salary, twoYearsAgo, age, organization);
	    	
	    	if(eligibleEmployees.isEmpty()) {
	    		throw new EmployeeException("No employees are eligible for salary hike!");
	    	}
	    	
	    	for(Object[] e : eligibleEmployees) {
	    		Employee employee = employeeRepository.findById((Integer) e[0]).get();
	    		
	    		employee.setSalary(salary+ (salary*0.1));
	    		
	    		Employee savedEmployee = employeeRepository.save(employee);
	    		
	    		String emailSubject = "Congratulations on Your Salary Hike!";
	            String emailContent = "Dear " + savedEmployee.getName() + ",\n" +
	    			    "Congratulations on your 10% salary increase! Your dedication and outstanding performance have earned you this well-deserved raise.\n" +
	    			    "Your contributions are invaluable, and we're excited to recognize your hard work.\n" +
	    			    "Warm regards,\n" +
	    			    "Papa ki pari\n" +
	    			    "Human Resources Manager\n" +
	    			    savedEmployee.getOrganization();
	    		
	    		emailService.sendEmailAccordingToRequirements(savedEmployee.getEmail(), emailSubject, emailContent);
	    	}
	    	
	    	return "Congratulation email sent to all the eligible employees successfully!";
	    }
}
