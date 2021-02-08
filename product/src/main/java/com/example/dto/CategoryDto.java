package com.example.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "Category related attributes")
public class CategoryDto implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="Unique ID for Category Dto")
	protected Integer id;
	
	@NotNull(message = "category  name is required")
	@NotEmpty(message = "category name is required")
	@ApiModelProperty(value="Category name")
	protected String name;
	
	@NotNull(message = "brand name is required")
	@NotEmpty(message = "brand name is required")
	@ApiModelProperty(value="Brand name for product")
	protected String brand;
}
