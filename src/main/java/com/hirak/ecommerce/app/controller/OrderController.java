package com.hirak.ecommerce.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hirak.ecommerce.app.dto.OrderDto;
import com.hirak.ecommerce.app.dto.OrderFormDto;
import com.hirak.ecommerce.app.model.Order;
import com.hirak.ecommerce.app.service.OrderService;

/**
 * @author hirak_mahanta
 *
 */
@RestController
@RequestMapping("/api")
public class OrderController {
	
	private final Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@GetMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Order>> getOrdersList() {
		logger.info("Controller method for fetching all orders called");
		return new ResponseEntity<List<Order>>(orderService.getAllOrders(), HttpStatus.OK);
	}

	@PostMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> placeOrder(@RequestBody @Valid OrderFormDto orderFormDto) {
		logger.info("Controller method for placing an order called");
		return new ResponseEntity<OrderDto>(orderService.placeOrder(orderFormDto), HttpStatus.CREATED);
	}

}
