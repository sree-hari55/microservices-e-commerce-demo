package com.example.fixedassets.loggerFilter.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.io.output.TeeOutputStream;

public class CustomHttpResponseWrapper extends HttpServletResponseWrapper{

	private ByteArrayOutputStream baos = new ByteArrayOutputStream();

	private PrintStream printStream = new PrintStream(baos);

	public ByteArrayOutputStream getBaos() {
		return baos;
	}

	public CustomHttpResponseWrapper(HttpServletResponse response) {
		super(response);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return new CustomDelegatingServletOutputStream(new TeeOutputStream(super.getOutputStream(), printStream));
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return new PrintWriter(new TeeOutputStream(super.getOutputStream(), printStream));
	}

}
