package com.hirak.ecommerce.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hirak.ecommerce.app.dto.ProductDto;
import com.hirak.ecommerce.app.model.Product;
import com.hirak.ecommerce.app.service.ProductService;

/**
 * @author hirak_mahanta
 *
 */
@RestController
@RequestMapping("/api")
public class ProductController {
	
	private final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@GetMapping(path = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
		logger.info("Controller method for fetching product details by id called");
		return new ResponseEntity<Product>(productService.getProduct(id), HttpStatus.FOUND);
	}

	@GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> getAllProducts() {
		logger.info("Controller method for fetching all the products called");
		return new ResponseEntity<List<Product>>(productService.getAllProducts(), HttpStatus.FOUND);
	}

	@PostMapping(path = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Product>> addProducts(@RequestBody @Valid List<ProductDto> product) {
		logger.info("Controller method for add new entries for products called");
		return new ResponseEntity<List<Product>>(productService.addProduct(product), HttpStatus.CREATED);
	}

	@PutMapping(path = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateProducts(@RequestBody @Valid Product product) {
		logger.info("Controller method for updating a record of product called");
		return new ResponseEntity<Product>(productService.updateProduct(product), HttpStatus.OK);
	}

	@DeleteMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteProducts(@RequestParam("id") Long id) {
		logger.info("Controller method for deleting an entry of record is called");
		return new ResponseEntity<String>(productService.removeProduct(id), HttpStatus.OK);
	}

}
