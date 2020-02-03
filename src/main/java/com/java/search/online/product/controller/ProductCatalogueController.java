package com.java.search.online.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.search.online.product.dto.ProductDto;
import com.java.search.online.product.service.ProductService;
import com.java.search.online.product.sql.domain.Product;

@RestController
public class ProductCatalogueController {

	@Autowired
	private ProductService productService;
	/**
	 * 
	 * @param brand
	 * @param size
	 * @param priceLower
	 * @param priceUpper
	 * @param category
	 * @param colour
	 * @return
	 */
	@GetMapping("/search-product")
	public ResponseEntity<List<Product>> getSearchProduct(@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(name = "size", required = false) String size,
			@RequestParam(value = "priceLower", required = false) Integer priceLower,
			@RequestParam(value = "priceUpper", required = false) Integer priceUpper,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "colour", required = false) String colour) {
		
		List<Product> products = productService.getproductBySearch(brand,size,category,colour,priceLower, priceUpper);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param productDto
	 * @return
	 */
	@PostMapping(value = "/create-product")
	public ResponseEntity<String> createProduct(@RequestBody ProductDto productDto) {
		 productService.upsertProduct(productDto);
		return new ResponseEntity<>("Sucess", HttpStatus.CREATED);
	}
	
	/**
	 * 
	 * @param productDto
	 * @return
	 */
	@PutMapping("/update-product")
	public ResponseEntity<String> updateProduct(@RequestBody ProductDto productDto) {
		 productService.upsertProduct(productDto);
		return new ResponseEntity<>("Sucess", HttpStatus.ACCEPTED);
	}
}
