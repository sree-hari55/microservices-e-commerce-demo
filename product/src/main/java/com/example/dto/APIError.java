package com.example.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIError implements Serializable{


	private static final long serialVersionUID = 1L;
	protected LocalDateTime timestamp;
    protected HttpStatus status;
    protected List<String> errorMessages;
    protected String pathUri;
}
