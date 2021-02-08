package com.example.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

// for swagger documentation
@ApiModel(value ="product attributes to added into system")
public class ProductDto implements Serializable{

	
	private static final long serialVersionUID = 1L;

	// for swagger documentation
	@ApiModelProperty(value = "Unique ID for product Dto ")
	protected int id;
	
	@NotNull(message = "product name is required")
	@ApiModelProperty(value = "product name ")
	protected String name;
	
	@NotNull(message = "category should not be null")
	@ApiModelProperty(value = "Product is related to which Category")
	protected CategoryDto category;
	
	
	@Min(value = 0,message = "price should be positive integers only")
	@ApiModelProperty(value="product amount")
	protected double price;
	
	@ApiModelProperty(value="currency type like INR,USD....")
	protected String currency;
	
	@Max(value = 100,message = "discount should not exced more than 100 percentage ")
	@Min(value=0,message = "discount should be positive integers only")
	@ApiModelProperty(value = "discount for product")
	protected double discount;
	
	@ApiModelProperty(value="discount discription for product")
	protected String discountDescription;
	
	@ApiModelProperty(value="product related images")
	protected List<String> imageUrls;
}
