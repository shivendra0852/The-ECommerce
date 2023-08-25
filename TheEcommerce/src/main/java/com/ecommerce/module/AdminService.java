package com.ecommerce.module;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecommerce.entity.Admin;
import com.ecommerce.exception.AdminException;

public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	public Admin registerAdmin(Admin admin) throws AdminException{
		Optional<Admin> existingAdmin = adminRepository.findById(admin.getId());
		
		if(existingAdmin.isPresent()) {
			throw new AdminException("User already registered with this details!");
		}
		
		return adminRepository.save(admin);
	}
	
}
