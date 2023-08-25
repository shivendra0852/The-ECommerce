package com.ecommerce.module;

import org.springframework.stereotype.Service;

import com.ecommerce.exception.AuthorizationException;
import com.ecommerce.model.LoginRequest;

public interface LoginService {
	
    public String logIn(LoginRequest request) throws AuthorizationException;
	
	public String logOut(String uniqueId) throws AuthorizationException;
}
