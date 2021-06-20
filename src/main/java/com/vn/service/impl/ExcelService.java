package com.vn.service.impl;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vn.Export.ExcelHelper;
import com.vn.entity.OrderDetail;
import com.vn.model.CartItemDto;
import com.vn.service.ShopCartService;

@Service
public class ExcelService {
	@Autowired
	ShopCartService shoppingCartSevice;
	



  public ByteArrayInputStream load() {
	//convert Collection<CartItemDto> ->>>> List
			Collection<CartItemDto> cartItems = shoppingCartSevice.getAllCartItems();
			List<CartItemDto> listEntity1 = cartItems.stream().collect(Collectors.toList());
			
			List<OrderDetail> listEntity = new ArrayList<OrderDetail>();
			
			
			for (CartItemDto cartItemDto : listEntity1) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setId(cartItemDto.getProductId());
				orderDetail.setName(cartItemDto.getName());
				orderDetail.setPrice(cartItemDto.getPrice());
				orderDetail.setQuantity(cartItemDto.getQuantity());
				orderDetail.setOrder(cartItemDto.getOrder());
				orderDetail.setProduct(cartItemDto.getProduct());
				
				listEntity.add(orderDetail);
			}
	  if(!listEntity.isEmpty()) {
		  ByteArrayInputStream in = ExcelHelper.tutorialsToExcel(listEntity);
		    return in;
	  }
	return null;
   
  }

}
