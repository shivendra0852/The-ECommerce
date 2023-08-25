package com.ecommerce.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.exception.AuthorizationException;
import com.ecommerce.model.LoginRequest;

@RestController
@RequestMapping("/admins")
public class AdminLoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) throws AuthorizationException{
		
		String res = loginService.logIn(request);
		
		return new ResponseEntity<String>(res,HttpStatus.OK);
    }

//    @PostMapping("/logout")
//    public ResponseEntity<String> logout(@RequestBody LogoutRequest request) {
//        try {
//            authService.logOut(request.getUniqueId());
//            return ResponseEntity.ok("Logged out successfully.");
//        } catch (AuthorizationException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//        }
//    }
	
}
