package com.ecommerce.module;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Address;
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
	
	@PutMapping("/update-salary")
    public ResponseEntity<List<Employee>> updateSalaryOfEmployees(@RequestParam Double performanceScore, @RequestParam Double salary) {
        List<Employee> updatedEmployees = employeeService.updateSalaryOfEmployeesByPerformanceScore(performanceScore, salary);
        return new ResponseEntity<>(updatedEmployees, HttpStatus.OK);
    }

    @PostMapping("/delete-addresses")
    public ResponseEntity<Void> deleteAddressesOfEmployees(@RequestBody List<Integer> employeeIds) {
        employeeService.deleteAddressesOfEmployees(employeeIds);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register-employee")
    public ResponseEntity<Employee> registerEmployee(@RequestParam String name, @RequestParam Integer age, @RequestParam Date joiningDate) {
        Employee registeredEmployee = employeeService.registerEmployee(name, age, joiningDate);
        return new ResponseEntity<>(registeredEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/ages")
    public ResponseEntity<List<Integer>> getAgesOfEmployeesByConditions(@RequestParam String nameEndsWith, @RequestParam Integer age, @RequestParam List<String> cities) {
        List<Integer> ages = employeeService.getAgesOfEmployeesByConditions(nameEndsWith, age, cities);
        return new ResponseEntity<>(ages, HttpStatus.OK);
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<Address>> getAddressesOfEmployeesByConditions(@RequestParam String state, @RequestParam List<String> pincodes, @RequestParam Double performanceScore, @RequestParam Double annualPackage, @RequestParam String startDate, @RequestParam String endDate) {
        List<Address> addresses = employeeService.getAddressesOfEmployeesByConditions(state, pincodes, performanceScore, annualPackage, startDate, endDate);
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
    
    @GetMapping("/employee-info")
    public ResponseEntity<List<Object[]>> getEmployeeInfoByAgeAndSalary( @RequestParam("age") Integer age, @RequestParam("salaries") List<Integer> salaries) {
        List<Object[]> employeeInfo = employeeService.getEmployeeInfoByAgeAndSalary(age, salaries);
        return new ResponseEntity<>(employeeInfo, HttpStatus.OK);
    }

    @GetMapping("/get-employees-by-conditions")
    public ResponseEntity<List<Employee>> getEmployeesByConditions(
            @RequestParam("startDate") Date startDate,
            @RequestParam("endDate") Date endDate,
            @RequestParam("initialAge") Integer initialAge,
            @RequestParam("finalAge") Integer finalAge,
            @RequestParam("stateNames") List<String> stateNames,
            @RequestParam("cityNames") List<String> cityNames) {
    	
        List<Employee> employees = employeeService.getEmployeeByConditions(startDate, endDate, initialAge, finalAge, stateNames, cityNames);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    
    @GetMapping("/total-yearly-spending")
    public ResponseEntity<Double> calculateTotalYearlySpending() throws EmployeeException {
        double totalYearlySpending = employeeService.calculateTotalYearlySpending();
        return new ResponseEntity<>(totalYearlySpending, HttpStatus.OK);
    }

    @GetMapping("/highest-lowest-average-salary")
    public ResponseEntity<List<Object[]>> getHighestLowestAverageSalaryByOrganization(@RequestParam String organization) throws EmployeeException {
        List<Object[]> result = employeeService.getHighestLowestAverageSalaryByOrganization(organization);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/average-age")
    public ResponseEntity<Integer> getAverageAgeByOrganization(@RequestParam String organization) throws EmployeeException {
        Integer averageAge = employeeService.getAverageAgeByOrganization(organization);
        return new ResponseEntity<>(averageAge, HttpStatus.OK);
    }

    @GetMapping("/one-year-anniversary")
    public ResponseEntity<String> sendEmailOnOneYearOfCompletion() throws EmployeeException {
        String result = employeeService.sendEmailOnOneYearOfCompletion();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/birth-month")
    public ResponseEntity<List<Employee>> getEmployeeByBirthMonth(@RequestParam Integer birthMonth) throws EmployeeException {
        List<Employee> employees = employeeService.getEmployeeByBirthMonth(birthMonth);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) throws EmployeeException{
    	
    	Employee employee = employeeService.getEmployeeById(id);
    	
    	return new ResponseEntity<>(employee,HttpStatus.OK);
    }
}
