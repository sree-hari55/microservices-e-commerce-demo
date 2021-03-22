package com.example.fixedassets.loggerFilter.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

public class CustomHttpRequestWrapper extends HttpServletRequestWrapper{

	byte[] requestBody;
	public CustomHttpRequestWrapper(HttpServletRequest request) {
		super(request);
		try {
			requestBody=IOUtils.toByteArray(request.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException("Issue while reading request stream");
		}
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return new CustomDelegatingServletInputStream(new ByteArrayInputStream(requestBody));
	}

	public byte[] getRequestBody() {
		return requestBody;
	}
}
