package com.hirak.ecommerce.app.dto;

import java.io.Serializable;
import java.util.List;

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
public class OrderFormDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7420354906366213071L;
	
	private List<OrderProductDto> orderProductDtoList;
	
	private String emailId;

}
