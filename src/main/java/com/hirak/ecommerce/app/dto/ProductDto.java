package com.hirak.ecommerce.app.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author hirak_mahanta
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7329429219555329243L;

	private String name;

	private Double price;

}
