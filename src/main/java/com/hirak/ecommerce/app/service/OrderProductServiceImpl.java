package com.hirak.ecommerce.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hirak.ecommerce.app.dao.OrderProductRepository;
import com.hirak.ecommerce.app.model.OrderProduct;

/**
 * @author hirak_mahanta
 *
 */
@Service
public class OrderProductServiceImpl implements OrderProductService {
	
	private final Logger logger = LoggerFactory.getLogger(OrderProductServiceImpl.class);

	@Autowired
	private OrderProductRepository orderProductRepo;

	@Override
	public OrderProduct createOrders(OrderProduct orderProduct) {
		logger.info("Inside service layer for saving orders");
		return orderProductRepo.save(orderProduct);
	}

}
