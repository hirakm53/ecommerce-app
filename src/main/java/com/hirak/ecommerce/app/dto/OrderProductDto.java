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
public class OrderProductDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4951792816756267433L;

	private Long id;

	private String name;

	private Double price;

	private Integer quantity;
	
}
