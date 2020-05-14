package com.hirak.ecommerce.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hirak.ecommerce.app.config.DatasourceConfiguration;
import com.hirak.ecommerce.app.model.Order;

/**
 * @author hirak_mahanta
 *
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY, transactionManager = DatasourceConfiguration.TX_MANAGER)
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Transactional
	public List<Order> findAll();

}
