package com.hirak.ecommerce.app.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hirak.ecommerce.app.dao.ProductRepository;
import com.hirak.ecommerce.app.dto.ProductDto;
import com.hirak.ecommerce.app.exception.ResourceNotFoundException;
import com.hirak.ecommerce.app.model.Product;

/**
 * @author hirak_mahanta
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	
	private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepo;

	@Override
	public Product getProduct(long id) {
		logger.info("Service - Fetch product by id");
		return productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}

	@Override
	public List<Product> getAllProducts() {
		logger.info("Service - Fetch all products");
		return productRepo.findAll();
	}

	@Override
	public List<Product> addProduct(List<ProductDto> productDtoList) {
		logger.info("Service - Add new entries of products");
		List<Product> productsList = new ArrayList<Product>();
		for (ProductDto productDto : productDtoList) {
			Product product = new Product();
			product.setName(productDto.getName());
			product.setPrice(productDto.getPrice());
			productsList.add(productRepo.save(product));
		}
		return productsList;
	}

	@Override
	public Product updateProduct(Product product) {
		logger.info("Service - Update an entry of product");
		return productRepo.save(product);
	}

	@Override
	public String removeProduct(Long id) {
		logger.info("Service - Remove an entry of product");
		productRepo.deleteById(id);
		return "Removed!";
	}

}
