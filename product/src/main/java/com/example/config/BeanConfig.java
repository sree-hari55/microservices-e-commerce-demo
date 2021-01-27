package com.example.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

	@Bean
	public ModelMapper modalMapper() {
		return new ModelMapper();
	}
	
	
	// to enable Http trace endpoint in Actuator
	@Bean 
	HttpTraceRepository httpTraceRepository() {
		return new InMemoryHttpTraceRepository();
	}
}
