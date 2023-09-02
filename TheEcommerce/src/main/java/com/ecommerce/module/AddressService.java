package com.ecommerce.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.Address;
import com.ecommerce.exception.AddressException;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Address registerAddress(Integer id, Address address) throws AddressException{
		
		address.setEmployee(employeeRepository.findById(id).get());
		return addressRepository.save(address);
	}
}
