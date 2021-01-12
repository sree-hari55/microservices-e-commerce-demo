package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.document.Product;
import com.example.dto.ProductDto;
import com.example.repository.ProductRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

	private ProductRepository productRepository;

	private ModelMapper modelMapper;

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		Product p=modelMapper.map(productDto, Product.class);
		Product product=productRepository.save(p);
		ProductDto pDto=modelMapper.map(product, ProductDto.class);
		return pDto;
	}

	@Override
	public List<ProductDto> getProductList() {
		List<Product> products=productRepository.findAll();
		List<ProductDto> productDtos=products.stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return productDtos;
	}

	@Override
	public ProductDto getProduct(Integer id) throws IllegalAccessException {
		Product product=productRepository.findById(id).orElseThrow(() -> new IllegalAccessException());
		ProductDto productDto=modelMapper.map(product, ProductDto.class);
		return productDto;
	}

	@Override
	public List<ProductDto> getCategoryName(String categoryName) {
		List<Product> products=productRepository.findByCategory(categoryName);
		List<ProductDto> productDtos=products.stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return productDtos;

	}

	@Override
	public List<ProductDto> getBrand(String brandName) {
		List<Product> products=productRepository.findByBrand(brandName);
		List<ProductDto> productDtos=products.stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return productDtos;
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto) {

		Product p=productRepository.findById(productDto.getId()).get();
		if(p!=null) {
			p=modelMapper.map(productDto, Product.class);
			Product product=productRepository.save(p);
			ProductDto pDto=modelMapper.map(product, ProductDto.class);
			return pDto;
		}else {
			return new ProductDto();
		}
	}

	@Override
	public String deleteProduct(Integer id) {

		productRepository.deleteById(id);
		return "product deleted successfully";
	}




}
