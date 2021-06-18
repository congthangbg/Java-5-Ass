package com.vn.model;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.vn.entity.Account;
import com.vn.entity.OrderDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {
	
	private Integer id;
	
	private String create_date;

	@NotBlank
	private String address;
	
	private Account account;
	
	private List<OrderDetail> order_details;
}
