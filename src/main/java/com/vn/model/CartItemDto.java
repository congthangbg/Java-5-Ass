package com.vn.model;

import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
	private Integer productId;
	private String name;
	@Positive(message = "Số lượng phải là số !")
	private Integer quantity=1; 
	private double price;
	
}
