package com.vn.entity;

import java.io.Serializable;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "OrderDetails")
public class OrderDetail implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Integer id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "quantity")
	private Integer quantity=1;
	
	@Column(name = "price")
	private Double price;
	
	@ManyToOne()
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private Order order;

	@ManyToOne()
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
}
