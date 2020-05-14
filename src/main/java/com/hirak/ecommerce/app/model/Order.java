package com.hirak.ecommerce.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author hirak_mahanta
 *
 */
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2487659925299775916L;

	@Id
	@Column(name = "order_id")
	@SequenceGenerator(name = "ORDER_ID_SQ", sequenceName = "ORDER_ID_SQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ID_SQ")
	private Long id;

	@Column(name = "order_date")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date createdOn;

	@Column(name = "email_id")
	private String emailId;
	
	private String status;

	@OneToMany(mappedBy = "op.order")
	private List<OrderProduct> orderProductsList = new ArrayList<OrderProduct>();

	@Transient
	public Double getTotalOrderPrice() {
		double sum = 0D;
		List<OrderProduct> orderProducts = getOrderProductsList();
		for (OrderProduct op : orderProducts) {
			sum += op.getTotalPrice();
		}
		return sum;
	}

	@Transient
	public int getNumberOfProducts() {
		return this.orderProductsList.size();
	}

}
