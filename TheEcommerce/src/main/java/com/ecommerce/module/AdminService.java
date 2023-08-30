package com.ecommerce.module;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Admin;
import com.ecommerce.exception.AdminException;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	public Admin registerAdmin(Admin admin) throws AdminException{
		Admin existingAdmin = adminRepository.findByEmail(admin.getEmail());
		
		if(existingAdmin != null) {
			throw new AdminException("User already registered with this details!");
		}
		
		return adminRepository.save(admin);
	}
	
}
