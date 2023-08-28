package com.ecommerce.module;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Employee;
import com.ecommerce.exception.EmployeeException;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/register")
	public ResponseEntity<Employee> registerEmployee(@RequestBody Employee employee) throws EmployeeException{
		
		return new ResponseEntity<Employee>(employeeService.registerEmployee(employee), HttpStatus.CREATED);
	}
	
	@GetMapping("/get-employees-by-filters")
	public ResponseEntity<List<Employee>> getEmployeeByFilters(
	    @RequestParam(defaultValue = "4") Integer itemPerPage,
	    @RequestParam(defaultValue = "1") Integer pageNumber,
	    @RequestParam(defaultValue = "asc") String sort,
	    @RequestParam(defaultValue = "id") String sortBy,
	    @RequestParam(defaultValue = "") String search,
	    @RequestParam(defaultValue = "name") String searchBy) throws EmployeeException {
		
	    return new ResponseEntity<List<Employee>>(employeeService.getEmployeeByFilters(itemPerPage, pageNumber, sort, sortBy, search, searchBy), HttpStatus.OK);
	}

}
