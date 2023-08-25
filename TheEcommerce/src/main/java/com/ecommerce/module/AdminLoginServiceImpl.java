package com.ecommerce.module;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Admin;
import com.ecommerce.entity.LoggedinUserSession;
import com.ecommerce.exception.AuthorizationException;
import com.ecommerce.model.LoginRequest;

@Service
public class AdminLoginServiceImpl implements LoginService{
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private LoggedinUserSessionRepository loggedinUserSessionRepository;

	@Override
	public String logIn(LoginRequest request) throws AuthorizationException {
		
		Admin existingAdmin = adminRepository.findByEmail(request.getEmail());
		
		if(existingAdmin == null) {
			throw new AuthorizationException("Please register your self first!");
		}
		
		Optional<LoggedinUserSession> loggedInAdmin = loggedinUserSessionRepository.findById(existingAdmin.getId());
		
		if(loggedInAdmin.isPresent()) {
			throw new AuthorizationException("Admin already logged in!");
		}
		
		if(existingAdmin.getPassword().equals(request.getPassword())) {
			
			String uniqueId = UUID.randomUUID().toString();
			
			LoggedinUserSession adminCurrentSession = new LoggedinUserSession(existingAdmin.getId(),uniqueId,LocalDateTime.now());
			
			loggedinUserSessionRepository.save(adminCurrentSession);
			
			return adminCurrentSession.toString();
		}
		else {
			throw new AuthorizationException("Please enter a valid password!");
		}
		
	}

	@Override
	public String logOut(String uniqueId) throws AuthorizationException {
		// TODO Auto-generated method stub
		return null;
	}

}
