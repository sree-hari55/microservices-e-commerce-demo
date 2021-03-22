package com.example.fixedassets.loggerFilter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.fixedassets.dto.FixedAssetResponse;
import com.example.fixedassets.dto.FixedAssetsDto;
import com.example.fixedassets.loggerFilter.utils.CustomHttpRequestWrapper;
import com.example.fixedassets.loggerFilter.utils.CustomHttpResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RequestResponseLoggers implements Filter{

	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		CustomHttpRequestWrapper customHttpRequestWrapper=new CustomHttpRequestWrapper( (HttpServletRequest) request);
		String uri=customHttpRequestWrapper.getRequestURI();
		log.info("Http URI: {}",uri);
		log.info("Http Method: {}",customHttpRequestWrapper.getMethod());
		String requestBody=new String(customHttpRequestWrapper.getRequestBody()).replace("\n","");
		
		// validating request
		if("/api/v1/assests/add".equalsIgnoreCase(uri) || "/api/v1/assests/update".equalsIgnoreCase(uri)) {
			// masking the confidential data
			FixedAssetsDto  fixedAssetsDto=objectMapper.readValue(requestBody, FixedAssetsDto.class);
			requestBody=objectMapper.writeValueAsString(fixedAssetsDto);
		}

		log.info("Http Request Body: {}",requestBody);
		
		CustomHttpResponseWrapper customHttpResponseWrapper=new CustomHttpResponseWrapper((HttpServletResponse) response);
		
		chain.doFilter(customHttpRequestWrapper, customHttpResponseWrapper);
		
		
		log.info("Http status code:{}",customHttpResponseWrapper.getStatus());
		String responseBody=new String(customHttpResponseWrapper.getBaos().toByteArray());
		
		// validating response
		if("/api/v1/assests/add".equalsIgnoreCase(uri) || "/api/v1/assests/update".equalsIgnoreCase(uri)) {
			// masking the confidential data
			FixedAssetResponse fixedAssetResponse=objectMapper.readValue(responseBody, FixedAssetResponse.class);
			responseBody=objectMapper.writeValueAsString(fixedAssetResponse);
		}
		log.info("Http Response Body:{}",responseBody);	
	}
}
