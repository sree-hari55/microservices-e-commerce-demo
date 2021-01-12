package com.example.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.document.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, Integer>{

	@Query("{'Category.name':?0}")
	List<Product> findByCategory(String categoryName);

	@Query("{'Category.brand':?0}")
	List<Product> findByBrand(String brandName);
}
