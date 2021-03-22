/**
 * 
 */
package com.example.fixedassets.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author srihari.g
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class APIError {
	private HttpStatus status;
	private List<String> errors;
	private LocalDateTime timeStamp;
	private String pathUri;
}
