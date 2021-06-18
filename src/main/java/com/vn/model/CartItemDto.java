package com.vn.model;

import javax.persistence.Id;
import javax.validation.constraints.Positive;

import com.vn.entity.Order;
import com.vn.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
	@Id
	private Integer productId;
	private String name;
	@Positive(message = "Số lượng phải là số !")
	private Integer quantity=1; 
	private double price;
	
	private Order order;

	private Product product;
}
