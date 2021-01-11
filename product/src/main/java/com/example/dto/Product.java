package com.example.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	protected Integer id;
	protected String name;
	protected Category category;
	protected double price;
	protected double discount;
	protected String discountDescription;
	protected List<String> imageUrls;
}
