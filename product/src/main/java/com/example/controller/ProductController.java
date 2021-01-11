package com.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.Product;
import com.example.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/product")
@Slf4j
@AllArgsConstructor
public class ProductController {

	private ProductService productService;

	@PostMapping(path ="/add",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Product> addProduct(@RequestBody Product product) {
		log.info("product object from request \t"+product);
		productService.addProduct(product);
		return new ResponseEntity<>(product,HttpStatus.CREATED);
	}

	@GetMapping(path="/list",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Product>> getProductList(){
		List<Product> products=productService.getProductList();
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
	}

	@GetMapping(path="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Product>  getProduct(@PathVariable(name = "id",required = true)  String id) {
		log.info("product id from request\t"+id);
		Integer productId=Integer.parseInt(id);
		Product product=productService.getProduct(productId);
		return new ResponseEntity<>(product,HttpStatus.OK);
	}

	@GetMapping(path="category/{categoryName}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Product>> getCategoryName(@PathVariable(name="categoryName",required = true)String categoryName){
		log.info("product category name from request\t"+categoryName);
		List<Product> products= productService.getCategoryName(categoryName);
		return new ResponseEntity<>(products,HttpStatus.OK);
	}



	@GetMapping(path="/brand/{brandName}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Product>> getBrand(@PathVariable(name="brandName",required = true) String brandName){
		log.info("product brand name from request\t"+brandName);
		List<Product> products= productService.getBrand(brandName);
		return new ResponseEntity<>(products,HttpStatus.OK);
	}

	@PutMapping(path="/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Product> updateProduct(@RequestBody Product product) {
		log.info("product object from request\t"+product);
		Product products= productService.updateProduct(product);
		return new ResponseEntity<>(products,HttpStatus.CREATED);
	}

	@DeleteMapping(path="/delete/{id}",produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody ResponseEntity<String> deleteProduct(@PathVariable(name="id",required = true) String id){
		log.info("product id from request\t"+id);
		Integer productId=Integer.parseInt(id);
		String status= productService.deleteProduct(productId);
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}
}
