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

import com.example.dto.ProductDto;
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
	public @ResponseBody ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto product) {
		log.info("product object from request \t"+product);
		productService.addProduct(product);
		return new ResponseEntity<>(product,HttpStatus.CREATED);
	}

	@GetMapping(path="/list",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<ProductDto>> getProductList(){
		List<ProductDto> products=productService.getProductList();
		return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);
	}

	@GetMapping(path="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ProductDto>  getProduct(@PathVariable(name = "id",required = true)  String id) {
		log.info("product id from request\t"+id);
		Integer productId=Integer.parseInt(id);
		ProductDto product;
		try {
			product = productService.getProduct(productId);
			return new ResponseEntity<>(product,HttpStatus.OK);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ProductDto(),HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(path="category/{categoryName}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<ProductDto>> getCategoryName(@PathVariable(name="categoryName",required = true)String categoryName){
		log.info("product category name from request\t"+categoryName);
		List<ProductDto> products= productService.getCategoryName(categoryName);
		return new ResponseEntity<>(products,HttpStatus.OK);
	}



	@GetMapping(path="/brand/{brandName}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<ProductDto>> getBrand(@PathVariable(name="brandName",required = true) String brandName){
		log.info("product brand name from request\t"+brandName);
		List<ProductDto> products= productService.getBrand(brandName);
		return new ResponseEntity<>(products,HttpStatus.OK);
	}

	@PutMapping(path="/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto product) {
		log.info("product object from request\t"+product);
		ProductDto products= productService.updateProduct(product);
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
