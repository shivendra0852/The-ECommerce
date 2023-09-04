package com.ecommerce.module;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.entity.Address;
import com.ecommerce.entity.City;
import com.ecommerce.entity.Employee;
import com.ecommerce.entity.State;

import jakarta.transaction.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	Optional<Employee> findByEmail(String email);
	
	@Modifying
	@Transactional
	@Query("UPDATE Employee e SET e.salary = e.salary + ?2 WHERE e.performanceScore > ?1")
	public List<Employee> updateSalaryOfEmployees(Double performanceScore, Double salary);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Address a WHERE a.employee.id IN ?1 AND a.employee.hasCar = true")
	public void deleteAddressOfEmployees(List<Integer> employeeIds);
	
	@Modifying
	@Transactional
	@Query("INSERT into Employee (name, age, joiningDate) values(?1, ?2, ?3)")
	public Employee registerEmployeeQuery(String name, Integer age, Date joiningDate);
	
	@Query("SELECT e.age FROM Address a JOIN a.employee e WHERE e.name LIKE %?1 AND e.age > ?2 AND e.hasCar = true AND a.city IN ?3")
	public List<Integer> fetchAgesOfEmployees(String nameEndsWith, Integer age, List<String> cities);
	
	@Query("SELECT a from Address a JOIN a.employee e WHERE a.state = ?1 AND a.pincode IN ?2 AND e.performanceScore > ?3 AND e.annualPackage = ?4 AND e.joiningDocumentSubmitted = true AND e.joiningDate BETWEEN ?5 AND ?6")
	public List<Address> fetchAddressesOfEmployees(String state, List<String> pincodes, Double performanceScore, Double annualPackage, String startDate, String endDate);
	
	@Query("SELECT e.name, a.city from Address a JOIN a.employee e WHERE e.age > ?1 AND e.salary IN ?2")
	public List<Object[]> fetchEmployeeInfoByAgeAndSalary(Integer age, List<Integer> salaries);
	
	@Query("SELECT e FROM Employee e " +"WHERE e.joiningDate BETWEEN ?1 AND ?2 " +"AND e.age BETWEEN ?3 AND ?4 " +"AND e.id IN (SELECT a.employee.id FROM Address a WHERE a.state IN ?5) " +"AND e.id IN (SELECT a.employee.id FROM Address a WHERE a.city IN ?6) " +"AND e.hasCar = false")
	public List<Employee> fetchEmployeeByConditions(Date startDate, Date endDate, Integer initialAge, Integer finalAge, List<String> state, List<String> city);
	
	@Query(value = "SELECT MAX(e.salary), MIN(e.salary), AVG(e.salary) from Employee e WHERE e.organization = ?1", nativeQuery = true)
	public List<Object[]> findHighestLowestAverageSalaryByOrganization(String organization);
	
	@Query(value = "SELECT AVG(e.age) from Employee e WHERE e.organization = ?1", nativeQuery = true)
	public Integer findAverageAgeByOrganization(String organization);
	
	
	public List<Employee> findByJoiningDateBefore(Date oneYearAgo);
	
	@Query("SELECT e FROM Employee e WHERE MONTH(e.birthDate) = ?1")
	public List<Employee> findByBirthDateMonth(Integer month);

}

