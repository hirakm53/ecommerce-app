package com.hirak.ecommerce.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author hirak_mahanta
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderProduct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3194657567319289941L;

	@EmbeddedId
	@JsonIgnore
	private OrderProductOP op;

	@Column(nullable = false)
	private Integer quantity;

	public OrderProduct(Order order, Product product, Integer quantity) {
		op = new OrderProductOP();
		op.setOrder(order);
		op.setProduct(product);
		this.quantity = quantity;
	}

	@Transient
	public Product getProduct() {
		return this.op.getProduct();
	}

	@Transient
	public Double getTotalPrice() {
		return getProduct().getPrice() * getQuantity();
	}

}
