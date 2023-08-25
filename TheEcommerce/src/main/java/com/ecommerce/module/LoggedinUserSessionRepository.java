package com.ecommerce.module;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.LoggedinUserSession;

public interface LoggedinUserSessionRepository extends JpaRepository<LoggedinUserSession, Integer>{
	
	public LoggedinUserSession findByUniqueId(String uniqueId);
	
}
