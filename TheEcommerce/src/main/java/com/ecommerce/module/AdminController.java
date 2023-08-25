package com.ecommerce.module;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Admin;

@RestController
@RequestMapping("/admins")
public class AdminController {
	
	private AdminService adminService;
	  
    @PostMapping("/register")
    public ResponseEntity<Admin> registerAdmin(@RequestBody Admin admin){  
        return new ResponseEntity<Admin>(adminService.registerAdmin(admin), HttpStatus.CREATED);  
    }
}
