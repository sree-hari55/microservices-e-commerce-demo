package com.example.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.any())
				// controller base package
				.apis(RequestHandlerSelectors.basePackage("com.example"))
				.build()
				.apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Product Service",
				"Product Micro services for online portal",
				"1.0.0",
				"localhost:4444/",
				new Contact("srihari yadav","http:javazone.com/","srihari.g@sritindia.com"),
				"license under java zone ", 
				"http:javazzone.com/",
				Collections.emptyList()
				);
	}
}
