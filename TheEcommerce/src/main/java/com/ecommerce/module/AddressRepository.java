package com.ecommerce.module;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	
	@Query(value = "SELECT e.id, a.city, e.age FROM Address a INNER JOIN Employee e ON a.employee_id = e.id WHERE e.salary < ?1 AND e.joining_date < ?2 AND e.age > ?3 AND e.organization = ?4" , nativeQuery = true)
	public List<Object[]> findCityAndAgeByOrganizationAndOtherConditions(Double salary, Date joiningDate, Integer age, String organization);
	
    public List<Address> findByEmployee_Id(Integer id);

}
