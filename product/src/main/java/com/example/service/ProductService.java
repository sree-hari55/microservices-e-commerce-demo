package com.example.service;

import java.util.List;

import com.example.dto.ProductDto;

public interface ProductService {

	ProductDto addProduct(ProductDto productDto);
	List<ProductDto> getProductList();
	ProductDto  getProduct(Integer id) throws IllegalAccessException;
	List<ProductDto> getCategoryName(String categoryName);
	List<ProductDto> getBrand(String brandName);
	ProductDto updateProduct(ProductDto productDto);
	String deleteProduct(Integer id);
}
