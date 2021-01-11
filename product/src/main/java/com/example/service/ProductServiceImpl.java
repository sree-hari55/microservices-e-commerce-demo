package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.Product;

@Service
public class ProductServiceImpl implements ProductService{

	List<Product> products=null;

	public ProductServiceImpl() {
		products=new ArrayList<>();
	}

	@Override
	public Product addProduct(Product product) {
		products.add(product);
		return product;
	}

	@Override
	public List<Product> getProductList() {

		return products.stream().collect(Collectors.toList());

	}

	@Override
	public Product getProduct(Integer id) {
		return products.stream().filter(product -> product.getId()==id).findAny().get();
	}

	@Override
	public List<Product> getCategoryName(String categoryName) {
		return	products.stream().filter(products -> products.getCategory().getName().equals(categoryName)).collect(Collectors.toList());
	}

	@Override
	public List<Product> getBrand(String brandName) {
		return	products.stream().filter(products -> products.getCategory().getBrand().equals(brandName)).collect(Collectors.toList());
	}


	@Override
	public Product updateProduct(Product product) {

		 products.add(product);
		 return product;
	}

	@Override
	public String deleteProduct(Integer id) {
     for(Product product:products) {
    	 if(product.getId()==id) {
    		 products.remove(product);
    	 }
     }
      return "product deleted";
	}

}
