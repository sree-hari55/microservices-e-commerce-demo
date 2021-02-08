package com.example.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/product")
@Slf4j
@AllArgsConstructor
@Api(description = "API's for communicating product services")
public class ProductController {

	private ProductService productService;
	private ObjectMapper objectMapper;

	@PostMapping(path ="/add",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "product added into system ")
	public @ResponseBody ResponseEntity<ProductDto> addProduct(@ApiParam(value = "Product Dto to adding product into system") @RequestBody @Valid ProductDto product) throws JsonProcessingException {
		log.info("product object from request :{}",objectMapper.writeValueAsString(product));
		ProductDto productDto=productService.addProduct(product);
		return new ResponseEntity<>(productDto,HttpStatus.CREATED);
	}

	@GetMapping(path="/list",produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get all products from system")
	public @ResponseBody ResponseEntity<List<ProductDto>> getProductList(){
		List<ProductDto> products=productService.getProductList();
		return new ResponseEntity<List<ProductDto>>(products,HttpStatus.OK);
	}

	
	@GetMapping(path="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get single product based on the produt id")
	public @ResponseBody ResponseEntity<ProductDto>  getProduct(@ApiParam(" Product Id to get product ") @PathVariable(name = "id",required = true)  String id) {
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
	@ApiOperation(value = "Get the Products based on the CategoryName")
	public @ResponseBody ResponseEntity<List<ProductDto>> getCategoryName(@ApiParam("Listed products based on CategoryName") @PathVariable(name="categoryName",required = true)String categoryName){
		log.info("product category name from request\t"+categoryName);
		List<ProductDto> products= productService.getCategoryName(categoryName);
		return new ResponseEntity<>(products,HttpStatus.OK);
	}



	@GetMapping(path="/brand/{brandName}",produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get product based on BrandName")
	public @ResponseBody ResponseEntity<List<ProductDto>> getBrand(@ApiParam("listed products based on BrandName") @PathVariable(name="brandName",required = true) String brandName){
		log.info("product brand name from request\t"+brandName);
		List<ProductDto> products= productService.getBrand(brandName);
		return new ResponseEntity<>(products,HttpStatus.OK);
	}

	@PutMapping(path="/update",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "update exsiting product to the system")
	public @ResponseBody ResponseEntity<ProductDto> updateProduct(@ApiParam(value = "Product Dto to adding product into system") @RequestBody @Valid ProductDto productDto) {
		log.info("product object from request\t"+productDto);
		ProductDto product= productService.updateProduct(productDto);
		return new ResponseEntity<>(product,HttpStatus.CREATED);
	}

	@DeleteMapping(path="/delete/{id}",produces = MediaType.TEXT_PLAIN_VALUE)
	@ApiOperation(value = "Delete Product Based on ID")
	public @ResponseBody ResponseEntity<String> deleteProduct(@ApiParam("Product ID") @PathVariable(name="id",required = true) String id){
		log.info("product id from request\t"+id);
		Integer productId=Integer.parseInt(id);
		String status= productService.deleteProduct(productId);
		return new ResponseEntity<String>(status,HttpStatus.OK);
	}
}
