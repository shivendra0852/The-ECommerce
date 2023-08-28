package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ecommerce.cors.CorsFilter;

@SpringBootApplication
public class TheEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheEcommerceApplication.class, args);
	}

    @Bean
    CorsFilter corsFilter() {
		CorsFilter filter = new CorsFilter();
		return filter;
	}

}
