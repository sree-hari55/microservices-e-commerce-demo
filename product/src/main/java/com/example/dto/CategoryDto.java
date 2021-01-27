package com.example.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto implements Serializable{

	private static final long serialVersionUID = 1L;

	protected Integer id;
	
	@NotNull(message = "category  name is required")
	@NotEmpty(message = "category name is required")
	protected String name;
	
	@NotNull(message = "brand name is required")
	@NotEmpty(message = "brand name is required")
	protected String brand;
}
