package com.ecommerce.module;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
	public Admin findByEmail(String email);
	
}
