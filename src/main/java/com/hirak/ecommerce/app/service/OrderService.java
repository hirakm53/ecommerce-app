package com.hirak.ecommerce.app.service;

import java.util.List;

import com.hirak.ecommerce.app.dto.OrderDto;
import com.hirak.ecommerce.app.dto.OrderFormDto;
import com.hirak.ecommerce.app.model.Order;

/**
 * @author hirak_mahanta
 *
 */
public interface OrderService {

	public List<Order> getAllOrders();

	public OrderDto placeOrder(OrderFormDto orderFormDto);

}
