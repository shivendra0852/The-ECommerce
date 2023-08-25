package com.ecommerce.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class LoggedinUserSession {
	@Id
	@Column(unique = true)
	private Integer id;
	
	private String uniqueId;
	private LocalDateTime localDateTime;
}