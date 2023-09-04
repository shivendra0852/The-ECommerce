package com.ecommerce.module;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entity.Address;
import com.ecommerce.exception.EmployeeException;

@RestController
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/register")
	public ResponseEntity<Address> registerAddress(@RequestParam Integer id, @RequestBody Address address){
		
		return new ResponseEntity<Address>(addressService.registerAddress(id, address), HttpStatus.CREATED);
	}
	
	@PostMapping("/salary-hike")
    public ResponseEntity<String> giveSalaryHikeAndSendMail(@RequestParam Double salary, @RequestParam Integer age, @RequestParam String organization) throws EmployeeException {
        String result = addressService.giveSalaryHikeAndSendMail(salary, age, organization);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
