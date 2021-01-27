package com.example.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto implements Serializable{

	
	private static final long serialVersionUID = 1L;

	protected int id;
	
	@NotNull(message = "product name is required")
	protected String name;
	
	@NotNull(message = "category should not be null")
	protected CategoryDto category;
	
	@Min(value = 0,message = "price should be positive integers only")
	protected double price;
	
	protected String currency;
	
	@Max(value = 100,message = "discount should not exced more than 100 percentage ")
	@Min(value=0,message = "discount should be positive integers only")
	protected double discount;
	
	protected String discountDescription;
	
	protected List<String> imageUrls;
}
