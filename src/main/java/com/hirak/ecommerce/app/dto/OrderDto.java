package com.hirak.ecommerce.app.dto;

import java.io.Serializable;

import com.hirak.ecommerce.app.model.Order;

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
public class OrderDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2159686087553318142L;
	
	private Order order;
	
	private Double amountPaid;

}
