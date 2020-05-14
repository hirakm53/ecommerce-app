package com.hirak.ecommerce.app.service;

import java.util.List;

import com.hirak.ecommerce.app.dto.ProductDto;
import com.hirak.ecommerce.app.model.Product;

/**
 * @author hirak_mahanta
 *
 */
public interface ProductService {

	public Product getProduct(long id);

	public List<Product> getAllProducts();

	public List<Product> addProduct(List<ProductDto> productDto);

	public String removeProduct(Long id);

	public Product updateProduct(Product product);

}
