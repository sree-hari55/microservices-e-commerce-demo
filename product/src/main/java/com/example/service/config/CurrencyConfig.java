package com.example.service.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "product")
public class CurrencyConfig {

	// reading currencies from config file
	List<String> currencies;
}
