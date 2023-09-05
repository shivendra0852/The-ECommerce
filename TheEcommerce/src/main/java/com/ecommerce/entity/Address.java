package com.ecommerce.entity;

import com.ecommerce.enums.AddressType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String city;
	private String state;
	private String pincode;
	
	@Enumerated(EnumType.STRING)
	private AddressType addressType;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;
}
