package com.ecommerce.module;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
