package com.hirak.ecommerce.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hirak.ecommerce.app.dao.OrderRepository;
import com.hirak.ecommerce.app.dto.OrderDto;
import com.hirak.ecommerce.app.dto.OrderFormDto;
import com.hirak.ecommerce.app.dto.OrderProductDto;
import com.hirak.ecommerce.app.exception.ResourceNotFoundException;
import com.hirak.ecommerce.app.model.Order;
import com.hirak.ecommerce.app.model.OrderProduct;

/**
 * @author hirak_mahanta
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderProductService orderProductService;

	@Override
	public List<Order> getAllOrders() {
		logger.info("Service - Fetch all orders");
		return orderRepo.findAll();
	}

	@Override
	public OrderDto placeOrder(OrderFormDto orderFormDto) {
		logger.info("Service - Placing an order");
		validateOrderRequest(orderFormDto.getOrderProductDtoList());
		Order order = new Order();
		order.setEmailId(orderFormDto.getEmailId());
		logger.info("Email id is {}", orderFormDto.getEmailId());
		order.setStatus("PAID");
		order.setCreatedOn(new Date());
		order = orderRepo.save(order);
		List<OrderProduct> orderProductsList = new ArrayList<OrderProduct>();
		for (OrderProductDto dto : orderFormDto.getOrderProductDtoList()) {
			orderProductsList.add(orderProductService
					.createOrders(new OrderProduct(order, productService.getProduct(dto.getId()), dto.getQuantity())));
		}
		order.setOrderProductsList(orderProductsList);
		order = orderRepo.save(order);
		OrderDto orderDto = new OrderDto();
		orderDto.setOrder(order);
		orderDto.setAmountPaid(order.getTotalOrderPrice());
		logger.info("Total amount is {}", order.getTotalOrderPrice());
		return orderDto;
	}

	private void validateOrderRequest(List<OrderProductDto> orderProductDto) {
		List<OrderProductDto> productsList = orderProductDto.stream()
				.filter(op -> Objects.isNull(productService.getProduct(op.getId()))).collect(Collectors.toList());
		if (productsList.isEmpty()) {
			logger.error("Product not found!");
			throw new ResourceNotFoundException("Product not found!");
		}
		Iterator<OrderProductDto> opd1 = productsList.iterator();
		Iterator<OrderProductDto> opd2 = orderProductDto.iterator();
		while(opd1.hasNext() && opd2.hasNext()) {
			OrderProductDto result = opd1.next();
			OrderProductDto request = opd2.next();
			if(result.getQuantity() < request.getQuantity()) {
				logger.error("Ordered quantity is more than available stock!");
				throw new ResourceNotFoundException("Ordered quantity is more than available stock!");
			} else if(result.getQuantity() == 0) {
				logger.error("One of the product is out of stock!");
				throw new ResourceNotFoundException("One of the product is out of stock!");
			}
		}
	}

}
