package com.example.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
public class Product {

	@Id
	@Field(name="_id")
	protected int id;
	
	@Field(name="name")
	protected String name;
	
	@Field(name="category")
	protected Category category;
	
	@Field(name="price")
	protected double price;
	
	@Field(name="currency")
	protected String currency;
	
	@Field(name="discount")
	protected double discount;
	
	@Field(name="discount_description")
	protected String discountDescription;
	
	@Field(name="img_url")
	protected List<String> imageUrls;
}
