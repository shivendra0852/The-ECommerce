package com.ecommerce.module;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ecommerce.entity.Village;

public interface VillageRepository extends JpaRepository<Village, Integer>{
	
	@Query("SELECT v.name, c.name from Village v JOIN v.city c WHERE v.pincode IN ?1")
	public List<Object[]> findCityAndVillageByPincode(List<String> pincodes);
}
