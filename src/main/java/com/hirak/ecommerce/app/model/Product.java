package com.hirak.ecommerce.app.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1375158270376921451L;

	@Id
	@Column(name = "product_id")
	@SequenceGenerator(name = "PROD_ID_SQ", sequenceName = "PROD_ID_SQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROD_ID_SQ")
	private Long id;

	@Column(name = "product_name")
	private String name;

	@Column(name = "product_price")
	private Double price;

	@Column(name = "product_quantity")
	private Integer quantity;
}
