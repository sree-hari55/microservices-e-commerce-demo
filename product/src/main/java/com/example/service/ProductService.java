package com.example.service;

import java.util.List;

import com.example.dto.Product;

public interface ProductService {

	Product addProduct(Product product);
	List<Product> getProductList();
	Product  getProduct(Integer id);
	List<Product> getCategoryName(String categoryName);
	List<Product> getBrand(String brandName);
	Product updateProduct(Product product);
	String deleteProduct(Integer id);
}
