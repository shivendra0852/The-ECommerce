package com.ecommerce.module;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	Optional<Employee> findByEmail(String email);
}
